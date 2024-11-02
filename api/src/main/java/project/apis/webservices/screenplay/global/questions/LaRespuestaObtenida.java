package project.apis.webservices.screenplay.global.questions;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class LaRespuestaObtenida implements Question<ResponseOptions<Response>> {

    public static LaRespuestaObtenida es() {
        return new LaRespuestaObtenida();
    }

    @Override
    public ResponseOptions<Response> answeredBy(Actor actor) {
        return lastResponse();
    }
}
