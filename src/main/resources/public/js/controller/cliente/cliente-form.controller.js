(function() {
    'use strict';

    angular
        .module('systock')
        .controller('ClienteFormController', ClienteFormController);

    ClienteFormController.$inject = ['Cliente', 'HALResourceService', '$state', '$stateParams', 'StringUtils'];

    /* @ngInject */
    function ClienteFormController(Cliente, HALResourceService, $state, $stateParams, StringUtils) {
        var vm = this;
		vm.cliente = null;
		vm.editar = false;
		vm.salvar = salvar;

        activate();

        function activate() {
			vm.editar = $stateParams.editar;
			if(vm.editar) {
				Cliente.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.cliente = response;
                    });
			} else {
				vm.cliente = new Cliente();
                vm.cliente.ativo = true;
			}
        }

		function salvar() {
			if(vm.editar) {
				HALResourceService.follow(vm.cliente, 'PUT', 'self', vm.cliente)
					.then(goToClienteList);
			} else {
				vm.cliente.$save(goToClienteList);
			}
		}

		function goToClienteList() {
			$state.go('app.clientes.consultar');
		}

    }
})();
