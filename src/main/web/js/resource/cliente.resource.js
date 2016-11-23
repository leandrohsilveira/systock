(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Cliente', Cliente);

    Cliente.$inject = ['$resource'];

    /* @ngInject */
    function Cliente($resource) {
        return $resource(
			'clientes/:id',
			{id: '@id'},
			{
				'query':  {method:'GET', isArray: false},
				'search': {
					url: 'clientes/search/:action',
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
