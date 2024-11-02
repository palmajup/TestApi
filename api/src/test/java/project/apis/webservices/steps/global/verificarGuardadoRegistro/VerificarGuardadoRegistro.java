package project.apis.webservices.steps.global.verificarGuardadoRegistro;

import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.ServiceSetup;
import project.apis.webservices.screenplay.global.models.SolicitudDeVerificacionPojo;
import project.apis.webservices.screenplay.global.questions.VerificarLosValoresDeLosCampos;
import io.cucumber.java.Before;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class VerificarGuardadoRegistro {
    Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Entonces
    @Entonces("se verifica que los registros impactaron correctamente a través de los siguientes campos")
    public void seVerificaQueLosRegistrosImpactaronCorrectamenteATravesDeLosSiguientesCampos(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapaVerificacion = new HashMap<>();
        Class<? extends SolicitudDeVerificacionPojo> templ = ServiceSetup.obtenerClaseVerificacion(tagNames);
        SolicitudDeVerificacionPojo clase = templ.getDeclaredConstructor().newInstance();

        try {
            mapaVerificacion = clase.setValores(mapaVerificacion, parametros.get(0), parametros.get(1), clase);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        usuarioAutorizado.should(seeThat(VerificarLosValoresDeLosCampos.de(mapaVerificacion)));
    }
    //endregion
}
