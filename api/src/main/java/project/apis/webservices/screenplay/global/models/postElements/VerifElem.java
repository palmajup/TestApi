package project.apis.webservices.screenplay.global.models.postElements;

import project.apis.webservices.screenplay.global.models.Verificable;
import lombok.Data;

@Data
public class VerifElem implements Verificable {
    private Object value;
    private String path;
    private Class tipo;

    public VerifElem(String path, Class tipo) {
        this.path = path;
        this.tipo = tipo;
    }

    @Override
    public Object getValor() {
        return this.value;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setValor(Object value) {
        if (!value.equals("null")) {
            if (this.tipo == Integer.class) {
                this.value = Integer.parseInt(String.valueOf(value));
            } else if (this.tipo == String.class) {
                this.value = value;
            } else if (this.tipo == Boolean.class) {
                this.value = Boolean.parseBoolean(String.valueOf(value));
            }
        } else {
            this.value = null;
        }
    }
}
