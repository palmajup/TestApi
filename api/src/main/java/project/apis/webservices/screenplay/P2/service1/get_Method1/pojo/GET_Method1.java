package project.apis.webservices.screenplay.P2.service1.get_Method1.pojo;

import project.apis.webservices.screenplay.global.models.RespuestaPojo;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"messages", "paging"})
public class GET_Method1 implements RespuestaPojo {

    @JsonProperty("param")
    private List<Class1> param = null;

    @Override
    public void atributosConTiposDeDatosCorrectos() {
        for (Class1 elem : this.param) {
            elem.fieldsHaveCorrectDataType();
        }
    }
}

