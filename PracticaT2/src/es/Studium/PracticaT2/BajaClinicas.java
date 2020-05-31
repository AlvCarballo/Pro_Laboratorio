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

public class BajaClinicas extends Frame implements WindowListener, ActionListener
{
	//Preparamos la conexion y desconexion de la base de datos
		Conexiones conexiones= new Conexiones();
		String BaseDatos="practica2laboratorio";
		String UsuarioDB="root";
		String ClaveBD="Studium.2019;";
	private static final long serialVersionUID = 1L;
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblEdificioFactura = new Label("Articulo a borrar:");
	Choice choClinica = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog seguro;
	Button btnSi;
	Button btnNo;

	BajaClinicas()
	{
		setTitle("Baja de Clinicas");//Ponemos nombre a la ventana
		setLayout(new FlowLayout());
		//Añadimos a la ventana  las etiquetas, los cuadros de texto y los botones
		// Montar el Choice
		choClinica.add("Seleccionar uno...");
		// Conectar a la base de datos
		Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
		// Sacar los datos de la tabla edificios
		// Rellenar el Choice
		String sqlSelect = "SELECT * FROM clinicas";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				choClinica.add(rs.getInt("idClinica")+
						"-"+rs.getString("nifClinica")+
						", "+rs.getString("nombreClinica")+
						", "+rs.getString("direccionClinica")+
						", "+rs.getString("telefonoClinica")+
						", "+rs.getString("emailClinica"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR: En la consulta"+"\n"+ex);
			ex.printStackTrace();
		}
		// Cerrar la conexión
		conexiones.desconectar(con);
		add(choClinica);
		add(btnAceptar);
		add(btnLimpiar);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		addWindowListener(this);
		setSize(600,200);
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
			choClinica.select(0);
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
			String[] Clinicas=choClinica.getSelectedItem().split("-");
			int respuesta = borrar(con, Integer.parseInt(Clinicas[0]));
			// Mostramos resultado
			if(respuesta == 0)
			{
				System.out.println("Borrado de Clinica correcto");
			}
			else
			{
				System.out.println("Error en Borrado de Clinica");
			}
			// Actualizar el Choice
			choClinica.removeAll();
			choClinica.add("Seleccionar uno...");
			String sqlSelect = "SELECT * FROM clinicas";
			try {
				// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sqlSelect);
				while (rs.next()) 
				{
					choClinica.add(rs.getInt("idClinica")+
							"-"+rs.getString("nifClinica")+
							", "+rs.getString("nombreClinica")+
							", "+rs.getString("direccionClinica")+
							", "+rs.getString("telefonoClinica")+
							", "+rs.getString("emailClinica"));
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
	public int borrar(Connection con, int idClinica)
	{
		int respuesta = 0;
		String sql = "DELETE FROM clinicas WHERE idClinica = " + idClinica;
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
