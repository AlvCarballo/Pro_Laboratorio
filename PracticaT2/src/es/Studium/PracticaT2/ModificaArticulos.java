package es.Studium.PracticaT2;
//Importamos los paquetes necesarios
import java.awt.Button;
import java.awt.Choice;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ModificaArticulos extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblEdificioFactura = new Label("Articulo a modificar:");
	Choice choArticulos = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog seguro;
	Button btnSi= new Button("Si");
	Button btnNo= new Button("No");
	Frame modificarArticulo;
	Button btnAceptarCambios;
	Button btnCancelarCambios;
	TextField txtIdArticulo;
	TextField txtNombreArticulo;
	TextField txtPrecioArticulo;
	TextField txtCantidadArticulo;
	Dialog Alta;
	Button btnAceptarDialogo = new Button("Aceptar");
	
	ModificaArticulos()
	{
		setTitle("Modificar Articulos");;//Ponemos nombre a la ventana
		setLayout(new FlowLayout());
		//Añadimos a la ventana  las etiquetas, los cuadros de texto y los botones
		// Montar el Choice
		choArticulos.add("Seleccionar uno...");
		Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar a la base de datos
		// Sacar los datos de la tabla edificios
		// Rellenar el Choice
		String sqlSelect = "SELECT * FROM articulos";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				choArticulos.add(rs.getInt("idArticulo")+
						"-"+rs.getString("nombreArticulo")+
						", "+rs.getString("precioArticulo")+
						", "+rs.getString("cantidadArticulo"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR:al rellenar el despegable"+"\n"+ex);
			ex.printStackTrace();
		}
		// Cerrar la conexión
		conexiones.desconectar(con);
		add(choArticulos);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnAceptarDialogo.addActionListener(this);
		btnSi.addActionListener(this);
		btnNo.addActionListener(this);
		addWindowListener(this);
		setSize(420,100);//Ponemos el tamaño de la ventana
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
			choArticulos.select(0);
		}
		else if(objetoPulsado.equals(btnAceptar))//Acciones del boton Aceptar
		{
			// Sacar el id del elemento elegido
			int id = Integer.parseInt(choArticulos.getSelectedItem().split("-")[0]);
			// Crear un Frame igual que el ALTA
			modificarArticulo = new Frame("Modificar Articulo");
			modificarArticulo.setLayout(new FlowLayout());
			Label lblIdArticulo = new Label("idArticulo:");
			Label lblNombreArticulo = new Label("nombreArticulo:");
			Label lblPrecioArticulo = new Label("precioArticulo:");
			Label lblCantidadArticulo = new Label("cantidadArticulo:");
			txtIdArticulo = new TextField(20);
			txtNombreArticulo = new TextField(20);
			txtPrecioArticulo = new TextField(20);
			txtCantidadArticulo = new TextField(20);
			btnAceptarCambios = new Button("Aceptar");
			btnCancelarCambios = new Button("Cancelar");
			modificarArticulo.add(lblIdArticulo);
			txtIdArticulo.setEnabled(false);
			modificarArticulo.add(txtIdArticulo);
			modificarArticulo.add(lblNombreArticulo);
			modificarArticulo.add(txtNombreArticulo);
			modificarArticulo.add(lblPrecioArticulo);
			modificarArticulo.add(txtPrecioArticulo);
			modificarArticulo.add(lblCantidadArticulo);
			modificarArticulo.add(txtCantidadArticulo);
			btnAceptarCambios.addActionListener(this);
			btnCancelarCambios.addActionListener(this);
			modificarArticulo.add(btnAceptarCambios);
			modificarArticulo.add(btnCancelarCambios);
			// Pero relleno-->
			// Conectar a la base de datos
			Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Seleccionar los datos del elemento
			mostrarDatos(con, id, txtIdArticulo, txtNombreArticulo, txtPrecioArticulo, txtCantidadArticulo);
			// seleccionado
			// Mostrarlos
			conexiones.desconectar(con);
			modificarArticulo.addWindowListener(this);
			modificarArticulo.setResizable(false);
			modificarArticulo.setSize(200,300);
			modificarArticulo.setLocationRelativeTo(null);
			modificarArticulo.setVisible(true);
		}
		else if(objetoPulsado.equals(btnNo))
		{
			seguro.setVisible(false);
		}
		else if(objetoPulsado.equals(btnCancelarCambios))
		{
			modificarArticulo.setVisible(false);
		}
		else if(objetoPulsado.equals(btnAceptarCambios))
		{
			//Muestra una ventana indicando que el alta, se ha realizado correctamente
			seguro = new Dialog(this,"Seguro de realizar los cambios", true);
			Label lblEtiqueta = new Label("Esta seguro de realizar los cambios");
			seguro.setLayout(new FlowLayout());
			seguro.setSize(300,150);
			seguro.add(lblEtiqueta);
			seguro.addWindowListener(this);
			seguro.setResizable(false);
			seguro.setLocationRelativeTo(null);
			seguro.add(btnSi);
			seguro.add(btnNo);
			seguro.setVisible(true);
		}
		else if(objetoPulsado.equals(btnSi))
		{
			seguro.setVisible(false);
			int respuesta = 0;
			int id = Integer.parseInt(txtIdArticulo.getText());
			String nArticulo = txtNombreArticulo.getText();
			String pArticulo = txtPrecioArticulo.getText();
			String cArticulo = txtCantidadArticulo.getText();
			// Conectar a la base de datos
			Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
			// Ejecutar el UPDATE
			String sql ="UPDATE articulos SET nombreArticulo = '"+nArticulo+"', precioArticulo = '"+pArticulo+"', cantidadArticulo = '"+cArticulo+"' WHERE idArticulo="+id;
			try {
				// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERROR:al insertar los cambios"+"\n"+ex);
				ex.printStackTrace();
				respuesta = 1;
			}
			// Cerrar la conexión
			conexiones.desconectar(con);
			modificarArticulo.setVisible(false);
			
			if(respuesta == 0)
			{
				//Muestra una ventana indicando que el alta, se ha realizado correctamente
				String Titulo="Modificacion realizada";
				String Etiqueta="Modificacion realizada Correctamente";
				dialogo(Titulo, Etiqueta);
			}
			else
			{
				//Muestra una ventana indicando que el alta, no se ha realizado
				String Titulo="Modificacion Erroneaa";
				String Etiqueta="Error en Modificacion";
				dialogo(Titulo, Etiqueta);
			}
			
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
		else if(modificarArticulo.isActive())
		{
			modificarArticulo.setVisible(false);
		}
		else if(Alta.isActive())
		{
			Alta.setVisible(false);
		}
		else
		{
			seguro.setVisible(false);
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
	
	public int borrar(Connection con, int idEdificio)
	{
		int respuesta = 0;
		String sql = "DELETE FROM edificios WHERE idEdificio = " + idEdificio;
		System.out.println(sql);
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			sta.executeUpdate(sql);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al hacer un Delete"+"\n"+ex);
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
		Alta.setSize(300,150);
		Alta.add(lblEtiqueta);
		Alta.addWindowListener(this);
		Alta.setResizable(false);
		Alta.setLocationRelativeTo(null);
		Alta.add(btnAceptarDialogo);
		Alta.setVisible(true);
	}
	public void mostrarDatos(Connection con, int IdArticulo0, TextField id, TextField NombreArticulo0, TextField PrecioArticulo0, TextField CantidadArticulo0 )
	{
		String sql = "SELECT * FROM Articulos WHERE idArticulo = "+IdArticulo0;
		try 
		{ 
			id.setText(IdArticulo0+"");
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next())
			{
				String n = rs.getString("NombreArticulo");
				NombreArticulo0.setText(n);
				String p = rs.getString("PrecioArticulo");
				PrecioArticulo0.setText(p);
				String c = rs.getString("CantidadArticulo");
				CantidadArticulo0.setText(c);
			}
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al mostrar"+"\n"+ex);
			ex.printStackTrace();
		}
	}
}
