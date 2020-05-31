package es.Studium.PracticaT2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexiones {
	public Connection conectar(String bd, String usuario, String clave)
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/"+bd+"?autoReconnect=true&useSSL=false";
		String user = usuario;
		String password = clave;
		Connection con = null;
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD empresa
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Conectado a la base de datos");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR:La dirección no es válida o el usuario y clave"+"\n"+ex);
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "ERROR:en la conexion"+"\n"+cnfe);
			cnfe.printStackTrace();
		}
		return con;
	}
	
	public void desconectar(Connection con)
	{
		try
		{
			con.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR: En la desconexion"+"\n"+e);
			e.printStackTrace();
		}
	}
	
}
