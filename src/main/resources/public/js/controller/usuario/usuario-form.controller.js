(function() {
    'use strict';

    angular
        .module('systock')
        .controller('UsuarioFormController', UsuarioFormController);

    UsuarioFormController.$inject = ['Usuario', '$state', '$stateParams'];

    /* @ngInject */
    function UsuarioFormController(Usuario, $state, $stateParams) {
        var vm = this;
		vm.usuario = null;
		vm.edit = false;

        activate();

        function activate() {
			if($stateParams.id) {
				vm.usuario = Usuario.get({id: $stateParams.id});
				vm.edit = true;
			} else {
				vm.usuario = new Usuario();
			}
        }

		function save() {
			if(vm.edit) {
				vm.usuario.$save(goToUsuarioList);
			} else {
				vm.usuario.$update(goToUsuarioList);
			}
		}

		function goToUsuarioList() {
			$state.go('app.usuarios');
		}

    }
})();
