(function() {
    'use strict';

    angular
        .module('systock')
        .config(CsrfTokenConfig);

    CsrfTokenConfig.$inject = ['csrfProvider'];

    /* @ngInject */
    function CsrfTokenConfig(csrfProvider) {
        csrfProvider.config({
			csrfTokenHeader: 'X-CSRF-TOKEN',
			maxRetries: 0
		});
    }

})();
