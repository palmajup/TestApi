package project.apis.webservices.screenplay.global.questions;

import project.apis.webservices.screenplay.global.models.RespuestasAPI;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.HashMap;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ElMensajeDevuelto implements Question<Boolean> {
    private static String portal;
    private static String metodo;

    public ElMensajeDevuelto(String portal, String metodo) {
        ElMensajeDevuelto.portal = portal;
        ElMensajeDevuelto.metodo = metodo;
    }

    public static ElMensajeDevuelto de(String portal, String metodo) {
        return Instrumented.instanceOf(ElMensajeDevuelto.class).withProperties(portal, metodo);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        HashMap<String, String> mensajes = new HashMap<>();
        mensajes = RespuestasAPI.getCamposValidaciones(portal, metodo, mensajes);

        if (mensajes != null) {
            actor.should(seeThat(CantidadDeMensajesDevueltos.es(), equalTo(1)));
            actor.should(seeThat(CodigoEstado.es(), equalTo(Integer.valueOf(mensajes.get("httpCode")))));
            actor.should(seeThat(EstadoDelMensajeDevuelto.es(), equalTo(mensajes.get("status"))));
            actor.should(seeThat(CodigoDelMensajeDevuelto.es(), equalTo(mensajes.get("code"))));
            actor.should(seeThat(TextoDelMensajeDevuelto.es(), equalTo(mensajes.get("text"))));
            actor.should(seeThat(AyudaDelMensajeDevuelto.es(), equalTo(mensajes.get("help"))));
            return true;
        } else {
            return false;
        }
    }
}
