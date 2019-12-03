package pem.proyectoSensores.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pem.proyectoSensores.AppMediador;

public class PresenterLogin implements IPresenterLogin {

    private IModelLogin modelLogin;
    private AppMediador appMediador;

    public PresenterLogin() {
        appMediador = AppMediador.getInstance();
        modelLogin = ModelLogin.getInstance();
    }

    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_AUTHENTICATION_SUCCESS)){
                String clave = intent.getStringExtra(AppMediador.CLAVE_USERNAME);
                if (clave.equals("Success")){
                    appMediador.getViewLogin().toMenu();
                } else if (clave.equals("Failed")){
                    appMediador.getViewLogin().cleanForm();
                    appMediador.getViewLogin().showErrorLogin();
                    appMediador.getViewLogin().hideProgress();
                }


            }
            appMediador.unRegisterReceiver(this);
        }
    };

    @Override
    public void doLogin(String username, String password) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_AUTHENTICATION_SUCCESS);
        modelLogin.doLogin(username, password);
    }
}
