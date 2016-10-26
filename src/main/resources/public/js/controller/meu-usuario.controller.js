(function() {
    'use strict';

    angular
        .module('systock')
        .controller('MeuUsuarioController', MeuUsuarioController);

    MeuUsuarioController.$inject = ['$state', '$stateParams', 'LoginService', 'MensagensGlobaisService'];

    /* @ngInject */
    function MeuUsuarioController($state, $stateParams, LoginService, MensagensGlobaisService) {
        var vm = this;
		vm.usuario = null;
        vm.funcionario = null;
		vm.alterarSenha = false;
		vm.senhaAtual = null;
		vm.novaSenha = null;
        vm.confirmarSenha = null;

		vm.salvar = salvar;

        activate();

        function activate() {
			LoginService.getUsuarioAutenticado()
				.then(function(response) {
					if($stateParams.alterarSenha) {
						vm.alterarSenha = true;
					}
					vm.usuario = response.data;
				});
        }

		function salvar() {

			var params = {
				alterarSenha: vm.alterarSenha,
				senhaAtual: vm.senhaAtual,
				novaSenha: vm.novaSenha,
				nome: vm.usuario.funcionario.nome
			}

			LoginService.alterarUsuarioAutenticado(params)
				.then(_goToMinhasVendas)
				.catch(function (response) {
					console.error(response.data);
					MensagensGlobaisService.addMensagemGlobal(response.data.message, 'danger');
				});



		}

		function _goToMinhasVendas() {
			MensagensGlobaisService.addMensagemGlobal('Os dados do usuário foram salvos com sucesso', 'success', /^app\.index$/);
			$state.go('app.index');
		}

    }
})();
