package pem.proyectoSensores.home.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SetOfFavorites {

	private static SetOfFavorites singleton = null;
	private ArrayList<ItemFavorites> listaDeRecetas;
	
	private SetOfFavorites() {
		listaDeRecetas = new ArrayList<ItemFavorites>();
		listaDeRecetas.add(new ItemFavorites("bigBang","The Big Bang Theory","Thermomix"));
		listaDeRecetas.add(new ItemFavorites("narcos","Narcos","Thermomix"));
		listaDeRecetas.add(new ItemFavorites("ironMan","Iron Man","Vitro"));
		
	}
	public static SetOfFavorites getInstance() {
		if (singleton == null)
			singleton = new SetOfFavorites();
		return singleton;
	}

	public ArrayList<ItemFavorites> getListaDeRecetas() {
		return listaDeRecetas;
	}

	public void setListaDeRecetas(ArrayList<ItemFavorites> listaDeRecetas) {
		this.listaDeRecetas = listaDeRecetas;
	}
	
	public void agregarItem(ItemFavorites nuevo) {
		listaDeRecetas.add(nuevo);
		Collections.sort(listaDeRecetas, new Comparator<ItemFavorites>() {
	        @Override
	        public int compare(ItemFavorites item1, ItemFavorites item2) {
	            return  item1.getNombreReceta().compareTo(item2.getNombreReceta());
	        }
	    });
	}
}


