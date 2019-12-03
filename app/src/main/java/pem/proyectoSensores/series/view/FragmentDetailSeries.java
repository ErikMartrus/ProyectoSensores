package pem.proyectoSensores.series.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import pem.proyectoSensores.R;

public class FragmentDetailSeries extends Fragment {
	
	private TextView nombreReceta;
	private ImageView imagenDeReceta;
	private TextView infoReceta;
    private VideoView trailer;
    private Button share;

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
        trailer = getView().findViewById(R.id.trailer);
        share = getView().findViewById(R.id.button2);
        share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.setType("text/plain");
				sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(sendIntent);

			}
		});
	}
	
	public void actualizarNombreReceta(String nombre) {
		nombreReceta.setText(nombre);
	}

	public void actualizarImagenReceta(String imagen) {
        Picasso.get().load(imagen).into(imagenDeReceta);

	}

	public void actualizarDescripcion(String descripcion) {
		infoReceta.setText(descripcion);
	}

    public void actualizarVideo(String url) {
        Uri uri=Uri.parse(url);
        trailer.setVideoURI(uri);
        trailer.start();
    }

	
}
