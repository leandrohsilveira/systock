(function() {
    'use strict';

    angular
        .module('systock')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$http', 'jQuery', 'FORM_DATA_CONFIG'];

    /* @ngInject */
    function LoginService($http, jQuery, FORM_DATA_CONFIG) {
        var service = {
            login: login,
			getUsuarioAutenticado: getUsuarioAutenticado,
			alterarUsuarioAutenticado: alterarUsuarioAutenticado,
			logout: logout
        };

        return service;

		function logout() {
			return $http.get('logout');
		}

        function login(formParams) {
			return $http.post('auth', jQuery.param(formParams), FORM_DATA_CONFIG);
        }

		function alterarUsuarioAutenticado(formParams) {
			return $http.put('auth', jQuery.param(formParams), FORM_DATA_CONFIG);
		}

		function getUsuarioAutenticado() {
			return $http.get('auth');
		}
    }
})();
