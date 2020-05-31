package es.studium.Laboratorio;
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

public class AltaArticulos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
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

	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
	Label mensaje = new Label("");
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	
	public AltaArticulos(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Alta Articulos");
		setTitle("ALTA de Artículo");//Ponemos nombre a la ventana
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
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			//Selexionamos todos los txt y los borramos
			txtDescripcionArt.selectAll();
			txtDescripcionArt.setText("");
			txtPrecio.selectAll();
			txtPrecio.setText("");
			txtCantidad.selectAll();
			txtCantidad.setText("");
			txtDescripcionArt.requestFocus();
			utilidad.registrarLog(NombreUsuario,"Limpiando datos formulario Alta Artículo");
		}
		else // btnAceptar
		{
			// Conectar BD
			conexion = bd.conectar();
			// Hacer INSERT
			String sentencia = "INSERT INTO articulos VALUES(null,'"+txtDescripcionArt.getText()+"','"+txtPrecio.getText()+"','"+txtCantidad.getText()+"')";
			utilidad.registrarLog(NombreUsuario,sentencia);
			// Feedback
			if((bd.altaClinica(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Alta de Artículo correcta");
				dlgMensaje.setTitle("Alta Artículo");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Alta realizada Correctamente");
			}
			else
			{
				// Error
				mensaje.setText("Error en Alta de Articulo");
				dlgMensaje.setTitle("Alta Artículo");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Error en Alta de Artículo");
			}
			bd.desconectar(conexion);
			// Desconectar
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			utilidad.registrarLog(NombreUsuario,"Saliendo de Alta Artículo");
			setVisible(false);
		}
	}
	@Override
	public void windowDeactivated(WindowEvent arg0){}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent arg0){}
	@Override
	public void windowOpened(WindowEvent arg0){}
}