package project.apis.webservices.screenplay.global.models;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public interface SolicitudDeVerificacionPojo {

    HashMap<String, Object> setValores(HashMap<String, Object> mapaVerificacion, List<String> paramName, List<String> paramValue, SolicitudDeVerificacionPojo clase) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException;
}
