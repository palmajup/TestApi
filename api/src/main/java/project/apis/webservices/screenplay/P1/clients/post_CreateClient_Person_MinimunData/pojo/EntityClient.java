package project.apis.webservices.screenplay.P1.clients.post_CreateClient_Person_MinimunData.pojo;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;

import java.lang.reflect.InvocationTargetException;

public class EntityClient implements Hidable {

    @JsonProperty("CRM_id")
    private Elemento crm_id;

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

    }
}
