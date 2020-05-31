package es.Studium.PracticaT2;
//Importamos los paquetes
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal extends Frame implements WindowListener, ActionListener
{
	//Login ULogeado=new Login();
	//String Comprobar=ULogeado.getULogeado();
	//String UAdmin="Administrador";
	//String UUser="Usuario";
	
	Login tipoUsuario=new Login();
	String ComprobarTipoUsuario=tipoUsuario.gettipoUsuario();
	Login nombreUsuario=new Login();
	String NombreUser=nombreUsuario.getnombreUsuario();
	String TAdmin="0";
	String TUser="1";
	
	private static final long serialVersionUID = 1L;
	//Creamos la barra de menu
	MenuBar barraMenu = new MenuBar();

	//Creamos los elementos del menu
	Menu mnuTrabajos = new Menu("Trabajos");
	Menu mnuArticulos = new Menu("Artículos");
	Menu mnuClinicas  = new Menu("Clínicas");
	//Creamos los elementos del menu trabajo
	MenuItem mniConsultaTrabajos = new MenuItem("Consulta");
	MenuItem mniAltaTrabajos = new MenuItem("Alta");
	MenuItem mniModificaTrabajos = new MenuItem("Modificación");
	MenuItem mniBajaTrabajos = new MenuItem("Baja");
	//Creamos los elementos del menu Articulos
	MenuItem mniConsultaArticulos = new MenuItem("Consulta");
	MenuItem mniAltaArticulos = new MenuItem("Alta");
	MenuItem mniModificaArticulos = new MenuItem("Modificación");
	MenuItem mniBajaArticulos = new MenuItem("Baja");
	//Creamos los elementos del menu Clinicas
	MenuItem mniConsultaClinicas  = new MenuItem("Consulta");
	MenuItem mniAltaClinicas = new MenuItem("Alta");
	MenuItem mniModificaClinicas  = new MenuItem("Modificación");
	MenuItem mniBajaClinicas  = new MenuItem("Baja");
	
	
	MenuPrincipal()
	{
		setTitle("Laboratorio Dental");
		setLayout(new FlowLayout());
		//Añadimos los elementos de trabajo a menu trabajo
		//if(Comprobar.equals(UAdmin)) {
		if(ComprobarTipoUsuario.equals(TAdmin)) {
		mnuTrabajos.add(mniConsultaTrabajos);
		mnuTrabajos.add(mniAltaTrabajos);
		mnuTrabajos.add(mniModificaTrabajos);
		mnuTrabajos.add(mniBajaTrabajos);
		}
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		else if((ComprobarTipoUsuario.equals(TAdmin))||(ComprobarTipoUsuario.equals(TUser)))
		{
			mnuTrabajos.add(mniAltaTrabajos);
		}
		//Añadimos los elementos de trabajo a menu Articulos
		//if(Comprobar.equals(UAdmin)) {
		if(ComprobarTipoUsuario.equals(TAdmin)) {
		mnuArticulos.add(mniConsultaArticulos);
		mnuArticulos.add(mniAltaArticulos);
		mnuArticulos.add(mniModificaArticulos);
		mnuArticulos.add(mniBajaArticulos);
		}
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		else if((ComprobarTipoUsuario.equals(TAdmin))||(ComprobarTipoUsuario.equals(TUser)))
		{
			mnuArticulos.add(mniAltaArticulos);
		}
		//Añadimos los elementos de trabajo a menu Clinicas
		if(ComprobarTipoUsuario.equals(TAdmin)) {
		//if(Comprobar.equals(UAdmin)) {
		mnuClinicas.add(mniConsultaClinicas);
		mnuClinicas.add(mniAltaClinicas);
		mnuClinicas.add(mniModificaClinicas);
		mnuClinicas.add(mniBajaClinicas);
		}
		else if((ComprobarTipoUsuario.equals(TAdmin))||(ComprobarTipoUsuario.equals(TUser)))
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		{
			mnuClinicas.add(mniAltaClinicas);
		}
		//Anadimos los menus a la barra de menu
		barraMenu.add(mnuTrabajos);
		barraMenu.add(mnuArticulos);
		barraMenu.add(mnuClinicas);
		//Añadimos los listener
		addWindowListener(this);
		mniAltaTrabajos.addActionListener(this);
		mniBajaTrabajos.addActionListener(this);
		mniConsultaTrabajos.addActionListener(this);
		mniModificaTrabajos.addActionListener(this);
		
		mniAltaArticulos.addActionListener(this);
		mniBajaArticulos.addActionListener(this);
		mniConsultaArticulos.addActionListener(this);
		mniModificaArticulos.addActionListener(this);
		
		mniAltaClinicas.addActionListener(this);
		mniBajaClinicas.addActionListener(this);
		mniConsultaClinicas.addActionListener(this);
		mniModificaClinicas.addActionListener(this);
		//mostramos la barra de menu
		setMenuBar(barraMenu);
		setSize(400,150); //Ponemos el tamaño de la ventana
		setResizable(false);//Ponemos que no se pueda cambiar el tamaño de la ventana.
		setLocationRelativeTo(null);//Indicamos la localizacion de la ventana
		setVisible(true);//Hacemos visibles la ventana
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new Login();
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		Object objetoPulsado = e.getSource(); //Obtenemos el objeto pulsado y lo guardamos en una variable
		//Comprobamos si el objeto pulsado es alguno de los siguientes.
		if(objetoPulsado.equals(mniConsultaTrabajos)) 
		{
			new ConsultaTrabajos();
		}
		else if(objetoPulsado.equals(mniAltaTrabajos))
		{
			new AltaTrabajos();
		}
		else if(objetoPulsado.equals(mniModificaTrabajos)) 
		{
			new ModificaTrabajos();
		}
		else if(objetoPulsado.equals(mniBajaTrabajos)) 
		{
			new BajaTrabajos();
		}
		
		else if(objetoPulsado.equals(mniConsultaArticulos)) 
		{
			new ConsultaArticulos();
		}
		else if(objetoPulsado.equals(mniAltaArticulos)) 
		{
			new AltaArticulos();
		}
		else if(objetoPulsado.equals(mniModificaArticulos)) 
		{
			new ModificaArticulos();
		}
		else if(objetoPulsado.equals(mniBajaArticulos)) 
		{
			new BajaArticulos();
		}
		
		else if(objetoPulsado.equals(mniConsultaClinicas)) 
		{
			new ConsultaClinicas();
		}
		else if(objetoPulsado.equals(mniAltaClinicas)) 
		{
			new AltaClinicas();
		}
		else if(objetoPulsado.equals(mniModificaClinicas)) 
		{
			new ModificaClinicas();
		}
		else if(objetoPulsado.equals(mniBajaClinicas)) 
		{
			new BajaClinicas();
		}
	}
	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		System.exit(0); //Cerramos el programa
	}
	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}