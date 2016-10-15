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
			if(rejection.status == 401) {
				console.debug('(401) Unauthorized');
				var $state = $injector.get('$state'),
					current = $location.path(),
					params = {};
				if(!/^\/login/.test(current)) {
					params = { next: current };
				}
				$state.transitionTo('login', params);
			}
			return $q.reject(rejection);
		}

    }
})();
