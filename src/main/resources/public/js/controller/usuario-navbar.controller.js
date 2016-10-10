(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioNavbarController', UsuarioNavbarController);

    UsuarioNavbarController.$inject = ['$http', '$state'];

    /* @ngInject */
    function UsuarioNavbarController($http, $state) {
        var vm = this;

		vm.logout = logout;

        activate();

        function activate() {

        }

		function logout() {
			$http.get('logout').then(function (response) {
				$state.go('login');
			})
		}
    }
})();
