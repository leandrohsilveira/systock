(function() {
    'use strict';

    angular
        .module('systock')
        .directive('sysRequiredIf', sysRequiredIf);

    /* @ngInject */
    function sysRequiredIf() {
        var directive = {
            restrict: 'A',
			require: 'ngModel',
			scope: {
				is: '='
			}
            link: linkFunc,
        };

        return directive;

        function linkFunc(scope, el, attr, ngModel) {
			ngModel.$validators.validCharacters = function(modelValue, viewValue) {
				if(scope.is) {
					return modelValue || viewValue;
				}
				return true;
			}
        }
    }

})();
