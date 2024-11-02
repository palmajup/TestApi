package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Pregunta que recibe un HashMap con jsonPath's(key) y valores esperados(value), itera dicho objeto y verifica
 * si la actual response posee cada elemento con su valor especificado
 */
public class VerificarLosValoresDeLosCampos implements Question<Boolean> {
    private static HashMap<String, Object> mapaVerificacion;

    public VerificarLosValoresDeLosCampos(HashMap<String, Object> mapaVerificacion) {
        VerificarLosValoresDeLosCampos.mapaVerificacion = mapaVerificacion;
    }

    public static VerificarLosValoresDeLosCampos de(HashMap<String, Object> mapaVerificacion) {
        return Instrumented.instanceOf(VerificarLosValoresDeLosCampos.class).withProperties(mapaVerificacion);
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        for (Map.Entry campo : mapaVerificacion.entrySet()) {
            actor.should(seeThat(ElValorDelCampoDevuelto.es(campo.getKey().toString()), equalTo(campo.getValue())));
        }
        return true;
    }
}
