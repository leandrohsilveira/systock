(function() {
    'use strict';

    angular
        .module('systock')
        .factory('Funcionario', Funcionario);

    Funcionario.$inject = ['$resource'];

    /* @ngInject */
    function Funcionario($resource) {
        return $resource('funcionarios/:id');
    }
})();
