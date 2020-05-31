package es.studium.Laboratorio;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
public class AltaTrabajos extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
		//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
		Label lblClinica = new Label("Clínica:");
		Choice choClinica = new Choice();
		Label lblArticulo = new Label("Artículo:");
		Choice choArticulo = new Choice();
		Label lblCantidad = new Label("Cantidad:");
		TextField txtCantidad = new TextField(20);
		Button btnAgregar = new Button("Agregar");
		String[] ArticuloT=null;
		String[] ArticuloT1=null;
		ArrayList<String> al = new ArrayList<String>();//Crea un array list
		
		
		 //creamos el arreglo de objetos que contendra el contenido de las columnas
		 Object[] data = new Object[5];
		// creamos el modelo de Tabla
		 DefaultTableModel dtm= new DefaultTableModel();
		 // se crea la Tabla con el modelo DefaultTableModel
		 final JTable table = new JTable(dtm);
		 
		
		Label lblDescripcion = new Label("Descripción:");
		TextField txtDescripcion = new TextField(20);
		Button btnAceptar = new Button("Aceptar");
		Button btnLimpiar = new Button("Limpiar");
		Dialog Alta;
		Button btnAceptarDialogo = new Button("Aceptar");
		
		
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this,"Mensaje", true);
	Label mensaje = new Label("");
	Utilidades utilidad = new Utilidades();

<<<<<<< HEAD
	String NombreUsuario="";
	
	public AltaTrabajos(String UserName)
	{
		NombreUsuario=UserName;
		utilidad.registrarLog(NombreUsuario,"Entrando en Alta Trabajo");
		setTitle("ALTA de Trabajo"); //Titulo de la ventana
		setLayout(new FlowLayout());
		//Añadimos etiquetas, cuadros de texto, botones ...
		add(lblClinica);
		add(choClinica);
		
		
		
		choClinica.add("Seleccionar uno...");// Montar el Choice
		// Conectar a la base de datos
		BaseDatos bd = new BaseDatos();
		Connection conexion = bd.conectar();
				// Rellenar el Choice
				String sqlSelectClinica = "SELECT * FROM clinicas";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtClinica = conexion.createStatement();
					ResultSet rsClinica = stmtClinica.executeQuery(sqlSelectClinica);
					while (rsClinica.next()) 
					{
						choClinica.add(rsClinica.getInt("idClinica")+
								"-"+rsClinica.getString("nifClinica")+
								", "+rsClinica.getString("nombreClinica"));
					}
					rsClinica.close();
					stmtClinica.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "ERROR:al consultar"+"\n"+ex);
					ex.printStackTrace();
				}
				
		add(lblArticulo);
		add(choArticulo);
		choArticulo.add("Seleccionar uno...");// Montar el Choice
		
		// Conectar a la base de datos
				// Rellenar el Choice
				String sqlSelectArticulo = "SELECT * FROM articulos";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtArticulo = conexion.createStatement();
					ResultSet rsArticulo = stmtArticulo.executeQuery(sqlSelectArticulo);
					while (rsArticulo.next()) 
					{
						choArticulo.add(rsArticulo.getInt("idArticulo")+
								"-"+rsArticulo.getString("nombreArticulo")+
								"-"+rsArticulo.getString("precioArticulo"));
					}
					rsArticulo.close();
					stmtArticulo.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "ERROR:al consultar"+"\n"+ex);
					ex.printStackTrace();
				}
				bd.desconectar(conexion);
				// Desconectar
				
				
		add(lblCantidad);
		add(txtCantidad);
		add(btnAgregar);
		
		 // insertamos las columnas
		 dtm.addColumn("Id Articulo");
		 dtm.addColumn("Nombre Articulo");
		 dtm.addColumn("Precio Articulo");
		 dtm.addColumn("Cantidad Articulo");
		 dtm.addColumn("Subtotal Articulo");
		 //se define el tamaño
		 table.setPreferredScrollableViewportSize(new Dimension(400, 70));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		 //manejamos la salida
		addWindowListener(new WindowAdapter() {});
		//Hasta aqui sera la tabla		

		add(lblDescripcion);
		add(txtDescripcion);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones.
		btnAceptarDialogo.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		addWindowListener(this); //Añadimos a la ventana
		setSize(500,300);//Ponemos el tamaño de la ventana
		setResizable(false);//Indicamos que no se puede redimencionar
		setLocationRelativeTo(null);//Indicamos que se ponga en el centro de la pantalla
		setVisible(true);//Indicamos que sea visible
		table.setEnabled(false); //Evita que se pueda editar la tabla.
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			//Selexionamos todos los txt y los borramos
			txtCantidad.selectAll();
			txtCantidad.setText("0");
			txtDescripcion.selectAll();
			txtDescripcion.setText("");
			choClinica.select(0);
			choArticulo.select(0);
			txtDescripcion.requestFocus();
				al.clear();
			
			
			utilidad.registrarLog(NombreUsuario,"Limpiando datos formulario Alta Artículo");
		}
		else if(btnAgregar.equals(arg0.getSource()))
		{
			ArticuloT=choArticulo.getSelectedItem().split("-");//guardamos en una tabla cada dato que esta separado por un guion del choise.
			al.add(ArticuloT[0]);
			al.add(ArticuloT[1]);
			al.add(ArticuloT[2]);
			al.add(txtCantidad.getText());
			al.add((Double.parseDouble(ArticuloT[2])*Double.parseDouble(txtCantidad.getText()))+"");
			 // insertamos las columnas
			 //dtm.addColumn("id");
			 //dtm.addColumn("Nombre Articulo");
			 //dtm.addColumn("Precio Articulo");
			 // insertamos el contenido de las columnas
			 for(int row = 0; row < al.size();row=row+5) {
			 data[0] = al.get(row);
			 data[1] = al.get(row+1);
			 data[2] = al.get(row+2);
			 data[3] = al.get(row+3);
			 data[4] = al.get(row+4);
			 	
			 }
			 dtm.addRow(data);
			 utilidad.registrarLog(NombreUsuario,"Agregamos linea de articulo al trabajo");
		}
		else // btnAceptar
		{
			// Conectar BD
						conexion = bd.conectar();
						// Hacer INSERT
						
						String[] idClinicaFK1=choClinica.getSelectedItem().split("-");
						String sentencia = "INSERT INTO trabajos VALUES(null,'"+txtDescripcion.getText()+"','"+idClinicaFK1[0]+"')";
						int idTrabajoFK = bd.ultimoIdTrabajo(conexion, sentencia);
						utilidad.registrarLog(NombreUsuario,sentencia);
						
						// Feedback
						
						if(idTrabajoFK!=0)
						{
							for(int i = 0; i < al.size();i=i+5)
							{
								String sentencia2 = "INSERT INTO articulostrabajos VALUES (null,'"+idTrabajoFK+"','"+al.get(i)+"','"+al.get(i+3)+"','"+al.get(i+2)+"','"+al.get(i+4)+"')";
								utilidad.registrarLog(NombreUsuario,sentencia2);
								bd.AltaTrabajos(conexion, sentencia2);
							}	
							mensaje.setText("Alta de Trabajo realizada");
							dlgMensaje.setTitle("Alta Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(NombreUsuario,"Alta de Trabajo realizada");
						}
						else
						{
							// Error
							mensaje.setText("Error en Alta de Trabajo");
							dlgMensaje.setTitle("Alta Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(NombreUsuario,"Error en Alta de Trabajo");
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
=======
	Login nombreUsuario=new Login();
	String nombreUser=nombreUsuario.getnombreUsuario();
	
	public AltaTrabajos()
	{
		utilidad.registrarLog(nombreUser,"Entrando en Alta Trabajo");
		setTitle("ALTA de Trabajo"); //Titulo de la ventana
		setLayout(new FlowLayout());
		//Añadimos etiquetas, cuadros de texto, botones ...
		add(lblClinica);
		add(choClinica);
		
		
		
		choClinica.add("Seleccionar uno...");// Montar el Choice
		// Conectar a la base de datos
		BaseDatos bd = new BaseDatos();
		Connection conexion = bd.conectar();
				// Rellenar el Choice
				String sqlSelectClinica = "SELECT * FROM clinicas";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtClinica = conexion.createStatement();
					ResultSet rsClinica = stmtClinica.executeQuery(sqlSelectClinica);
					while (rsClinica.next()) 
					{
						choClinica.add(rsClinica.getInt("idClinica")+
								"-"+rsClinica.getString("nifClinica")+
								", "+rsClinica.getString("nombreClinica"));
					}
					rsClinica.close();
					stmtClinica.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "ERROR:al consultar"+"\n"+ex);
					ex.printStackTrace();
				}
				
		add(lblArticulo);
		add(choArticulo);
		choArticulo.add("Seleccionar uno...");// Montar el Choice
		
		// Conectar a la base de datos
				// Rellenar el Choice
				String sqlSelectArticulo = "SELECT * FROM articulos";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtArticulo = conexion.createStatement();
					ResultSet rsArticulo = stmtArticulo.executeQuery(sqlSelectArticulo);
					while (rsArticulo.next()) 
					{
						choArticulo.add(rsArticulo.getInt("idArticulo")+
								"-"+rsArticulo.getString("nombreArticulo")+
								"-"+rsArticulo.getString("precioArticulo"));
					}
					rsArticulo.close();
					stmtArticulo.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "ERROR:al consultar"+"\n"+ex);
					ex.printStackTrace();
				}
				bd.desconectar(conexion);
				// Desconectar
				
				
		add(lblCantidad);
		add(txtCantidad);
		add(btnAgregar);
		
		 // insertamos las columnas
		 dtm.addColumn("Id Articulo");
		 dtm.addColumn("Nombre Articulo");
		 dtm.addColumn("Precio Articulo");
		 dtm.addColumn("Cantidad Articulo");
		 dtm.addColumn("Subtotal Articulo");
		 //se define el tamaño
		 table.setPreferredScrollableViewportSize(new Dimension(400, 70));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		 //manejamos la salida
		addWindowListener(new WindowAdapter() {});
		//Hasta aqui sera la tabla		

		add(lblDescripcion);
		add(txtDescripcion);
		add(btnAceptar);
		add(btnLimpiar);
		//Indicamos que los botones tienen acciones.
		btnAceptarDialogo.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		addWindowListener(this); //Añadimos a la ventana
		setSize(500,300);//Ponemos el tamaño de la ventana
		setResizable(false);//Indicamos que no se puede redimencionar
		setLocationRelativeTo(null);//Indicamos que se ponga en el centro de la pantalla
		setVisible(true);//Indicamos que sea visible
		table.setEnabled(false); //Evita que se pueda editar la tabla.
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			//Selexionamos todos los txt y los borramos
			txtCantidad.selectAll();
			txtCantidad.setText("0");
			txtDescripcion.selectAll();
			txtDescripcion.setText("");
			choClinica.select(0);
			choArticulo.select(0);
			txtDescripcion.requestFocus();
				al.clear();
			
			
			utilidad.registrarLog(nombreUser,"Limpiando datos formulario Alta Artículo");
		}
		else if(btnAgregar.equals(arg0.getSource()))
		{
			ArticuloT=choArticulo.getSelectedItem().split("-");//guardamos en una tabla cada dato que esta separado por un guion del choise.
			al.add(ArticuloT[0]);
			al.add(ArticuloT[1]);
			al.add(ArticuloT[2]);
			al.add(txtCantidad.getText());
			al.add((Double.parseDouble(ArticuloT[2])*Double.parseDouble(txtCantidad.getText()))+"");
			 // insertamos las columnas
			 //dtm.addColumn("id");
			 //dtm.addColumn("Nombre Articulo");
			 //dtm.addColumn("Precio Articulo");
			 // insertamos el contenido de las columnas
			 for(int row = 0; row < al.size();row=row+5) {
			 data[0] = al.get(row);
			 data[1] = al.get(row+1);
			 data[2] = al.get(row+2);
			 data[3] = al.get(row+3);
			 data[4] = al.get(row+4);
			 	
			 }
			 dtm.addRow(data);
		}
		else // btnAceptar
		{
			// Conectar BD
						conexion = bd.conectar();
						// Hacer INSERT
						
						String[] idClinicaFK1=choClinica.getSelectedItem().split("-");
						String sentencia = "INSERT INTO trabajos VALUES(null,'"+txtDescripcion.getText()+"','"+idClinicaFK1[0]+"')";
						int idTrabajoFK = bd.ultimoIdTrabajo(conexion, sentencia);
						utilidad.registrarLog(nombreUser,sentencia);
						
						// Feedback
						
						if(idTrabajoFK!=0)
						{
							for(int i = 0; i < al.size();i=i+5)
							{
								String sentencia2 = "INSERT INTO articulostrabajos VALUES (null,'"+idTrabajoFK+"','"+al.get(i)+"','"+al.get(i+3)+"','"+al.get(i+2)+"','"+al.get(i+4)+"')";
								bd.AltaTrabajos(conexion, sentencia2);
							}	
							mensaje.setText("Alta de Trabajo realizada");
							dlgMensaje.setTitle("Alta Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(nombreUser,"Alta de Trabajo realizada");
						}
						else
						{
							// Error
							mensaje.setText("Error en Alta de Trabajo");
							dlgMensaje.setTitle("Alta Trabajo");
							dlgMensaje.setSize(180,120);
							dlgMensaje.setLayout(new FlowLayout());
							dlgMensaje.addWindowListener(this);
							dlgMensaje.add(mensaje);
							dlgMensaje.setLocationRelativeTo(null);
							dlgMensaje.setVisible(true);
							utilidad.registrarLog(nombreUser,"Error en Alta de Trabajo");
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
			utilidad.registrarLog(nombreUser,"Saliendo de Alta Artículo");
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