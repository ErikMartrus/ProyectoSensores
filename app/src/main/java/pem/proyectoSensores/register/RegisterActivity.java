package pem.proyectoSensores.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pem.proyectoSensores.AppMediador;
import pem.proyectoSensores.R;
import pem.proyectoSensores.home.view.HomeActivity;

public class RegisterActivity extends AppCompatActivity implements IViewRegister {


    private EditText username, password, repeatPassword;
    private ProgressBar loading;
    private Button register;
    private IPresenterRegister presenterRegister;
    private AppMediador appMediador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appMediador = (AppMediador) this.getApplication();
        presenterRegister = appMediador.getPresenterRegister();
        appMediador.setViewRegister(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        loading = findViewById(R.id.loading);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                presenterRegister.register(username.getText().toString(), password.getText().toString());
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        //presenterRegister.onStop();
    }

    @Override
    public void onBackPressed(){
        toLogin();
    }

    @Override
    public void toLogin() {
        finish();
    }

    @Override
    public void toMenu() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("EXTRA_EMAIL", username.getText().toString());
        startActivity(intent);
        finish();
    }



    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
        username.setClickable(false);
        username.setEnabled(false);
        password.setClickable(false);
        password.setEnabled(false);
        repeatPassword.setClickable(false);
        repeatPassword.setEnabled(false);
        register.setClickable(false);
        register.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
        username.setClickable(true);
        username.setEnabled(true);
        password.setClickable(true);
        password.setEnabled(true);
        repeatPassword.setClickable(true);
        repeatPassword.setEnabled(true);
        register.setClickable(true);
        register.setEnabled(true);
    }

    @Override
    public void clearForm() {
        username.setText("");
        password.setText("");
        repeatPassword.setText("");
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Error al crear cuenta",Toast.LENGTH_SHORT).show();
    }

}
