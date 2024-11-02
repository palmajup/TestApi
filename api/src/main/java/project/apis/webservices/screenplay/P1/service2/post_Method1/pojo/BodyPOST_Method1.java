package project.apis.webservices.screenplay.P1.service2.post_Method1.pojo;

import project.apis.webservices.screenplay.global.models.Hidable;
import project.apis.webservices.screenplay.global.models.PostParametersMapper;
import project.apis.webservices.screenplay.global.models.SolicitudPojo;
import project.apis.webservices.screenplay.global.models.postElements.Elemento;
import project.apis.webservices.screenplay.global.models.postElements.ElementoLista;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
public class BodyPOST_Method1 implements SolicitudPojo {
    private Class1 class1;
    private Elemento extra;

    @Override
    public void setAtributos(List<String> paramName, List<String> paramValue, SolicitudPojo clase) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        this.class1 = new Class1();
        this.extra = new Elemento(String.class);

        ElementoLista<Class2> files = new ElementoLista<>();
        Class2 class2 = new Class2();
        ElementoLista<Class3> parameters = new ElementoLista<>();
        Class3 class31 = new Class3();
        Class3 class32 = new Class3();
        Class3 class33 = new Class3();
        Class3 class34 = new Class3();
        Class3 class35 = new Class3();
        Class3 class36 = new Class3();
        Class3 class37 = new Class3();

        HashMap<String, Hidable> contenedorObjetos = new HashMap<>();
        contenedorObjetos.put("pref1", this.class1);
        contenedorObjetos.put("pref2", class2);
        contenedorObjetos.put("1", class31);
        contenedorObjetos.put("2", class32);
        contenedorObjetos.put("3", class33);
        contenedorObjetos.put("4", class34);
        contenedorObjetos.put("5", class35);
        contenedorObjetos.put("6", class36);
        contenedorObjetos.put("7", class37);

        PostParametersMapper.iteradorDeParametros(clase, contenedorObjetos, paramName, paramValue);

        this.class1 = (Class1) contenedorObjetos.get("pref1");

        class2.checkAndSetHidden();
        files.setValores(contenedorObjetos.get("pref2"));
        files.checkAndSetHidden();

        class31.checkAndSetHidden();
        class32.checkAndSetHidden();
        class33.checkAndSetHidden();
        class34.checkAndSetHidden();
        class35.checkAndSetHidden();
        class36.checkAndSetHidden();
        class37.checkAndSetHidden();
        parameters.setValores(contenedorObjetos.get("1"));
        parameters.setValores(contenedorObjetos.get("2"));
        parameters.setValores(contenedorObjetos.get("3"));
        parameters.setValores(contenedorObjetos.get("4"));
        parameters.setValores(contenedorObjetos.get("5"));
        parameters.setValores(contenedorObjetos.get("6"));
        parameters.setValores(contenedorObjetos.get("7"));
        parameters.checkAndSetHidden();

        this.class1.setAttachedFiles(files);
        this.class1.setParameters(parameters);
    }
}
