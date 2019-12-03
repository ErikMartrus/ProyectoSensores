package pem.proyectoSensores.series.presenter;

public interface IPresenterSeries {
	
	// TODO Anadir el metodo obtenerDatos() de tipo void que solicita los datos de la lista maestro al modelo.
    void obtenerDatos();

	// TODO Anadir el metodo obtenerDetalles() que recupera los datos de una receta dada su posicion
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO Anadir el metodo tratarAgregar() que lanza la vista de agregacion por medio del mediador.

}
