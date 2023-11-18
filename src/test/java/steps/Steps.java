package steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import pages.Actions;
import runner.RunCucumberTest;

import static steps.Hook.driver;

public class Steps extends RunCucumberTest {

    Actions actions = new Actions(driver);

    @Dado("^Que he entrado a login$")
    public void acessoPagina() {
        actions.acessarTela();
    }

    @Y("^Que he Ingresando las credenciales$")
    public void ingresoCredenciales() {
        actions.acessarCampo();
        actions.clickBotonlogin();
    }

    @Cuando("^Elijo el proyecto$")
    public void clickProyecto() throws InterruptedException {
        actions.clickSelect();
        actions.cerrarVentana();
    }

    @Entonces("^Quiero hacer todo el flujo$")
    public void flujoCompleto() throws Exception {
        actions.clickBotonProyecto();
        actions.informacionPersonal();
        actions.clickBotonInfoPersonal();
        actions.clickCheckInfoPersonal();
        actions.simulacion();
        actions.resultadoSimulacion();
        actions.autenticacionLiviana();
        actions.formulario();
        actions.formulario2();
        actions.pp();
        actions.evaluacion();
    }
    @Entonces("^Quiero hacer todo el flujoGTM$")
    public void flujoCompletoGTM() throws Exception {
        actions.clickBotonProyectoGTM();
        actions.informacionPersonalGTM();
        actions.clickBotonInfoPersonalGTM();
        actions.clickCheckInfoPersonalGTM();
        actions.simulacionGTM();
        actions.resultadoSimulacionGTM();
        actions.autenticacionLivianaGTM();
        actions.formularioGTM();
        actions.formulario2GTM();
        actions.ppGTM();
        actions.evaluacionGTM();
        actions.preaprobadoGTM();
    }

}
