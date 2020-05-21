package es.studium.Laboratorio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConsultaClinicas extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	TextArea listado = new TextArea(13,40);
	Button exportarPdf = new Button("Exportar PDF");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	public ConsultaClinicas()
	{
		setTitle("Consulta Clinicas");
		setLayout(new FlowLayout());
		conexion = bd.conectar();
		String[] data1 = bd.consultarClinicasTabla(conexion).split("#");
		bd.desconectar(conexion);
		 //creamos el arreglo de objetos que contendra el
		 //contenido de las columnas
		 Object[] data = new Object[6];
		// creamos el modelo de Tabla
		 DefaultTableModel dtm= new DefaultTableModel();
		 // se crea la Tabla con el modelo DefaultTableModel
		 final JTable table = new JTable(dtm);
		 // insertamos las columnas
		 dtm.addColumn("id");
		 dtm.addColumn("NIF Clinica");
		 dtm.addColumn("Nombre Clinica");
		 dtm.addColumn("Direccion Clinica");
		 dtm.addColumn("Telefono Clinica");
		 dtm.addColumn("Email Clinica");
		 // insertamos el contenido de las columnas
		 for(int row = 0; row < data1.length;) {
		 data[0] = data1[row];
		 data[1] = data1[row+1];
		 data[2] = data1[row+2];
		 data[3] = data1[row+3];
		 data[4] = data1[row+4];
		 data[5] = data1[row+5];
		 dtm.addRow(data);
		 row=row+6;
		 }
		 
		 //se define el tamaño
		 table.setPreferredScrollableViewportSize(new Dimension(400, 70));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		 //manejamos la salida
		addWindowListener(new WindowAdapter() {});
		//Hasta aqui sera la tabla
		exportarPdf.addActionListener(this);
		add(exportarPdf);
		addWindowListener(this);
		setSize(500,200);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		table.setEnabled(false); //Evita que se pueda editar la tabla.
	}
	public void actionPerformed(ActionEvent ez)
	{
		// Se crea el documento 
				Document documento = new Document();
				try 
				{ 
					// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
					FileOutputStream ficheroPdf = new FileOutputStream("ConsultaClinicas.pdf");
					PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
					// Se abre el documento. 
					documento.open();
					Paragraph titulo = new Paragraph("Informe de Clinicas", 
							FontFactory.getFont("arial", // fuente 
									22, // tamaño 
									Font.ITALIC, // estilo 
									BaseColor.BLUE)); // color
					titulo.setAlignment(Element.ALIGN_CENTER);
					documento.add(titulo);
					// Sacar los datos
					conexion = bd.conectar();
					String[] cadena = bd.consultarClinicas(conexion).split("\n");
					bd.desconectar(conexion);
					PdfPTable tabla = new PdfPTable(6); // Se indica el número de columnas
					tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
					tabla.addCell("id");
					tabla.addCell("NIF Clinica");
					tabla.addCell("Nombre Clinica");
					tabla.addCell("Direccion Clinica");
					tabla.addCell("Telefono Clinica");
					tabla.addCell("Email Clinica");
					// En cada posición de cadena tenemos un registro completo
					// cadena[0] = "1-Fulanito-12/12/2020"
					String[] subCadena;
					// En subCadena, separamos cada campo por -
					// subCadena[0] = 1
					// subCadena[1] = Fulanito
					// subCadena[2] = 12/12/2020
					for (int i = 0; i < cadena.length; i++) 
					{
						subCadena = cadena[i].split("-");
						for(int j = 0; j < 6;j++)
						{
							tabla.addCell(subCadena[j]);
						}
					}
					documento.add(tabla); 
					documento.close(); 
					//Abrimos el archivo PDF recién creado 
					try 
					{
						File path = new File ("ConsultaClinicas.pdf"); 
						Desktop.getDesktop().open(path); 
					}
					catch (IOException ex) 
					{
						System.out.println("Se ha producido un error al abrir el archivo PDF"); 
					}
				}
				catch ( Exception e ) 
				{ 
					System.out.println("Se ha producido un error al generar el archivo PDF");  
				}
	}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
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