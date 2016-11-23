(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Produto', Produto);

    Produto.$inject = ['$resource'];

    /* @ngInject */
    function Produto($resource) {
		return $resource(
			'produtos/:id',
			{id: '@id'},
			{
				'query':  {method:'GET', isArray: false},
				'search': {
					url: 'produtos/search/:action',
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
