(function() {
    'use strict';

    angular
        .module('systock')
        .filter('capitalize', capitalize);

    function capitalize() {
        return capitalizeFilter

        function capitalizeFilter(input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        }
    }
})();
