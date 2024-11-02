package project.apis.webservices.screenplay;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class ScreenplaySetup {
    private static ScreenplaySetup instance;
    private final Actor actor;

    private ScreenplaySetup(String nombreActor) {
        OnStage.setTheStage(new OnlineCast());
        actor = theActorCalled(nombreActor);
    }

    public static ScreenplaySetup montarElEscenario(String nombreActor) {
        if (instance == null) {
            instance = new ScreenplaySetup(nombreActor);
        }
        return instance;
    }

    public Actor obtenerActor() {
        return actor;
    }
}
