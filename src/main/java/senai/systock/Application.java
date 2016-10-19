package senai.systock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import senai.systock.model.Cargo;
import senai.systock.model.Cliente;
import senai.systock.model.Funcionario;
import senai.systock.model.ItemVenda;
import senai.systock.model.Produto;
import senai.systock.model.SituacaoVenda;
import senai.systock.model.Usuario;
import senai.systock.model.Venda;
import senai.systock.repository.ClienteRepository;
import senai.systock.repository.FuncionarioRepository;
import senai.systock.repository.ProdutoRepository;
import senai.systock.repository.UsuarioRepository;
import senai.systock.repository.VendaRepository;
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
	public CommandLineRunner inicializarUsuarios(
			UsuarioRepository usuarioRepository, 
			FuncionarioRepository funcionarioRepository,
			VendaRepository vendaRepository,
			ClienteRepository clienteRepository, 
			ProdutoRepository produtoRepository) {
    	return args -> {
    		Usuario admin = usuarioRepository.findByLogin("admin");
    		if(admin == null) {
    			Funcionario funcionario = funcionarioRepository.save(new Funcionario("Administrador do Sistema", CPFUtils.gerarCPF(), Cargo.ADMINISTRADOR));
    			usuarioRepository.save(new Usuario("admin", "12345678", funcionario));
    			
    			funcionario = funcionarioRepository.save(new Funcionario("Gerente do Sistema", CPFUtils.gerarCPF(), Cargo.GERENTE));
    			usuarioRepository.save(new Usuario("gerente", "12345678", funcionario));
    			
    			funcionario = funcionarioRepository.save(new Funcionario("Vendedor do Sistema", CPFUtils.gerarCPF(), Cargo.VENDEDOR));
    			usuarioRepository.save(new Usuario("vendedor", "12345678", funcionario));
    			
    			Produto produto = produtoRepository.save(new Produto("Produto do Sistema", Double.valueOf(Math.random() * 5000 + 1).floatValue(), 30));
    			
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
					
					clienteRepository.save(new Cliente(String.format("Cliente %d", i + 1), CPFUtils.gerarCPF(), String.format("cliente%d@gmail.com", i + 1)));
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
    			
    			for (int i = 0; i < 20; i++) {
    				Venda venda = vendaRepository.save(new Venda(funcionarioRepository.findOne(Double.valueOf(Math.random() * 49 + 1).longValue()), SituacaoVenda.ABERTA));
    				
    				venda.setCliente(clienteRepository.findOne(Double.valueOf(Math.random() * 49 + 1).longValue()));
    				venda.setDesconto(Double.valueOf(Math.random()).floatValue());
    				venda.addItemVenda(new ItemVenda(venda, produto, produto.getPreco(), Long.valueOf(Math.round(Math.random() * 10 + 1)).intValue()));
    				
    				venda = vendaRepository.save(venda);
    				
    				venda.setSituacao(SituacaoVenda.CONCLUIDA);
    				venda = vendaRepository.save(venda);
				}
    			

    		}
    	};
	}
    
}
