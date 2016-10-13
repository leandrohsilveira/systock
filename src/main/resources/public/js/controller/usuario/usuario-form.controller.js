(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioFormController', UsuarioFormController);

    UsuarioFormController.$inject = ['Usuario', 'Funcionario', 'HALResourceService', '$state', '$stateParams'];

    /* @ngInject */
    function UsuarioFormController(Usuario, Funcionario, HALResourceService, $state, $stateParams) {
        var vm = this;
		vm.usuario = null;
        vm.funcionario = null;
        vm.confirmarSenha = null;
		vm.edit = false;
        vm.cargos = [
            {value: null, rotulo: 'Todos'},
            {value: 'ADMINISTRADOR', rotulo: 'Administradores'},
            {value: 'GERENTE', rotulo: 'Gerentes'},
            {value: 'VENDEDOR', rotulo: 'Vendedores'}
        ];

        activate();

        function activate() {
			if($state.is('app.usuarios.edit')) {
				Usuario.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.usuario = response;
                        HALResourceService.follow(vm.usuario, 'GET', 'funcionario')
                            .then(function(response) {
                                vm.funcionario = new Funcionario(response.data);
                            });
                    });
				vm.edit = true;
			} else if($state.is('app.usuarios.new')) {
				vm.usuario = new Usuario();
                vm.funcionario = new Funcionario();
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

    }
})();
