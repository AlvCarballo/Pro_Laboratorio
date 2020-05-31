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
import java.util.Date;

public class AltaClinicas extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
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

	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
	Label mensaje = new Label("");
<<<<<<< HEAD
	Date fecha;
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	public AltaClinicas(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Alta Clínica");
		setTitle("ALTA de Clínica");//Titulo de la ventana
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
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
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
			utilidad.registrarLog(NombreUsuario,"Limpiando datos formulario Alta Clínicas");
		}
		else // btnAceptar
		{
			// Conectar BD
			conexion = bd.conectar();
			// Hacer INSERT
			String sentencia = "INSERT INTO clinicas VALUES(null,'"+txtNif.getText()+"','"+txtNombre.getText()+"','"+txtDireccion.getText()+"','"+txtTelefono.getText()+"','"+txtEmail.getText()+"')";
			utilidad.registrarLog(NombreUsuario,sentencia);
			// Feedback
			if((bd.altaClinica(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Alta de Clínicas correcta");
				dlgMensaje.setTitle("Alta Clínicas");
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
				mensaje.setText("Error en Alta de Clínicas");
				dlgMensaje.setTitle("Alta Cliente");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Error en Alta de Clínicas");
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
			utilidad.registrarLog(NombreUsuario,"Saliendo de Alta Clínicas");
=======
	Utilidades utilidad = new Utilidades();
	Date fecha;

	Login nombreUsuario=new Login();
	String nombreUser=nombreUsuario.getnombreUsuario();
	
	public AltaClinicas()
	{
		utilidad.registrarLog(nombreUser,"Entrando en Alta Clínica");
		setTitle("ALTA de Clínica");//Titulo de la ventana
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
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
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
			utilidad.registrarLog(nombreUser,"Limpiando datos formulario Alta Clínicas");
		}
		else // btnAceptar
		{
			// Conectar BD
			conexion = bd.conectar();
			// Hacer INSERT
			String sentencia = "INSERT INTO clinicas VALUES(null,'"+txtNif.getText()+"','"+txtNombre.getText()+","+txtDireccion.getText()+","+txtTelefono.getText()+","+txtEmail.getText()+"')";
			utilidad.registrarLog(nombreUser,sentencia);
			// Feedback
			if((bd.altaClinica(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Alta de Clínicas correcta");
				dlgMensaje.setTitle("Alta Clínicas");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(nombreUser,"Alta realizada Correctamente");
			}
			else
			{
				// Error
				mensaje.setText("Error en Alta de Clínicas");
				dlgMensaje.setTitle("Alta Cliente");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(nombreUser,"Error en Alta de Clínicas");
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
			utilidad.registrarLog(nombreUser,"Saliendo de Alta Clínicas");
>>>>>>> branch 'master' of https://github.com/AlvCarballo/Pro_Laboratorio
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