package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import project.apis.webservices.screenplay.global.models.RespuestaPojo;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "param"
})
@JsonIgnoreProperties({"messages", "paging"})
public class POST_Method1 implements RespuestaPojo {

    @JsonProperty("param")
    private Class4 class4 = null;

    @Override
    public void atributosConTiposDeDatosCorrectos() {
        class4.fieldsHaveCorrectDataType();
    }
}
