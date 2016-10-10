(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state('app', {
				url: '/app',
				templateUrl: 'layout.html'
			})
			.state('login', {
				url: '/login?next',
				templateUrl: 'login.html',
				controller: 'LoginController as vm'
			})
			.state('app.index', {
				parent: 'app',
				url: '/index',
				templateUrl: 'paginas/index.html'
			})
			.state('app.funcionarios', {
				parent: 'app',
				url: '/funcionarios',
				templateUrl: 'paginas/funcionarios.html'
			});

		$urlRouterProvider.otherwise("/app/index");
	}

})();
