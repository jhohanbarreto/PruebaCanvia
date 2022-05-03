package ControllerFacturacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

	DBConfiguracion DBConfiguracion = new DBConfiguracion();
	private String usuario = DBConfiguracion.getUsuario();
	private String clave = DBConfiguracion.getClave();
	private String url = DBConfiguracion.getUrl();

	private static String controlador = "com.mysql.cj.jdbc.Driver";

	private Connection cnx;

	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}
		static {
			try {
				Class.forName(controlador);
			}catch (ClassNotFoundException e){
				
				System.out.println("No se pudo conectar");
				e.printStackTrace();
				
			}
		}

	public void Conectar() throws Exception {

		try {
			

			this.cnx = DriverManager.getConnection(url, usuario, clave);

			if (this.cnx != null) {
				
				System.out.println("Conexion exitosa");

			} else {
				System.out.println("Error al conectarse");
			}
		} catch (SQLException e) {

			throw e;

		}

	}

	public void Desconectar(){
		try {
			//si esixte la conexion 
			if(this.cnx != null) {
				if(this.cnx.isClosed() != true) {
					//cerramos la conexion
					this.cnx.close();
					System.out.println("Conexion cerrada");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String dato ="insert into(c1, c2);";
		int lenght = dato.length();
		int position = dato.indexOf(";");
		System.out.println(dato);
		System.out.println(lenght);
		System.out.println(position);
		System.out.println(dato.substring(1, position));
	}
}