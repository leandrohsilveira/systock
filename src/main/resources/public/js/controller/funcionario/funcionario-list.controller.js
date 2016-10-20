(function() {
    'use strict';

    angular
        .module('systock')
        .controller('FuncionarioListController', FuncionarioListController);

    FuncionarioListController.$inject = ['$state', 'Funcionario', 'HALResourceService', 'StringUtils'];

    /* @ngInject */
    function FuncionarioListController($state, Funcionario, HALResourceService, StringUtils) {
        var vm = this;

		vm.carregando = true;
		vm.resource = null;
		vm.query = null;
		vm.funcionarios = [];
        vm.cargos = [
            {value: null, rotulo: 'Todos'},
            {value: 'ADMINISTRADOR', rotulo: 'Administradores'},
            {value: 'GERENTE', rotulo: 'Gerentes'},
            {value: 'VENDEDOR', rotulo: 'Vendedores'}
        ]
        vm.filtros = {
            nome: null,
            cpf: null,
            cargo: vm.cargos[0],
            nenhumFiltroPreenchido: nenhumFiltroPreenchido,
            filtrar: filtrar
        }

		vm.recarregar = recarregar;
		vm.podeCarregarMais = podeCarregarMais;
		vm.possuiFuncionarios = possuiFuncionarios;
		vm.carregarMais = carregarMais;
        vm.formularioAberto = formularioAberto;

        activate();

        function activate() {
			vm.query = Funcionario.query;
			_carregar(_storeNewResource)
        }

		function _carregar(then) {
			vm.carregando = true;
			vm.query({
				action: 'filter',
				nome: StringUtils.getUppercaseOrNullIfEmpty(vm.filtros.nome),
				cpf: StringUtils.getReplacedOrNullIfEmpty(vm.filtros.cpf, /\.|\-/g, ''),
				cargo: vm.filtros.cargo.value,
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
			vm.query = Funcionario.search;
			_carregar(_storeNewResource)
		}

		function recarregar() {
			if(!vm.carregando) {
				vm.filtros.nome = null;
				vm.filtros.cpf = null;
				vm.filtros.cargo = vm.cargos[0];
				vm.query = Funcionario.query;
				_carregar(_storeNewResource)
			}
		}

		function possuiFuncionarios() {
			return vm.funcionarios && vm.funcionarios.length;
		}

		function podeCarregarMais() {
			return vm.resource && vm.resource._links && vm.resource._links.next;
		}

        function formularioAberto() {
            return $state.is('app.funcionarios.new') || $state.is('app.funcionarios.edit');
        }

		function nenhumFiltroPreenchido() {
			return !vm.filtros.login && !vm.filtros.nome && !vm.filtros.cpf && !vm.filtros.cargo.value;
		}

		function _storeNewResource(result) {
			vm.funcionarios = [];
			_storeResource(result);
		}

		function _storeResource(result) {
			vm.resource = result;
			vm.resource._embedded.funcionarios.forEach(function(funcionario) {
				vm.funcionarios.push(funcionario);
			});
		}

		function _fimCarregando() {
			vm.carregando = false;
		}
    }
})();
