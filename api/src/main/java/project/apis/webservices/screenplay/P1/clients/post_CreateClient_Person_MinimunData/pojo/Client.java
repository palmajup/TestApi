package project.apis.webservices.screenplay.P1.clients.post_CreateClient_Person_MinimunData.pojo;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;

import java.lang.reflect.InvocationTargetException;

public class Client implements Hidable {

    @JsonProperty("CRM_id")
    private Elemento crm_id;
    @JsonProperty("entity")
    private EntityClient entity;
    @JsonProperty("address")
    private Address address;
    private boolean hidden = true;

    public Client() {
        this.crm_id = new Elemento(String.class);
    }

    public void setcliCRM_id(Object value, boolean hidden, boolean cast) {
        this.crm_id.setValor(value, hidden, cast);
    }

    public void setEntityClient(EntityClient entity) {
        this.entity = entity;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        this.hidden = PostParametersMapper.mapearIsHiddenObjeto(this);
    }
}
