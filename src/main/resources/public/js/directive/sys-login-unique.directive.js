(function() {
    'use strict';

    angular
        .module('systock')
        .directive('sysLoginUnique', sysLoginUnique);


	sysLoginUnique.$inject = ['$q', '$http'];

    /* @ngInject */
    function sysLoginUnique($q, $http) {
        var directive = {
            restrict: 'A',
			require: 'ngModel',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope, el, attr, ngModel) {
			ngModel.$asyncValidators.loginExistente = function(modelValue, viewValue) {
				var value = modelValue || viewValue;

				return $http({
					url: 'usuarios/search/existsWithLogin',
					params: {
						login: value
					}
				}).then(function(response) {
					if(response.data) {
						return $q.reject('loginExistente')
					} else {
						return true;
					}
				});

			};
        }

    }

})();
