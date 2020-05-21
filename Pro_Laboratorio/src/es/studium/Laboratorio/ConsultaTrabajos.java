package es.studium.Laboratorio;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConsultaTrabajos extends JFrame implements WindowListener, ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L;
	TextArea txaListado = new TextArea(13,40);
	Button exportarPdf = new Button("Exportar PDF");
	Choice choLista = new Choice();
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	String[] cadena;
	
	public ConsultaTrabajos()
	{
		setTitle("Consulta Trabajos");
		setLayout(new FlowLayout());	
		conexion = bd.conectar();
		String[] data1 = bd.consultarTrabajosTabla(conexion).split("#");
		 //creamos el arreglo de objetos que contendra el
		 //contenido de las columnas
		 Object[] data = new Object[3];
		// creamos el modelo de Tabla
		 DefaultTableModel dtm= new DefaultTableModel();
		 // se crea la Tabla con el modelo DefaultTableModel
		 final JTable table = new JTable(dtm);
		 // insertamos las columnas
		 dtm.addColumn("id Trabajo");
		 dtm.addColumn("Descripción");
		 dtm.addColumn("id Clinica");
		 // insertamos el contenido de las columnas
		 for(int row = 0; row < data1.length;) {
		 data[0] = data1[row];
		 data[1] = data1[row+1];
		 data[2] = data1[row+2];
		 dtm.addRow(data);
		 row=row+3;
		 }
		 //se define el tamaño
		 table.setPreferredScrollableViewportSize(new Dimension(300, 70));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		 //manejamos la salida
		addWindowListener(new WindowAdapter() {});	
		exportarPdf.addActionListener(this);
		// Rellenar
		choLista.add("Seleccionar un trabajo, para detalles");
		cadena = (bd.consultarTrabajosChoice(conexion)).split("#");
		for(int i = 0; i < cadena.length; i++)
		{
			choLista.add(cadena[i]);
		}
		choLista.addItemListener(this);
		add(choLista);
		bd.desconectar(conexion);
		add(exportarPdf);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		table.setEnabled(false); //Evita que se pueda editar la tabla.
	}
	@Override
	public void actionPerformed(ActionEvent arg0){
		// Se crea el documento 
		Document documento = new Document();
		try 
		{ 
			// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
			FileOutputStream ficheroPdf = new FileOutputStream("ConsultaTrabajos.pdf");
			PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
			// Se abre el documento. 
			documento.open();
			Paragraph titulo = new Paragraph("Informe de Trabajos", 
					FontFactory.getFont("arial", // fuente 
							22, // tamaño 
							Font.ITALIC, // estilo 
							BaseColor.BLUE)); // color
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			// Sacar los datos
			conexion = bd.conectar();
			String[] cadena = bd.consultarTrabajos(conexion).split("\n");
			bd.desconectar(conexion);
			PdfPTable tabla = new PdfPTable(3); // Se indica el número de columnas
			tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
			tabla.addCell("id Trabajo");
			tabla.addCell("Descripción Trabajo");
			tabla.addCell("id Clínica");
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
				for(int j = 0; j < 3;j++)
				{
					tabla.addCell(subCadena[j]);
				}
			}
			documento.add(tabla); 
			documento.close(); 
			//Abrimos el archivo PDF recién creado 
			try 
			{
				File path = new File ("ConsultaTrabajos.pdf"); 
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
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		setVisible(false);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0){}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent arg0){}
	@Override
	public void windowOpened(WindowEvent arg0){}
	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		// Averiguar elemento seleccionado
		String[] seleccionado = choLista.getSelectedItem().split("-");
		// seleccionado[0] --> idTrabajo
		new DetalleTrabajos(Integer.parseInt(seleccionado[0]));
	}
}