(function() {
    'use strict';

    angular
        .module('systock.menu', []);

})();

(function() {
    'use strict';

    angular
        .module('systock.menu')
        .directive('sysMenu', SystockMenuDirective);

    /* @ngInject */
    function SystockMenuDirective() {
        var directive = {
            restrict: 'EA',
			transclude: true,
            template:
				[
					'<div class="ystock-menu-content" ng-transclude>',
					'</div>',
				].join(''),
            scope: {

            },
            link: linkFunc,
            controllerAs: 'vm'
        };

        return directive;

        function linkFunc(scope, el, attr, ctrl) {

        }
    }

})();
