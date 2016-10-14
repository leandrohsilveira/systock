(function() {
    'use strict';

    angular
        .module('systock')
        .factory('StringUtils', StringUtils);

    StringUtils.$inject = [];

    /* @ngInject */
    function StringUtils() {
        var service = {
            getOrNullIfEmpty: getOrNullIfEmpty,
			getUppercaseOrNullIfEmpty: getUppercaseOrNullIfEmpty,
			getContainsLikeOrNullIfEmpty: getContainsLikeOrNullIfEmpty,
			getReplacedOrNullIfEmpty :getReplacedOrNullIfEmpty
        };

        return service;

		function getOrNullIfEmpty(value) {
			if(value) {
				return value;
			}
			return null;
		}

		function getUppercaseOrNullIfEmpty(value) {
			if(value) {
				return value.toUpperCase();
			}
			return null;
		}

		function getReplacedOrNullIfEmpty(value, regex, replace) {
			if(value) {
				return value.replace(regex, replace);
			}
			return null;
		}

		function getContainsLikeOrNullIfEmpty(value) {
			if(value) {
				return "%" + value.toUpperCase() + "%";
			}
			return null;
		}
    }
})();
