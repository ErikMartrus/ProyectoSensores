package pem.proyectoSensores.films.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import pem.proyectoSensores.AppMediador;
import pem.proyectoSensores.films.model.IModelFilms;
import pem.proyectoSensores.films.model.ItemFilms;
import pem.proyectoSensores.films.model.ModelFilms;

public class PresenterFilms implements IPresenterFilms {

    private AppMediador appMediador;

	// TODO Declarar una variable modelo para acceder al ModelHome
    private IModelFilms modelo;

	// TODO Declarar e implementar el receptor broadcast que espera por la notificacion del modelo.
	// El modelo notificara cuando los datos de los telefonos a notificar estan disponibles.
    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_DATOS_LISTOS)){
                ArrayList<ItemFilms> infoReceta = (ArrayList<ItemFilms>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_ITEM);
                String[] datos = new String[infoReceta.size()];
                for (int i = 0; i < infoReceta.size(); i++){
                    datos[i] = infoReceta.get(i).getName();
                }
                appMediador.getViewFilms().actualizarMaestro(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DETALLE_LISTO)){
                String[] datosDetalle = intent.getStringArrayExtra(AppMediador.CLAVE_DETALLE_ITEM);
                Object[] datos = new Object[3];
                datos[0] = datosDetalle[0];
                datos[1] = BitmapFactory.decodeFile(datosDetalle[2]);
                datos[2] = datosDetalle[1];
                appMediador.getViewFilms().actualizarDetalle(datos);
            }
            appMediador.unRegisterReceiver(this);
        }
    };

	// TODO Implementar un constructor que crea el modelo.
    public PresenterFilms(){
        appMediador = AppMediador.getInstance();
        modelo = ModelFilms.getInstance();
    }


    // TODO Implementar el metodo obtenerDatos() que registra el receptor para recibir notificaciones y
	// solicita al modelo que recupere los datos de la lista maestro.
    @Override
    public void obtenerDatos() {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DATOS_LISTOS);
        modelo.obtenerDatos();

    }

    // TODO Implementar el metodo obtenerDetalle(int posicion) que registra el receptor para recibir
	// notificaciones y solicita al modelo que recupere los datos de la lista detalle para una receta dada su posicion.
    @Override
    public void obtenerDetalle(int posicion) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DETALLE_LISTO);
        modelo.obtenerDetalle(posicion);
    }

	// TODO Implementar el metodo tratarAgregar() que lanza la vista de agregacion por medio del mediador.

}
