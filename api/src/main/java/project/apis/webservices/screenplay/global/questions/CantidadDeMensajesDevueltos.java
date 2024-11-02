package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>CantidadDeMensajesDevueltos</h1>
 * Pregunta que nos retornara el valor del tamaño que posee el array ('messages')
 */
public class CantidadDeMensajesDevueltos implements Question<Integer> {
    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().jsonPath().getList("messages").size();
    }

    public static CantidadDeMensajesDevueltos es() {
        return new CantidadDeMensajesDevueltos();
    }
}
