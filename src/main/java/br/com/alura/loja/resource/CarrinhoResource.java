package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") Long id) {

		return new CarrinhoDAO().busca(id);

	}

	// @POST
	// @Produces(MediaType.APPLICATION_XML)
	// public String adiciona(String content) {
	//
	// Carrinho carrinho = (Carrinho) new XStream().fromXML(content);
	// new CarrinhoDAO().adiciona(carrinho);
	// return "<status>sucesso</status>";
	// }

	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		
		return Response.created(uri).build();
	}
	
	@DELETE
	@Path("{id}/produto/{produtoId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response remove(@PathParam("{id}") long id, @PathParam("{produtoId}") long produtoId) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		
		return Response.ok().build();
	}

	@PUT
	@Path("{id}/produto/{produtoId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response alterar(Produto produto, @PathParam("{id}") long id, @PathParam("{produtoId}") long produtoId) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.troca(produto);
		
		return Response.ok().build();
	}

	@PUT
	@Path("{id}/produto/{produtoId}/quantidade")
	@Produces(MediaType.APPLICATION_XML)
	public Response alterarQuantidade(Produto produto, @PathParam("{id}") long id, @PathParam("{produtoId}") long produtoId) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.trocaQuantidade(produto);
		
		return Response.ok().build();
	}	
}
