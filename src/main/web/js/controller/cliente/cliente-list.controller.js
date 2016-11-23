(function() {
    'use strict';

    angular
        .module('systock')
        .controller('ClienteListController', ClienteListController);

    ClienteListController.$inject = ['$state', 'Cliente', 'HALResourceService', 'StringUtils'];

    /* @ngInject */
    function ClienteListController($state, Cliente, HALResourceService, StringUtils) {
        var vm = this;

		vm.carregando = true;
		vm.resource = null;
		vm.query = null;
		vm.clientes = [];
        vm.filtros = {
            nome: null,
            cpf: null,
            email: null,
            sort: 'nome,desc',
            nenhumFiltroPreenchido: nenhumFiltroPreenchido,
            filtrar: filtrar
        }

		vm.recarregar = recarregar;
		vm.podeCarregarMais = podeCarregarMais;
		vm.possuiClientes = possuiClientes;
		vm.carregarMais = carregarMais;

        activate();

        function activate() {
			vm.query = Cliente.query;
			_carregar(_storeNewResource)
        }

		function _carregar(then) {
			vm.carregando = true;
			vm.query({
				action: 'filter',
				nome: vm.filtros.nome || null,
				cpf: vm.filtros.cpf || null,
				email: vm.filtros.email || null,
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
			vm.query = Cliente.search;
			_carregar(_storeNewResource)
		}

		function recarregar() {
			if(!vm.carregando) {
				vm.filtros.nome = null;
				vm.filtros.cpf = null;
				vm.filtros.email = null;
				vm.query = Cliente.query;
				_carregar(_storeNewResource)
			}
		}

		function possuiClientes() {
			return vm.clientes && vm.clientes.length;
		}

		function podeCarregarMais() {
			return vm.resource && vm.resource._links && vm.resource._links.next;
		}

		function nenhumFiltroPreenchido() {
			return !vm.filtros.nome && !vm.filtros.cpf && !vm.filtros.email;
		}

		function _storeNewResource(result) {
			vm.clientes = [];
			_storeResource(result);
		}

		function _storeResource(result) {
			vm.resource = result;
			vm.resource._embedded.clientes.forEach(function(cliente) {
				vm.clientes.push(cliente);
			});
		}

		function _fimCarregando() {
			vm.carregando = false;
		}
    }
})();
