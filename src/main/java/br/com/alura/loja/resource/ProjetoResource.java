package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.CarrinhoDAO;
import br.com.alura.loja.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

@Path("projeto")
public class ProjetoResource {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") Long id) {
		
		return new ProjetoDAO().busca(1L).toXML();
		
	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_XML)
//	public String adiciona(String content) {
//		
//		Projeto projeto = (Projeto) new XStream().fromXML(content);
//		new ProjetoDAO().adiciona(projeto);
//		
//		return "<status>sucesso</status>";
//		
//	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
    public Response adiciona(String xml) {
       Projeto projeto = (Projeto) new XStream().fromXML(xml);
       new ProjetoDAO().adiciona(projeto);
       URI uri = URI.create("/projeto/" + projeto.getId());
       return Response.created(uri).build();
   }
}
