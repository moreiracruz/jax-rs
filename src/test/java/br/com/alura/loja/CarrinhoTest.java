package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class CarrinhoTest {
	
	private HttpServer server;

	@Before
	public void before() {
		server = Servidor.inicializar();
	}
	
	@After
	public void after() {
		server.stop();
	}

	@Test
	public void buscar() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:80");
		String content = target.path("carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(content);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}

	@Test
	public void adicionar() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost");
		
		Carrinho carrinho = new Carrinho();
		carrinho.setId(2L);
		carrinho.setCidade("João Pessoa");
		carrinho.setRua("Rua Joana Morais Lordão, 184");
		
		String xml = new XStream().toXML(carrinho);
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
	}

}
