package es.studium.Laboratorio;

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

public class BajaTrabajos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this,"¿Está seguro?", true);
	Label mensaje = new Label("¿Está seguro/a de eliminar este trabajo?.");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");
	String[] cadena;
	Dialog dlgMensajeFeedback = new Dialog(this,"Mensaje", true);
	Label mensajeFeedback = new Label("");
	int idTrabajoBorrar = 0;
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	
	public BajaTrabajos(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en baja trabajo");
		setTitle("Baja Trabajos");
		setLayout(new FlowLayout());
		// Rellenar el Choice
		listado.add("Seleccionar un trabajo...");
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
		setSize(400,100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(btnLimpiar.equals(e.getSource()))
		{
			listado.select(0);
		}
		else if(btnAceptar.equals(e.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un trabajo..."))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El idCliente que quiero borrar está en tabla[0]
				idTrabajoBorrar = Integer.parseInt(tabla[0]);
				dlgMensaje.setSize(400,120);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				btnSi.addActionListener(this);
				btnNo.addActionListener(this);
				dlgMensaje.add(btnSi);
				dlgMensaje.add(btnNo);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
			}
		}
		else if(btnSi.equals(e.getSource()))
		{
			// Conectar BD
			conexion = bd.conectar();
			// Ejecutar DELETE
			if((bd.borrarTrabajos(conexion, idTrabajoBorrar))==0)
			{
				// Todo bien
				mensajeFeedback.setText("Baja de trabajo correcta");
				dlgMensajeFeedback.setTitle("Baja trabajo");
				dlgMensajeFeedback.setSize(400,120);
				dlgMensajeFeedback.setLayout(new FlowLayout());
				dlgMensajeFeedback.addWindowListener(this);
				dlgMensajeFeedback.add(mensajeFeedback);
				dlgMensajeFeedback.setLocationRelativeTo(null);
				dlgMensajeFeedback.setVisible(true);
				utilidad.registrarLog(NombreUsuario,"Baja de trabajo correcta");
				setVisible(false);
				utilidad.registrarLog(NombreUsuario,"Saliendo de baja clinica");
			}
			else
			{
				// Error
//				mensajeFeedback.setText("Error al borrar el trabajo");
//				dlgMensajeFeedback.setTitle("Baja trabajo");
//				dlgMensajeFeedback.setSize(400,120);
//				dlgMensajeFeedback.setLayout(new FlowLayout());
//				dlgMensajeFeedback.addWindowListener(this);
//				dlgMensajeFeedback.add(mensajeFeedback);
//				dlgMensajeFeedback.setLocationRelativeTo(null);
//				dlgMensajeFeedback.setVisible(true);
//				utilidad.registrarLog(NombreUsuario,"Error al borrar el trabajo");
			}
			// Desconectar BD
			bd.desconectar(conexion);
		}
		else // btnNo
		{
			dlgMensaje.setVisible(false);
		}

	}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else if(dlgMensajeFeedback.isActive())
		{
			dlgMensajeFeedback.setVisible(false);
		}
		else
		{
			setVisible(false);
			utilidad.registrarLog(NombreUsuario,"Saliendo de baja trabajo");
		}
	}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowOpened(WindowEvent e){}
}
