package es.studium.Laboratorio;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class EdicionTrabajos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	int idTrabajoEditar = 0;
<<<<<<< HEAD
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	
	public EdicionTrabajos(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Edicion Trabajo");
		setTitle("Editar Trabajo");
		setLayout(new FlowLayout());
		// Rellenar el Choice
		listado.add("Seleccionar un Trabajo...");
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
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			listado.select(0);
			utilidad.registrarLog(NombreUsuario,"Limpiando lista de Edicion Trabajo");
		}
		else if(btnAceptar.equals(arg0.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un Trabajo ..."))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El Trabajo que quiero editar está en tabla[0]
				idTrabajoEditar = Integer.parseInt(tabla[0]);
				setVisible(false);
				new ModificarTrabajos(NombreUsuario, idTrabajoEditar);
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		setVisible(false);
		utilidad.registrarLog(NombreUsuario,"Saliendo de Edicion Trabajo");
=======
	
	public EdicionTrabajos()
	{
		setTitle("Editar Trabajo");
		setLayout(new FlowLayout());
		// Rellenar el Choice
		listado.add("Seleccionar un Trabajo...");
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
			listado.select(0);
		}
		else if(btnAceptar.equals(arg0.getSource()))
		{
			if(listado.getSelectedItem().equals("Seleccionar un Trabajo ..."))
			{
				// No hacemos nada
			}
			else
			{
				// Coger el elemento seleccionado
				String[] tabla = listado.getSelectedItem().split("-");
				// El Trabajo que quiero editar está en tabla[0]
				idTrabajoEditar = Integer.parseInt(tabla[0]);
				System.out.println(idTrabajoEditar);
				new ModificarTrabajos(idTrabajoEditar); 
			}
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		setVisible(false);
>>>>>>> branch 'master' of https://github.com/AlvCarballo/Pro_Laboratorio
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
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowOpened(WindowEvent e){}
}
