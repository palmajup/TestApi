package project.apis.webservices.screenplay.P1.service1.get_Method2.pojo;


import project.apis.webservices.screenplay.global.models.RespuestaPojo;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "param"
})
public class GET_Method2 implements RespuestaPojo {

    @JsonProperty("param")
    private List<Class1> param = null;

    @Override
    public void atributosConTiposDeDatosCorrectos() {
        for (Class1 elem : this.param) {
            elem.fieldsHaveCorrectDataType();
        }
    }
}

