(function() {
    'use strict';

    angular
        .module('systock')
        .controller('ProdutoFormController', ProdutoFormController);

    ProdutoFormController.$inject = ['Produto', 'HALResourceService', '$state', '$stateParams', 'StringUtils'];

    /* @ngInject */
    function ProdutoFormController(Produto, HALResourceService, $state, $stateParams, StringUtils) {
        var vm = this;
		vm.produto = null;
		vm.editar = false;
		vm.salvar = salvar;

        activate();

        function activate() {
			vm.editar = $stateParams.editar;
			if(vm.editar) {
				Produto.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.produto = response;
                    });
			} else {
				vm.produto = new Produto();
                vm.produto.ativo = true;
			}
        }

		function salvar() {
			if(vm.editar) {
				HALResourceService.follow(vm.produto, 'PUT', 'self', vm.produto)
					.then(goToProdutoList);
			} else {
				vm.produto.$save(goToProdutoList);
			}
		}

		function goToProdutoList() {
			$state.go('app.produtos.consultar');
		}

    }
})();
