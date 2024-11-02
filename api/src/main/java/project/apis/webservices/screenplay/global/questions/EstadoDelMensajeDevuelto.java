package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>CodigoDelMensajeDevuelto</h1>
 * Pregunta que nos retornara el valor de la response en el jsonPath('messages.status')
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class EstadoDelMensajeDevuelto implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        return (String)lastResponse().jsonPath().getList("messages.status").get(0);
    }

    public static EstadoDelMensajeDevuelto es(){
        return new EstadoDelMensajeDevuelto();
    }
}
