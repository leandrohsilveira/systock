(function() {
    'use strict';

    angular
        .module('systock')
		.config(RouteConfig);

	RouteConfig.$inject = ['$stateProvider'];

	/* @ngInject */
	function RouteConfig($stateProvider) {
		$stateProvider
            .state('app.funcionarios', {
                parent: 'app',
                abstract: true
            })
			.state('app.funcionarios.consultar', {
				parent: 'app',
				url: '/funcionarios',
				controller: 'FuncionarioListController as vm',
				templateUrl: 'paginas/funcionario/funcionario-list.html'
			})
			.state('app.funcionarios.cadastrar', {
				parent: 'app',
				url: '/funcionarios/cadastrar',
				controller: 'FuncionarioFormController as vm',
				templateUrl: 'paginas/funcionario/funcionario-form.html',
				params: {
					editar: false
				}
			})
			.state('app.funcionarios.editar', {
				parent: 'app',
				url: '/funcionarios/:id',
				controller: 'FuncionarioFormController as vm',
				templateUrl: 'paginas/funcionario/funcionario-form.html',
				params: {
					editar: true
				}
			});
	}

})();
