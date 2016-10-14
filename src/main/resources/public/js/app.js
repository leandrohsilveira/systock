(function() {
    'use strict';

    angular
        .module('systock', [
			'ui.router',
			'ui.bootstrap',
			'ui.bootstrap.alert',
			'ui.bootstrap.typeahead',
			'ngCookies',
			'ngResource',
			'idf.br-filters'
		])
		.constant('jQuery', jQuery);
})();
