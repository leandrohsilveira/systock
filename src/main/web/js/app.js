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
		.constant('FORM_DATA_CONFIG', {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
		.constant('jQuery', jQuery);
})();
