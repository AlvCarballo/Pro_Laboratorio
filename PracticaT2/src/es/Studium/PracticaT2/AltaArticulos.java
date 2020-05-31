package es.Studium.PracticaT2;
//Importamos los paquetes necesarios
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
public class AltaArticulos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblDescripcionArt = new Label("Descripción Artículo:");
	TextField txtDescripcionArt = new TextField(20);
	Label lblPrecio = new Label("Precio Unidad:");
	TextField txtPrecio = new TextField(20);
	Label lblCantidad = new Label("Cantidad:");
	TextField txtCantidad = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog Alta;
	Button btnAceptarDialogo = new Button("Aceptar");
	
	AltaArticulos()
	{
		setTitle("ALTA de Artículos");//Ponemos nombre a la ventana
		setLayout(new FlowLayout());
		//Añadimos a la ventana  las etiquetas, los cuadros de texto y los botones
		add(lblDescripcionArt);
		add(txtDescripcionArt);
		add(lblPrecio);
		add(txtPrecio);
		add(lblCantidad);
		add(txtCantidad);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnAceptarDialogo.addActionListener(this);
		addWindowListener(this);
		setSize(200,250); //Ponemos el tamaño de la ventana
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
			//Selexionamos todos los txt y los borramos
			txtDescripcionArt.selectAll();
			txtDescripcionArt.setText("");
			txtPrecio.selectAll();
			txtPrecio.setText("");
			txtCantidad.selectAll();
			txtCantidad.setText("");
			txtDescripcionArt.requestFocus();
		}
		else if(objetoPulsado.equals(btnAceptar))//Acciones del boton Aceptar
		{
			// Conectar BD
			Connection conAceptar=conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Hacer el INSERT
			int respuesta = insertar(conAceptar, "articulos", txtDescripcionArt.getText(), txtPrecio.getText(), txtCantidad.getText());
			// Mostramos resultado
			if(respuesta == 0)
			{
				//Muestra una ventana indicando que el alta, se ha realizado
				String Titulo="Alta realizada";
				String Etiqueta="Alta realizada Correctamente";
				dialogo(Titulo, Etiqueta);
			}
			else
			{
				//Muestra una ventana indicando que el alta, no se ha realizado
				String Titulo="Error Alta";
				String Etiqueta="Error en ALTA de Articulo";
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
	
	private void dialogo(String titulo, String etiqueta) 
	 {
			Alta = new Dialog(this,titulo, true);
			Label lblEtiqueta = new Label(etiqueta);
			Alta.setLayout(new FlowLayout());
			Alta.setSize(300,200);
			Alta.add(lblEtiqueta);
			Alta.addWindowListener(this);
			Alta.setResizable(false);
			Alta.setLocationRelativeTo(null);
			Alta.add(btnAceptarDialogo);
			Alta.setVisible(true);
		}
	public int insertar(Connection con, String tabla, String articulo, String precio, String cantidad) 
	{
		int respuesta = 0;
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO " + tabla 
					+ " VALUES (null, '" + articulo 
					+ "', " + precio 
					+ ", " + cantidad+ ")";
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
}