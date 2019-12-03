package pem.proyectoSensores;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import pem.proyectoSensores.films.presenter.IPresenterFilms;
import pem.proyectoSensores.films.presenter.PresenterFilms;
import pem.proyectoSensores.films.view.IViewFilms;
import pem.proyectoSensores.home.presenter.IPresenterHome;
import pem.proyectoSensores.home.presenter.PresenterHome;
import pem.proyectoSensores.home.view.IViewHome;
import pem.proyectoSensores.login.IPresenterLogin;
import pem.proyectoSensores.login.IViewLogin;
import pem.proyectoSensores.login.LoginActivity;
import pem.proyectoSensores.login.PresenterLogin;
import pem.proyectoSensores.register.IPresenterRegister;
import pem.proyectoSensores.register.IViewRegister;
import pem.proyectoSensores.register.PresenterRegister;
import pem.proyectoSensores.register.RegisterActivity;
import pem.proyectoSensores.series.presenter.IPresenterSeries;
import pem.proyectoSensores.series.presenter.PresenterSeries;
import pem.proyectoSensores.series.view.IViewSeries;


//import pem.tema4.presentador.IPresentadorMapa;
//import pem.tema4.presentador.PresentadorMapa;
//import pem.tema4.vista.IVistaMapa;

@SuppressWarnings("rawtypes")
public class AppMediador extends Application {
	private static AppMediador singleton;

	// variables correspondientes a los presentadores, vistas y modelo
	private IPresenterLogin presenterLogin;
	private IViewLogin viewLogin;
	private IPresenterRegister presenterRegister;
	private IViewRegister viewRegister;
	// variables correspondientes a los presentadores, vistas y modelo
	private IPresenterHome presenterHome;
	private IViewHome viewHome;
	private IPresenterSeries presenterSeries;
	private IViewSeries viewSeries;
	private IPresenterFilms presenterFilms;
	private IViewFilms viewFilms;

	// constantes de comunicación, almacenamiento y petición
	public static final String CLAVE_LISTA_ITEM = "listaRecetas";
	public static final String AVISO_DATOS_LISTOS = "pem.tema4.AVISO_DATOS_LISTOS";
	public static final String CLAVE_DETALLE_ITEM = "detalleReceta";
	public static final String AVISO_DETALLE_LISTO = "pem.tema4.AVISO_DETALLE_LISTO";
	public static final String AVISO_DATOS_AGREGADOS = "pem.tema4.AVISO_DATOS_AGREGADOS";


	// constantes de comunicación, almacenamiento y petición
	public static final int ZOOM = 12; //este valor debería ser una preferencia de la aplicación, pero como no tenemos...
	public static final int ESTADO_INICIAL = 0;
	public static final int ESTADO_AGREGAR_MARCA = 1;
	public static final int ESTADO_BORRAR_MARCA = 2;
	public static final String CLAVE_USERNAME = "username";
	public static final String CLAVE_PASSWORD = "password";
	public static final String CLAVE_FAILED = "failed";
	public static final String CLAVE_SUCCESS = "success";

	public static final String AVISO_ESTADO_INICIAL = "pem.tema4.AVISO_ESTADO_INICIAL";
	public static final String AVISO_LOCALIZACION_GPS = "pem.tema4.AVISO_LOCALIZACION_GPS";
	public static final String AVISO_AGREGAR_MARCA = "pem.tema4.AVISO_AGREGAR_MARCA";
	public static final String AVISO_BORRAR_MARCA = "pem.tema4.AVISO_BORRAR_MARCA";
	public static final String AVISO_AUTHENTICATION_SUCCESS = "pem.tema4.seriesmania.AVISO_AUTHENTICATION_SUCCESS";
	public static final String AVISO_AUTHENTICATION_FAILED = "pem.tema4.seriesmania.AVISO_AUTHENTICATION_FAILED";


	public static AppMediador getInstance(){
		return singleton;
	}

	public IPresenterHome getPresenterHome() {
		if (presenterHome == null)
			presenterHome = new PresenterHome();
		return presenterHome;
	}

	public void removePresenterHome() {
		presenterHome = null;
	}

	public IViewHome getViewHome() {
		return viewHome;
	}

	public void setViewHome(IViewHome viewHome) {
		this.viewHome = viewHome;
	}

	//////////////////////////SERIES////////////////////////////////
	public IPresenterSeries getPresenterSeries() {
		if (presenterSeries == null)
			presenterSeries = new PresenterSeries();
		return presenterSeries;
	}

	public void removePresenterSeries() {
		presenterFilms = null;
	}

	public IViewFilms getViewFilms() {
		return viewFilms;
	}

	public void setViewSeries(IViewSeries viewSeries) {
		this.viewSeries = viewSeries;
	}

    //////////////////////////FILMS////////////////////////////////
    public IPresenterFilms getPresenterFilms() {
        if (presenterFilms == null)
            presenterFilms = new PresenterFilms();
        return presenterFilms;
    }


    public void removePresenterFilms() {
        presenterSeries = null;
    }

    public IViewSeries getViewSeries() {
        return viewSeries;
    }

    public void setViewFilms(IViewFilms viewFilms) {
        this.viewFilms = viewFilms;
    }


	/*public void setVistaMapa(IVistaMapa vistaMapa) {
		this.vistaMapa = vistaMapa;
	}
*/
	public IPresenterLogin getPresenterLogin() {
		if (presenterLogin == null)
			presenterLogin = new PresenterLogin();
			return presenterLogin;

	}

	public IViewLogin getViewLogin() {
		return viewLogin;
	}

	public void setViewLogin(LoginActivity loginActivity) {
		this.viewLogin = loginActivity;
	}

	//////////////////////////////////////////////////////////////////

	public void setViewRegister(RegisterActivity registerActivity) {
		this.viewRegister = registerActivity;
	}
	public IPresenterRegister getPresenterRegister() {
		if (presenterRegister == null)
			presenterRegister = new PresenterRegister();
		return presenterRegister;

	}

	public IViewRegister getViewRegister() {
		return viewRegister;
	}




	/*public IVistaMapa getVistaMapa() {
		return vistaMapa;
	}
*/

/*	// Métodos accessor de los presentadores, vistas y modelo
	public IPresentadorMapa getPresentadorMapa() {
		if (presentadorMapa == null)
			presentadorMapa = new PresentadorMapa();
		return presentadorMapa;
	}*/

	/*public void removePresentadorMapa() {
		presentadorMapa = null;
	}
*/

	// Métodos destinados a la navegación en la aplicación y a la definición de servicios

	// Métodos de manejo de los componentes de Android

	public void launchActivity(Class actividadInvocada, Object invocador, Bundle extras) {
		Intent i = new Intent(this, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		if (!invocador.getClass().equals(Activity.class))
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}

	public void launchActivityForResult(Class actividadInvocada,
                                        Activity actividadInvocadora, int requestCode, Bundle extras) {
		Intent i = new Intent(actividadInvocadora, actividadInvocada);
		if (extras != null)
			i.putExtras(extras);
		actividadInvocadora.startActivityForResult(i, requestCode);
	}

	public void launchService(Class servicioInvocado, Bundle extras) {
		Intent i = new Intent(this, servicioInvocado);
		if (extras != null)
			i.putExtras(extras);
        startService(i);
	}

	public void stopService(Class servicioInvocado) {
		Intent i = new Intent(this, servicioInvocado);
        stopService(i);
	}

	public void registerReceiver(BroadcastReceiver receptor, String accion) {
		LocalBroadcastManager.getInstance(this).registerReceiver(receptor, new IntentFilter(accion));
	}

	public void unRegisterReceiver(BroadcastReceiver receptor) {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(receptor);
	}

	public void sendBroadcast(String accion, Bundle extras) {
		Intent intent = new Intent();
		intent.setAction(accion);
		if (extras != null)
			intent.putExtras(extras);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	@Override
	public void onCreate() {
		super.onCreate();
//		presentadorMapa = null;
		singleton = this;
	}
}
