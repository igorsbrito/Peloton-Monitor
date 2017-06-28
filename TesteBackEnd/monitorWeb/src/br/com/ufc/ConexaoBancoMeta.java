package br.com.ufc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.ufc.model.Database;
import br.com.ufc.model.TableMeta;

@Path("/meta")
public class ConexaoBancoMeta {
	
	@GET
	@Produces("application/json; charset=utf-8")
	@Path("/tables/{ip}/{port}")
	public List<TableMeta> tables(@PathParam("ip") String ip, @PathParam("port") String port) throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/","pg_catalog", "");
		Statement statement = connection.createStatement();
		ResultSet bancos  = statement.executeQuery("select * from pg_catalog.pg_database");
		
		List<Database> databases = new ArrayList<Database>();
		while(bancos.next()){
			
			String id = bancos.getString(1);
			String name = bancos.getString(2);
			
			Database db = new Database(name, id);
			
			databases.add(db);
		}
		
		String idDefaultDatabase = "";
		for(Database db: databases){
			if(db.getName().equals("default_database")){
				idDefaultDatabase = db.getId();
			}
		}
		
		ResultSet tabelas  = statement.executeQuery("select * from pg_catalog.pg_table tb where tb.database_oid = "+idDefaultDatabase);
		
		List<TableMeta> tables = new ArrayList<TableMeta>();
		while(tabelas.next()){
			
			String id = tabelas.getString(1);
			String name = tabelas.getString(2);
			
			TableMeta tm = new TableMeta(name, id);
			
			tables.add(tm);
		}
		
		for(TableMeta tm : tables){
			ResultSet attrs  = statement.executeQuery("select column_name from pg_catalog.pg_attribute ta where ta.table_oid = "+tm.getTableId());
			
			List<String> aux = new ArrayList<String>();
			while(attrs.next()){
				
				String nameAttr = attrs.getString(1);
				aux.add(nameAttr);
			}
			
			tm.setAttrs(aux);
			
		}
		
		return tables;
	}
}
