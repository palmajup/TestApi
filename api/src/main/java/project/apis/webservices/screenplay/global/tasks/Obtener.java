package project.apis.webservices.screenplay.global.tasks;

import project.apis.webservices.screenplay.global.models.Headers;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * <h1>Obtener</h1>
 * Representacion del verbo HTTP GET
 * Toma como parametros una URI, un HashMap con el body, ids en caso de haber, un booleano indicando
 * si corresponde utilizar los headers y un token en caso de haber con el contenido del mismo
 */
public class Obtener extends Request implements Task {

    public Obtener(String URI, HashMap<String, Object> info, String[] ids, boolean headers, boolean token) {
        super(URI, info, ids, headers, token);
    }

    public static Obtener recurso(String URI, HashMap<String, Object> info, String[] ids, boolean headers) {
        return instrumented(Obtener.class, URI, info, ids, headers, true);
    }

    public static Obtener recurso(String URI, HashMap<String, Object> info, boolean headers, boolean token) {
        return instrumented(Obtener.class, URI, info, new String[]{}, headers, token);
    }

    @Step("{0} envía la request para obtener recurso")
    @Override
    public <T extends Actor> void performAs(T actor) {
        String[] ids = new String[this.ids.length];
        for (int i = 0; i < this.ids.length; i++) {
            //Solo valido para campos al final de la url
            //no se puede enviar campos vacios en medio de la url
            //no esta permitido // en medio de la dirección, solo luego de http:
            ids[i] = (this.ids[i].equals("noEnviarCampo") ? "" : this.ids[i]);

            //Simular error interno - 500, solo valido para campos en los que se debe enviar un "Int"
            //al enviar un Long el servicio responde con error Interno
            if (this.ids[i].equals("errorInterno")) ids[i] = "231231231231321321351313213135135";

        }
        given().relaxedHTTPSValidation().contentType("application/json")
                .headers(Headers.mapear(headers, token))
                .queryParams(info)
                .get(String.format(URI, ids));

    }
}
