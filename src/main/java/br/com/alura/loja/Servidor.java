package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		HttpServer server = inicializar();
		
		System.out.println("Servidor rodando.\nPressione qualquer tecla para pará-lo!");
		System.in.read(); 
		
		server.stop();
		
	}

	static HttpServer inicializar() {
		URI uri = URI.create("http://localhost:80/");
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja"); 
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
	
}
