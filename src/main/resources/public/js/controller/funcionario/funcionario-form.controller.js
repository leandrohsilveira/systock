(function() {
    'use strict';

    angular
        .module('systock')
        .controller('FuncionarioFormController', FuncionarioFormController);

    FuncionarioFormController.$inject = ['Funcionario', 'HALResourceService', '$state', '$stateParams'];

    /* @ngInject */
    function FuncionarioFormController(Funcionario, HALResourceService, $state, $stateParams) {
        var vm = this;
        vm.funcionario = null;
		vm.editar = false;
        vm.salvar = salvar;

        activate();

        function activate() {
            vm.editar = $stateParams.editar;
			if(vm.editar) {
				Funcionario.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.funcionario = response;
                    });
			} else {
                vm.funcionario = new Funcionario();
                vm.funcionario.ativo = true;
                vm.funcionario.cargo = 'VENDEDOR';
			}
        }

		function salvar() {
			if(vm.editar) {
                HALResourceService.follow(vm.funcionario, 'PUT', 'self', vm.funcionario)
					.then(goToFuncionarioList);
			} else {
                vm.funcionario.$save(goToFuncionarioList);
			}
		}

		function goToFuncionarioList() {
			$state.go('app.funcionarios.consultar');
		}

    }
})();