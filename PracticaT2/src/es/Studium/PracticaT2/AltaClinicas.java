package es.Studium.PracticaT2;
//Importamos los paquetes
import java.awt.Button;
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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class AltaClinicas extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblNif = new Label("NIF/CIF:");
	TextField txtNif = new TextField(20);
	Label lblNombre = new Label("Nombre:");
	TextField txtNombre = new TextField(20);
	Label lblDireccion = new Label("Dirección:");
	TextField txtDireccion = new TextField(20);
	Label lblTelefono = new Label("Teléfono:");
	TextField txtTelefono = new TextField(20);
	Label lblEmail = new Label("E-mail:");
	TextField txtEmail = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog Alta;
	Button btnAceptarDialogo = new Button("Aceptar");

	AltaClinicas()
	{
		setTitle("ALTA de Clínicas");//Titulo de la ventana
		setLayout(new FlowLayout());
		//Añadimos etiquetas, cuadros de texto, botones ...
		add(lblNif);
		add(txtNif);
		add(lblNombre);
		add(txtNombre);
		add(lblDireccion);
		add(txtDireccion);
		add(lblTelefono);
		add(txtTelefono);
		add(lblEmail);
		add(txtEmail);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones.
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnAceptarDialogo.addActionListener(this);
		addWindowListener(this);
		setSize(280,200);//Ponemos el tamaño de la ventana
		setResizable(false);//Indicamos que no se puede redimencionar
		setLocationRelativeTo(null);//Indicamos que se ponga en el centro de la pantalla
		setVisible(true);//Indicamos que sea visible
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object objetoPulsado = e.getSource();
		if(objetoPulsado.equals(btnLimpiar))
		{
			txtNif.selectAll();
			txtNif.setText("");
			txtNombre.selectAll();
			txtNombre.setText("");
			txtDireccion.selectAll();
			txtDireccion.setText("");
			txtTelefono.selectAll();
			txtTelefono.setText("");
			txtEmail.selectAll();
			txtEmail.setText("");
			txtNif.requestFocus();
		}
		else if(objetoPulsado.equals(btnAceptar))
		{
			// Conectar BD
			Connection conAceptar=conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Hacer el INSERT
			int respuesta = insertar (conAceptar, "clinicas", txtNif.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtEmail.getText());
			// Mostramos resultado
			if(respuesta == 0)
			{
				//Muestra una ventana indicando que el alta, se ha realizado correctamente
				Alta = new Dialog(this,"Alta realizada", true);
				String Titulo="Alta realizada";
				String Etiqueta="Alta realizada Correctamente";
				dialogo(Titulo, Etiqueta);
			}
			else
			{
				//Muestra una ventana indicando que el alta, no se ha realizado
				String Titulo="Error Alta";
				String Etiqueta="Error en ALTA de clinica";
				dialogo(Titulo, Etiqueta);
			}
			// Desconectar de la base
		
			conexiones.desconectar(conAceptar);
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
		else
		{
			Alta.setVisible(false);
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
	
	public int insertar(Connection con, String tabla, String dNI, String nombre, String direccion,String telefono,String email) 
	{
		int respuesta = 0;
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO " + tabla 
					+ " VALUES (null, '" + dNI 
					+ "', '" + nombre 
					+ "', '" + direccion
					+ "', " + telefono
					+ ", '" + email+ "')";
			System.out.println(cadenaSQL);
			sta.executeUpdate(cadenaSQL);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al hacer un Insert"+"\n"+ex);
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
			Alta.setSize(450,200);
			Alta.add(lblEtiqueta);
			Alta.addWindowListener(this);
			Alta.setResizable(false);
			Alta.setLocationRelativeTo(null);
			Alta.add(btnAceptarDialogo);
			Alta.setVisible(true);
		}
}