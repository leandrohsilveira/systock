(function() {
    'use strict';

    angular
        .module('systock')
        .factory('AutenticacaoInterceptor', AutenticacaoInterceptor);

    AutenticacaoInterceptor.$inject = ['$q', '$location', '$window'];

    /* @ngInject */
    function AutenticacaoInterceptor($q, $location, $window) {
        var interceptor = {
            responseError: responseError
        };

        return interceptor;

		function responseError(rejection) {
			if(rejection.status == 401) {
				console.debug('(401) Unauthorized');
				var path = $location.path();
				return $location.path('/login').search('next', path);
			}
			return $q.reject(rejection);
		}

    }
})();
