package pem.proyectoSensores.series.model;

import android.os.Bundle;

import pem.proyectoSensores.AppMediador;

public class ModeloSeries implements IModeloSeries {

    private static ModeloSeries singleton = null;
    private SetOfSeries setOfSeries;
    private AppMediador appMediador;

    private ModeloSeries() {
        appMediador = AppMediador.getInstance();
        setOfSeries =	SetOfSeries.getInstance();
    }

    public static ModeloSeries getInstance() {
        if (singleton == null)
            singleton = new ModeloSeries();
        return singleton;
    }



    // TODO Implementar el metodo obtenerDatos() que recupera los datos de la lista de recetas del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DATOS_LISTOS al presentador.
    @Override
    public void obtenerDatos() {
        Bundle extras = new Bundle();
        extras.putSerializable(AppMediador.CLAVE_LISTA_ITEM, setOfSeries.getListSeries());
        appMediador.sendBroadcast(AppMediador.AVISO_DATOS_LISTOS, extras);
    }

    // TODO Implementar el metodo obtenerDetalle(int posicion) que recupera los datos del detalle de una receta del
	// conjunto de recetas y envia una notificacion del tipo AVISO_DETALLE_LISTO al presentador.
    @Override
    public void obtenerDetalle(int posicion) {
        ItemSeries series = setOfSeries.getListSeries().get(posicion);
        String[] datos = new String[4];
        datos[0] = series.getName();
        datos[1] = series.getDescription();
        datos[2] = series.getImage();
        datos[3] = series.getTrailer();


        Bundle extras = new Bundle();
        extras.putStringArray(AppMediador.CLAVE_DETALLE_ITEM, datos);
        appMediador.sendBroadcast(AppMediador.AVISO_DETALLE_LISTO, extras);
    }

	// TODO Anadir el metodo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas. En la posicion 0 se almacena el nombre del archivo de imagen, en la posicion 1 se
	// almacena el nombre de la receta y en la posicion 2 se almacena la descripcion de la receta.

}

