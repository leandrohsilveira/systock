(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioFormController', UsuarioFormController);

    UsuarioFormController.$inject = ['Usuario', 'Funcionario', 'HALResourceService', '$state', '$stateParams', 'StringUtils'];

    /* @ngInject */
    function UsuarioFormController(Usuario, Funcionario, HALResourceService, $state, $stateParams, StringUtils) {
        var vm = this;
		vm.usuario = null;
        vm.funcionario = null;
        vm.confirmarSenha = null;
		vm.editar = false;
		vm.selecionarFuncionario = selecionarFuncionario;
		vm.salvar = salvar;

        activate();

        function activate() {
			vm.editar = $stateParams.editar;
			if(vm.editar) {
				Usuario.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.usuario = response;
                        return HALResourceService.follow(vm.usuario, 'GET', 'funcionario');
                    })
					.then(function(response) {
						vm.funcionario = new Funcionario(response.data);;
					});
			} else {
				vm.usuario = new Usuario();
			}
        }

		function salvar() {
			vm.usuario.funcionario = vm.funcionario._links.self.href;
			if(vm.editar) {
				HALResourceService.follow(vm.usuario, 'PUT', 'self', vm.usuario)
					.then(goToUsuarioList);
			} else {
				vm.usuario.$save(goToUsuarioList);
			}
		}

		function goToUsuarioList() {
			$state.go('app.usuarios.consultar');
		}

		function selecionarFuncionario(nome) {
			return Funcionario.search({
				action: 'findFuncionarioLike',
				nome: StringUtils.getContainsLikeOrNullIfEmpty(nome),
				page: 0,
				size: 10
			}).$promise
				.then(function(response) {
					return response._embedded.funcionarios;
				});
		}

    }
})();
