(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioNavbarController', UsuarioNavbarController);

    UsuarioNavbarController.$inject = ['$rootScope', 'LoginService', '$state'];

    /* @ngInject */
    function UsuarioNavbarController($rootScope, LoginService, $state) {
        var vm = this;
		vm.usuarioAutenticado = null;
		vm.logout = logout;
		vm.possuiUsuarioAutenticado = possuiUsuarioAutenticado;
        vm.possuiPrivilegioAdministrador = possuiPrivilegioAdministrador;
        vm.possuiPrivilegioGerente = possuiPrivilegioGerente;
        vm.possuiPrivilegioVendedor = possuiPrivilegioVendedor;

        activate();

        function activate() {
			getUsuarioAutenticado();
			$rootScope.$on('$stateChangeStart', function () {
				getUsuarioAutenticado();
			});
        }

		function possuiUsuarioAutenticado() {
			return !!vm.usuarioAutenticado;
		}

		function getUsuarioAutenticado() {
			LoginService.getUsuarioAutenticado()
				.then(function(response) {
					vm.usuarioAutenticado = response.data;
				});
		}

		function logout() {
			LoginService.logout().then(function (response) {
				$state.go('login');
			});
		}

        function possuiPrivilegioAdministrador() {
            return possuiUsuarioAutenticado() && vm.usuarioAutenticado.funcionario.cargo == 'ADMINISTRADOR';
        }

        function possuiPrivilegioGerente() {
            return possuiUsuarioAutenticado() && (possuiPrivilegioAdministrador() || vm.usuarioAutenticado.funcionario.cargo == 'GERENTE');
        }

        function possuiPrivilegioVendedor() {
            return possuiUsuarioAutenticado() && (possuiPrivilegioGerente() || vm.usuarioAutenticado.funcionario.cargo == 'VENDEDOR');
        }
    }
})();
