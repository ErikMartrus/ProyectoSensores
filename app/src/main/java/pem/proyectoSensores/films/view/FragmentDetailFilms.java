package pem.proyectoSensores.films.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pem.proyectoSensores.R;

public class FragmentDetailFilms extends Fragment {
	
	private TextView nombreReceta;
	private ImageView imagenDeReceta;
	private TextView infoReceta;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragmento_detalle,
				container, false); 
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		nombreReceta = (TextView) getView().findViewById(R.id.name);
		imagenDeReceta = (ImageView) getView().findViewById(R.id.imagenDeReceta);
		infoReceta = (TextView) getView().findViewById(R.id.infoReceta);
	}
	
	public void actualizarNombreReceta(String nombre) {
		nombreReceta.setText(nombre);
	}

	public void actualizarImagenReceta(Bitmap imagen) {
		imagenDeReceta.setImageBitmap(imagen);
	}

	public void actualizarDescripcion(String descripcion) {
		infoReceta.setText(descripcion);
	}

	
	
}
