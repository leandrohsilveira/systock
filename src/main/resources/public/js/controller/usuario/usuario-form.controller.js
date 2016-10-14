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
		vm.edit = false;
		vm.selecionarFuncionario = selecionarFuncionario;

        activate();

        function activate() {
			if($state.is('app.usuarios.edit')) {
				Usuario.get({id: $stateParams.id}).$promise
                    .then(function(response) {
						vm.edit = true;
                        vm.usuario = response;
                        return HALResourceService.follow(vm.usuario, 'GET', 'funcionario');
                    })
					.then(function(response) {
						vm.funcionario = new Funcionario(response.data);;
					});
			} else if($state.is('app.usuarios.new')) {
				vm.usuario = new Usuario();
			}
        }

		function save() {
			if(vm.edit) {
				vm.usuario.$save(goToUsuarioList);
			} else {
				vm.usuario.$update(goToUsuarioList);
			}
		}

		function goToUsuarioList() {
			$state.go('app.usuarios');
		}

		function selecionarFuncionario(nome) {
			return Funcionario.search({
				action: 'findFuncionarioLike',
				nome: StringUtils.getContainsLikeOrNullIfEmpty(nome),
				page: 0,
				size: 5
			}).$promise
				.then(function(response) {
					return response._embedded.funcionarios;
				});
		}

    }
})();
