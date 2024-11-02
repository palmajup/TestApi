package project.apis.webservices.steps.global;

import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.models.ParametrosNEW;
import project.apis.webservices.screenplay.global.tasks.Modificar;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ModificarStepsDef {
    private Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Modificar
    @Cuando("el usuario realiza una solicitud a APIs de tipo PUT completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPUTCompletandoLosCampos(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Modificar.recurso(pathRecurso, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo PUT completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) y con token vacío$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPUTCompletandoLosCamposYTokenVacio(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Modificar.recurso(pathRecurso, mapa, true, false));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo PUT completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) y sin headers$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPUTCompletandoLosCamposYSinHeaders(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Modificar.recurso(pathRecurso, mapa, false, true));
    }

    @Cuando("el usuario realiza una solicitud a ESB de tipo PUT completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))$")
    public void elUsuarioRealizaUnaSolicitudAESBDeTipoPUTCompletandoLosCampos(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String baseESBURI = usuarioAutorizado.recall("baseESBURI");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        usuarioAutorizado.can(CallAnApi.at(baseESBURI));
        RestAssured.baseURI = baseESBURI;
        String endPoint = usuarioAutorizado.recall("endPoint");

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Modificar.recurso(endPoint, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo PUT sin completar (?:el|los|ningún) campo(?:|s) (?:obligatorio(?:|s)|opcional(?:|es)|del método)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarLosCampos() {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        usuarioAutorizado.attemptsTo(Modificar.recurso(pathRecurso, new LinkedHashMap<>(), true, true));
    }
    //endregion
}
