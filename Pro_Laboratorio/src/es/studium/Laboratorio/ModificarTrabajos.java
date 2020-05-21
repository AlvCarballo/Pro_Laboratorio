package es.studium.Laboratorio;

import java.awt.Button;
import java.awt.Choice;
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

public class ModificarTrabajos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
		Label lblDescripcionTrab = new Label("Descripcion Trabajo:");
		TextField txtDescripcionTrab = new TextField(20);
		Label lblIdClinica = new Label("ID Clinica:");
		TextField txtIdClinica = new TextField(20);
		Choice listado = new Choice();

		Button btnAceptar = new Button("Aceptar");
		Button btnLimpiar = new Button("Limpiar");
		
		BaseDatos bd = new BaseDatos();
		Connection conexion = null;
		String[] cadena;
		Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
		Label mensaje = new Label("");
		int idTrabajoEditar;
		Utilidades utilidad = new Utilidades();

		Login nombreUsuario=new Login();
		String nombreUser=nombreUsuario.getnombreUsuario();
		
	public ModificarTrabajos(int idTrabajoEditar)
	{
		this.idTrabajoEditar = idTrabajoEditar; 
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarTrabajo2(conexion, idTrabajoEditar)).split("-");
		// cadena[0] = idTrabajo
		// cadena[1] = descripcionTrabajo
		// cadena[2] = idClinicaFK1
		setTitle("Modificar Trabajo");
		setLayout(new GridLayout(5,2));
		
		add(lblDescripcionTrab);
		txtDescripcionTrab.setText(cadena[1]);
		add(txtDescripcionTrab);
		
		add(lblIdClinica);
		txtIdClinica.setText(cadena[2]);
		add(txtIdClinica);
		
		listado.add("Seleccionar una linea de Trabajo...");
		// Conectar BD
		conexion = bd.conectar();
		cadena = (bd.consultarTrabajosChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			listado.add(cadena[i]);
		}
		add(listado);
		
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
			txtDescripcionTrab.selectAll();
			txtDescripcionTrab.setText("");
		}
		else // btnAceptar
		{
			// Hacer UPDATE
			String sentencia = "UPDATE articulos SET nombreArticulo = '"+txtDescripcionTrab.getText()+"', nombreArticulo = '"+txtDescripcionTrab.getText()+"' WHERE idArticulo = "+idTrabajoEditar;
			utilidad.registrarLog(nombreUser,sentencia);
			// Feedback
			if((bd.modificarArticulos(conexion, sentencia))==0)
			{
				// Todo bien
				mensaje.setText("Modificación de Trabajo correcta");
				dlgMensaje.setTitle("Modificar Trabajo");
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
				mensaje.setText("Error en Modificación de Trabajo");
				dlgMensaje.setTitle("Modificar Trabajo");
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