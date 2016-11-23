(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider) {
		$stateProvider
            .state('app.produtos', {
                parent: 'app',
                abstract: true
            })
			.state('app.produtos.consultar', {
				parent: 'app',
				url: '/produtos',
				controller: 'ProdutoListController as vm',
				templateUrl: 'paginas/produto/produto-list.html'
			})
			.state('app.produtos.cadastrar', {
				parent: 'app',
				url: '/produtos/cadastrar',
				controller: 'ProdutoFormController as vm',
				templateUrl: 'paginas/produto/produto-form.html',
				params: {
					editar: false
				}
			})
			.state('app.produtos.editar', {
				parent: 'app',
				url: '/produtos/:id',
				controller: 'ProdutoFormController as vm',
				templateUrl: 'paginas/produto/produto-form.html',
				params: {
					editar: true
				}
			});
	}

})();
