(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider) {
		$stateProvider
            .state('app.vendas', {
                parent: 'app',
                abstract: true
            })
			.state('app.vendas.consultar', {
				parent: 'app',
				url: '/vendas',
				controller: 'VendaListController as vm',
				templateUrl: 'paginas/venda/venda-list.html'
			})
			.state('app.vendas.cadastrar', {
				parent: 'app',
				url: '/vendas/cadastrar',
				controller: 'VendaFormController as vm',
				templateUrl: 'paginas/venda/venda-form.html',
				params: {
					editar: false
				}
			})
			.state('app.vendas.editar', {
				parent: 'app',
				url: '/vendas/:id',
				controller: 'VendaFormController as vm',
				templateUrl: 'paginas/venda/venda-form.html',
				params: {
					editar: true
				}
			});
	}

})();
