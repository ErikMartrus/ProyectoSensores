package pem.proyectoSensores.home.model;

import android.os.Bundle;
import android.os.Environment;

import pem.proyectoSensores.AppMediador;

public class ModelHome implements IModelHome {

    private static ModelHome singleton = null;
    private SetOfFavorites setOfFavorites;
    private AppMediador appMediador;

    private ModelHome() {
        appMediador = AppMediador.getInstance();
        setOfFavorites =	SetOfFavorites.getInstance();
    }

    public static ModelHome getInstance() {
        if (singleton == null)
            singleton = new ModelHome();
        return singleton;
    }



    // TODO Implementar el metodo obtenerDatos() que recupera los datos de la lista de recetas del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DATOS_LISTOS al presentador.
    @Override
    public void obtenerDatos() {
        Bundle extras = new Bundle();
        extras.putSerializable(AppMediador.CLAVE_LISTA_ITEM, setOfFavorites.getListaDeRecetas());
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_LISTOS, extras);
    }

    // TODO Implementar el metodo obtenerDetalle(int posicion) que recupera los datos del detalle de una receta del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DETALLE_LISTO al presentador.
    @Override
    public void obtenerDetalle(int posicion) {
        ItemFavorites receta = setOfFavorites.getListaDeRecetas().get(posicion);
        String[] datos = new String[4];
        datos[0] = receta.getNombreReceta();
        datos[1] = receta.getAparatoReceta();
        datos[2] = Environment.getExternalStorageDirectory().getAbsolutePath() + "/imagenes/" + receta.getIdReceta() + ".png";
        datos[3] = AccessFileHome.leerReceta(receta.getIdReceta() + ".txt");

        Bundle extras = new Bundle();
        extras.putStringArray(AppMediador.CLAVE_DETALLE_ITEM, datos);
        appMediador.sendBroadcast(AppMediador.AVISO_DETALLE_LISTO, extras);
    }

	// TODO Anadir el metodo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas. En la posicion 0 se almacena el nombre del archivo de imagen, en la posicion 1 se
	// almacena el nombre de la receta y en la posicion 2 se almacena la descripcion de la receta.

}

