package es.studium.Laboratorio;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class DetalleTrabajos extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	int idTrabajo;
	Label lblFactura = new Label("Factura Nº");
	Label lblDescripcionTrabajo = new Label("Descripcion Trabajo: ");
	Label lblNombreClinica = new Label("Clinica: ");
	Label lblTotal = new Label("Total");
	TextField txtTotal = new TextField(8);
	Button btnAceptar = new Button("Aceptar");
	Button btnExportar = new Button("Exportar PDF");
	int idFacturaFK;
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Double total = 0.0;

	public DetalleTrabajos(int idTrabajo)
	{
		this.idTrabajo = idTrabajo;
		setTitle("Detalle Trabajos");
		setLayout(new FlowLayout());
		lblFactura.setText("Trabajo Nº "+idTrabajo);
		add(lblFactura);
		// Conectar a la BD
		conexion = bd.conectar();
		// Sacar los datos de la factura concreta
		String[] data1 = bd.consultarTrabajoDetalleTabla(conexion, idTrabajo).split("#");
		
		// data1[0] = descripcionTrabajo
		// data1[1] = nombreArticulo
		// data1[2] = cantidadArticuloTrabajo
		// data1[3] = precioArticuloTrabajo
		// data1[4] = SubtotalArticuloTrabajo
		// data1[5] = nombreClinica
		lblDescripcionTrabajo.setText("Descripcion Trabajo: "+data1[0]);
		lblNombreClinica.setText("Clinica: "+data1[5]);
		add(lblDescripcionTrabajo);
		add(lblNombreClinica);
		 for(int i = 0; i < data1.length;i=i+6) {
		total = total + Double.parseDouble(data1[i+4]);
		 }
		//creamos el arreglo de objetos que contendra el
		 //contenido de las columnas
		 Object[] data = new Object[4];
		// creamos el modelo de Tabla
		 DefaultTableModel dtm= new DefaultTableModel();
		 // se crea la Tabla con el modelo DefaultTableModel
		 final JTable table = new JTable(dtm);
		 // insertamos las columnas
		 dtm.addColumn("Nombre Articulo");
		 dtm.addColumn("Cantidad Articulo");
		 dtm.addColumn("Precio Articulo");
		 dtm.addColumn("Subtotal Articulo");
		 // insertamos el contenido de las columnas
		 for(int row = 0; row < data1.length;row=row+6) {
		 data[0] = data1[row+1];
		 data[1] = data1[row+2];
		 data[2] = data1[row+3];
		 data[3] = data1[row+4];
		 dtm.addRow(data);
		 }
		 //se define el tamaño
		 table.setPreferredScrollableViewportSize(new Dimension(490, 100));
		 //Creamos un JscrollPane y le agregamos la JTable
		 JScrollPane scrollPane = new JScrollPane(table);
		 //Agregamos el JScrollPane al contenedor
		 getContentPane().add(scrollPane, BorderLayout.CENTER);
		 //manejamos la salida
		addWindowListener(new WindowAdapter() {});
		//Hasta aqui sera la tabla
		
		// Sacar los detalles de la factura concreta
		//txaListado.setText("Fecha:"+cadena[0]+"\n"+"Cliente:"+cadena[1]+"\n"+bd.consultarDetallesTrabajos(conexion, idTrabajo));
		// SELECT * FROM lineasFactura, servicios
		// Los idServicioFK y las cantidades correspondientes
		// Por cada idServicioFK, tengo que sacar la descripcion y el precio
		add(lblTotal);
		txtTotal.setText(total+"");
		txtTotal.setEnabled(false);
		add(txtTotal);
		btnAceptar.addActionListener(this);
		btnExportar.addActionListener(this);
		add(btnAceptar);
		add(btnExportar);
		addWindowListener(this);
		setSize(500,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		// Desconectar
		bd.desconectar(conexion);
		table.setEnabled(false); //Evita que se pueda editar la tabla.
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource().equals(btnAceptar))
		{
			setVisible(false);
		}
		else
		{
			// Exportar a PDF
			// Se crea el documento 
			Document documento = new Document();
			try 
			{ 
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
				FileOutputStream ficheroPdf = new FileOutputStream("ConsultaTrabajos.pdf");
				PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
				// Se abre el documento. 
				documento.open();
				Paragraph titulo = new Paragraph("Informe de Trabajo", 
						FontFactory.getFont("arial", // fuente 
								22, // tamaño 
								Font.ITALIC, // estilo 
								BaseColor.BLUE)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				// Sacar los datos
				conexion = bd.conectar();
				String[] cadena = bd.consultarTrabajoDetalleTabla(conexion, idTrabajo).split("#");
				bd.desconectar(conexion);
				PdfPTable tabla = new PdfPTable(4); // Se indica el número de columnas
				tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
				tabla.addCell("Nombre Articulo");
				tabla.addCell("Cantidad Articulo");
				tabla.addCell("Precio Articulo");
				tabla.addCell("Subtotal Articulo");
				// cadena[0] = descripcionTrabajo
				// cadena[1] = nombreArticulo
				// cadena[2] = cantidadArticuloTrabajo
				// cadena[3] = precioArticuloTrabajo
				// cadena[4] = SubtotalArticuloTrabajo
				// cadena[5] = nombreClinica
				Paragraph Trabajo = new Paragraph("Descripcion Trabajo: "+cadena[0], 
						FontFactory.getFont("arial", // fuente 
								12, // tamaño 
								Font.ITALIC, // estilo 
								BaseColor.BLUE));
				documento.add(Trabajo);
				Paragraph Clinica = new Paragraph("Nombre Clinica: "+cadena[5], 
						FontFactory.getFont("arial", // fuente 
								12, // tamaño 
								Font.ITALIC, // estilo 
								BaseColor.BLUE));
				documento.add(Clinica);
				 for(int row = 0; row < cadena.length;row=row+6) {
					 tabla.addCell( cadena[row+1]);
					 tabla.addCell( cadena[row+2]);
					 tabla.addCell( cadena[row+3]);
					 tabla.addCell( cadena[row+4]);
					
					 }
				documento.add(tabla);
				 Paragraph Total = new Paragraph("Total: "+total, 
							FontFactory.getFont("arial", // fuente 
									14, // tamaño 
									Font.ITALIC, // estilo 
									BaseColor.RED));
					documento.add(Total);
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
