package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;
import project.apis.webservices.screenplay.global.models.postElements.ElementoLista;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.InvocationTargetException;

public class Class1 implements Hidable {

    @JsonProperty("param1")
    private Elemento param1;
    @JsonProperty("files")
    private ElementoLista<Class2> files;
    @JsonProperty("parameters")
    private ElementoLista<Class3> parameters;
    private boolean hidden = true;

    public Class1() {
        this.param1 = new Elemento(String.class);
    }

    public void setpref1Param1(Object value, boolean hidden, boolean cast) {
        this.param1.setValor(value, hidden, cast);
    }

    public void setAttachedFiles(ElementoLista<Class2> files) {
        this.files = files;
    }

    public void setParameters(ElementoLista<Class3> parameters) {
        this.parameters = parameters;
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
