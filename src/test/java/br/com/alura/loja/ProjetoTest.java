package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

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
		String content = target.path("projeto").request().get(String.class);
		Projeto projeto = (Projeto) new XStream().fromXML(content);
		Assert.assertEquals("Paulo André Moreira Cruz", projeto.getNome());
		
	}

}
