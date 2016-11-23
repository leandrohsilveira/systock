(function() {
    'use strict';

    angular
        .module('systock')
        .directive('unique', unique);

    unique.$inject = ['$q', '$http']

    /* @ngInject */
    function unique($q, $http) {
        var directive = {
            restrict: 'A',
            require: 'ngModel',
            scope: {
                res: '@uniqueRes',
                action: '@uniqueAction',
                except: '=uniqueExcept'
            },
            link: linkFunc,
        };

        return directive;

        function linkFunc(scope, el, attr, ngModel) {
			if(!attr.ngDisabled) {
				ngModel.$asyncValidators.unique = function(modelValue, viewValue) {
					var value = modelValue || viewValue;

					return $http({
						url: scope.res + '/search/' + (scope.action || 'unique'),
						params: {
							value: value || null,
							except: scope.except || null
						}
					}).then(function(response) {
						if(response.data) {
							return $q.reject('unique')
						} else {
							return true;
						}
					});

				};
			}

        }
    }

})();
