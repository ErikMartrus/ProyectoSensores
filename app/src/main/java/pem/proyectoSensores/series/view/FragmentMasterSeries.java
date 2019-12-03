package pem.proyectoSensores.series.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pem.proyectoSensores.R;

public class FragmentMasterSeries extends Fragment implements AdapterSeries.SeleccionListener {

	private RecyclerView recyclerView;
	private EscuchaFragmento escucha;

	@Override
	public void onClick(AdapterSeries.FilaViewHolder fvh, int posicion) {
		escucha.alSeleccionarItem(posicion);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_fragmento_maestro, container, false);
		recyclerView = (RecyclerView)v.findViewById(R.id.lista);
		return v;
	}
	
	@Override
	public void onAttach(Context contexto) {
		super.onAttach(contexto);
		if (contexto instanceof EscuchaFragmento) {
			escucha = (EscuchaFragmento) contexto;
		} else {
			throw new RuntimeException(contexto.toString()
					+ " debes implementar EscuchaFragmento");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		escucha = null;
	}

	public void crearLista(String[] datos) {
		if (datos == null)
			return;
		// crea un adapterSeries
		AdapterSeries adapterSeries = new AdapterSeries(datos, this);
		recyclerView.setAdapter(adapterSeries);
	}

	public interface EscuchaFragmento {
		void alSeleccionarItem(int posicion);
	}
}
