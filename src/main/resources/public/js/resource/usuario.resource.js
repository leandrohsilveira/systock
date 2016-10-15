(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Usuario', Usuario);

    Usuario.$inject = ['$resource'];

    /* @ngInject */
    function Usuario($resource) {
        return $resource(
			'usuarios/:id',
			{id: '@id'},
			{
				'query':  {method:'GET', isArray: false},
				'search': {
					url: 'usuarios/search/:action',
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
