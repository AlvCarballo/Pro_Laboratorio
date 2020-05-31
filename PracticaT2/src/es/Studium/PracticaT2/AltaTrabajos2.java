package es.Studium.PracticaT2;
//Importamos los paquetes
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
public class AltaTrabajos2 extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos variables
	String txaArticulo1=null;
	//Declaramos ArrayList
		ArrayList<String> al = new ArrayList<String>();
		//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	Label lblClinica = new Label("Clínica:");
	Choice choClinica = new Choice();
	Label lblArticulo = new Label("Artículo:");
	Choice choArticulo = new Choice();
	Label lblCantidad = new Label("Cantidad:");
	TextField txtCantidad = new TextField(20);
	Button btnAgregar = new Button("Agregar");
	TextArea txaArticulo = new TextArea(5,20);
	Label lblDescripcion = new Label("Descripción:");
	TextField txtDescripcion = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	Dialog Alta;
	Button btnAceptarDialogo = new Button("Aceptar");
	
	AltaTrabajos2()
	{
		setTitle("ALTA de Trabajos"); //Titulo de la ventana
		setLayout(new FlowLayout());
		//Añadimos etiquetas, cuadros de texto, botones ...
		add(lblClinica);
		add(choClinica);
		choClinica.add("Seleccionar uno...");// Montar el Choice
				Connection conClinica = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar a la base de datos
				// Rellenar el Choice
				String sqlSelectClinica = "SELECT * FROM clinicas";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtClinica = conClinica.createStatement();
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
				conexiones.desconectar(conClinica);// Cerrar la conexión
		add(lblArticulo);
		add(choArticulo);
		choArticulo.add("Seleccionar uno...");// Montar el Choice
				Connection conArticulo = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar a la base de datos
				// Rellenar el Choice
				String sqlSelectArticulo = "SELECT * FROM articulos";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmtArticulo = conArticulo.createStatement();
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
				conexiones.desconectar(conArticulo);// Cerrar la conexión
		add(lblCantidad);
		add(txtCantidad);
		add(btnAgregar);
		txaArticulo.setEditable(false);//Ponemos que el Textarea no se pueda editar.
		add(txaArticulo);
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
		setSize(250,300);//Ponemos el tamaño de la ventana
		setResizable(false);//Indicamos que no se puede redimencionar
		setLocationRelativeTo(null);//Indicamos que se ponga en el centro de la pantalla
		setVisible(true);//Indicamos que sea visible
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		//Indicamos las acciones a seguir segun el boton pulsado
		Object objetoPulsado = e.getSource();//comprobamos que boton se ha pulsado
		if(objetoPulsado.equals(btnLimpiar))//comparamos el boton pulsado para ver si coincide
		{
			//Las siguientes instruciones seleccionan todo el texto, y despues lo borran
			txtCantidad.selectAll();
			txtCantidad.setText("");
			txtDescripcion.selectAll();
			txtDescripcion.setText("");
			choClinica.select(0);
			choArticulo.select(0);
			txaArticulo.selectAll();
			txaArticulo.setText("");
		}
		else if(objetoPulsado.equals(btnAgregar))//si el anterior no es comparamos el boton pulsado para ver si coincide
		{
			String[] ArticuloT=choArticulo.getSelectedItem().split("-");//guardamos en una tabla cada dato que esta separado por un guion del choise.
			// Añadir elementos a un ArrayList
			al.add(ArticuloT[0]+", "+ArticuloT[1]+", "+ArticuloT[2]+", "+ txtCantidad.getText());//Cojemos cada dato de la tabla anterior le unimos el testfield y lo guardamos en una lista de array
				Iterator<String> it = al.iterator();
			       while(it.hasNext()){//recorremos la lista de array
			    	   txaArticulo1=  it.next(); //añadimos la lista de array a una variable
			       } 
			       txaArticulo.append(txaArticulo1.toString() + "\n");//cojemos y añadimos los tatos al textarea
		}
		else if(objetoPulsado.equals(btnAceptar))//si el anterior no es comparamos el boton pulsado para ver si coincide
		{
			Connection conAceptar=conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar BD
			// Hacer el INSERT
			String[] idClinicaFK1=choClinica.getSelectedItem().split("-");
			String descripciontrabajo = txtDescripcion.getText();
			int respuesta = insertar(conAceptar, "trabajos", descripciontrabajo, idClinicaFK1[0]);
			String ultimoValor = null;//Declaramos una variable para guardar el id del ultimo insert
			try {
				Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);// Conectar BD
			    PreparedStatement stmtr = con.prepareStatement("SELECT * FROM trabajos ORDER BY idTrabajo DESC");//Sentencia para obtener el idtrabajo de fotma descendiente
			    ResultSet rsr = stmtr.executeQuery();//guardamos el resultado de ejecutar la sentencia anterior
			    if(rsr.next()){ //Recorremos los valores guardados hasta llegar al ultimo.
			        ultimoValor = rsr.getString("idTrabajo");//Guardamos el id en la variable, al repetirse, solo guardara el ultimo introducido
			    }
			    stmtr.close();//cerramos stmtr
			    rsr.close();//cerramos rsr
			    con.close();//cerramos con
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "ERROR:al hacer un Insert"+"\n"+ex);
				ex.printStackTrace();
			}
			String idTrabajo=ultimoValor;//guardamos el ultimo vamor en una variable que usaremos mas adelante
			Iterator<String> it = al.iterator(); 
			int respuesta2=0;
			while(it.hasNext()){ //mientras halla mas resultados
		    	   txaArticulo1=  it.next(); //guardamos cada resultado 
		    	   String[] ArticuloT=txaArticulo1.split(", ");//Guardamos en una tabla los datos contenidos en la variable txaarticulo1 separandolos por comas
		    	   Float Precio = Float.parseFloat(ArticuloT[2]);//De la tabla ArticuloT, generada en el paso anterior cojemos la posicion 3 que corresponde a precio y lo guardamos en la una nueva variable como fload
		    	   Float Cantidad = Float.parseFloat(txtCantidad.getText());//cojemos txtcantidad lo convertimos a float y lo guardamos en una nueva variable
		    	   Float Subtotal = Precio *Cantidad;//Hacemos la operacion de las 2 nuevas variables generando una nueva.
		    	   respuesta2 = insertar2(conAceptar, "articulostrabajos", idTrabajo, ArticuloT[0], txtCantidad.getText(), ArticuloT[2], Subtotal);
			 	}
			// Mostramos resultado
			
			if((respuesta == 0)&(respuesta2 == 0))
			{
				//Muestra una ventana indicando que el alta, se ha realizado correctamente
				Alta = new Dialog(this,"Alta realizada", true);
				Label lblEtiqueta = new Label("Alta realizada Correctamente");
				Alta.setLayout(new FlowLayout());
				Alta.setSize(350,200);
				Alta.add(lblEtiqueta);
				Alta.addWindowListener(this);
				Alta.setResizable(false);
				Alta.setLocationRelativeTo(null);
				Alta.add(btnAceptarDialogo);
				Alta.setVisible(true);
			}
			else
			{
				//Muestra una ventana indicando que el alta, no se ha realizado
				Alta = new Dialog(this,"Error Alta", true);
				Label lblEtiqueta = new Label("Error en ALTA de trabajo");
				Alta.setLayout(new FlowLayout());
				Alta.setSize(120,200);
				Alta.add(lblEtiqueta);
				Alta.addWindowListener(this);
				Alta.setResizable(false);
				Alta.setLocationRelativeTo(null);
				Alta.add(btnAceptarDialogo);
				Alta.setVisible(true);
			}
			// Desconectar de la base
			conexiones.desconectar(conAceptar);
		}
		else if(objetoPulsado.equals(btnAceptarDialogo))
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
		else
		{
			Alta.setVisible(false);
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
	
	public int insertar(Connection con, String tabla, String descripciontrabajo, String idClinicaFK1) 
	{
		int respuesta = 0;
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO " + tabla 
					+ " VALUES (null, '" + descripciontrabajo 
					+ "', " + idClinicaFK1 + ")";
			System.out.println(cadenaSQL);
			sta.executeUpdate(cadenaSQL);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al hacer un Insert"+"\n"+ex);
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;
	}
	public int insertar2(Connection con, String tabla, String idTrabajoFK2, String idArticuloFK3, String cantidadArticuloTrabajo, String precioArticuloTrabajo, Float SubtotalArticuloTrabajo) 
	{
		int respuesta = 0;
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO " + tabla 
					+ " VALUES (null, "+idTrabajoFK2 +
					", "+idArticuloFK3+
					", "+ cantidadArticuloTrabajo + 
					", "+precioArticuloTrabajo+
					", "+SubtotalArticuloTrabajo+ ")";
			System.out.println(cadenaSQL);
			sta.executeUpdate(cadenaSQL);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			JOptionPane.showMessageDialog(null, "ERROR:al hacer un Insert"+"\n"+ex);
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;
	}
	public void rellenarTextAreaArticulo(Connection con, TextArea t)
	{
		String sqlSelect = "SELECT * FROM articulostrabajos";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				if(t.getText().length()==0)
				{
					t.setText(rs.getInt("idArticuloFK3")+
							"-"+rs.getString("NombreArticulo"));
				}
				else
				{
					t.setText(t.getText() + "\n" +
							rs.getInt("idArticuloFK3")+
							"-"+rs.getString("NombreArticulo"));
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR:al consultar"+"\n"+ex);
			ex.printStackTrace();
		}
	}
}