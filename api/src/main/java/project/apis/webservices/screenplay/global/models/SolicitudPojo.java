package project.apis.webservices.screenplay.global.models;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SolicitudPojo {

    void setAtributos(List<String> paramName, List<String> paramValue, SolicitudPojo clase) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException;
}
