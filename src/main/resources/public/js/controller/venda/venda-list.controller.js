(function() {
    'use strict';

    angular
        .module('systock')
        .controller('VendaListController', VendaListController);

    VendaListController.$inject = ['$state', 'Venda', 'HALResourceService', 'StringUtils'];

    /* @ngInject */
    function VendaListController($state, Venda, HALResourceService, StringUtils) {
        var vm = this;

		vm.carregando = true;
		vm.resource = null;
		vm.query = null;
		vm.vendas = [];
		vm.situacoes = [
			{value: null, rotulo: 'Todas situações'},
			{value: 'CONCLUIDA', rotulo: 'Vendas concluidas'},
			{value: 'ABERTA', rotulo: 'Vendas abertas'},
			{value: 'CANCELADA', rotulo: 'Vendas cancelada'},
			{value: 'DEVOLVIDA', rotulo: 'Vendas devolvida'},
			{value: 'TROCADA', rotulo: 'Venda trocada'}
		]


        vm.filtros = {
			cliente: null,
			funcionario: null,
			situacao: vm.situacoes[0],
            nenhumFiltroPreenchido: nenhumFiltroPreenchido,
            filtrar: filtrar
        }

		vm.recarregar = recarregar;
		vm.podeCarregarMais = podeCarregarMais;
		vm.possuiVendas = possuiVendas;
		vm.carregarMais = carregarMais;
        vm.getSituacaoNgClass = getSituacaoNgClass;

        activate();

        function activate() {
			vm.query = Venda.query;
			_carregar(_storeNewResource)
        }

		function _carregar(then) {
			vm.carregando = true;
			vm.query({
				action: 'filter',
				cliente: (vm.filtros.cliente || null),
				funcionario: (vm.filtros.funcionario || null),
				situacao: (vm.filtros.situacao.value),
				sort: 'dataConclusao,dataCriacao',
				'dataConclusao.dir': 'asc',
				'dataCriacao.dir': 'asc',
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
			vm.query = Venda.search;
			_carregar(_storeNewResource)
		}

		function recarregar() {
			if(!vm.carregando) {
				vm.filtros.funcionario = null;
				vm.filtros.cliente = null;
				vm.filtros.situacao = vm.situacoes[0];
				vm.query = Venda.query;
				_carregar(_storeNewResource)
			}
		}

		function possuiVendas() {
			return vm.vendas && vm.vendas.length;
		}

		function podeCarregarMais() {
			return vm.resource && vm.resource._links && vm.resource._links.next;
		}

		function nenhumFiltroPreenchido() {
			return !vm.filtros.funcionario && !vm.filtros.cliente && !vm.filtros.situacao.value;
		}

        function getSituacaoNgClass(venda) {
            switch (venda.situacao) {
                case 'CONCLUIDA':
                    return 'text-success';
                case 'CANCELADA':
                case 'DEVOLVIDA':
                    return 'text-danger';
                case 'TROCADA':
                    return 'text-warning';
                default:
                    return 'text-default';
            }
        }

		function _storeNewResource(result) {
			vm.vendas = [];
			_storeResource(result);
		}

		function _storeResource(result) {
			vm.resource = result;
			vm.resource._embedded.vendas.forEach(function(venda) {
				vm.vendas.push(venda);
			});
		}

		function _fimCarregando() {
			vm.carregando = false;
		}
    }
})();
