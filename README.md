"# jax-rs" 


Serviços Web REST e Addressability

Bem vindo ao nosso curso de Web Services Rest com JAX-RS e Jersey. O que veremos neste curso é como fazer dois programs distintos se comunicarem na Internet. Acontece que hoje em dia é muito comum que nosso sistema esteja na internet e não viva isolado: ele quer se comunicar com outros sistemas, sistemas que foram escritos por outros desenvolvedores, em outras empresas, em outros lugares do mundo, em outras linguagens de programação.
Quero escrever para mim um código que possa se comunicar com todos esses sistemas. Para fazer isso, uma das soluções que existe no mercado é o que chamamos de serviços baseados na web, os web services. Veremos nesse curso os web services que chamamos de REST.

Para começar imagine que temos um sistema que suporta a compra de produtos através de um carrinho: ele suporta adicionar produtos no carrinho, remover produtos etc. Provavelmente eu tenho uma página que me mostra o carrinho que estou comprando. Vou acessar aqui uma página que já mostra para mim um carrinho de compra. Acessamos o link <a href="http://www.mocky.io/v2/52aaf5bbee7ba8c60329fb7b"></a> e temos um carrinho de compra:

<img src="http://caelum-online-public.s3.amazonaws.com/JAX-RS/CarrinhoHtml.png">

Já conhecemos um carrinho de compra html. Como que sei que é html? Clico da direita e escolho View page source, o resultado sendo:

<code>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
    <h1>Carrinho</h1>
    <h2>Produtos</h2>
    <table>
		<tr>
			<td>ID</td>
			<td>Quantidade</td>
			<td>Nome</td>
			<td>Preço</td>
		<tr>
			<td>6237</td>
            <td>1</td>
            <td>Videogame 4</td>
            <td>4000</td>
        </tr>
        <tr>
            <td>3467</td>
            <td>2</td>
            <td>Jogo de esporte</td>
            <td>120</td>
        </tr>
</table>
<h2>Total: R$ 4120</h2>
<h2>Entrega</h2>
	Rua Vergueiro 3185, 8 andar<br/>
	São Paulo
</html>
</code>

Então quando faço uma requisição para o servidor, usando o link acima, o programa navegador (browser, o cliente) faz uma requisição para um servidor (outro programa), que nos devolve uma representação do carrinho. Cada página, cada link, ou melhor, cada URI que eu acesso representa um carrinho diferente contido no meu servidor. Cada carrinho que eu tenho tem uma URI diferente. Óbvio, o carrinho em si não é este HTML, o carrinho em si não é a URI, ele é algo que está no servidor, provavelmente os dados que estão no banco de dados do servidor. O html que é enviado de um lado para o outro, do servidor para o cliente, é uma representação do carrinho - uma representation.

Quando eu acesso a URI que identifica o carrinho, ele vem com a representação do carrinho para mim.

Mas vou ficar escrevendo programas que leêm html e entender a tabela? Qual campo é qual campo? Vou ter que fazer o scrapping do html para entender os dados do carrinho? Trabalhoso né? As vezes o servidor é um programa antigo que só suporta html e temos que escrever clientes que fazem esse scrap. Mas na prática, hoje em dia, os servidores já se preparam disponibilizando o dado em outro formato, e não html. Algum outro formato, mais fácil de nossa máquina entender.

Vou acessar agora uma outra URI, <a href="http://www.mocky.io/v2/52aaf5deee7ba8c70329fb7d"></a> uma URI que representa outro carrinho, e repara que o resultado retornado é uma representação em XML.

<img src="http://caelum-online-public.s3.amazonaws.com/JAX-RS/Screenshot%202014-04-22%2016.29.57.png">

Os dados da representação são então uma representação do nosso carrinho, e temos lá dois produtos diferentes, com quantidades diferentes. Tenho um XML enviado de um lado para o outro, temos uma representação feita via XML. E tudo isso é muito importante quando falamos de REST.

Temos uma URI que identifica o nosso carrinho, o nosso recurso que está no servidor. E através dessa URI recebo a representação de nosso carrinho, que poderia ser json, xml, html, tem diversos formatos - ou mais especificamente diferentes media types, que podemos trabalhar.

Cada URI representa um carrinho, um recurso diferente, seja ele um produto, usuário, boleto, compra etc. Vamos então agora fazer um primeiro cliente que acessa essa URI, acessa essa representação, e verifica os dados que foram retornados? Para fazer isso vamos baixar os jars do JAX-RS e de sua implementação, o Jersey. Já temos um projeto configurado com esse JAR, baixe o arquivo zip a seguir: <a href="https://github.com/alura-cursos/webservices-rest-com-jaxrs-e-jersey/raw/master/loja.zip"></a> ou faça um fork e clone do projeto do github <a href="https://github.com/alura-cursos/webservices-rest-com-jaxrs-e-jersey"></a>.

Descompacte o arquivo e temos o diretório chamado loja. Vamos importar o projeto dentro do Eclipse. Vamos no Eclipse e escolhemos 'File, Import', 'Existing Projects into Workspace', escolhemos o root directory como sendo o diretório 'loja' que foi descompactado. Ele detecta o projeto e damos 'Finish'. Perfeito, o projeto é importado.

O que tenho aqui dentro do projeto? Tenho o diretório src/main/java com meu código java e o src/test/java para colocar futuros testes que escreveremos daqui a pouco. A primeira coisa que queremos fazer é criar um cliente, vamos então criar um teste que testa acessar essa URI e verifica que o XML resultante é o que esperamos, isto é, faz a requisição HTTP e confere que a representação que o servidor devolve é o que esperamos. Vamos lá? 'File, new, new class', o nome da classe será 'ClienteTest' (em inglês a palavra Test), no nosso pacote br.com.alura.loja. Dentro dessa classe colocaremos o primeiro método de teste:

<code>
public void testaQueAConexaoComOServidorFunciona() {

}
</code>

Colocamos uma anotação de teste para dizer que isto é um teste:

<code>
public class ClienteTest {

	@Test
	public void testaQueAConexaoComOServidorFunciona() {

	}

}
</code>

Uso o CTRL+SHIFT+O para importar a anotação. Dentro desse código de teste queremos um cliente http para acessar o servidor, portanto criamos um cliente novo:

<code>
Client client = ClientBuilder.newClient();
</code>


