(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Funcionario', Funcionario);

    Funcionario.$inject = ['$resource'];

    /* @ngInject */
    function Funcionario($resource) {
		return $resource(
			'funcionarios/:id',
			{id: '@id'},
			{
				'query':  {method:'GET', isArray: false},
				'search': {
					url: 'funcionarios/search/:action',
					method:'GET',
					isArray: false,
					params: {
						action: '@action'
					}
				}
			}
		);
    }
})();
