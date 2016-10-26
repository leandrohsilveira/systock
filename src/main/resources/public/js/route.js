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
				templateUrl: 'paginas/index.html',
                controller: 'MinhasVendasController as vm'
			})
			.state('app.meuUsuario', {
				parent: 'app',
				url: '/autenticado?alterarSenha',
				controller: 'MeuUsuarioController as vm',
				templateUrl: 'paginas/meu-usuario.html'
			});

		$urlRouterProvider.otherwise("/app/index");
	}

})();
