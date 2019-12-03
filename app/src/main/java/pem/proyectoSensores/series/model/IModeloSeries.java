package pem.proyectoSensores.series.model;

public interface IModeloSeries {
	
	// TODO Anadir el metodo obtenerDatos() que recupera los datos a mostrar en la lista maestro.
    void obtenerDatos();

	// TODO Anadir el metodo obtenerDetalles() que recupera los datos de una receta dada su posicion
	// en la lista maestro.
    void obtenerDetalle(int posicion);

	// TODO Anadir el metodo agregarReceta(Object[] datos) que almacena una nueva receta en la lista
	// de recetas.

}


