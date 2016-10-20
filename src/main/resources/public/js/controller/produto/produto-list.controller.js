(function() {
    'use strict';

    angular
        .module('systock')
        .controller('ProdutoListController', ProdutoListController);

    ProdutoListController.$inject = ['$state', 'Produto', 'HALResourceService', 'StringUtils'];

    /* @ngInject */
    function ProdutoListController($state, Produto, HALResourceService, StringUtils) {
        var vm = this;

		vm.carregando = true;
		vm.resource = null;
		vm.query = null;
		vm.produtos = [];
        vm.filtros = {
            descricao: null,
            nenhumFiltroPreenchido: nenhumFiltroPreenchido,
            filtrar: filtrar
        }

		vm.recarregar = recarregar;
		vm.podeCarregarMais = podeCarregarMais;
		vm.possuiProdutos = possuiProdutos;
		vm.carregarMais = carregarMais;
        vm.getQuantidadeText = getQuantidadeText;
        vm.getProdutoNgClass = getProdutoNgClass;

        activate();

        function activate() {
			vm.query = Produto.query;
			_carregar(_storeNewResource)
        }

		function _carregar(then) {
			vm.carregando = true;
			vm.query({
				action: 'searchByDescricao',
				descricao: vm.filtros.descricao || null,
                sort: 'descricao,desc',
				page: 0,
				size: 10
			}).$promise
				.then(then)
				.finally(_fimCarregando);
		}

		function carregarMais() {
			if(!vm.carregando) {
				vm.carregando = true;
				HALResourceService.follow(vm.resource, 'GET', 'next')
					.then(function(response) {
						_storeResource(response.data);
					})
					.finally(_fimCarregando);
			}
		}

		function filtrar() {
			vm.query = Produto.search;
			_carregar(_storeNewResource)
		}

		function recarregar() {
			if(!vm.carregando) {
				vm.filtros.descricao = null;
				vm.query = Produto.query;
				_carregar(_storeNewResource)
			}
		}

		function possuiProdutos() {
			return vm.produtos && vm.produtos.length;
		}

		function podeCarregarMais() {
			return vm.resource && vm.resource._links && vm.resource._links.next;
		}

		function nenhumFiltroPreenchido() {
			return !vm.filtros.descricao;
		}

        function getQuantidadeText(quantidade) {
            switch (quantidade) {
                case 0:
                    return "sem item em estoque";
                case 1:
                    return "com 1 item em estoque";
                default:
                    return "com "+ quantidade +" itens em estoque";
            }
        }

        function getProdutoNgClass(produto) {
            if(produto.ativo) {
                if(produto.quantidade == 0) {
                    return 'text-muted';
                } else if (produto.quantidade < 3) {
                    return 'text-warning';
                } else {
                    return 'text-default';
                }
            } else {
                return 'text-danger';
            }
        }

		function _storeNewResource(result) {
			vm.produtos = [];
			_storeResource(result);
		}

		function _storeResource(result) {
			vm.resource = result;
			vm.resource._embedded.produtos.forEach(function(produto) {
				vm.produtos.push(produto);
			});
		}

		function _fimCarregando() {
			vm.carregando = false;
		}
    }
})();
