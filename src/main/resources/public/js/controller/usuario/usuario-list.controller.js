(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioListController', UsuarioListController);

    UsuarioListController.$inject = ['$state', 'Usuario', 'HALResourceService', 'StringUtils'];

    /* @ngInject */
    function UsuarioListController($state, Usuario, HALResourceService, StringUtils) {
        var vm = this;

		vm.carregando = true;
		vm.resource = null;
		vm.query = null;
		vm.usuarios = [];
        vm.cargos = [
            {value: null, rotulo: 'Todos'},
            {value: 'ADMINISTRADOR', rotulo: 'Administradores'},
            {value: 'GERENTE', rotulo: 'Gerentes'},
            {value: 'VENDEDOR', rotulo: 'Vendedores'}
        ]
        vm.filtros = {
            login: null,
            nome: null,
            cpf: null,
            cargo: vm.cargos[0],
            nenhumFiltroPreenchido: nenhumFiltroPreenchido,
            filtrar: filtrar
        }

		vm.recarregar = recarregar;
		vm.podeCarregarMais = podeCarregarMais;
		vm.possuiUsuarios = possuiUsuarios;
		vm.carregarMais = carregarMais;
        vm.getCargoNgClass = getCargoNgClass;

        activate();

        function activate() {
			vm.query = Usuario.query;
			_carregar(_storeNewResource)
        }

		function _carregar(then) {
			vm.carregando = true;
			vm.query({
				action: 'filter',
				login: StringUtils.getOrNullIfEmpty(vm.filtros.login),
				nome: StringUtils.getContainsLikeOrNullIfEmpty(vm.filtros.nome),
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
			vm.query = Usuario.search;
			_carregar(_storeNewResource)
		}

		function recarregar() {
			if(!vm.carregando) {
				vm.filtros.login = null;
				vm.filtros.nome = null;
				vm.filtros.cpf = null;
				vm.filtros.cargo = vm.cargos[0];
				vm.query = Usuario.query;
				_carregar(_storeNewResource)
			}
		}

		function possuiUsuarios() {
			return vm.usuarios && vm.usuarios.length;
		}

		function podeCarregarMais() {
			return vm.resource && vm.resource._links && vm.resource._links.next;
		}

		function nenhumFiltroPreenchido() {
			return !vm.filtros.login && !vm.filtros.nome && !vm.filtros.cpf && !vm.filtros.cargo.value;
		}

        function getCargoNgClass(usuario) {
            if(usuario.ativo) {
                return {
                    'text-success': usuario.funcionario.cargo == 'ADMINISTRADOR',
                    'text-primary': usuario.funcionario.cargo == 'GERENTE'
                };
            } else {
                return 'text-danger';
            }
        }

		function _storeNewResource(result) {
			vm.usuarios = [];
			_storeResource(result);
		}

		function _storeResource(result) {
			vm.resource = result;
			vm.resource._embedded.usuarios.forEach(function(usuario) {
				vm.usuarios.push(usuario);
			});
		}

		function _fimCarregando() {
			vm.carregando = false;
		}
    }
})();
