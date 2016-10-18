(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider) {
		$stateProvider
			.state('app.usuarios', {
				parent: 'app',
				url: '/usuarios',
				abstract: true
			})
			.state('app.usuarios.consultar', {
				parent: 'app',
				url: '/usuarios',
				controller: 'UsuarioListController as vm',
				templateUrl: 'paginas/usuario/usuario-list.html'
			})
			.state('app.usuarios.cadastrar', {
				parent: 'app',
				url: '/usuarios/cadastrar',
				controller: 'UsuarioFormController as vm',
				templateUrl: 'paginas/usuario/usuario-form.html',
				params: {
					editar: false
				}
			})
			.state('app.usuarios.editar', {
				parent: 'app',
				url: '/usuarios/:id',
				controller: 'UsuarioFormController as vm',
				templateUrl: 'paginas/usuario/usuario-form.html',
				params: {
					editar: true
				}
			});
	}

})();
