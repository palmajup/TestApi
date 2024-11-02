package project.apis.webservices.screenplay.global.questions;

import project.apis.webservices.screenplay.ServiceSetup;
import project.apis.webservices.screenplay.global.models.RespuestaPojo;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Collection;

public class LasRespuestas implements Question<Boolean> {
    private final Collection<String> tagNames;
    private final ResponseOptions<Response> apiResponse;
    private final ResponseOptions<Response> esbResponse;


    public LasRespuestas(Collection<String> tagNames, ResponseOptions<Response> apiResponse, ResponseOptions<Response> esbResponse) {
        this.tagNames = tagNames;
        this.apiResponse = apiResponse;
        this.esbResponse = esbResponse;
    }

    public static LasRespuestas coinciden(Collection<String> tagNames, ResponseOptions<Response> apiResponse, ResponseOptions<Response> esbResponse) {
        return Instrumented.instanceOf(LasRespuestas.class).withProperties(tagNames, apiResponse, esbResponse);
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        ObjectMapper mapeador = new ObjectMapper();

        RespuestaPojo api = mapeador.convertValue(apiResponse.getBody().jsonPath().get(), ServiceSetup.obtenerRespuesta(tagNames));
        RespuestaPojo esb = mapeador.convertValue(esbResponse.getBody().jsonPath().get(), ServiceSetup.obtenerRespuesta(tagNames));

        // Verifico que los objetos instanciados no sean NULL
        assert api != null : "api es NULL";
        assert esb != null : "esb es NULL";

        // Verifico que todos los atributos tengan el tipo de dato correcto
        api.atributosConTiposDeDatosCorrectos();
        esb.atributosConTiposDeDatosCorrectos();

        // Retorno la comparación de los POJO que respresentan ambas respuestas
        return api.equals(esb);
    }
}
