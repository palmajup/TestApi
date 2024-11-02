package project.apis.webservices.steps.global.validacionesAPIs;

import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.questions.CodigoEstado;
import project.apis.webservices.screenplay.global.questions.ElMensajeDevuelto;
import project.apis.webservices.screenplay.global.questions.LaRespuestaObtenida;
import io.cucumber.java.Before;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidacionesESBStepsDef {
    Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Entonces
    @Entonces("el usuario obtiene los datos para  realizar la comparación de los resultados obtenidos")
    public void elUsuarioObtieneLosDatosParaRealizarLaComparacionDeLosResultadosObtenidos() {
        usuarioAutorizado.should(seeThat(ElMensajeDevuelto.de(usuarioAutorizado.recall("portal"), "templateSinProcesar")));
        usuarioAutorizado.remember("esbResponse", LaRespuestaObtenida.es());
    }

    @Entonces("el servicio devuelve los datos solicitados")
    public void elServicioDevuelveLosDatosSolicitados() {
        usuarioAutorizado.should(seeThat(CodigoEstado.es(), equalTo(200)));
    }
    //endregion
}

