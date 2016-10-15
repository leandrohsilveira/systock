package senai.systock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import senai.systock.model.Cargo;
import senai.systock.model.Funcionario;
import senai.systock.model.Usuario;
import senai.systock.repository.FuncionarioRepository;
import senai.systock.repository.UsuarioRepository;
import senai.systock.utils.CPFUtils;

@SpringBootApplication
public class Application {
	
    public static void main(String[] args) throws Exception {
    	if(StringUtils.isEmpty(System.getProperty("server.port"))) {
    		System.setProperty("server.port", "8081");
    	}
        SpringApplication.run(Application.class, args);
    }


    @Bean
	public CommandLineRunner inicializarUsuarios(UsuarioRepository usuarioRepository, FuncionarioRepository funcionarioRepository) {
    	return args -> {
    		Usuario admin = usuarioRepository.findByLogin("admin");
    		if(admin == null) {
    			Funcionario funcionario = funcionarioRepository.save(new Funcionario("Administrador do Sistema", "34958572570", Cargo.ADMINISTRADOR));
    			usuarioRepository.save(new Usuario("admin", "12345678", funcionario));
    			
    			for (int i = 0; i < 50; i++) {
    				Cargo cargo;
    				switch (i % 10) {
						case 0:
							cargo = Cargo.ADMINISTRADOR;
							break;
						case 1:
						case 2:
						case 3:
							cargo = Cargo.GERENTE;
							break;
						default:
							cargo = Cargo.VENDEDOR;
							break;
					}
    				
    				String login = String.format("%s%d", cargo.getDescricao().toLowerCase(), i + 1);
    				String nome = String.format("%s - %d", cargo.getDescricao(), i + 1);
					String cpf = CPFUtils.gerarCPF();
					String senha = "12345678";
					funcionario = funcionarioRepository.save(new Funcionario(nome, cpf, cargo));
					usuarioRepository.save(new Usuario(login, senha, funcionario));
				}
    			for (int i = 50; i < 80; i++) {
    				Cargo cargo;
    				switch (i % 10) {
						case 0:
							cargo = Cargo.ADMINISTRADOR;
							break;
						case 1:
						case 2:
						case 3:
							cargo = Cargo.GERENTE;
							break;
						default:
							cargo = Cargo.VENDEDOR;
							break;
					}
    				
    				String nome = String.format("%s - %d", cargo.getDescricao(), i + 1);
					String cpf = CPFUtils.gerarCPF();
					funcionarioRepository.save(new Funcionario(nome, cpf, cargo));
				}
    		}
    	};
	}
    
}