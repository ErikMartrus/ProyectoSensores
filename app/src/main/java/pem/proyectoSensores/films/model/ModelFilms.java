package pem.proyectoSensores.films.model;

import android.os.Bundle;

import pem.proyectoSensores.AppMediador;

public class ModelFilms implements IModelFilms {

    private static ModelFilms singleton = null;
    private SetOfFilms setOfFilms;
    private AppMediador appMediador;

    private ModelFilms() {
        appMediador = AppMediador.getInstance();
        setOfFilms = SetOfFilms.getInstance();
    }

    public static ModelFilms getInstance() {
        if (singleton == null)
            singleton = new ModelFilms();
        return singleton;
    }



    // TODO Implementar el metodo obtenerDatos() que recupera los datos de la lista de recetas del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DATOS_LISTOS al presentador.
    @Override
    public void obtenerDatos() {
        Bundle extras = new Bundle();
        extras.putSerializable(AppMediador.CLAVE_LISTA_ITEM, setOfFilms.getListaDeRecetas());
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_LISTOS, extras);
    }

    // TODO Implementar el metodo obtenerDetalle(int posicion) que recupera los datos del detalle de una receta del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DETALLE_LISTO al presentador.
    @Override
    public void obtenerDetalle(int posicion) {
        ItemFilms receta = setOfFilms.getListaDeRecetas().get(posicion);
        String[] datos = new String[4];
        datos[0] = receta.getName();
        datos[1] = receta.getDescription();
        datos[2] = receta.getImage();
        datos[3] = receta.getTrailer();

        Bundle extras = new Bundle();
        extras.putStringArray(AppMediador.CLAVE_DETALLE_ITEM, datos);
        appMediador.sendBroadcast(AppMediador.AVISO_DETALLE_LISTO, extras);
    }

	// TODO Anadir el metodo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas. En la posicion 0 se almacena el nombre del archivo de imagen, en la posicion 1 se
	// almacena el nombre de la receta y en la posicion 2 se almacena la descripcion de la receta.

}

