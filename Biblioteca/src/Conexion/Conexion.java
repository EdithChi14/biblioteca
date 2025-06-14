package Conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Conexion {
	private static String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/bd_biblioteca2?useSSL=false&allowPublicKeyRetrieval=true";
	private static String USUARIO = "root";
	private static String CLAVE = "";
	public static Statement sentencia; 
	public ResultSet resultado; 
	
	static {
		try 
		{	
			Class.forName(CONTROLADOR);
		}catch (ClassNotFoundException e)
		{
			  System.out.println("Error el cargar el controlador");
				e.printStackTrace(); 
		}
	}
	public static  Connection getconectar(){
		Connection conexion = null;
		try {
		
			conexion = DriverManager.getConnection(URL,USUARIO,CLAVE );
			sentencia = (Statement) conexion.createStatement();
			System.out.println("Conexión OK");
			
		
		} catch (SQLException e){
		
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		return conexion;
	}
	
	
	
	
	
}

