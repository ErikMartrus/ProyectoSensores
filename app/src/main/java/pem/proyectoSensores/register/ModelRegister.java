package pem.proyectoSensores.register;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pem.proyectoSensores.AppMediador;

public class ModelRegister implements IModelRegister{

    private static ModelRegister singlenton = null;
    private AppMediador appMediador;
    private FirebaseAuth mAuth;

    public ModelRegister() {
        appMediador = AppMediador.getInstance();
        mAuth = FirebaseAuth.getInstance();

    }

    public static ModelRegister getInstance(){
        if (singlenton == null)
            singlenton = new ModelRegister();
        return singlenton;
    }

    @Override
    public void register(final String username, final String password) {
        try{
            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    login(username, password);
                }
            });
        } catch (Exception e){

        }
    }

    public void login(final String username, String password) {
        try {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Bundle extras = new Bundle();
                    if (task.isSuccessful()){
                        registerUserDB(username);
                        extras.putString(AppMediador.CLAVE_USERNAME, "Success");
                    } else {
                        extras.putString(AppMediador.CLAVE_USERNAME, "Failed");
                    }
                    appMediador.sendBroadcast(AppMediador.AVISO_AUTHENTICATION_SUCCESS, extras);
                }
            });
        }catch (Exception e){

        }


    }

    private void registerUserDB(String username){
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        String key = userRef.push().getKey();
        User user = new User();
        user.setId(key);
        user.setUsername(username);
        userRef.child(key).setValue(username);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
