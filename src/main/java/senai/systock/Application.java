package senai.systock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import senai.systock.dao.FuncionarioRepository;
import senai.systock.dao.UsuarioRepository;
import senai.systock.model.Funcionario;
import senai.systock.model.Usuario;

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
    			Funcionario funcionario = funcionarioRepository.save(new Funcionario("Administrador", "00000000123", "admin"));
    			usuarioRepository.save(new Usuario("admin", "systock@admin", funcionario));
    		}
    	};
	}
    
}