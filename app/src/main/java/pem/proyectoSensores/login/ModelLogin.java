package pem.proyectoSensores.login;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import pem.proyectoSensores.AppMediador;

public class ModelLogin implements IModelLogin{

    private static ModelLogin singlenton = null;
    private AppMediador appMediador;
    private FirebaseAuth mAuth;

    public ModelLogin() {
        appMediador = AppMediador.getInstance();
        mAuth = FirebaseAuth.getInstance();

    }

    public static ModelLogin getInstance(){
        if (singlenton == null)
            singlenton = new ModelLogin();
        return singlenton;
    }

    @Override
    public void doLogin(final String email, final String password) {
        try {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Bundle extras = new Bundle();
                    if (task.isSuccessful()){
                        extras.putString(AppMediador.CLAVE_USERNAME, "Success");
                    } else {
                        extras.putString(AppMediador.CLAVE_USERNAME, "Failed");
                    }
                    appMediador.sendBroadcast(AppMediador.AVISO_AUTHENTICATION_SUCCESS, extras);
                }
            });
        } catch (Exception e){

        }
    }
}
