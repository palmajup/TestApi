package project.apis.webservices.screenplay.global.models;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.InvocationTargetException;

@JsonIgnoreProperties({"hidden", "empty"})
public interface Hidable {
    boolean isHidden();

    void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;
}
