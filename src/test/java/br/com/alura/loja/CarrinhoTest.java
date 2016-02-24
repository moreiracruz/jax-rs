package br.com.alura.loja;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoTest {
	
	private HttpServer server;
	private WebTarget target;

	@Before
	public void before() {
		server = Servidor.inicializar();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		Client client = ClientBuilder.newClient(config);
		target = client.target("http://localhost");
	}
	
	@After
	public void after() {
		server.stop();
	}

	@Test
	public void buscar() {
		
		String content = target.path("carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(content);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}

	@Test
	public void adicionar() {

		Carrinho carrinho = new Carrinho();
		carrinho.setId(2L);
		carrinho.setCidade("João Pessoa");
		carrinho.setRua("Rua Joana Morais Lordão, 184");
		
		String xml = new XStream().toXML(carrinho);
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
	}
	
	@Test
	public void remover() {
		
		Response response = target.path("/carrinhos/1/produtos/6237").request().delete();
        Assert.assertEquals(200, response.getStatus());	
	}
	
	@Test
	public void alterar() {
		
		String content = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(content);
		
		for ( Produto produto : carrinho.getProdutos() ) {
			
			if (produto.getId() == 3467) {
				produto.setQuantidade(1);
			}
			
		}
		
		String xml = new XStream().toXML(carrinho);
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

		Response response = target.path("/carrinhos/1/produtos/6237").request().put(entity);
        Assert.assertEquals(200, response.getStatus());	
        
        /**
         * Carrinho carrinho = new Carrinho();
		carrinho.setId(2L);
		carrinho.setCidade("João Pessoa");
		carrinho.setRua("Rua Joana Morais Lordão, 184");
		
		String xml = new XStream().toXML(carrinho);
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
         */
		
	}

}
