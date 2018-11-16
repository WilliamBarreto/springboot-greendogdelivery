package com.wb.casadocodigo.greendogdelivery.load;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.wb.casadocodigo.greendogdelivery.domain.Cliente;
import com.wb.casadocodigo.greendogdelivery.domain.Item;
import com.wb.casadocodigo.greendogdelivery.domain.Pedido;
import com.wb.casadocodigo.greendogdelivery.repository.ClienteRepository;

@Component
public class RepositoryTest implements ApplicationRunner {

	private static final long ID_CLIENTE_MANE_GALINHA = 1l;
	private static final long ID_CLIENTE_CAPITAO_NASCIMENTO = 2l;
	
	private static final long ID_ITEM1 = 100l;
	private static final long ID_ITEM2 = 101l;
	private static final long ID_ITEM3 = 102l;
	
	private static final long ID_PEDIDO1 = 1000l;
	private static final long ID_PEDIDO2 = 1001l;
	private static final long ID_PEDIDO3 = 1002l;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
    	System.out.println(">>> Iniciando carga de dados...");
    	
    	Item dog1 = new Item(ID_ITEM1,"Green Dog tradicional",25d);
    	Item dog2 = new Item(ID_ITEM2,"Green Dog tradicional picante",27d);
		Item dog3 = new Item(ID_ITEM3,"Green Dog max salada",30d);
    	
		
		// Dados Iniciais - Mané Galinha
		
		Cliente maneGalinha = new Cliente(ID_CLIENTE_MANE_GALINHA, "Mané Galinha", "Cidade de Deus");
		
    	List<Item> listaPedidoManeGalinha1 = new ArrayList<Item>();
    	listaPedidoManeGalinha1.add(dog1);
    	
    	List<Item> listaPedidoManeGalinha2 = new ArrayList<Item>();
		listaPedidoManeGalinha2.add(dog2);
		
		Pedido pedidoDoManeGalinha1 = new Pedido(ID_PEDIDO1, maneGalinha, listaPedidoManeGalinha1, dog1.getPreco());
    	maneGalinha.novoPedido(pedidoDoManeGalinha1);

    	Pedido pedidoDoManeGalinha2  = new Pedido(ID_PEDIDO3, maneGalinha, listaPedidoManeGalinha2, dog2.getPreco());
    	maneGalinha.novoPedido(pedidoDoManeGalinha2);
    	
    	System.out.println(">>> Mané Galinha - Pedido 1 : "+ pedidoDoManeGalinha1);
		System.out.println(">>> Mané Galinha - Pedido 2 : "+ pedidoDoManeGalinha2);
		
		clienteRepository.saveAndFlush(maneGalinha);
		System.out.println(">>> Mané Galinha - Gravado : " + maneGalinha);
    	
		// Dados Iniciais - Capitão Nascimento

    	Cliente capitao = new Cliente(ID_CLIENTE_CAPITAO_NASCIMENTO, "Capitão Nascimento", "BOPE");    
    	
    	List<Item> listaPedidoCapitaoNascimento1 = new ArrayList<Item>();
    	listaPedidoCapitaoNascimento1.add(dog2);
    	listaPedidoCapitaoNascimento1.add(dog3);
    	
    	Pedido pedidoDoCapitaoNascimento1 = new Pedido(ID_PEDIDO2, capitao, listaPedidoCapitaoNascimento1, dog2.getPreco() + dog3.getPreco());
    	capitao.novoPedido(pedidoDoCapitaoNascimento1);
    	
    	System.out.println(">>> Capitão Nascimento - Pedido 1 : "+ pedidoDoCapitaoNascimento1);
    	clienteRepository.saveAndFlush(capitao);
		System.out.println(">>> Capitão Nascimento - Gravado : " + capitao);
	}

}
