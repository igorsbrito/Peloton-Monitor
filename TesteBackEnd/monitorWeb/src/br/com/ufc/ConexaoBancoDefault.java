package br.com.ufc;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/banco")
public class ConexaoBancoDefault {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/consulta")
	public String consulta( ){
		return "Hello World";
	}
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/teste2")
	public List<String> listar(){
		
		List<String> lista = new ArrayList<String>();
		
		lista.add("Elemento 1");
		lista.add("Elemento 2");
		
		return lista;
		
	}
	
	@GET
	@Produces("text/html")
	@Path("/pagina")
	public Viewable view() {
		System.out.println("teste");
		return new Viewable("/index.html");
		
	}
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/employees")
	public List<List<String>> queryEmployees() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","default_database", "");
		Statement statement = connection.createStatement();
		ResultSet resultSet  = statement.executeQuery("select * from"
				+ " employees");
		
		List<List<String>> result = new ArrayList<List<String>>();
		System.out.println("Entrou aqui!!==================");
		while(resultSet.next()){
			List<String> list = new ArrayList<String>();
			
			String id = resultSet.getString(1);
			list.add(id);
			String name = resultSet.getString(2);
			list.add(name);
			String sal = resultSet.getString(3);
			list.add(sal);
			String dId = resultSet.getString(4);
			list.add(dId);
			
			result.add(list);
			
		}
		
		return result;
			
	}
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/departments")
	public List<List<String>> queryDepartments() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","default_database", "");
		Statement statement = connection.createStatement();
		ResultSet resultSet  = statement.executeQuery("select * from departments");
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		while(resultSet.next()){
			List<String> list = new ArrayList<String>();
			
			String id = resultSet.getString(1);
			list.add(id);
			String name = resultSet.getString(2);
			list.add(name);
			String sal = resultSet.getString(3);
			list.add(sal);
			
			result.add(list);
			
		}
		
		return result;
		
		
	}
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/clients")
	public List<List<String>> queryClients() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","default_database", "");
		Statement statement = connection.createStatement();
		ResultSet resultSet  = statement.executeQuery("select * from clients");
		
		List<List<String>> result = new ArrayList<List<String>>();
		System.out.println("Entrou aqui!!==================");
		while(resultSet.next()){
			List<String> list = new ArrayList<String>();
			
			String id = resultSet.getString(1);
			list.add(id);
			String name = resultSet.getString(2);
			list.add(name);
			String sal = resultSet.getString(3);
			list.add(sal);
			
		
		
			result.add(list);
			
		}
		
		return result;
		
		
	}
}
