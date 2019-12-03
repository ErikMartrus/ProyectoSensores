package pem.proyectoSensores.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pem.proyectoSensores.AppMediador;
import pem.proyectoSensores.R;
import pem.proyectoSensores.home.view.HomeActivity;
import pem.proyectoSensores.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements IViewLogin{

    private IPresenterLogin presenterLogin;
    private AppMediador appMediador;
    private EditText username, password;
    private Button login;
    private TextView register, forgotPassword;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        appMediador = (AppMediador) this.getApplication();
        presenterLogin = appMediador.getPresenterLogin();
        appMediador.setViewLogin(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        loading = findViewById(R.id.loading);
        forgotPassword = findViewById(R.id.forgotPassword);

        login.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                showProgress();
                presenterLogin.doLogin(username.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                toRegister();

            }
        });

        forgotPassword.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                //presenter.forgotPassword(username.getText().toString());
            }
        });
    }


    @Override
    public void doLogin(String username, String password) {
        appMediador.getPresenterLogin().doLogin(username, password);
    }

    @Override
    public void showProgress() {
        loading.setVisibility(android.view.View.VISIBLE);
        login.setClickable(false);
        login.setEnabled(false);
        register.setClickable(false);
        register.setEnabled(false);
        username.setClickable(false);
        username.setEnabled(false);
        password.setClickable(false);
        password.setEnabled(false);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(android.view.View.GONE);
        login.setClickable(true);
        login.setEnabled(true);
        register.setClickable(true);
        register.setEnabled(true);
        username.setClickable(true);
        username.setEnabled(true);
        password.setClickable(true);
        password.setEnabled(true);
    }

    @Override
    public void showErrorLogin() {
        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cleanForm() {
        username.setText("");
        password.setText("");
    }


    @Override
    public boolean isValidEmail() {
        return Patterns.EMAIL_ADDRESS.matcher(username.getText().toString()).matches();
    }

    @Override
    public boolean isValidPassword() {
        if (TextUtils.isEmpty(password.getText().toString()) && password.getText().toString().length() < 4) {
            Toast.makeText(this, "No es un password valido", Toast.LENGTH_SHORT).show();
            password.setError("No es un password valido");
            return false;
        } else{
            return true;
        }
    }

    @Override
    public void toMenu() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("EXTRA_EMAIL", username.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void toRegister() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        //finish();
    }


    @Override
    public void showPassSent() {
        Toast.makeText(getApplicationContext(), "Email enviado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPassSent() {
        Toast.makeText(getApplicationContext(), "Email no encontrado", Toast.LENGTH_SHORT).show();
    }

}
