package project.apis.webservices.screenplay.global.tasks;

import project.apis.webservices.screenplay.global.models.Headers;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * <h1>Modificar</h1>
 * Representacion del verbo HTTP PUT
 * Toma como parametros una URI, un HasMap con el body, un id en caso de haber, un boolean indicando
 * si corresponde utilizar los headers y un token en caso de haber con el contenido del mismo
 */
public class Modificar extends Request implements Task {

    public Modificar(String URI, HashMap<String, Object> info, boolean headers, boolean token) {
        super(URI, info, headers, token);
    }

    public static Modificar recurso(String URI, HashMap<String, Object> info, boolean headers) {
        return instrumented(Modificar.class, URI, info, headers, null);
    }

    public static Modificar recurso(String URI, HashMap<String, Object> info, boolean headers, boolean token) {
        return instrumented(Modificar.class, URI, info, headers, token);
    }

    @Step("{0} envía la request para modificar recurso")
    @Override
    public <T extends Actor> void performAs(T t) {

        given().relaxedHTTPSValidation().contentType("application/json")
                .headers(Headers.mapear(headers, token))
                .body(info)
                .put(URI);

    }
}
