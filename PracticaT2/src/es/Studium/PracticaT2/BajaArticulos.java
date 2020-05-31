package es.Studium.PracticaT2;
//Importamos los paquetes necesarios
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BajaArticulos extends Frame implements WindowListener, ActionListener
{
	//Preparamos la conexion y desconexion de la base de datos
		Conexiones conexiones= new Conexiones();
		String BaseDatos="practica2laboratorio";
		String UsuarioDB="root";
		String ClaveBD="Studium.2019;";
	private static final long serialVersionUID = 1L;
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblEdificioFactura = new Label("Articulo a borrar:");
	Choice choArticulo = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog seguro;
	Button btnSi;
	Button btnNo;

	BajaArticulos()
	{
		setTitle("Baja de Articulo");//Ponemos nombre a la ventana
		setLayout(new FlowLayout());
		//Añadimos a la ventana  las etiquetas, los cuadros de texto y los botones
		// Montar el Choice
		choArticulo.add("Seleccionar uno...");
		// Conectar a la base de datos
		Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
		// Sacar los datos de la tabla edificios
		// Rellenar el Choice
		String sqlSelect = "SELECT * FROM Articulos";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				choArticulo.add(rs.getInt("idArticulo")+
						"-"+rs.getString("nombreArticulo")+
						", "+rs.getString("precioArticulo")+
						", "+rs.getString("cantidadArticulo"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: En la consultar"+"\n"+ex);
			ex.printStackTrace();
		}
		// Cerrar la conexión
		conexiones.desconectar(con);
		add(choArticulo);
		add(btnAceptar);
		add(btnLimpiar);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		addWindowListener(this);
		setSize(200,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object objetoPulsado = e.getSource();
		if(objetoPulsado.equals(btnLimpiar))
		{
			choArticulo.select(0);
		}
		else if(objetoPulsado.equals(btnAceptar))
		{
			seguro = new Dialog(this,"¿Seguro?", true);
			btnSi = new Button("Sí");
			btnNo = new Button("No");
			Label lblEtiqueta = new Label("¿Está seguro de eliminar?");
			seguro.setLayout(new FlowLayout());
			seguro.setSize(200,100);
			btnSi.addActionListener(this);
			btnNo.addActionListener(this);
			seguro.add(lblEtiqueta);
			seguro.add(btnSi);
			seguro.add(btnNo);
			seguro.addWindowListener(this);
			seguro.setResizable(false);
			seguro.setLocationRelativeTo(null);
			seguro.setVisible(true);
		}
		else if(objetoPulsado.equals(btnNo))
		{
			seguro.setVisible(false);
		}
		else if(objetoPulsado.equals(btnSi))
		{
			// Conectar a BD
			Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD); 
			// Borrar
			String[] Edificio=choArticulo.getSelectedItem().split("-");
			int respuesta = borrar(con, Integer.parseInt(Edificio[0]));
			// Mostramos resultado
			if(respuesta == 0)
			{
				System.out.println("Borrado de Articulo correcto");
			}
			else
			{
				System.out.println("Error en Borrado de Articulo");
			}
			// Actualizar el Choice
			choArticulo.removeAll();
			choArticulo.add("Seleccionar uno...");
			String sqlSelect = "SELECT * FROM Articulos";
			try {
				// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sqlSelect);
				while (rs.next()) 
				{
					choArticulo.add(rs.getInt("idArticulo")+
							"-"+rs.getString("nombreArticulo")+
							", "+rs.getString("precioArticulo")+
							", "+rs.getString("cantidadArticulo"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERROR: En la consultar"+"\n"+ex);
				ex.printStackTrace();
			}
			// Desconectar
			conexiones.desconectar(con);
			seguro.setVisible(false);
		}
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		if(this.isActive())
		{
			setVisible(false);
		}
		else
		{
			seguro.setVisible(false);
		}
	}

	@Override
	public void windowActivated(WindowEvent e){}// TODO Auto-generated method stub
	@Override
	public void windowClosed(WindowEvent e){}// TODO Auto-generated method stub
	@Override
	public void windowDeactivated(WindowEvent e){}// TODO Auto-generated method stub
	@Override
	public void windowDeiconified(WindowEvent e){}// TODO Auto-generated method stub
	@Override
	public void windowIconified(WindowEvent e){}// TODO Auto-generated method stub
	@Override
	public void windowOpened(WindowEvent e){}// TODO Auto-generated method stub
	public int borrar(Connection con, int idArticulo)
	{
		int respuesta = 0;
		String sql = "DELETE FROM articulos WHERE idArticulo = " + idArticulo;
		System.out.println(sql);
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			sta.executeUpdate(sql);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al hacer un Delete"+"\n"+ex);
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;
	}
}
