(function() {
    'use strict';

    angular
        .module('systock')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$http', 'jQuery'];

    /* @ngInject */
    function LoginService($http, jQuery) {
        var service = {
            login: login,
			getUsuarioAutenticado: getUsuarioAutenticado,
			logout: logout
        };

        return service;

		function logout() {
			return $http.get('logout');
		}

        function login(formParams) {
			var config = {
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				}
			return $http.post('auth', jQuery.param(formParams), config);
        }

		function getUsuarioAutenticado() {
			return $http.get('auth');
		}
    }
})();
