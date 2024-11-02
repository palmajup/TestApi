package project.apis.webservices.screenplay.global.tasks;

import project.apis.webservices.screenplay.global.models.Headers;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * <h1>Guardar</h1>
 * Representacion del verbo HTTP POST
 * Toma como parametros una URI, un HasMap con el body, un id en caso de haber, un boolean indicando
 * si corresponde utilizar los headers y un token en caso de haber con el contenido del mismo
 */
public class Guardar extends Request implements Task {

    public Guardar(String URI, HashMap<String, Object> info, String[] ids, boolean headers, boolean token) {
        super(URI, info, ids, headers, token);
    }

    public static Guardar recurso(String URI, HashMap<String, Object> info, boolean headers) {
        return instrumented(Guardar.class, URI, info, new String[]{}, headers, null);
    }

    public static Guardar recurso(String URI, HashMap<String, Object> info, boolean headers, boolean token) {
        return instrumented(Guardar.class, URI, info, new String[]{}, headers, token);
    }

    public static Guardar recurso(String URI, HashMap<String, Object> info, String[] ids, boolean headers, boolean token) {
        return instrumented(Guardar.class, URI, info, ids, headers, token);
    }

    @Step("{0} envía la request para guardar recurso")
    @Override
    public <T extends Actor> void performAs(T t) {

        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (type, s) -> oMapper
        ));

        String[] ids = new String[this.ids.length];
        for (int i = 0; i < this.ids.length; i++) {
            //Solo valido para campos al final de la url
            //no se puede enviar campos vacios en medio de la url
            //no esta permitido // en medio de la dirección, solo luego de http:
            ids[i] = (this.ids[i].equals("noEnviarCampo") ? "" : this.ids[i]);
        }

        given().relaxedHTTPSValidation().contentType("application/json")
                .headers(Headers.mapear(headers, token))
                .body(info)
                .post(String.format(URI, ids));
    }
}
