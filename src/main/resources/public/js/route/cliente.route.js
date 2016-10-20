(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider) {
		$stateProvider
			.state('app.clientes', {
				parent: 'app',
				url: '/clientes',
				abstract: true
			})
			.state('app.clientes.consultar', {
				parent: 'app',
				url: '/clientes',
				controller: 'ClienteListController as vm',
				templateUrl: 'paginas/cliente/cliente-list.html'
			})
			.state('app.clientes.cadastrar', {
				parent: 'app',
				url: '/clientes/cadastrar',
				controller: 'ClienteFormController as vm',
				templateUrl: 'paginas/cliente/cliente-form.html',
				params: {
					editar: false
				}
			})
			.state('app.clientes.editar', {
				parent: 'app',
				url: '/clientes/:id',
				controller: 'ClienteFormController as vm',
				templateUrl: 'paginas/cliente/cliente-form.html',
				params: {
					editar: true
				}
			});
	}

})();
