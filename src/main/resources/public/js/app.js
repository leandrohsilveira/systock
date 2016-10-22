(function() {
    'use strict';

    angular
        .module('systock', [
			'ui.router',
			'ui.bootstrap',
			'ui.bootstrap.alert',
			'ui.bootstrap.typeahead',
			'spring-security-csrf-token-interceptor',
			'ngCookies',
			'ngResource',
			'ngMessages',
            'ngCpfCnpj',
            'ngMask',
			'idf.br-filters',
			'ui.utils.masks',
			'validation.match'
		])
		.constant('jQuery', jQuery);
})();
