package project.apis.webservices.steps.global.validacionesAPIs;

import project.apis.webservices.screenplay.ScreenplaySetup;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;

public class ValidacionesParticularesStepsDef {
    Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion
}
