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
			})
			.state('app.funcionarios', {
				parent: 'app',
				url: '/funcionarios',
				controller: 'FuncionarioListController as vm',
				templateUrl: 'paginas/funcionario/funcionario-list.html'
			})
			.state('app.funcionarios.new', {
				parent: 'app.funcionarios',
				url: '/new',
				controller: 'FuncionarioFormController as vm',
				templateUrl: 'paginas/funcionario/funcionario-form.html'
			})
			.state('app.funcionarios.edit', {
				parent: 'app.funcionarios',
				url: '/:id',
				controller: 'FuncionarioFormController as vm',
				templateUrl: 'paginas/funcionario/funcionario-form.html'
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
