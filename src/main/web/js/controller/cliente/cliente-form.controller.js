(function() {
    'use strict';

    angular
        .module('systock')
        .controller('ClienteFormController', ClienteFormController);

    ClienteFormController.$inject = ['Cliente', 'HALResourceService', '$state', '$stateParams', 'MensagensGlobaisService'];

    /* @ngInject */
    function ClienteFormController(Cliente, HALResourceService, $state, $stateParams, MensagensGlobaisService) {
        var vm = this;
		vm.cliente = null;
		vm.editar = false;
		vm.cpfInicial = null;
		vm.emailInicial = null;

		vm.salvar = salvar;

        activate();

        function activate() {
			vm.editar = $stateParams.editar;
			if(vm.editar) {
				Cliente.get({id: $stateParams.id}).$promise
                    .then(function(response) {
						vm.cpfInicial = response.cpf;
						vm.emailInicial = response.email;
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
			MensagensGlobaisService.addMensagemGlobal('Os dados do cliente foram salvos com sucesso', 'success', /^app\.clientes\.consultar$/);
			$state.go('app.clientes.consultar');
		}

    }
})();
