package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>AyudaDelMensajeDevuelto</h1>
 * Pregunta que nos retornara el valor de la response en el jsonPath('messages.help')
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class AyudaDelMensajeDevuelto implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {return (String)lastResponse().jsonPath().getList("messages.help").get(0);
    }

    public static AyudaDelMensajeDevuelto es(){
        return new AyudaDelMensajeDevuelto();
    }

}
