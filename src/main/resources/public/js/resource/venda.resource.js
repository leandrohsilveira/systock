(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Venda', Venda);

    Venda.$inject = ['$resource'];

    /* @ngInject */
    function Venda($resource) {
        return $resource(
			'vendas/:id',
			{id: '@id'},
			{
				'query':  {method:'GET', isArray: false},
				'search': {
					url: 'vendas/search/:action',
					method:'GET',
					isArray: false,
					params: {
						action: '@action'
					}
				},
				'update': { method: 'PUT' }
			}
		);
    }

})();
