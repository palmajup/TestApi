package project.apis.webservices.steps.global;

import project.apis.webservices.screenplay.EnvironmentSetup;
import project.apis.webservices.screenplay.ScreenplaySetup;
import project.apis.webservices.screenplay.global.tasks.AgregarHeader;
import project.apis.webservices.screenplay.global.tasks.AgregarToken;
import project.apis.webservices.screenplay.token.TokensAuthorization;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InicializacionStepsDef {
    private Actor usuarioAutorizado;
    private String baseURI;
    private String baseESBURI;
    private String portal;
    private String env;

    //region Before
    @Before
    public void setUp(Scenario scenario) {
        usuarioAutorizado = ScreenplaySetup.montarElEscenario("Usuario autorizado").obtenerActor();
        usuarioAutorizado.remember("tags", scenario.getSourceTagNames());
    }

    @Before
    public void environmentSetUp(Scenario scenario) {
        env = EnvironmentSetup.obtenerEnv(System.getProperty("Environment"));
        baseURI = EnvironmentSetup.obtenerURI(scenario.getSourceTagNames(), env);
        baseESBURI = EnvironmentSetup.obtenerESBURI(scenario.getSourceTagNames(), env);
        portal = EnvironmentSetup.obtenerPortal(scenario.getSourceTagNames());
    }
    //endregion

    //region Dado
    @Dado("que el usuario se encuentra habilitado para realizar una solicitud a {string}")
    public void queElUsuarioSeEncuentraHabilitadoParaRealizarUnaSolicitudA(String pathRecurso) {
        usuarioAutorizado.remember("pathRecurso", pathRecurso);
        usuarioAutorizado.can(CallAnApi.at(baseURI));
        RestAssured.baseURI = baseURI;
        usuarioAutorizado.remember("portal", portal);
        usuarioAutorizado.remember("env", env);
    }

    @Dado("que el usuario se encuentra autorizado para realizar la solicitud")
    public void queElUsuarioSeEncuentraAutorizadoParaRealizarLaSolicitud() throws IOException {
//        HashMap<String, String> tokensAuthorization = TokensAuthorization.getToken(portal);
//        List<String> tokenNames = new ArrayList<>();
//        List<String> tokenValues = new ArrayList<>();

//        tokenNames.add("Authorization");
//        tokenNames.add("AuthorizationInfo");
//        tokenValues.add(tokensAuthorization.get("authorization"));
//        tokenValues.add(tokensAuthorization.get("authorizationInfo"));

//        usuarioAutorizado.wasAbleTo(AgregarToken.aLaSolicitud(tokenNames, tokenValues));
        usuarioAutorizado.remember("user", "Default");
        usuarioAutorizado.remember("token", true);
    }

    @Dado("que el usuario se encuentra habilitado para realizar una solicitud con")
    public void queElUsuarioSeEncuentraHabilitadoParaRealizarUnaSolicitudCon(List<List<String>> headers) {
        usuarioAutorizado.wasAbleTo(AgregarHeader.aLaSolicitud(headers.get(0), headers.get(1)));
        usuarioAutorizado.remember("headers", true);
    }

    @Dado("que el usuario se encuentra habilitado para verificar los resultados en {string}")
    public void queElUsuarioSeEncuentraHabilitadoParaVerificarLosResultadosEn(String endpoint) {
        usuarioAutorizado.remember("baseESBURI", baseESBURI);
        usuarioAutorizado.remember("endPoint", endpoint);
    }
    //endregion
}
