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
public class DetalleArticulosTrabajosEditar extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	int idArticuloTrabajo;
	Label lblIdArticulo = new Label("Id Articulo");
	TextField txtIdArticulo = new TextField(8);
	Label lblCantidadArticulo = new Label("Cantidad: ");
	TextField txtCantidadArticulo = new TextField(12);
	Label lblPrecioArticulo = new Label("Precio: ");
	TextField txtPrecioArticulo = new TextField(20);
	Label lblSubTotal = new Label("SubTotal");
	TextField txtSubTotal = new TextField(20);
	
	Button btnAceptar = new Button("Aceptar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	
	Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
	Label mensaje = new Label("");
	String NombreUsuario="";
	Utilidades utilidad = new Utilidades();
	int idTrabajoEditar;

	public DetalleArticulosTrabajosEditar(String UserName, int idTrabajoEditar, int idArticuloTrabajo)
	{
		this.idTrabajoEditar=idTrabajoEditar;
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en detalles articulos trabajo");
		this.idArticuloTrabajo = idArticuloTrabajo;
		setTitle("Detalle Articulos Trabajo");
		setLayout(new GridLayout(5,2));
		
		// Conectar a la BD
		conexion = bd.conectar();
		// Sacar los datos de la factura concreta
		String[] data1 = bd.consultarTrabajoDetalleEditar(conexion, idArticuloTrabajo).split("#");

		// data1[0] = idArticuloFK3
		// data1[1] = cantidadArticuloTrabajo
		// data1[2] = precioArticuloTrabajo
		// data1[3] = SubtotalArticuloTrabajo
		
		
		add(lblIdArticulo);
		txtIdArticulo.setText(data1[0]);
		add(txtIdArticulo);
		add(lblCantidadArticulo);
		txtCantidadArticulo.setText(data1[1]);
		add(txtCantidadArticulo);
		add(lblPrecioArticulo);
		txtPrecioArticulo.setText(data1[2]);
		add(txtPrecioArticulo);
		add(lblSubTotal);
		txtSubTotal.setText(data1[3]);
		add(txtSubTotal);
		
		btnAceptar.addActionListener(this);
		add(btnAceptar);
		
		addWindowListener(this);
		setSize(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource().equals(btnAceptar))
		{
			// Hacer UPDATE
						String sentencia = "UPDATE articulostrabajos SET idArticuloFK3 = '"+txtIdArticulo.getText()+"', cantidadArticuloTrabajo = '"+txtCantidadArticulo.getText()+"', precioArticuloTrabajo = '"+txtPrecioArticulo.getText()+"', SubtotalArticuloTrabajo = '"+txtSubTotal.getText()+"' WHERE idArticuloTrabajo = "+idArticuloTrabajo;
						utilidad.registrarLog(NombreUsuario,sentencia);
						// Feedback
						if((bd.modificarArticulos(conexion, sentencia))==0)
						{
							// Todo bien
							mensaje.setText("Modificación de Linea de Trabajo correcta");
							dlgMensaje.setTitle("Modificar Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(NombreUsuario,"Modificación de Linea de Trabajo correcta");
							setVisible(false);
							new ModificarTrabajos(NombreUsuario, idTrabajoEditar);
						}
						else
						{
							// Error
							mensaje.setText("Error en Modificación de Linea de Trabajo");
							dlgMensaje.setTitle("Modificar Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(NombreUsuario,"Error en modificación de Linea de Trabajo");
						}
						// Desconectar
						bd.desconectar(conexion);
			setVisible(false);
		}
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
	}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowOpened(WindowEvent e){}
}
