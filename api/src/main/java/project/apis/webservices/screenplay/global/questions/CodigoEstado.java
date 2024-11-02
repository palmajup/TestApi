package project.apis.webservices.screenplay.global.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>CodigoEstado</h1>
 * Metodo que retorna el codigo de estado de la ultima response
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class CodigoEstado implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().statusCode();
    }

    public static CodigoEstado es(){
        return new CodigoEstado();
    }

}
