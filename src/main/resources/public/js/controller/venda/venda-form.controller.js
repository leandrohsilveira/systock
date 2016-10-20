 (function() {
    'use strict';

    angular
        .module('systock')
        .controller('VendaFormController', VendaFormController);

    VendaFormController.$inject = [
		'Venda',
		'Funcionario',
		'Cliente',
		'Produto',
		'LoginService',
		'HALResourceService',
		'$q',
		'$state',
		'$stateParams',
		'$http'];

    /* @ngInject */
    function VendaFormController(
			Venda,
			Funcionario,
			Cliente,
			Produto,
			LoginService,
			HALResourceService,
			$q,
			$state,
			$stateParams,
			$http) {
        var vm = this;
        vm.venda = null;
        vm.funcionario = null;
        vm.cliente = null;
        vm.itensVenda = null;
		vm.itens = [];
		vm.itensParaRemover = [];
        vm.editar = false;
		vm.situacaoInicial = null;
		vm.subtotal = 0;

        vm.salvar = salvar;
		vm.selecionarCliente = selecionarCliente;
		vm.selecionarProduto = selecionarProduto;
		vm.adicionarItem = adicionarItem;
		vm.removerItem = removerItem;
		vm.desabilitarBotaoSalvar = desabilitarBotaoSalvar;
		vm.possuiItens = possuiItens;
		vm.calcularSubtotal = calcularSubtotal;

        activate();

        function activate() {
            vm.editar = $stateParams.editar;
            if(vm.editar) {
                Venda.get({id: $stateParams.id}).$promise
                    .then(function(venda) {
                        vm.venda = venda;
						vm.situacaoInicial = vm.venda.situacao;
                        vm.funcionario = venda._embedded.funcionario;
                        vm.cliente = venda._embedded.cliente;
                        return _recuperarItens();
                    })
					.then(function(response) {
						vm.itensVenda = response.data;
						vm.itens = vm.itensVenda._embedded.itens;
						vm.calcularSubtotal();
					});
            } else {
                vm.venda = new Venda();
                vm.venda.ativo = true;
                vm.venda.situacao = 'ABERTA';
				vm.venda.desconto = 0;
				vm.situacaoInicial = 'ABERTA';
				LoginService.getUsuarioAutenticado()
					.then(function (response) {
						return Funcionario.get({id: response.data.funcionario.id}).$promise;
					})
					.then(function(funcionario) {
						vm.funcionario = funcionario;
					});
            }
        }

		function selecionarCliente(query) {
			return Cliente.search({
				action: 'query',
				query: query || null,
				page: 0,
				size: 10
			}).$promise
				.then(function(response) {
					return response._embedded.clientes;
				});
		}

		function selecionarProduto(query) {
			return Produto.search({
				action: 'query',
				query: query || null,
				page: 0,
				size: 10
			}).$promise
				.then(function(response) {
					return response._embedded.produtos;
				});
		}

		function adicionarItem() {
			var item = {
				ativo: true,
				produto: vm.produto,
				valor: vm.produto.preco,
				quantidade: 1
			}

			for (var i = 0; i < vm.itens.length; i++) {
				var it = vm.itens[i];
				if(item.produto.id == it.produto.id) {
					// TODO: adicionar mensagem.
					return;
				}
			}
			vm.itens.push(item);
			vm.calcularSubtotal();
			vm.produto = null;
		}

		function removerItem(indice) {
			var item = vm.itens.splice(indice, 1)[0];
			if(item.id) {
				vm.itensParaRemover.push(item);
			}
			vm.calcularSubtotal();
		}

		function possuiItens() {
			return vm.itens.length;
		}

		function desabilitarBotaoSalvar() {
			return (!vm.itens.length && (vm.venda.situacao == 'CONCLUIDA' || vm.venda.situacao == 'DEVOLVIDA' || vm.venda.situacao == 'TROCADA'));
		}

		function salvar() {
			vm.venda.funcionario = vm.funcionario._links.self.href;
			if(vm.cliente) {
				vm.venda.cliente = vm.cliente._links.self.href;
			}
			if(vm.editar) {
				HALResourceService.follow(vm.venda, 'PUT', 'self', vm.venda)
					.then(_salvarItens);
			} else {
				vm.venda.$save(_salvarItens);
			}

		}

		function calcularSubtotal() {
			vm.subtotal = 0;
			vm.itens.forEach(function(item) {
				vm.subtotal += item.quantidade * item.valor;
			})
		}

		function _recuperarItens() {
			return HALResourceService.follow(vm.venda, 'GET', 'itens');
		}

		function _salvarItens() {
			var promise;
			if(vm.itensVenda) {
				promise = _patchItens();
			} else {
				promise = _recuperarItens().then(function (response) {
					vm.itensVenda = response.data;
					return _patchItens();
				})
			}
			return promise
					.then(function(promisesResult) {
						return _recuperarItens();
					})
					.then(function (response) {
						vm.venda.itens = [];
						response.data._embedded.itens.forEach(function(item) {
							vm.venda.itens.push(item._links.self.href);
						});
						return HALResourceService.follow(vm.venda, 'PUT', 'self', vm.venda);
					})
					.then(_goToVendaList);
		}

		function _patchItens() {
			var promises = [];
			vm.itensParaRemover.forEach(function(item) {
				promises.push(HALResourceService.follow(item, 'DELETE', 'self'));
			});

			vm.itens.forEach(function(item) {
				item.venda = vm.venda._links.self.href;
				if(item.id) {
					item.produto = item.produto.id;
					promises.push(HALResourceService.follow(item, 'PUT', 'self', item));
				} else {
					item.venda = vm.venda._links.self.href;
					item.produto = item.produto._links.self.href;
					// promises.push(HALResourceService.follow(vm.itensVenda, 'POST', 'self', item));
					promises.push($http.post('itens', item));
				}
			});

			return $q.all(promises);
		}

        function _goToVendaList() {
			$state.go('app.vendas.consultar');
        }
    }
})();
