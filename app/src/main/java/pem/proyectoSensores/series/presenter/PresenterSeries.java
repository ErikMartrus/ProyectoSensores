package pem.proyectoSensores.series.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import pem.proyectoSensores.series.model.IModeloSeries;
import pem.proyectoSensores.series.model.ItemSeries;
import pem.proyectoSensores.series.model.ModeloSeries;
import pem.proyectoSensores.AppMediador;

public class PresenterSeries implements IPresenterSeries {

    private AppMediador appMediador;

	// TODO Declarar una variable modelo para acceder al ModelHome
    private IModeloSeries modelo;

	// TODO Declarar e implementar el receptor broadcast que espera por la notificacion del modelo.
	// El modelo notificara cuando los datos de los telefonos a notificar estan disponibles.
    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_DATOS_LISTOS)){
                ArrayList<ItemSeries> infoReceta = (ArrayList<ItemSeries>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_ITEM);
                String[] datos = new String[infoReceta.size()];
                for (int i = 0; i < infoReceta.size(); i++){
                    datos[i] = infoReceta.get(i).getName();
                }
                appMediador.getViewSeries().actualizarMaestro(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DETALLE_LISTO)){
                String[] datosDetalle = intent.getStringArrayExtra(AppMediador.CLAVE_DETALLE_ITEM);
//                Object[] datos = new Object[3];
//                datos[0] = datosDetalle[0] + "(" + datosDetalle[1] + ")";
//                datos[1] = BitmapFactory.decodeFile(datosDetalle[2]);
//                datos[2] = datosDetalle[3];
                appMediador.getViewSeries().actualizarDetalle(datosDetalle);
            }
            appMediador.unRegisterReceiver(this);
        }
    };

	// TODO Implementar un constructor que crea el modelo.
    public PresenterSeries(){
        appMediador = AppMediador.getInstance();
        modelo = ModeloSeries.getInstance();
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
