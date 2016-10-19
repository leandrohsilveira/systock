(function() {
    'use strict';

    angular
        .module('systock')
        .controller('VendaFormController', VendaFormController);

    VendaFormController.$inject = ['Venda'];

    /* @ngInject */
    function VendaFormController(Venda) {
        var vm = this;

        activate();

        function activate() {

        }
    }
})();
