package com.fiap.exemplos.ws;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;


@WebService
public class ProdutoWeb {

	static private List<Produto> produtos;
	
	@Resource
	WebServiceContext wsctx;
	
	static{
		produtos = new ArrayList<Produto>();
		
		Produto a = new Produto();
		a.setCodigo(1);
		a.setDescricao("Camisa Cavalera Amarela");
		a.setValor(30.3);
		produtos.add(a);
		
		Produto b = new Produto();
		b.setCodigo(2);
		b.setDescricao("Camisa Infatil Vermelha");
		b.setValor(40.5);
		produtos.add(b);
		
		Produto c = new Produto();
		c.setCodigo(3);
		c.setDescricao("Bermuda Tommy Hilfiger Lisa Azul P");
		c.setValor(30.5);
		produtos.add(c);
		
		Produto d = new Produto();
		c.setCodigo(4);
		c.setDescricao("Bermuda Generica Vermelha G");
		c.setValor(20.5);
		produtos.add(c);
		
		
	}	
	
	
	@WebMethod(exclude=true)
	public boolean autenticado(String usuario, String senha){
		if (usuario.equals("loja") && senha.equals("123")) return true;
		else return false;
	}

	@WebMethod
	public String novo(@WebParam(name="produto", header=false) Produto a,
			@WebParam(name="Username", header=true) String usuario,
			@WebParam(name="Password", header=true) String senha		
			) {
		if (autenticado(usuario,senha)){
			produtos.add(a);
			return "Produto cadastrado com sucesso";
		}else{
			return "Falha na autenticação";
		}
	}

	@WebMethod
	public List<Produto> listar(
			@WebParam(name="Username", header=true) String usuario,
			@WebParam(name="Password", header=true) String senha) throws Exception {
		if (autenticado(usuario,senha)){
			return produtos;
		} else{
			throw new Exception("Falha na autenticação");
		}
	}
}
