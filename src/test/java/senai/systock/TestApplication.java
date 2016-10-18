package senai.systock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import senai.systock.model.Cargo;
import senai.systock.model.Funcionario;
import senai.systock.model.Produto;
import senai.systock.model.Usuario;
import senai.systock.repository.FuncionarioRepository;
import senai.systock.repository.ProdutoRepository;
import senai.systock.repository.UsuarioRepository;
import senai.systock.utils.CPFUtils;

@SpringBootApplication
public class TestApplication {
	
    public static void main(String[] args) throws Exception {
    	if(StringUtils.isEmpty(System.getProperty("server.port"))) {
    		System.setProperty("server.port", "8082");
    	}
        SpringApplication.run(TestApplication.class, args);
    }


    @Bean
	public CommandLineRunner inicializarUsuarios(UsuarioRepository usuarioRepository, FuncionarioRepository funcionarioRepository, ProdutoRepository produtoRepository) {
    	return args -> {
			Funcionario funcionario = funcionarioRepository.save(new Funcionario("Administrador do Sistema", "94149790507", Cargo.ADMINISTRADOR));
			usuarioRepository.save(new Usuario("admin", "12345678", funcionario));
			
			funcionario = funcionarioRepository.save(new Funcionario("Gerente do Sistema", "03606681275", Cargo.GERENTE));
			usuarioRepository.save(new Usuario("gerente", "12345678", funcionario));
			
			funcionario = funcionarioRepository.save(new Funcionario("Vendedor do Sistema", "06130074689", Cargo.VENDEDOR));
			usuarioRepository.save(new Usuario("vendedor", "12345678", funcionario));
			
			produtoRepository.save(new Produto("Produto", 23.75f, 3));
    	};
	}
    
}