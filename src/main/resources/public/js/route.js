(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('index', {
				url: '/',
				templateUrl: 'index.html'
			});

		$urlRouterProvider.otherwise("/");
	}

})();
