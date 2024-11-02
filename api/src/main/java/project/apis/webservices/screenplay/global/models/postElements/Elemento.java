package project.apis.webservices.screenplay.global.models.postElements;

import project.apis.webservices.screenplay.global.models.Hidable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

@Data
public class Elemento implements Hidable {
    @JsonValue
    @Getter
    private Object value;
    private boolean hidden = true;
    private Class tipo;

    public Elemento(Class tipo) {
        this.tipo = tipo;
    }

    public void setValor(Object value, boolean hidden, boolean cast) {
        if (cast) {
            this.value = castearValores(value);
        } else {
            this.value = value;
        }
        this.hidden = hidden;
    }

    //Castea los strings recibidos a los valores que corresponden
    private Object castearValores(Object value) {
        if (!value.equals("null")) {
            if (this.tipo == Integer.class) {
                value = Integer.parseInt(String.valueOf(value));
            } else if (this.tipo == Long.class) {
                value = Long.parseLong(String.valueOf(value));
            } else if (this.tipo == Boolean.class) {
                value = Boolean.parseBoolean(String.valueOf(value));
            } else if (this.tipo == Float.class) {
                value = Float.parseFloat(String.valueOf(value));
            }
        } else {
            value = null;
        }
        return value;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    }
}
