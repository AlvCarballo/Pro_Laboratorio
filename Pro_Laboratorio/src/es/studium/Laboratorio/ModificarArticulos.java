package es.studium.Laboratorio;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class ModificarArticulos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
		Label lblDescripcionArt = new Label("Descripcion Artículo:");
		TextField txtDescripcionArt = new TextField(20);
		Label lblPrecio = new Label("Precio Unidad:");
		TextField txtPrecio = new TextField(20);
		Label lblCantidad = new Label("Cantidad:");
		TextField txtCantidad = new TextField(20);
		Button btnAceptar = new Button("Aceptar");
		Button btnLimpiar = new Button("Limpiar");
		
		BaseDatos bd = new BaseDatos();
		Connection conexion = null;
		String[] cadena;
		Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
		Label mensaje = new Label("");
		int idArticuloEditar;
		String NombreUsuario="";
		Utilidades utilidad = new Utilidades();
		
	public ModificarArticulos(String UserName, int idArticuloEditar)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Modificar Articulo");
		this.idArticuloEditar = idArticuloEditar; 
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarArticulo(conexion, idArticuloEditar)).split("#");
		// cadena[0] = idArticulo
		// cadena[1] = nombreArticulo
		// cadena[2] = precioArticulo
		// cadena[3] = cantidadArticulo
		setTitle("Modificar Cliente");
		setLayout(new GridLayout(5,2));
		
		add(lblDescripcionArt);
		txtDescripcionArt.setText(cadena[1]);
		add(txtDescripcionArt);
		add(lblPrecio);
		txtPrecio.setText(cadena[2]);
		add(txtPrecio);
		add(lblCantidad);
		txtCantidad.setText(cadena[3]);
		add(txtCantidad);
		
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		add(btnAceptar);
		add(btnLimpiar);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			txtDescripcionArt.selectAll();
			txtDescripcionArt.setText("");
			txtPrecio.selectAll();
			txtPrecio.setText("");
			txtCantidad.selectAll();
			txtCantidad.setText("");
			utilidad.registrarLog(NombreUsuario,"Limpiando Modificar Articulo");
		}
		else // btnAceptar
		{
			// Hacer UPDATE
			String sentencia = "UPDATE articulos SET nombreArticulo = '"+txtDescripcionArt.getText()+"', precioArticulo = '"+txtPrecio.getText()+"', precioArticulo = '"+txtCantidad.getText()+"' WHERE idArticulo = "+idArticuloEditar;
			utilidad.registrarLog(NombreUsuario,sentencia);
			// Feedback
			if((bd.modificarArticulos(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Modificación de Articulo correcta");
				dlgMensaje.setTitle("Modificar Articulo");
				dlgMensaje.setSize(400,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Modificación de Articulo correcta");
				setVisible(false);
				utilidad.registrarLog(NombreUsuario,"Saliendo de Modificar Articulo");
				new EdicionArticulos(NombreUsuario);
				
			}
			else
			{
				// Error
				mensaje.setText("Error en Modificación de Articulo");
				dlgMensaje.setTitle("Modificar Articulo");
				dlgMensaje.setSize(400,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Error en Modificación de Articulo");
			}
			// Desconectar
			bd.desconectar(conexion);
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			setVisible(false);
			utilidad.registrarLog(NombreUsuario,"Saliendo de Modificar Articulo");
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowDeactivated(WindowEvent arg0){}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent arg0){}
	@Override
	public void windowOpened(WindowEvent arg0){}
}
