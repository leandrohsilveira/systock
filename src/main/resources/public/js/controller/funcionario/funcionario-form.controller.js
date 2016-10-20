(function() {
	'use strict';

	angular.module('systock').controller('FuncionarioFormController',
			FuncionarioFormController);

	FuncionarioFormController.$inject = ['Funcionario', 'HALResourceService',
			'$state', '$stateParams' ];

	/* @ngInject */
	function FuncionarioFormController(Funcionario, HALResourceService, $state,
			$stateParams) {
		var vm = this;
		vm.funcionario = null;
		vm.confirmarSenha = null;
		vm.edit = false;

		vm.cargos = [ {
			value : null,
			rotulo : 'Todos'
		}, {
			value : 'ADMINISTRADOR',
			rotulo : 'Administradores'
		}, {
			value : 'GERENTE',
			rotulo : 'Gerentes'
		}, {
			value : 'VENDEDOR',
			rotulo : 'Vendedores'
		} ];

		activate();

		function activate() {
			if ($state.is('app.funcionarios.edit')) {
				Funcionario.get({
					id : $stateParams.id
				}).$promise.then(function(funcionario) {
					vm.funcionario = funcionario;
				});
				vm.edit = true;
			} else if ($state.is('app.funcionarios.new')) {
				vm.funcionario = new Funcionario();
			}
		}

		function save() {
			if (vm.edit) {
				HALResourceService.follow(vm.funcionario, 'PUT', 'self',
						vm.funcionario).then(goToUsuarioList);
			} else {
				vm.funcionario.$save(goToFuncionarioList);
			}
		}

		function goToFuncionarioList() {
			$state.go('app.funcionarios');
		}

	}
})();
