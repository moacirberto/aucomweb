package com.bertolezo.aucom;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bertolezo.aucom.domain.Categoria;
import com.bertolezo.aucom.domain.Cidade;
import com.bertolezo.aucom.domain.Cliente;
import com.bertolezo.aucom.domain.Endereco;
import com.bertolezo.aucom.domain.Estado;
import com.bertolezo.aucom.domain.ItemPedido;
import com.bertolezo.aucom.domain.Pagamento;
import com.bertolezo.aucom.domain.PagamentoComBoleto;
import com.bertolezo.aucom.domain.PagamentoComCartao;
import com.bertolezo.aucom.domain.Pedido;
import com.bertolezo.aucom.domain.Produto;
import com.bertolezo.aucom.domain.enums.EstadoPagamento;
import com.bertolezo.aucom.domain.enums.TipoCliente;
import com.bertolezo.aucom.repositories.CategoriaRepository;
import com.bertolezo.aucom.repositories.CidadeRepository;
import com.bertolezo.aucom.repositories.ClienteRepository;
import com.bertolezo.aucom.repositories.EnderecoRepository;
import com.bertolezo.aucom.repositories.EstadoRepository;
import com.bertolezo.aucom.repositories.ItemPedidoRepository;
import com.bertolezo.aucom.repositories.PagamentoRepository;
import com.bertolezo.aucom.repositories.PedidoRepository;
import com.bertolezo.aucom.repositories.ProdutoRepository;

@SpringBootApplication
public class AucomwebApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;  
	
	public static void main(String[] args) {
		SpringApplication.run(AucomwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");

		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(cat1);
		categoriaRepository.save(cat2);
		categoriaRepository.save(cat3);
		categoriaRepository.save(cat4);
		categoriaRepository.save(cat5);
		categoriaRepository.save(cat6);
		categoriaRepository.save(cat7);
		
		produtoRepository.save(p1);
		produtoRepository.save(p2);
		produtoRepository.save(p3);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(est1);
		estadoRepository.save(est2);
		
		cidadeRepository.save(c1);
		cidadeRepository.save(c2);
		cidadeRepository.save(c3);
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
        Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 303","Jardim","38220834",cli1,c1);
        Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        
        clienteRepository.save(cli1);
        enderecoRepository.save(e1);
        enderecoRepository.save(e2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);

        Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
        ped1.setPagamento(pagto1);
        
        Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
        ped2.setPagamento(pagto2);
        
        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
        
        pedidoRepository.save(ped1);
        pedidoRepository.save(ped2);
        
        pagamentoRepository.save(pagto1);
        pagamentoRepository.save(pagto2);
        
        ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
        ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
        ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
         
        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepository.save(ip1);
        itemPedidoRepository.save(ip2);
        itemPedidoRepository.save(ip3);
        
	}
}
