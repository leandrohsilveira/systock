(function() {
    'use strict';

    angular
        .module('systock', [
			'ui.router',
			'ui.bootstrap',
			'ui.bootstrap.alert',
			'ui.bootstrap.accordion',
			'ngCookies',
			'ngResource',
			'idf.br-filters'
		])
		.constant('jQuery', jQuery);
})();
