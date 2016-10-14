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
	}

})();
