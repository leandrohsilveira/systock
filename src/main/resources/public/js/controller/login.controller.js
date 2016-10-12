(function() {
    'use strict';

    angular
        .module('systock')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$http', '$stateParams', '$location', '$cookies', 'jQuery'];

    /* @ngInject */
    function LoginController($http, $stateParams, $location, $cookies, jQuery) {
        var vm = this;

		vm.mensagens = [];

		vm.login = null;
		vm.senha = null;

		vm.next = null;

		vm.entrar = entrar;
		vm.removerMensagem = removerMensagem;

        activate();

        function activate() {
			vm.next = $stateParams.next;
        }

		function entrar() {
			var params = {
					login: vm.login,
					senha: vm.senha,
					_csrf: $cookies.get('csrftoken')
				},
				config = {
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				}
			$http.post('auth', jQuery.param(params), config)
				.then(function (response) {
					if(vm.next) {
						$location.path(vm.next);
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
