(function() {
    'use strict';

    angular
        .module('systock')
        .config(AutenticacaoConfig);

    AutenticacaoConfig.$inject = ['$httpProvider'];

    /* @ngInject */
    function AutenticacaoConfig($httpProvider) {
        $httpProvider.interceptors.push('AutenticacaoInterceptor');
    }
})();
