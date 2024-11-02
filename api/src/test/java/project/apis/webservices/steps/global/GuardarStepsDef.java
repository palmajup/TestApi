package project.apis.webservices.steps.global;

import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.models.ParametrosNEW;
import project.apis.webservices.screenplay.global.tasks.Guardar;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GuardarStepsDef {
    private Actor usuarioAutorizado;

    //region Before
    @Before
    public void setUp() {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
    }
    //endregion

    //region Guardar
    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCampos(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado) con al menos un campo que forma partre de la URL$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposInLine(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");

        int inline = ParametrosNEW.inLine(pathRecurso);
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, ids, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, ids, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado) con al menos un campo que forma partre de la URL con campo extra$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposInLineConCampoExtra(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");

        int inline = ParametrosNEW.inLine(pathRecurso);
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, ids, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, ids, true, true));
    }



    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado) con al menos un campo que forma partre de la URL sin headers$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposInLineSinHeaders(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");

        int inline = ParametrosNEW.inLine(pathRecurso);
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, ids, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, ids, false, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))(?:| y campo extra no especificado) con al menos un campo que forma partre de la URL con token vacìo$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposInLineConTokenVacio(List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");

        int inline = ParametrosNEW.inLine(pathRecurso);
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, ids, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, ids, true, false));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST del metodo {string} completando los campos")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTDelMetodoCompletandoLosCampos(String metodo, List<List<String>> parametros) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        String tagMetodo = "@metodo:" + metodo;
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        Collection<String> tagNamesModif = new ArrayList<>();
        tagNames.forEach(tagName -> {
            if (!tagName.contains("@metodo")) {
                tagNamesModif.add(tagName);
            }
        });
        tagNamesModif.add(tagMetodo);
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNamesModif, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) y con token vacío$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposYTokenVacio(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, true, false));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|opcional(?:|es)) y sin headers$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoPOSTCompletandoLosCamposYSinHeaders(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, mapa, false, true));
    }

    @Cuando("el usuario realiza una solicitud a ESB de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es))$")
    public void elUsuarioRealizaUnaSolicitudAESBDeTipoPOSTCompletandoLosCampos(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String baseESBURI = usuarioAutorizado.recall("baseESBURI");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        usuarioAutorizado.can(CallAnApi.at(baseESBURI));
        RestAssured.baseURI = baseESBURI;
        String endPoint = usuarioAutorizado.recall("endPoint");

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(endPoint, mapa, true, true));
    }

    @Cuando("el usuario realiza una solicitud a ESB de tipo POST completando (?:el|los) campo(?:|s) (?:obligatorio(?:|s)|obligatorios y opcionales|opcional(?:|es)) con al menos un campo que forma partre de la URL$")
    public void elUsuarioRealizaUnaSolicitudAESBDeTipoPOSTCompletandoLosCamposInLine(List<List<String>> parametros) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String baseESBURI = usuarioAutorizado.recall("baseESBURI");
        Collection<String> tagNames = usuarioAutorizado.recall("tags");
        usuarioAutorizado.can(CallAnApi.at(baseESBURI));
        RestAssured.baseURI = baseESBURI;
        String endPoint = usuarioAutorizado.recall("endPoint");

        int inline = ParametrosNEW.inLine(endPoint);
        String[] ids = ParametrosNEW.mapearInline(inline, parametros.get(1));

        HashMap<String, Object> mapa = ParametrosNEW.mapearPost(tagNames, ids, parametros.get(0), parametros.get(1));
        usuarioAutorizado.attemptsTo(Guardar.recurso(endPoint, mapa, ids, true, true));
    }

    @Cuando("el usuario realiza una solicitud a APIs de tipo POST sin completar (?:el|los|ningún) campo(?:|s) (?:obligatorio(?:|s)|opcional(?:|es)|del método)$")
    public void elUsuarioRealizaUnaSolicitudAAPIsDeTipoGETSinCompletarLosCampos() {
        String pathRecurso = usuarioAutorizado.recall("pathRecurso");
        usuarioAutorizado.attemptsTo(Guardar.recurso(pathRecurso, new LinkedHashMap<>(), true, true));
    }
    //endregion
}
