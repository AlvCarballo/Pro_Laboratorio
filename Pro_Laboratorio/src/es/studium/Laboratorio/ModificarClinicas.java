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

public class ModificarClinicas extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
		Label lblNifClinica = new Label("Nif Clinica:");
		TextField txtNifClinica = new TextField(20);
		Label lblNombreClinica = new Label("Nombre Clinica:");
		TextField txtNombreClinica = new TextField(20);
		Label lblDireccionClinica = new Label("Direccion Clinica:");
		TextField txtDireccionClinica = new TextField(20);
		Label lblTelefonoClinica = new Label("Telefono Clinica:");
		TextField txtTelefonoClinica = new TextField(20);
		Label lblEmailClinica = new Label("Email Clinica:");
		TextField txtEmailClinica = new TextField(20);
		Button btnAceptar = new Button("Aceptar");
		Button btnLimpiar = new Button("Limpiar");
		
		BaseDatos bd = new BaseDatos();
		Connection conexion = null;
		String[] cadena;
		Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
		Label mensaje = new Label("");
		int idClinicaEditar;
<<<<<<< HEAD
		String NombreUsuario="";
		Utilidades utilidad = new Utilidades();
		
	public ModificarClinicas(String UserName, int idClinicaEditar)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Modificar Clinica");
		this.idClinicaEditar = idClinicaEditar; 
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarClinica(conexion, idClinicaEditar)).split("#");
		// cadena[0] = idArticulo
		// cadena[1] = nombreArticulo
		// cadena[2] = precioArticulo
		// cadena[3] = cantidadArticulo
		setTitle("Modificar Clinica");
		setLayout(new GridLayout(7,2));
		
		add(lblNifClinica);
		txtNifClinica.setText(cadena[1]);
		add(txtNifClinica);
		add(lblNombreClinica);
		txtNombreClinica.setText(cadena[2]);
		add(txtNombreClinica);
		add(lblDireccionClinica);
		txtDireccionClinica.setText(cadena[3]);
		add(txtDireccionClinica);
		add(lblTelefonoClinica);
		txtTelefonoClinica.setText(cadena[4]);
		add(txtTelefonoClinica);
		add(lblEmailClinica);
		txtEmailClinica.setText(cadena[5]);
		add(txtEmailClinica);
		
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
			txtNifClinica.selectAll();
			txtNifClinica.setText("");
			txtNombreClinica.selectAll();
			txtNombreClinica.setText("");
			txtDireccionClinica.selectAll();
			txtDireccionClinica.setText("");
			txtTelefonoClinica.selectAll();
			txtTelefonoClinica.setText("");
			txtEmailClinica.selectAll();
			txtEmailClinica.setText("");
			utilidad.registrarLog(NombreUsuario,"Limpiar Modificar Clinica");
		}
		else // btnAceptar
		{
			// Hacer UPDATE
			String sentencia = "UPDATE clinicas SET nifClinica = '"+txtNifClinica.getText()+"', nombreClinica = '"+txtNombreClinica.getText()+"', direccionClinica = '"+txtDireccionClinica.getText()+"', telefonoClinica = '"+txtTelefonoClinica.getText()+"', emailClinica = '"+txtEmailClinica.getText()+"' WHERE idClinica = "+idClinicaEditar;
			utilidad.registrarLog(NombreUsuario,sentencia);
			// Feedback
			if((bd.modificarArticulos(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Modificación de Clinica correcta");
				dlgMensaje.setTitle("Modificar Clinica");
				dlgMensaje.setSize(400,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Modificación de Clinica correcta");
				setVisible(false);
				utilidad.registrarLog(NombreUsuario,"Saliendo de Modificar Clinica");
				new EdicionClinicas(NombreUsuario);
				
			}
			else
			{
				// Error
				mensaje.setText("Error en Modificación de Clinica");
				dlgMensaje.setTitle("Modificar Clinica");
				dlgMensaje.setSize(400,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Error en Modificación de Clinica");
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
			utilidad.registrarLog(NombreUsuario,"Saliendo de Modificar Clinica");
=======
		Utilidades utilidad = new Utilidades();

		Login nombreUsuario=new Login();
		String nombreUser=nombreUsuario.getnombreUsuario();
		
	public ModificarClinicas(int idClinicaEditar)
	{
		this.idClinicaEditar = idClinicaEditar; 
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarClinica(conexion, idClinicaEditar)).split("#");
		// cadena[0] = idArticulo
		// cadena[1] = nombreArticulo
		// cadena[2] = precioArticulo
		// cadena[3] = cantidadArticulo
		setTitle("Modificar Cliente");
		setLayout(new GridLayout(7,2));
		
		add(lblNifClinica);
		txtNifClinica.setText(cadena[1]);
		add(txtNifClinica);
		add(lblNombreClinica);
		txtNombreClinica.setText(cadena[2]);
		add(txtNombreClinica);
		add(lblDireccionClinica);
		txtDireccionClinica.setText(cadena[3]);
		add(txtDireccionClinica);
		add(lblTelefonoClinica);
		txtTelefonoClinica.setText(cadena[4]);
		add(txtTelefonoClinica);
		add(lblEmailClinica);
		txtEmailClinica.setText(cadena[5]);
		add(txtEmailClinica);
		
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
			txtNifClinica.selectAll();
			txtNifClinica.setText("");
			txtNombreClinica.selectAll();
			txtNombreClinica.setText("");
			txtDireccionClinica.selectAll();
			txtDireccionClinica.setText("");
			txtTelefonoClinica.selectAll();
			txtTelefonoClinica.setText("");
			txtEmailClinica.selectAll();
			txtEmailClinica.setText("");
		}
		else // btnAceptar
		{
			// Hacer UPDATE
			String sentencia = "UPDATE clinicas SET nifClinica = '"+txtNifClinica.getText()+"', nombreClinica = '"+txtNombreClinica.getText()+"', direccionClinica = '"+txtDireccionClinica.getText()+"', telefonoClinica = '"+txtTelefonoClinica.getText()+"', direccionClinica = '"+txtEmailClinica.getText()+"' WHERE idClinica = "+idClinicaEditar;
			utilidad.registrarLog(nombreUser,sentencia);
			// Feedback
			if((bd.modificarArticulos(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Modificación de Clinica correcta");
				dlgMensaje.setTitle("Modificar Clinica");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
			else
			{
				// Error
				mensaje.setText("Error en Modificación de Clinica");
				dlgMensaje.setTitle("Modificar Clinica");
				dlgMensaje.setSize(180,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
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
>>>>>>> branch 'master' of https://github.com/AlvCarballo/Pro_Laboratorio
			setVisible(false);
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
