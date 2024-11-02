package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.InvocationTargetException;

public class Class2 implements Hidable {

    @JsonProperty("param1")
    private Elemento param1;
    private boolean hidden = true;

    public Class2() {
        this.param1 = new Elemento(String.class);
    }

    public void setpref2Param1(Object value, boolean hidden, boolean cast) {
        this.param1.setValor(value, hidden, cast);
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
