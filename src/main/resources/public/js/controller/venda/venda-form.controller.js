(function() {
    'use strict';

    angular
        .module('systock')
        .controller('VendaFormController', VendaFormController);

    VendaFormController.$inject = ['Venda', 'Cliente', 'Produto', 'LoginService', 'HALResourceService', '$state', '$stateParams'];

    /* @ngInject */
    function VendaFormController(Venda, Cliente, Produto, LoginService, HALResourceService, $state, $stateParams) {
        var vm = this;
        vm.venda = null;
        vm.funcionario = null;
        vm.cliente = null;
        vm.itens = [];
        vm.editar = false;
        vm.goToVendaList = goToVendaList;

        activate();

        function activate() {
            vm.editar = $stateParams.editar;
            if(vm.editar) {
                Venda.get({id: $stateParams.id}).$promise
                    .then(function(venda) {
                        vm.venda = venda;
                        vm.funcionario = venda._embedded.funcionario;
                        vm.cliente = venda._embedded.cliente;
                        vm.itens = venda.itens;
                    });
            } else {
                vm.venda = new Venda();
                vm.venda.ativo = true;
                vm.venda.situacao = 'ABERTA';
            }
        }

        function goToVendaList() {

        }
    }
})();
