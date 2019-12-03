package pem.proyectoSensores.login;

public interface IViewLogin {


    void doLogin(String username, String password);

    void showProgress();

    void hideProgress();

    void showErrorLogin();

    void cleanForm();

    boolean isValidEmail();

    boolean isValidPassword();

    void toMenu();

    void toRegister();

    void showPassSent();

    void showErrorPassSent();
}
