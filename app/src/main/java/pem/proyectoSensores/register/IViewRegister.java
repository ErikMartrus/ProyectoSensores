package pem.proyectoSensores.register;

public interface IViewRegister {


    void onStop();
    void toLogin();
    void toMenu();

    void showProgress();
    void hideProgress();
    void clearForm();
    void showError();
}
