package pem.proyectoSensores.series.view;

public interface IViewSeries {
	
	// TODO Anadir el metodo actualizarMaestro(Object[] datos) que actualiza la lista maestro con los datos
	// recibidos por parametros. En cada entrada del vector, esta el nombre de una receta.
    void actualizarMaestro(Object[] datos);

	// TODO Anadir el metodo actualizarDetalle(Object[] datos) que actualiza la lista detalle con los datos
	// de una receta recibidos por parametros. Asi:
	// datos[0] = almacena el nombre de la receta y en que se usa para realizarla (String).
	// datos[1] = almacena una imagen de la receta (Bitmap).
	// datos[2] = almacena la descripcion de la receta (String).
    void actualizarDetalle(Object[] datos);

}
