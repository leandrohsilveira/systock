package senai.systock.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import senai.systock.TestApplication;
import senai.systock.model.Cargo;
import senai.systock.model.Cliente;
import senai.systock.model.Funcionario;
import senai.systock.model.ItemVenda;
import senai.systock.model.Produto;
import senai.systock.model.SituacaoVenda;
import senai.systock.model.Venda;
import senai.systock.repository.ClienteRepository;
import senai.systock.repository.FuncionarioRepository;
import senai.systock.repository.ProdutoRepository;
import senai.systock.repository.VendaRepository;
import senai.systock.utils.CPFUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ProcessoVendaTest {

	private static final int QUANTIDADE_ITEM = 2;
	private static final Float VALOR_DESCONTO = 3.5f;
	private static final Float VALOR_PRODUTO = 325.33f;
	private static final Float VALOR_SUBTOTAL = VALOR_PRODUTO * QUANTIDADE_ITEM;
	private static final Float VALOR_TOTAL = VALOR_SUBTOTAL - (VALOR_SUBTOTAL * (VALOR_DESCONTO / 100));

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Test
	public void sucessoTest() {
		Funcionario funcionario = funcionarioRepository.save(new Funcionario("Vendedor 1", CPFUtils.gerarCPF(), Cargo.VENDEDOR));
		assertNotNull(funcionario);
		assertNotNull(funcionario.getId());

		Venda venda = vendaRepository.save(new Venda(funcionario, SituacaoVenda.ABERTA));
		assertNotNull(venda);
		assertNotNull(venda.getId());
		assertEquals(SituacaoVenda.ABERTA, venda.getSituacao());

		Cliente cliente = clienteRepository.save(new Cliente("Cliente 1", CPFUtils.gerarCPF(), "cliente1@gmail.com"));
		assertNotNull(cliente);
		assertNotNull(cliente.getId());

		Produto produto = produtoRepository.save(new Produto("Produto 1", VALOR_PRODUTO, 5));
		assertNotNull(produto);
		assertNotNull(produto.getId());

		venda.setCliente(cliente);
		venda.setDesconto(VALOR_DESCONTO);
		venda.addItemVenda(new ItemVenda(venda, produto, VALOR_PRODUTO, QUANTIDADE_ITEM));

		venda = vendaRepository.save(venda);
		assertNotNull(venda);
		assertEquals(VALOR_SUBTOTAL, venda.getSubtotal());
		assertEquals(VALOR_TOTAL, venda.getTotal());
		assertEquals(SituacaoVenda.ABERTA, venda.getSituacao());

		venda.setSituacao(SituacaoVenda.CONCLUIDA);
		venda = vendaRepository.save(venda);
		assertEquals(SituacaoVenda.CONCLUIDA, venda.getSituacao());

	}

}
