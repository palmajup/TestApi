package project.apis.webservices.screenplay.global.models.postElements;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListaStrings<E> implements Hidable {
    @JsonValue
    private List<Hidable> lista;
    @JsonProperty("hidden")
    private boolean hidden = true;

    public ListaStrings() {
        this.lista = new ArrayList<>();
    }

    public void setValor(Hidable value) {
        this.lista.add(value);
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        if (this.lista.size() != 0) {
            this.hidden = PostParametersMapper.mapearIsHiddenElementoLista(this.lista);
        }
    }
}
