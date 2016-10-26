(function() {
    'use strict';

    angular
        .module('systock')
        .factory('AutenticacaoInterceptor', AutenticacaoInterceptor);

    AutenticacaoInterceptor.$inject = ['$q', '$location', '$injector'];

    /* @ngInject */
    function AutenticacaoInterceptor($q, $location, $injector) {
        var interceptor = {
            responseError: responseError
        };

        return interceptor;

		function responseError(rejection) {
			var params = {},
				current = $location.path();

			if(!/^(\/login|\/app\/autenticado)/.test(current)) {
				switch (rejection.status) {
					case 403:
						params.msg = 'Sem privilégios para acessar esta página, tente acessar com outro usuário.';
					case 401:
						var $state = $injector.get('$state');
						params.next = current;
						$state.transitionTo('login', params);
					default:
						break;
				}
			}
			return $q.reject(rejection);
		}

    }
})();
