(function() {
    'use strict';

    angular
        .module('systock')
        .factory('TemplateCacheInterceptor', TemplateCacheInterceptor);

    TemplateCacheInterceptor.$inject = ['$q'];

    /* @ngInject */
    function TemplateCacheInterceptor($q) {
        var interceptor = {
            request: request
        };

        return interceptor;

        function request(config) {
            var url = config.url;
            if(config.method === 'GET' && /(\/?)paginas\//.test(url)) {
                var token = Date.now();
                if(/\?/.test(url)) {
                    url += '&__nocache=' + token;
                } else {
                    url += '?__nocache=' + token;
                }
                config.url = url;
            }
            return config || $q.when(config);
        }
    }
})();
