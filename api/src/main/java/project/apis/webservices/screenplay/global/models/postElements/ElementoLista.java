package project.apis.webservices.screenplay.global.models.postElements;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ElementoLista<E> implements Hidable {
    @JsonValue
    private List<Hidable> lista;
    private boolean hidden = true;

    public ElementoLista() {
        this.lista = new ArrayList<>();
    }

    public void setValores(Hidable value) {
        this.lista.add(value);
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        this.hidden = PostParametersMapper.mapearIsHiddenElementoLista(this.lista);
    }
}
