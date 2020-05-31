package es.studium.Laboratorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos
{
	String BaseDatos="practica2laboratorio";
	String login="root";
	String password="Studium.2019;";
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/"+BaseDatos+"?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC&useSSL=false";
	
	Connection connection = null;
	Statement statement = null;
	Statement statement1 = null;
	Statement statement2 = null;
	ResultSet rs = null;

	public Connection conectar()
	{
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return connection;
	}
	public int altaClinica(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int modificarArticulos(Connection c, String sentencia)
	{
		int resultado = 1;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulos(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idArticulo") + "-" +
						rs.getString("nombreArticulo") + "-" +
						rs.getString("precioArticulo")+ "-" +
						rs.getString("cantidadArticulo")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulosTabla(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idArticulo") + "#" +
						rs.getString("nombreArticulo") + "#" +
						rs.getString("precioArticulo")+ "#" +
						rs.getString("cantidadArticulo")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulosChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idArticulo") + "-" +
						rs.getString("nombreArticulo") + "-" +
						rs.getString("precioArticulo")+ "-" +
						rs.getString("cantidadArticulo")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulo(Connection c, int idArticulo)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulos WHERE idArticulo="+idArticulo;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getInt("idArticulo") + "#" +
					rs.getString("nombreArticulo") + "#" +
					rs.getString("precioArticulo")+ "#" +
					rs.getString("cantidadArticulo")+ "#";
			
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int borrarArticulos(Connection c, int idArticulo)
	{
		int resultado = 1;
		try
		{
			String sentencia1 = "DELETE FROM articulostrabajos WHERE idArticuloFK3 = "+ idArticulo;
			String sentencia2 = "DELETE FROM articulos WHERE idArticulo = "+ idArticulo;
			//Crear una sentencia
			statement1 = c.createStatement();
			statement2 = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement1.executeUpdate(sentencia1))==1)
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
				resultado = 0;
				}
			}
			else
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
				resultado = 0;
				}
				else
				{
				resultado = 1;
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarClinicas(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM clinicas";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idClinica") + "-" +
						rs.getString("nifClinica") + "-" +
						rs.getString("nombreClinica")+ "-" +
						rs.getString("direccionClinica")+"-"+
						rs.getString("telefonoClinica")+"-"+
						rs.getString("emailClinica")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarClinicasChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM clinicas";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idClinica") + "-" +
						rs.getString("nifClinica") + "-" +
						rs.getString("nombreClinica") + "-" +
						rs.getString("direccionClinica") + "-" +
						rs.getString("telefonoClinica") + "-" +
						rs.getString("emailClinica") +"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarClinicasTabla(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM clinicas";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idClinica") + "#" +
						rs.getString("nifClinica") + "#" +
						rs.getString("nombreClinica")+ "#" +
						rs.getString("direccionClinica")+"#"+
						rs.getString("telefonoClinica")+"#"+
						rs.getString("emailClinica")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarClinica(Connection c, int idClinica)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM clinicas WHERE idClinica="+idClinica;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getInt("idClinica") + "#" +
					rs.getString("nifClinica") + "#" +
					rs.getString("nombreClinica")+ "#" +
					rs.getString("direccionClinica")+"#"+
					rs.getString("telefonoClinica")+"#"+
					rs.getString("emailClinica")+"#";
			
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int borrarClinicas(Connection c, int idClinica)
	{
		int resultado = 1;
		try
		{
			String sentencia1 = "DELETE FROM trabajos WHERE idClinicaFK1 = "+ idClinica;
			String sentencia2 = "DELETE FROM clinicas WHERE idClinica = "+ idClinica;
			//Crear una sentencia
			statement1 = c.createStatement();
			statement2 = c.createStatement();
			// Ejecutar la sentencia SQL
				if((statement1.executeUpdate(sentencia1))==1)
				{
					if((statement2.executeUpdate(sentencia2))==1) 
					{
					resultado = 0;
					}
				}
				else
				{
					if((statement2.executeUpdate(sentencia2))==1) 
					{
					resultado = 0;
					}
					else
					{
					resultado = 1;
					}
				}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int AltaTrabajos(Connection c, String sentencia)
	{
		int resultado = 0;
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return(resultado);
	}
	public String consultarTrabajos(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM trabajos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idTrabajo") + "-" +
						rs.getString("descripcionTrabajo") + "-" +
						rs.getString("idClinicaFK1")+ "\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajosTabla(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM trabajos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idTrabajo") + "#" +
						rs.getString("descripcionTrabajo") + "#" +
						rs.getString("idClinicaFK1")+ "#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajoDetalleTabla(Connection c, int idTrabajo)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT descripcionTrabajo, nombreArticulo, cantidadArticuloTrabajo, precioArticuloTrabajo, SubtotalArticuloTrabajo, nombreClinica FROM articulostrabajos, trabajos, articulos, clinicas WHERE idTrabajo = "+idTrabajo+" AND idArticulo = idArticuloFK3 AND idClinica = idClinicaFK1 and idTrabajo = idTrabajoFK2";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
			resultado = resultado + rs.getString("descripcionTrabajo") + "#" + rs.getString("nombreArticulo") + "#" + rs.getString("cantidadArticuloTrabajo") + "#"+ rs.getString("precioArticuloTrabajo") + "#" + rs.getString("SubtotalArticuloTrabajo")+ "#" + rs.getString("nombreClinica")+ "#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajoDetalleEditar(Connection c, int idArticuloTrabajo)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM articulostrabajos WHERE idArticuloTrabajo = "+idArticuloTrabajo;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
			resultado = resultado + rs.getString("idArticuloFK3") + "#" + rs.getString("cantidadArticuloTrabajo") + "#" + rs.getString("precioArticuloTrabajo") + "#" + rs.getString("SubtotalArticuloTrabajo")+ "#" ;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarArticulosTrabajosChoice(Connection c, int idTrabajoEditar)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT idArticuloTrabajo,idArticuloFK3,cantidadArticuloTrabajo,precioArticuloTrabajo,SubtotalArticuloTrabajo FROM articulostrabajos where idTrabajoFK2="+idTrabajoEditar;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idArticuloTrabajo") + "-" +
						rs.getString("idArticuloFK3") + "-" +
						rs.getString("cantidadArticuloTrabajo") + "-" +
						rs.getString("precioArticuloTrabajo") + "-" +
						rs.getString("SubtotalArticuloTrabajo")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajosChoice(Connection c)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT * FROM trabajos";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				resultado = resultado + rs.getInt("idTrabajo") + "-" +
						rs.getString("descripcionTrabajo") + "-" +
						rs.getString("idClinicaFK1")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajo(Connection c, int idTrabajo)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT descripcionTrabajo, nombreArticulo, cantidadArticuloTrabajo, precioArticuloTrabajo, SubtotalArticuloTrabajo, nombreClinica FROM articulostrabajos, trabajos, articulos WHERE idTrabajo = "+idTrabajo+" AND idArticulo = idArticuloFK3 AND idClinica = idClinicaFK1 ORDER BY idArticuloTrabajo";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getString("descripcionTrabajo") + "-" + rs.getString("nombreArticulo") + "-" + rs.getString("cantidadArticuloTrabajo") + "-"+ rs.getString("precioArticuloTrabajo") + "-" + rs.getString("SubtotalArticuloTrabajo")+ "-" + rs.getString("nombreClinica");
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarTrabajo2(Connection c, int idTrabajoEditar)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT idTrabajo, descripcionTrabajo, idClinicaFK1 FROM trabajos WHERE idTrabajo = "+idTrabajoEditar;
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			rs.next();
			resultado = resultado + rs.getString("idTrabajo") + "-" + rs.getString("descripcionTrabajo") + "-" + rs.getString("idClinicaFK1");
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarDetallesTrabajos(Connection c, int idFactura)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT cantidad, descripcionServicio, precioServicio, precioServicio*cantidad AS subtotal FROM lineasfactura, servicios WHERE idFacturaFK = "+idFactura+" AND idServicioFK = idServicio";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				resultado = resultado + rs.getInt("cantidad") + "-" + rs.getString("descripcionServicio") + "-" + rs.getDouble("precioServicio") + "-" + rs.getDouble("subtotal")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public String consultarDetallesTrabajo(Connection c, int idFactura)
	{
		String resultado = "";
		try
		{
			String sentencia = "SELECT cantidad, descripcionServicio, precioServicio, precioServicio*cantidad AS subtotal FROM lineasfactura, servicios WHERE idFacturaFK = "+idFactura+" AND idServicioFK = idServicio";
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			rs = statement.executeQuery(sentencia);
			while(rs.next())
			{
				resultado = resultado + rs.getInt("cantidad") + "-" + rs.getString("descripcionServicio") + "-" + rs.getDouble("precioServicio") + "-" + rs.getDouble("subtotal")+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int ultimoIdTrabajo(Connection c, String sentencia)
	{
		int resultado = 0; // INSERT incorrecto
		try
		{
			//Crear una sentencia
			statement = c.createStatement();
			//Crear un objeto ResultSet para guardar lo obtenido
			//y ejecutar la sentencia SQL
			if((statement.executeUpdate(sentencia))==1)
			{
				String sentenciaConsulta = "SELECT idTrabajo FROM trabajos ORDER BY 1 DESC LIMIT 1";
				ResultSet rs = statement.executeQuery(sentenciaConsulta);
				if(rs.next())
				{
					resultado = rs.getInt("idTrabajo");
				}
			}
			else
			{
				resultado = 0;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public int borrarTrabajos(Connection c, int idCTrabajo)
	{
		int resultado = 1;
		try
		{
			String sentencia1 = "DELETE FROM articulostrabajos WHERE idTrabajoFK2 = "+ idCTrabajo;
			String sentencia2 = "DELETE FROM trabajos WHERE idTrabajo = "+ idCTrabajo;
			//Crear una sentencia
			statement1 = c.createStatement();
			statement2 = c.createStatement();
			// Ejecutar la sentencia SQL
			if((statement1.executeUpdate(sentencia1))==1)
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
				resultado = 0;
				}
			}
			else
			{
				if((statement2.executeUpdate(sentencia2))==1) 
				{
				resultado = 0;
				}
				else
				{
				resultado = 1;
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	public void desconectar(Connection c)
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error 3-"+e.getMessage());
		}
	}
}