package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>TextoDelMensajeDevuelto</h1>
 * Pregunta que retornara el valor de la response en el jsonPath('messages.text')
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class TextoDelMensajeDevuelto implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return (String)lastResponse().jsonPath().getList("messages.text").get(0);
    }

    public static TextoDelMensajeDevuelto es(){
        return new TextoDelMensajeDevuelto();
    }
}
