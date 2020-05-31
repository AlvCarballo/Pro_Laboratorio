package es.Studium.PracticaT2;
//Importamos los paquetes necesarios
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ModificaClinicas extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblEdificioFactura = new Label("Articulo a modificar:");
	Choice choArticulos = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog seguro;
	Button btnSi= new Button("Si");
	Button btnNo= new Button("No");
	Frame modificarCliente;
	Button btnAceptarCambios;
	Button btnCancelarCambios;
	TextField txtIdClinica;
	TextField txtNifClinica;
	TextField txtNombreClinica;
	TextField txtDireccionClinica;
	TextField txtTelefonoClinica;
	TextField txtEmailClinica;
	Dialog Alta;
	Button btnAceptarDialogo = new Button("Aceptar");
	
	ModificaClinicas()
	{
		setTitle("Modificar Clinicas");;//Ponemos nombre a la ventana
		setLayout(new FlowLayout());
		//Añadimos a la ventana  las etiquetas, los cuadros de texto y los botones
		// Montar el Choice
		choArticulos.add("Seleccionar uno...");
		Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar a la base de datos
		// Sacar los datos de la tabla edificios
		// Rellenar el Choice
		String sqlSelect = "SELECT * FROM Clinicas";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				choArticulos.add(rs.getInt("idClinica")+
						"-"+rs.getString("nifClinica")+
						", "+rs.getString("nombreClinica")+
						", "+rs.getString("direccionClinica")+
						", "+rs.getString("telefonoClinica")+
						", "+rs.getString("emailClinica"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR:al rellenar el despegable"+"\n"+ex);
			ex.printStackTrace();
		}
		// Cerrar la conexión
		conexiones.desconectar(con);
		add(choArticulos);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnAceptarDialogo.addActionListener(this);
		btnSi.addActionListener(this);
		btnNo.addActionListener(this);
		addWindowListener(this);
		setSize(500,100);//Ponemos el tamaño de la ventana
		setResizable(false);//Indicamos que no se pueda editar el tamaño
		setLocationRelativeTo(null);//Indicamos la localizacion inicial de la ventana
		setVisible(true);//Hacemos visible la ventana
	}
	//Indicamos las acciones a realizar
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object objetoPulsado = e.getSource();
		if(objetoPulsado.equals(btnLimpiar))//Acciones del boton Limpiar
		{
			choArticulos.select(0);
		}
		else if(objetoPulsado.equals(btnAceptar))//Acciones del boton Aceptar
		{
			// Sacar el id del elemento elegido
			int id = Integer.parseInt(choArticulos.getSelectedItem().split("-")[0]);
			// Crear un Frame igual que el ALTA
			modificarCliente = new Frame("Modificar Articulo");
			modificarCliente.setLayout(new FlowLayout());
			Label lblIdClinica = new Label("IdClinica:");
			Label lblNifClinica = new Label("NifClinica:");
			Label lblNombreClinica = new Label("NombreClinica:");
			Label lblDireccionClinica = new Label("DireccionClinica:");
			Label lblTelefonoClinica = new Label("TelefonoClinica:");
			Label lblEmailClinica = new Label("EmailClinica:");
			txtIdClinica = new TextField(20);
			txtNifClinica = new TextField(20);
			txtNombreClinica = new TextField(20);
			txtDireccionClinica = new TextField(20);
			txtTelefonoClinica = new TextField(20);
			txtEmailClinica = new TextField(20);
			btnAceptarCambios = new Button("Aceptar");
			btnCancelarCambios = new Button("Cancelar");
			modificarCliente.add(lblIdClinica);
			txtIdClinica.setEnabled(false);
			modificarCliente.add(txtIdClinica);
			modificarCliente.add(lblNifClinica);
			modificarCliente.add(txtNifClinica);
			modificarCliente.add(lblNombreClinica);
			modificarCliente.add(txtNombreClinica);
			modificarCliente.add(lblDireccionClinica);
			modificarCliente.add(txtDireccionClinica);
			modificarCliente.add(lblTelefonoClinica);
			modificarCliente.add(txtTelefonoClinica);
			modificarCliente.add(lblEmailClinica);
			modificarCliente.add(txtEmailClinica);
			btnAceptarCambios.addActionListener(this);
			btnCancelarCambios.addActionListener(this);
			modificarCliente.add(btnAceptarCambios);
			modificarCliente.add(btnCancelarCambios);
			// Pero relleno-->
			// Conectar a la base de datos
			Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Seleccionar los datos del elemento
			mostrarDatos(con, id, txtIdClinica, txtNifClinica, txtNombreClinica, txtDireccionClinica, txtTelefonoClinica, txtEmailClinica);
			// seleccionado
			// Mostrarlos
			conexiones.desconectar(con);
			modificarCliente.addWindowListener(this);
			modificarCliente.setResizable(false);
			modificarCliente.setSize(200,450);
			modificarCliente.setLocationRelativeTo(null);
			modificarCliente.setVisible(true);
		}
		else if(objetoPulsado.equals(btnNo))
		{
			seguro.setVisible(false);
		}
		else if(objetoPulsado.equals(btnCancelarCambios))
		{
			modificarCliente.setVisible(false);
		}
		else if(objetoPulsado.equals(btnAceptarCambios))
		{
			//Muestra una ventana indicando que el alta, se ha realizado correctamente
			seguro = new Dialog(this,"Esta seguro de que desea modificar", true);
			Label lblEtiqueta = new Label("Esta seguro de que desea modificar estos campos");
			seguro.setLayout(new FlowLayout());
			seguro.setSize(420,100);
			seguro.add(lblEtiqueta);
			seguro.addWindowListener(this);
			seguro.setResizable(false);
			seguro.setLocationRelativeTo(null);
			seguro.add(btnSi);
			seguro.add(btnNo);
			seguro.setVisible(true);
		}
		else if(objetoPulsado.equals(btnSi))
		{
			seguro.setVisible(false);
			int respuesta = 0;
			int id = Integer.parseInt(txtIdClinica.getText());
			String nClinica = txtNifClinica.getText();
			String nmClinica = txtNombreClinica.getText();
			String dClinica = txtDireccionClinica.getText();
			String tClinica = txtTelefonoClinica.getText();
			String eClinica = txtEmailClinica.getText();
			
			// Conectar a la base de datos
			Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Ejecutar el UPDATE
			String sql ="UPDATE clinicas SET nifClinica = '"+nClinica+"', nombreClinica = '"+nmClinica+"', direccionClinica = '"+dClinica+"', telefonoClinica = '"+tClinica+"', emailClinica = '"+eClinica+"' WHERE idClinica="+id;
			try {
				// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERROR:al insertar los cambios"+"\n"+ex);
				ex.printStackTrace();
				respuesta = 1;
			}
			// Cerrar la conexión
			conexiones.desconectar(con);
			modificarCliente.setVisible(false);
			
			if(respuesta == 0)
			{
				//Muestra una ventana indicando que el alta, se ha realizado correctamente
				String Titulo="Modificacion realizada";
				String Etiqueta="Modificacion realizada Correctamente";
				dialogo(Etiqueta, Titulo);
			}
			else
			{
				//Muestra una ventana indicando que el alta, no se ha realizado
				String Titulo="Modificacion Erroneaa";
				String Etiqueta="Error en Modificacion";
				dialogo(Etiqueta, Titulo);
			}
		}
		else if(objetoPulsado.equals(btnAceptarDialogo))//Acciones del boton aceptar del dialogo
		{
			Alta.setVisible(false);
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
		else if(modificarCliente.isActive())
		{
			modificarCliente.setVisible(false);
		}
		else if(Alta.isActive())
		{
			Alta.setVisible(false);
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
		String sql = "DELETE FROM Clinicas WHERE idClinica = " + idClinica;
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
	private void dialogo(String titulo, String etiqueta) 
 {
		Alta = new Dialog(this,titulo, true);
		Label lblEtiqueta = new Label(etiqueta);
		Alta.setLayout(new FlowLayout());
		Alta.setSize(420,100);
		Alta.add(lblEtiqueta);
		Alta.addWindowListener(this);
		Alta.setResizable(false);
		Alta.setLocationRelativeTo(null);
		Alta.add(btnAceptarDialogo);
		Alta.setVisible(true);
	}
	public void mostrarDatos(Connection con, int IdClinica0, TextField id, TextField nifClinica0, TextField nombreClinica0, TextField direccionClinica0,TextField telefonoClinica0,TextField emailClinica0 )
	{
		String sql = "SELECT * FROM Clinicas WHERE idClinica = "+IdClinica0;
		try 
		{ 
			id.setText(IdClinica0+"");
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next())
			{
				String n = rs.getString("nifClinica");
				nifClinica0.setText(n);
				String nm = rs.getString("nombreClinica");
				nombreClinica0.setText(nm);
				String d = rs.getString("direccionClinica");
				direccionClinica0.setText(d);
				String t = rs.getString("telefonoClinica");
				telefonoClinica0.setText(t);
				String e = rs.getString("emailClinica");
				emailClinica0.setText(e);
			}
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al mostrar"+"\n"+ex);
			ex.printStackTrace();
		}
	}
}
