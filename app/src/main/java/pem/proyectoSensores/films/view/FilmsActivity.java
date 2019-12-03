package pem.proyectoSensores.films.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import pem.proyectoSensores.AppMediador;
import pem.proyectoSensores.R;
import pem.proyectoSensores.films.presenter.IPresenterFilms;
import pem.proyectoSensores.home.view.HomeActivity;
import pem.proyectoSensores.login.LoginActivity;
import pem.proyectoSensores.series.view.SeriesActivity;

public class FilmsActivity extends AppCompatActivity implements IViewFilms,
        FragmentMasterFilms.EscuchaFragmento {

    private AppMediador appMediador;
    private IPresenterFilms presenterFilms;
    private FragmentMasterFilms fragmentMasterFilms;
    private FragmentDetailFilms fragmentDetailFilms;
    //Declaracion de un objeto llamado fab, que corresponda con un boton flotante


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        appMediador = (AppMediador)this.getApplication();
        presenterFilms = appMediador.getPresenterFilms();
        appMediador.setViewFilms(this);
        // Se comprueba si la actividad esta usando una version de layout con un contenedor de fragmentos
        // de tipo FrameLayout (si es asi, es un smartphone y no permite mas de un fragmento en pantalla),
        // por tanto, solo se anade el primero
        if (findViewById(R.id.contenedorDeFragmentos) != null) {
            // se crea el fragmento maestro y se anade al contenedor de fragmentos
            fragmentMasterFilms = new FragmentMasterFilms();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedorDeFragmentos, fragmentMasterFilms)
                    .commit();
        }
        // Creacion de un boton flotante para que, cuando se seleccione, solicite al presentador principal que trate
        // la opcion de agregar una nueva receta
        ImageButton home = (ImageButton) findViewById(R.id.m_home);

        ImageButton series = (ImageButton) findViewById(R.id.m_series);
        ImageButton films = (ImageButton) findViewById(R.id.m_films);
        films.setColorFilter(getResources().getColor(R.color.blue));
        ImageButton calendar = (ImageButton) findViewById(R.id.m_calendar);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeriesActivity.class);
                startActivity(intent);
                finish();
            }
        });


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_calendar);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // TODO Solicitar al presentador que recupere los datos desde el modelo.
        presenterFilms.obtenerDatos();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appMediador.removePresenterFilms();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void alSeleccionarItem(int posicion) {
        // Si no hay fragmento detalle, se crea la vista detalle (esto ocurre si es panel unico)
        if (fragmentDetailFilms == null)
            fragmentDetailFilms = new FragmentDetailFilms();

        if (findViewById(R.id.contenedorDeFragmentos) != null) {
            // si es de panel unico, se reemplaza, en el contenedor de fragmentos
            // el fragmento que esta visible por el de la vista detalle
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedorDeFragmentos, fragmentDetailFilms);
            transaccion.addToBackStack(null);
            transaccion.commit();
            // Quita la visibilidad al boton flotante (para que no aparezca en el detalle)

            // realiza la transaccion
            getSupportFragmentManager().executePendingTransactions();
        }
        // TODO Solicitar al presentador que trate el item seleccionado.
        presenterFilms.obtenerDetalle(posicion);

    }


    // Redefinicion del metodo onBackPressed para que si se tiene un dispositivo de panel unico, y el boton
    // flotante no esta visible (esta el fragmento detalle en pantalla), reemplace el fragmento detalle por el
    // fragmento maestro. En cualquier otro caso, la actividad debe finalizar (porque se quiere salir de ella)
    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed(){
        if (findViewById(R.id.contenedorDeFragmentos)!=null){
            //es panel único
            //está en la vista del detalle
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedorDeFragmentos, fragmentMasterFilms);
            transaccion.addToBackStack(null);
            transaccion.commit();
            presenterFilms.obtenerDatos();

        }else
            //no es panel único
            finish();
    }




    // TODO Añadir el método actualizarMaestro(Object[] datos) que actualiza la lista maestro con los datos
    // recibidos por parámetros. En cada entrada del vector, está el nombre de una receta.
    @Override
    public void actualizarMaestro(Object[] datos) {
        // TODO Dentro del método actualizarMaestro(Object[] datos), crear la lista maestro con los nombres
        // de las recetas que entran por parámetros.
        fragmentMasterFilms.crearLista((String[]) datos);
        // TODO Dentro del método actualizarMaestro(Object[] datos), si es una pantalla multi-panel, presentar
        // el detalle de la primera receta.
        if (findViewById(R.id.contenedorDeFragmentos) == null){
            presenterFilms.obtenerDetalle(0);
        }


    }

    // TODO Añadir el método actualizarDetalle(Object[] datos) que actualiza los valores del detalle,
    // teniendo en cuenta que en la posición 0 del vector está el nombre de la receta y en qué se usa
    // para realizarla, en la posición 1 del vector está la imagen como un Bitmap y en en la posición 3
    // del vector está la descripción de la receta.
    @Override
    public void actualizarDetalle(Object[] datos) {
        fragmentDetailFilms.actualizarNombreReceta((String) datos[0]);
        fragmentDetailFilms.actualizarImagenReceta((Bitmap) datos[1]);
        fragmentDetailFilms.actualizarDescripcion((String) datos[2]);
    }


}
