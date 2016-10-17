(function() {
    'use strict';

    angular
        .module('systock')
        .config(TemplateCacheConfig);

    TemplateCacheConfig.$inject = ['$httpProvider'];

    /* @ngInject */
    function TemplateCacheConfig($httpProvider) {

        $httpProvider.interceptors.push('TemplateCacheInterceptor');

    }

})();
