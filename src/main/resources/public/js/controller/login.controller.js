(function() {
    'use strict';

    angular
        .module('systock')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['LoginService', '$stateParams', '$location'];

    /* @ngInject */
    function LoginController(LoginService, $stateParams, $location) {
        var vm = this;

		vm.mensagens = [];

		vm.login = null;
		vm.senha = null;

		vm.next = null;

		vm.entrar = entrar;
		vm.removerMensagem = removerMensagem;

        activate();

        function activate() {
			if($stateParams.msg) {
				vm.mensagens.push({tipo: 'info', descricao: $stateParams.msg});
			}
			vm.next = $stateParams.next;
        }

		function entrar() {
			var params = {
					login: vm.login,
					senha: vm.senha
				};
			LoginService.login(params)
				.then(function (response) {
					if(vm.next) {
						$location.path(vm.next).search('next', null);
					} else {
						$location.path('/');
					}
				})
				.catch(function (response) {
					console.error(response);
					vm.mensagens.push({tipo: 'danger', descricao: response.data.message});
				});
		}

		function removerMensagem(indice) {
			if(vm.mensagens && indice < vm.mensagens.length) {
				vm.mensagens.splice(indice, 1);
			}
		}
    }
})();
