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
				url: '/login?next&msg',
				templateUrl: 'login.html',
				controller: 'LoginController as vm'
			})
			.state('app.index', {
				parent: 'app',
				url: '/index',
				templateUrl: 'paginas/index.html'
			});

		$urlRouterProvider.otherwise("/app/index");
	}

	function getFindFuncionarioState(parent) {
		return {
			parent: parent,
			url: '/funcionario/find',
			templateUrl: 'paginas/usuario/funcionario/usuario-funcionario-find.html'
		};
	}

	function getNewFuncionarioState(parent) {
		return {
			parent: parent,
			url: '/funcionario/new',
			templateUrl: 'paginas/usuario/funcionario/usuario-funcionario-form.html'
		};
	}

})();
