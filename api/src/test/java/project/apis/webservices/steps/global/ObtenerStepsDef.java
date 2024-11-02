package project.apis.webservices.steps.global;

import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.models.ParametrosNEW;
import project.apis.webservices.screenplay.global.tasks.Obtener;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ObtenerStepsDef {
    private Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Obtener
    @Cuando("el usuario realiza una solicitud a APIs de tipo GET completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)|extra)(?:| y campo extra no especificado)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETCompletandoLosCampos(List<List<String>> parametros) {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        HashMap<String, Object> mapa = ParametrosNEW.mapear(parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET sin completar (?:el|los|ningún) campo(?:|s) (?:obligatorio(?:|s)|opcional(?:|es)|del método)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarLosCampos() {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, new LinkedHashMap<>(), true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET sin completar ningún campo del método y con campo extra no especificado$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarNingunCampoYConCampoExtraNoEspecificado(List<List<String>> parametros) {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        HashMap<String, Object> mapa = ParametrosNEW.mapear(parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) y sin headers$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETCompletandoLosCamposYSinHeaders(List<List<String>> parametros) {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        HashMap<String, Object> mapa = ParametrosNEW.mapear(parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, mapa, false, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET sin completar ningún campo del método y sin headers$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarNingunCampoYSinHeaders() {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, new LinkedHashMap<>(), false, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) y con token vacío$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETCompletandoLosCamposYTokenVacio(List<List<String>> parametros) {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        HashMap<String, Object> mapa = ParametrosNEW.mapear(parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, mapa, true, false));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo GET sin completar ningún campo del método y con token vacío$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarNingunCampoYTokenVacio() {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        usuarioAutorizado.attemptsTo(Obtener.recurso(pathRecurso, new LinkedHashMap<>(), true, false));
    }

    @Cuando("el usuario realiza una solicitud a ESB de tipo GET completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))$")
    public void elUsuarioRealizaUnaSolicitudAESBDeTipoGETCompletandoLosCampos(List<List<String>> parametros) {
        String baseESBURI = usuarioAutorizado.recall("baseESBURI");
        String endPoint = usuarioAutorizado.recall("endPoint");
        List<String> tags = usuarioAutorizado.recall("tags");

        usuarioAutorizado.can(CallAnApi.at(baseESBURI));
        RestAssured.baseURI = baseESBURI;

        int inline = ParametrosNEW.inLine(endPoint);
        HashMap<String, Object> mapa = ParametrosNEW.mapear(inline, parametros.get(0), parametros.get(1));
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        usuarioAutorizado.attemptsTo(Obtener.recurso(endPoint, mapa, ids, true));
    }

    @Cuando("el usuario realiza una solicitud a ESB de tipo GET sin completar los campos del método")
    public void elUsuarioRealizaUnaSolicitudAESBDeTipoGETSinCompletarLosCamposDelMetodo() {
        String baseESBURI = usuarioAutorizado.recall("baseESBURI");
        String endPoint = usuarioAutorizado.recall("endPoint");
        List<String> tags = usuarioAutorizado.recall("tags");

        usuarioAutorizado.can(CallAnApi.at(baseESBURI));
        RestAssured.baseURI = baseESBURI;

        usuarioAutorizado.attemptsTo(Obtener.recurso(endPoint, new LinkedHashMap<>(), true, true));
    }
    //endregion
}
