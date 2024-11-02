package project.apis.webservices.steps.global.compararRespuestas;

import project.apis.webservices.screenplay.global.questions.LasRespuestas;
import io.cucumber.java.es.Entonces;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.Collection;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CompararRespuestasStepsDef {

    //region Entonces
    @Entonces("las respuestas se corresponden correctamente")
    public void lasRespuestasSeCorrespondenCorrectamente() {
        ResponseOptions<Response> apiResponse = theActorInTheSpotlight().recall("apiResponse");
        ResponseOptions<Response> esbResponse = theActorInTheSpotlight().recall("esbResponse");
        Collection<String> tagNames = theActorInTheSpotlight().recall("tags");

        theActorInTheSpotlight().should(seeThat(LasRespuestas.coinciden(tagNames, apiResponse, esbResponse)));
    }
    //endregion
}
