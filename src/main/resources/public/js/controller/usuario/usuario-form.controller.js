(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioFormController', UsuarioFormController);

    UsuarioFormController.$inject = ['Usuario', 'Funcionario', 'HALResourceService', '$state', '$stateParams', 'MensagensGlobaisService'];

    /* @ngInject */
    function UsuarioFormController(Usuario, Funcionario, HALResourceService, $state, $stateParams, MensagensGlobaisService) {
        var vm = this;
		vm.usuario = null;
        vm.funcionario = null;
        vm.confirmarSenha = null;
		vm.alterarSenha = false;
		vm.editar = false;
		vm.loginInicial = null;

		vm.selecionarFuncionario = selecionarFuncionario;
		vm.salvar = salvar;

        activate();

        function activate() {
			vm.editar = $stateParams.editar;
			if(vm.editar) {
				Usuario.get({id: $stateParams.id}).$promise
                    .then(function(response) {
                        vm.usuario = response;
						vm.loginInicial = vm.usuario.login;
                        return HALResourceService.follow(vm.usuario, 'GET', 'funcionario');
                    })
					.then(function(response) {
						vm.funcionario = new Funcionario(response.data);;
					});
			} else {
				vm.usuario = new Usuario();
                vm.usuario.ativo = true;
			}
        }

		function salvar() {
			vm.usuario.funcionario = vm.funcionario._links.self.href;
			if(vm.editar) {
                if(vm.usuario.senha) {
                    vm.usuario.secret = null;
                }
				HALResourceService.follow(vm.usuario, 'PUT', 'self', vm.usuario)
					.then(goToUsuarioList);
			} else {
				vm.usuario.$save(goToUsuarioList);
			}
		}

		function goToUsuarioList() {
			MensagensGlobaisService.addMensagemGlobal('Os dados do usuário foram salvos com sucesso', 'success', /^app\.usuarios\.consultar$/);
			$state.go('app.usuarios.consultar');
		}

		function selecionarFuncionario(query) {
			return Funcionario.search({
				action: 'query',
				query: query || null,
				page: 0,
				size: 10
			}).$promise
				.then(function(response) {
					return response._embedded.funcionarios;
				});
		}

    }
})();
