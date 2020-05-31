package es.Studium.PracticaT2;
//Importamos los paquetes
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class ConsultaTrabajos extends Frame implements WindowListener, ActionListener 
{
	private static final long serialVersionUID = 1L;
	//Preparamos la conexion y desconexion de la base de datos
	Conexiones conexiones= new Conexiones();
	String BaseDatos="practica2laboratorio";
	String UsuarioDB="root";
	String ClaveBD="Studium.2019;";
	//Declaramos las etiquetas, los cuadros de texto, los botones, los dialogos, las listas, ...
	TextArea consulta = new TextArea(5,20);
	Button btnAceptar = new Button("Aceptar");
	Button btnPdf = new Button("Exportar a PDF");

	ConsultaTrabajos()
	{
		setTitle("Consulta de Trabajos");
		setLayout(new FlowLayout());
		// Conectar a la base de datos
		Connection con = conexiones.conectar(BaseDatos, UsuarioDB, ClaveBD);
		// Seleccionar de la tabla edificios
		// Sacar la información
		rellenarTextArea(con, consulta);
		// Cerrar la conexión
		conexiones.desconectar(con);
		consulta.setEditable(false);
		add(consulta);
		add(btnAceptar);
		add(btnPdf);
		btnAceptar.addActionListener(this);
		btnPdf.addActionListener(this);
		addWindowListener(this);
		setSize(200,170);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object objetoPulsado = e.getSource();
		if(objetoPulsado.equals(btnAceptar))
		{
			setVisible(false);
		}
		else
		{
			System.out.println("Exportando a PDF...");
		}
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		setVisible(false);
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
	
	public void rellenarTextArea(Connection con, TextArea t)
	{
		String sqlSelect = "SELECT * FROM trabajos";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				if(t.getText().length()==0)
				{
					t.setText(rs.getInt("idTrabajo")+
							"-"+rs.getString("descripcionTrabajo")+
							", "+rs.getString("idClinicaFK1"));
				}
				else
				{
					t.setText(t.getText() + "\n" +
							rs.getInt("idTrabajo")+
							"-"+rs.getString("descripcionTrabajo")+
							", "+rs.getString("idClinicaFK1"));
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
