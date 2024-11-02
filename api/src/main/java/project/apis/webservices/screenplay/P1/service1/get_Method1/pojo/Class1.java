package project.apis.webservices.screenplay.P1.service1.get_Method1.pojo;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "param1",
        "param2"
})
public class Class1 {

    @JsonProperty("param1")
    private Object param1;
    @JsonProperty("param2")
    private Object param2;

    HashMap<String, Class> dataType;

    {
        dataType = new HashMap<String, Class>();
        dataType.put("param1", String.class);
        dataType.put("param2", String.class);
    }

    public void fieldsHaveCorrectDataType() {
        assert param1 == null || param1.getClass().equals(dataType.get("param1")) : "param1 tiene tipo de dato incorrecto";
        assert param2 == null || param2.getClass().equals(dataType.get("param2")) : "param2 tiene tipo de dato incorrecto";
    }
}
