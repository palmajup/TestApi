package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.InvocationTargetException;

public class Class3 implements Hidable {

    @JsonProperty("name")
    private Elemento name;
    @JsonProperty("value")
    private Elemento value;
    private boolean hidden = true;

    public Class3() {
        this.name = new Elemento(String.class);
        this.value = new Elemento(String.class);
    }

    public void set1Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set1Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set2Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set2Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set3Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set3Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set4Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set4Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set5Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set5Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set6Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set6Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
    }

    public void set7Name(Object value, boolean hidden, boolean cast) {
        this.name.setValor(value, hidden, cast);
    }

    public void set7Value(Object value, boolean hidden, boolean cast) {
        this.value.setValor(value, hidden, cast);
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
