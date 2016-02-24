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

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {
	
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
	public void testaConexaoComServidor() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:80");

		Projeto projeto = target.path("projeto/1").request().get(Projeto.class);
		
		Assert.assertEquals("Paulo André Moreira Cruz", projeto.getNome());
		
	}

	@Test
	public void adicionar() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost");
		
		Projeto projeto = new Projeto(2L, "João Ricardo", 2002);	
		
		Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);
		
		Response response = target.path("/projeto").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
	}

}
