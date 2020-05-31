package es.studium.Laboratorio;

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
<<<<<<< HEAD

	String TAdmin="0";
	String TUser="1";
	String UserName="";
	
	
	Utilidades utilidad = new Utilidades();
	
	private static final long serialVersionUID = 1L;
	//Creamos la barra de menu
		MenuBar barraMenu = new MenuBar();

		//Creamos los elementos del menu
		Menu mnuTrabajos = new Menu("Trabajos");
		Menu mnuArticulos = new Menu("Artículos");
		Menu mnuClinicas  = new Menu("Clínicas");
		Menu mnuAyuda = new Menu("Ayuda");
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
	
		MenuItem mniAyuda = new MenuItem("Ayuda");
	
	public MenuPrincipal(String nombreUsuario, String tipoUsuario)
	{
		UserName=nombreUsuario;
		setTitle("Laboratorio Dental");
		setLayout(new FlowLayout());
		//Añadimos los elementos de trabajo a menu trabajo
		//if(Comprobar.equals(UAdmin)) {
		if(tipoUsuario.equals(TAdmin)) {
		mnuTrabajos.add(mniConsultaTrabajos);
		mnuTrabajos.add(mniAltaTrabajos);
		mnuTrabajos.add(mniModificaTrabajos);
		mnuTrabajos.add(mniBajaTrabajos);
		}
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		else if((tipoUsuario.equals(TAdmin))||(tipoUsuario.equals(TUser)))
		{
			mnuTrabajos.add(mniAltaTrabajos);
		}
		//Añadimos los elementos de trabajo a menu Articulos
		//if(Comprobar.equals(UAdmin)) {
		if(tipoUsuario.equals(TAdmin)) {
		mnuArticulos.add(mniConsultaArticulos);
		mnuArticulos.add(mniAltaArticulos);
		mnuArticulos.add(mniModificaArticulos);
		mnuArticulos.add(mniBajaArticulos);
		}
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		else if((tipoUsuario.equals(TAdmin))||(tipoUsuario.equals(TUser)))
		{
			mnuArticulos.add(mniAltaArticulos);
		}
		//Añadimos los elementos de trabajo a menu Clinicas
		if(tipoUsuario.equals(TAdmin)) {
		//if(Comprobar.equals(UAdmin)) {
		mnuClinicas.add(mniConsultaClinicas);
		mnuClinicas.add(mniAltaClinicas);
		mnuClinicas.add(mniModificaClinicas);
		mnuClinicas.add(mniBajaClinicas);
		}
		else if((tipoUsuario.equals(TAdmin))||(tipoUsuario.equals(TUser)))
		//else if((Comprobar.equals(UAdmin))||(Comprobar.equals(UUser)))
		{
			mnuClinicas.add(mniAltaClinicas);
		}
		mnuAyuda.add(mniAyuda);
		
		//Anadimos los menus a la barra de menu
		barraMenu.add(mnuTrabajos);
		barraMenu.add(mnuArticulos);
		barraMenu.add(mnuClinicas);
		barraMenu.add(mnuAyuda);
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
		
		mniAyuda.addActionListener(this);
		//mostramos la barra de menu
		setMenuBar(barraMenu);
		setSize(400,150); //Ponemos el tamaño de la ventana
		setResizable(false);//Ponemos que no se pueda cambiar el tamaño de la ventana.
		setLocationRelativeTo(null);//Indicamos la localizacion de la ventana
		setVisible(true);//Hacemos visibles la ventana
	}
	public static void main(String[] args)
	{
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object objetoPulsado = arg0.getSource(); //Obtenemos el objeto pulsado y lo guardamos en una variable
		//Comprobamos si el objeto pulsado es alguno de los siguientes.
		if(objetoPulsado.equals(mniConsultaTrabajos)) 
		{
			new ConsultaTrabajos(UserName);
		}
		else if(objetoPulsado.equals(mniAltaTrabajos))
		{
			new AltaTrabajos(UserName);
		}
		else if(objetoPulsado.equals(mniModificaTrabajos)) 
		{
			new EdicionTrabajos(UserName);
		}
		else if(objetoPulsado.equals(mniBajaTrabajos)) 
		{
			new BajaTrabajos(UserName);
		}
		
		else if(objetoPulsado.equals(mniConsultaArticulos)) 
		{
			new ConsultaArticulos(UserName);
		}
		else if(objetoPulsado.equals(mniAltaArticulos)) 
		{
			new AltaArticulos(UserName);
		}
		else if(objetoPulsado.equals(mniModificaArticulos)) 
		{
			new EdicionArticulos(UserName);
		}
		else if(objetoPulsado.equals(mniBajaArticulos)) 
		{
			new BajaArticulos(UserName);
		}
		
		else if(objetoPulsado.equals(mniConsultaClinicas)) 
		{
			new ConsultaClinicas(UserName);
		}
		else if(objetoPulsado.equals(mniAltaClinicas)) 
		{
			new AltaClinicas(UserName);
		}
		else if(objetoPulsado.equals(mniModificaClinicas)) 
		{
			new EdicionClinicas(UserName);
		}
		else if(objetoPulsado.equals(mniBajaClinicas)) 
		{
			new BajaClinicas(UserName);
		}
		else if(objetoPulsado.equals(mniAyuda)) 
		{
			//new Ayuda();
			utilidad.Ayuda();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowClosing(WindowEvent arg0){
		utilidad.registrarLog(UserName,"Saliendo del programa");
=======
	Login tipoUsuario=new Login();
	String ComprobarTipoUsuario=tipoUsuario.gettipoUsuario();
	Login nombreUsuario=new Login();
	String nombreUser=nombreUsuario.getnombreUsuario();
	String TAdmin="0";
	String TUser="1";
	
	
	
	Utilidades utilidad = new Utilidades();
	
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
	
	Menu ayuda = new Menu("Ayuda");
	MenuItem mniAyuda = new MenuItem("Ayuda");
	
	public MenuPrincipal()
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
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object objetoPulsado = arg0.getSource(); //Obtenemos el objeto pulsado y lo guardamos en una variable
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
			new EdicionTrabajos();
		}
		else if(objetoPulsado.equals(mniBajaTrabajos)) 
		{
			//new BajaTrabajos();
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
			new EdicionArticulos();
		}
		else if(objetoPulsado.equals(mniBajaArticulos)) 
		{
			//new BajaArticulos();
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
			new EdicionClinicas();
		}
		else if(objetoPulsado.equals(mniBajaClinicas)) 
		{
			//new BajaClinicas();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0){}
	@Override
	public void windowClosing(WindowEvent arg0){
		utilidad.registrarLog(nombreUser,"Saliendo del programa");
>>>>>>> branch 'master' of https://github.com/AlvCarballo/Pro_Laboratorio
		System.exit(0);
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