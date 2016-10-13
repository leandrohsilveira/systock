(function() {
    'use strict';

    angular
        .module('systock')
        .factory('HALResourceService', HALResourceService);

    HALResourceService.$inject = ['$http'];

    /* @ngInject */
    function HALResourceService($http) {
        var service = {
            follow: follow
        };

        return service;

        function follow(resource, method, link) {
			if(resource && resource._links) {
				var rel = resource._links[link];
				if(rel) {
					var url = rel.href;
					return $http({
						url: url,
						method: method,
					});
				}
			}
        }
    }
})();
