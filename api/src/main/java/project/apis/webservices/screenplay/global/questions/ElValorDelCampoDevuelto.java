package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1></h1>
 * Pregunta que recibe el jsonPath de un elemento y retornará el valor que posee la response de dicho path
 */
public class ElValorDelCampoDevuelto implements Question<Object> {
    String pathCampoEvaluado;

    public ElValorDelCampoDevuelto(String pathCampoEvaluado) {
        this.pathCampoEvaluado = pathCampoEvaluado;
    }

    public static ElValorDelCampoDevuelto es(String pathCampoEvaluado) {
        return Instrumented.instanceOf(ElValorDelCampoDevuelto.class).withProperties(pathCampoEvaluado);
    }

    @Override
    public Object answeredBy(Actor actor) {
        return lastResponse().jsonPath().get(pathCampoEvaluado);
    }
}
