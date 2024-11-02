package project.apis.webservices.screenplay.P1.clients.post_CreateClient_Person_MinimunData.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.SolicitudPojo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class BodyPOST_CreateClient_Person_MinimunData implements SolicitudPojo {
    private Client client;

    @Override
    public void setAtributos(List<String> paramName, List<String> paramValue, SolicitudPojo clase) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        this.client = new Client();

        EntityClient entity = new EntityClient();
        Address address = new Address();

        HashMap<String, Hidable> contenedorObjetos = new HashMap<>();
        contenedorObjetos.put("cli", this.client);
        contenedorObjetos.put("ent", entity);
        contenedorObjetos.put("add", address);

        PostParametersMapper.iteradorDeParametros(clase, contenedorObjetos, paramName, paramValue);

        entity = (EntityClient) contenedorObjetos.get("ent");
        entity.checkAndSetHidden();

        address = (Address) contenedorObjetos.get("add");
        address.checkAndSetHidden();

        this.client = (Client) contenedorObjetos.get("cli");
        this.client.setEntityClient(entity);
        this.client.setAddress(address);
        this.client.checkAndSetHidden();
    }
}
