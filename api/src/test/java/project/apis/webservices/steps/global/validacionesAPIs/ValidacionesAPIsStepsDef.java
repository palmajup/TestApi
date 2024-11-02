package project.apis.webservices.steps.global.validacionesAPIs;

import io.cucumber.java.Before;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.questions.CodigoEstado;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidacionesAPIsStepsDef {

    Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Entonces
    @Entonces("el servicio devuelve los datos solicitados")
    public void elServicioDevuelveLosDatosSolicitados() {
        usuarioAutorizado.should(seeThat(CodigoEstado.es(), equalTo(200)));
    }
    //endregion
}
