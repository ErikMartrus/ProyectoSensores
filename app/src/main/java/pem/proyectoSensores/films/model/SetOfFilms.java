package pem.proyectoSensores.films.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SetOfFilms {

	private static SetOfFilms singleton = null;
	private ArrayList<ItemFilms> listaDeRecetas;

	private SetOfFilms() {
		listaDeRecetas = new ArrayList<ItemFilms>();
		listaDeRecetas.add(new ItemFilms("ironMan","Iron Man","Thermomix", "", ""));
		listaDeRecetas.add(new ItemFilms("hulk","Hulk","Thermomix", "", ""));
		listaDeRecetas.add(new ItemFilms("8apellidosVascos","ocho Apellidos Vascos","Vitro", "", ""));

	}
	public static SetOfFilms getInstance() {
		if (singleton == null)
			singleton = new SetOfFilms();
		return singleton;
	}

	public ArrayList<ItemFilms> getListaDeRecetas() {
		return listaDeRecetas;
	}

	public void setListaDeRecetas(ArrayList<ItemFilms> listaDeRecetas) {
		this.listaDeRecetas = listaDeRecetas;
	}

	public void agregarItem(ItemFilms nuevo) {
		listaDeRecetas.add(nuevo);
		Collections.sort(listaDeRecetas, new Comparator<ItemFilms>() {
	        @Override
	        public int compare(ItemFilms item1, ItemFilms item2) {
	            return  item1.getName().compareTo(item2.getName());
	        }
	    });
	}
}


