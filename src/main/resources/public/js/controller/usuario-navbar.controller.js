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
    }
})();
