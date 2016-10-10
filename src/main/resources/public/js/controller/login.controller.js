(function() {
    'use strict';

    angular
        .module('systock')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$http', '$stateParams', '$location', '$cookies', 'jQuery'];

    /* @ngInject */
    function LoginController($http, $stateParams, $location, $cookies, jQuery) {
        var vm = this;

		vm.login = null;
		vm.senha = null;

		vm.next = null;

		vm.entrar = entrar;

        activate();

        function activate() {
			vm.next = $stateParams.next;
			console.log('next = ' + vm.next)
        }

		function entrar() {
			var params = {
					login: vm.login,
					senha: vm.senha,
					_csrf: $cookies.get('csrftoken')
				},
				config = {
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				}
			$http.post('auth', jQuery.param(params), config)
				.then(function (response) {
					if(vm.next) {
						$location.path(vm.next);
					} else {
						$location.path('/');
					}
				})
				.catch(function (response) {
					console.error(response);
				});
		}
    }
})();
