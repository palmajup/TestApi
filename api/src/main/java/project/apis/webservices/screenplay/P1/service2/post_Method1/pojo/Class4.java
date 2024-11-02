package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success"
})
public class Class4 {

    @JsonProperty("success")
    private Object success;

    HashMap<String, Class> dataType;

    {
        dataType = new HashMap<String, Class>();
        dataType.put("success", Boolean.class);
    }

    public void fieldsHaveCorrectDataType() {
        assert success == null || success.getClass().equals(dataType.get("success")) : "success tiene tipo de dato incorrecto";
    }
}
