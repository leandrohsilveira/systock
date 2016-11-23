(function() {
    'use strict';

    angular
        .module('systock')
        .component('sysMensagensGlobais', sysMensagensGlobais());

    /* @ngInject */
    function sysMensagensGlobais() {
        var component = {
            templateUrl: 'js/component/sys-mensagens-globais.html',
            controller: MensagensGlobaisController,
			controllerAs: 'vm'
        };

        return component;
    }

    MensagensGlobaisController.$inject = ['$rootScope'];

    /* @ngInject */
    function MensagensGlobaisController($rootScope) {
		var vm = this;
		vm.mensagens = [];
		vm.removerMensagem = removerMensagem;

        activate();

        function activate() {

			$rootScope.$on('$stateChangeStart', _onStateChangeStart);
			$rootScope.$on('sysAddMensagemGlobal', _addMensagemGlobal);
			$rootScope.$on('sysLimparMensagensGlobais', _limparMensagensGlobais);

        }

		function removerMensagem(indice) {
			vm.mensagens.splice(indice, 1);
		}

		function _onStateChangeStart(event, toState, toParams, fromState, fromParams) {
			for (var i = 0; i < vm.mensagens.length; i++) {
				var mensagem = vm.mensagens[i];
				if(!mensagem.manter || !mensagem.manter.test(toState.name)) {
					removerMensagem(i);
				}
			}
		}

		function _addMensagemGlobal(event, mensagem) {
			vm.mensagens.push(mensagem);
		}

		function _limparMensagensGlobais(event) {
			vm.mensagens = [];
		}
    }
})();

(function() {
    'use strict';

    angular
        .module('systock')
        .factory('MensagensGlobaisService', MensagensGlobaisService);

    MensagensGlobaisService.$inject = ['$rootScope'];

    /* @ngInject */
    function MensagensGlobaisService($rootScope) {
        var service = {
            addMensagemGlobal: addMensagemGlobal,
			setMensagemGlobal: setMensagemGlobal,
			limparMensagensGlobais: limparMensagensGlobais
        };

        return service;

        function addMensagemGlobal(descricao, status, manter) {
			if(descricao) {
				var mensagem = {
					descricao: descricao,
					status: (status || 'info'),
					manter: manter
				}
				$rootScope.$broadcast('sysAddMensagemGlobal', mensagem);
			}
        }

		function setMensagemGlobal(descricao, status, manter) {
			limparMensagensGlobais();
			addMensagemGlobal(descricao, status, manter);
		}

		function limparMensagensGlobais() {
			$rootScope.$broadcast('sysLimparMensagensGlobais');
		}
    }
})();
