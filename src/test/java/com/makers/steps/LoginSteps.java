package com.makers.steps;

import com.makers.pages.LoginPage;
import com.makers.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.Assert.*;

public class LoginSteps {

    private LoginPage loginPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Dado("el usuario navega a la pagina de login")
    public void elUsuarioNavegaALaPaginaDeLogin() {
        loginPage.navigate();
    }

    @Cuando("el usuario ingresa el nombre de usuario {string}")
    public void elUsuarioIngresaElNombreDeUsuario(String usuario) {
        loginPage.enterUsername(usuario);
    }

    @Cuando("el usuario ingresa la contrasena {string}")
    public void elUsuarioIngresaLaContrasena(String contrasena) {
        loginPage.enterPassword(contrasena);
    }

    @Cuando("el usuario hace clic en el boton de login")
    public void elUsuarioHaceClicEnElBotonDeLogin() {
        loginPage.clickLogin();
    }

    @Cuando("el usuario deja el campo usuario {string}")
    public void elUsuarioDejaElCampoUsuario(String usuario) {
        loginPage.enterUsername(usuario);
    }

    @Cuando("el usuario deja el campo contrasena {string}")
    public void elUsuarioDejaElCampoContrasena(String contrasena) {
        loginPage.enterPassword(contrasena);
    }

    @Entonces("el resultado del login debe ser {string}")
    public void elResultadoDelLoginDebeSer(String resultado) {
        if (resultado.equalsIgnoreCase("exitoso")) {
            assertTrue(
                    "Se esperaba login exitoso pero la URL actual es: " + loginPage.getCurrentUrl(),
                    loginPage.isLoginSuccessful()
            );
        } else {
            assertFalse(
                    "Se esperaba login fallido pero el usuario fue redirigido a inventory.",
                    loginPage.isLoginSuccessful()
            );
            assertTrue(
                    "Se esperaba mensaje de error pero no fue visible.",
                    loginPage.isErrorDisplayed()
            );
        }
    }

    @Entonces("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String mensajeEsperado) {
        if (loginPage.isLoginSuccessful()) {
            String titulo = loginPage.getPageTitle();
            assertTrue(
                    "Titulo esperado: '" + mensajeEsperado + "' | Titulo actual: '" + titulo + "'",
                    titulo.contains(mensajeEsperado)
            );
        } else {
            String errorActual = loginPage.getErrorMessage();
            assertTrue(
                    "Mensaje esperado: '" + mensajeEsperado + "' | Mensaje actual: '" + errorActual + "'",
                    errorActual.contains(mensajeEsperado)
            );
        }
    }
}