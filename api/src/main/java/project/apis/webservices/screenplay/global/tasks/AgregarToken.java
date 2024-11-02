package project.apis.webservices.screenplay.global.tasks;

import project.apis.webservices.screenplay.global.models.Headers;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AgregarToken implements Task {
    private final List<String> headerName;
    private final List<String> headerValue;

    public AgregarToken(List<String> headerName, List<String> headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public static AgregarToken aLaSolicitud(List<String> headerName, List<String> headerValue) {
        return instrumented(AgregarToken.class, headerName, headerValue);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        Headers.setSecurityHeaders(headerName, headerValue);
    }
}
