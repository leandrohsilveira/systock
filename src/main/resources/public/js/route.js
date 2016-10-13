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
			.state('app.usuarios', {
				parent: 'app',
				url: '/usuarios',
				controller: 'UsuarioListController as vm',
				templateUrl: 'paginas/usuario/usuario-list.html'
			})
			.state('app.usuarios.new', {
				parent: 'app.usuarios',
				url: '/new',
				controller: 'UsuarioFormController as vm',
				templateUrl: 'paginas/usuario/usuario-form.html'
			})
			.state('app.usuarios.edit', {
				parent: 'app.usuarios',
				url: '/:id',
				controller: 'UsuarioFormController as vm',
				templateUrl: 'paginas/usuario/usuario-form.html'
			});

		$urlRouterProvider.otherwise("/app/index");
	}

})();
