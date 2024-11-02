package project.apis.webservices.screenplay;

import project.apis.webservices.screenplay.global.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ServiceSetup {

    public static MapaDeParametros obtenerParametros(Collection<String> tagNames) {
        String classTagName = extractClassFromTag(tagNames, "Parametros");
        MapaDeParametros campos = null;
        try {
            campos = (MapaDeParametros) getObjectForClassName(classTagName);

        } catch (Exception ex) {
            loggearMSJ("la clase: Parametros" + classTagName + "; no extiende de MapaDeParametros");
        }
        return campos;
    }

    public static Class<? extends SolicitudPojo> obtenerBody(Collection<String> tagNames) {
        String classTagName = extractClassFromTag(tagNames, "Body");
        Class<? extends SolicitudPojo> campos = null;
        try {
            campos = getBodyClassPojoForClassName(classTagName);

        } catch (Exception ex) {
            loggearMSJ("la clase: Body" + classTagName + "; no extiende de SolicitudPojo");
        }
        return campos;
    }

    public static Class<? extends SolicitudDeVerificacionPojo> obtenerClaseVerificacion(Collection<String> tagNames) {
        String classTagName = extractClassFromTag(tagNames, "VerificarGET_");
        Class<? extends SolicitudDeVerificacionPojo> campos = null;
        try {
            campos = getClassVerificacionPojoForClassName(classTagName);

        } catch (Exception ex) {
            loggearMSJ("la clase: VerificarGET_" + classTagName + "; no extiende de SolicitudDeVerificacionPojo");
        }
        return campos;
    }

    public static CamposBody obtenerBodyESB(Collection<String> tagNames) {
        String classTagName = extractClassFromTag(tagNames, "BodyESB");
        CamposBody campos = null;
        try {
            campos = (CamposBody) getObjectForClassName(classTagName);

        } catch (Exception ex) {
            loggearMSJ("la clase: BodyESB" + classTagName + "; no extiende de CamposBody");
        }
        return campos;
    }

    public static Class<? extends RespuestaPojo> obtenerRespuesta(Collection<String> tagNames) {
        String classTagName = extractClassFromTag(tagNames, "");
        Class<? extends RespuestaPojo> campos = null;
        try {
            campos = getClassPojoForClassName(classTagName);

        } catch (Exception ex) {
            loggearMSJ("la clase: Respuesta" + classTagName + "; no extiende de CamposDeRespuesta");
        }
        return campos;
    }

    private static String extractClassFromTag(Collection<String> tagNames, String data) {

        String ubicacion = "canales.apis.webservices.screenplay.";
        String portal = "";
        String servicio = "";
        String metodoTagName = "";
        String metodo = "";
        String type = "";

        for (String tag : tagNames) {
            if (tag.contains("portal:")) {
                portal = tag.substring(8); // 8 = len("portal:") + 1
                break;
            }
        }

        for (String tag : tagNames) {
            if (tag.contains("servicio:")) {
                servicio = tag.substring(10); // 10 = len("servicio:") + 1
                servicio = servicio.substring(0, 1).toLowerCase() + servicio.substring(1);
                break;
            }
        }

        for (String tag : tagNames) {
            if (tag.contains("metodo:")) {
                metodoTagName = tag.substring(8); // 8 = len("metodo:") + 1
                try {
                    type = metodoTagName.split("_")[0].toLowerCase();
                    metodo = metodoTagName.split("_")[1];
                } catch (Exception ex) {
                    loggearMSJ("No se encontró el _ en el tag del método");
                }
                break;
            }
        }

        if (servicio.equals("")) {
            // si no se agrego el tag del servicio en el feature
            loggearMSJ("No se encontró el tag del servicio en el archivo feature");
        }

        if (portal.equals("")) {
            // si no se agrego el tag del portal en el feature
            loggearMSJ("No se encontró el tag del portal en el archivo feature");
        }

        if (metodoTagName.equals("")) {
            // si no se agrego el tag del metodo en el feature
            loggearMSJ("No se encontró el tag del método en el archivo feature");
        }
        return ubicacion + portal + "." + servicio + "." + type + "_" + metodo + ".pojo." + data + metodoTagName;
    }

    private static Object getObjectForClassName(String className) {
        Object objectByClassName = null;
        try {
            Class<?> c = Class.forName(className);
            objectByClassName = c.newInstance();
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            loggearMSJ("No se encontró la clase: " + className + " para generar el objeto");
        } catch (InstantiationException x) {
            loggearMSJ("Se produjo un error al instanciar la clase: " + className + " para generar el objeto");
        } catch (IllegalAccessException x) {
            loggearMSJ("Se produjo un error al instanciar la clase: " + className + "; ilegal access");
        }
        return objectByClassName;
    }

    private static Class<? extends SolicitudDeVerificacionPojo> getClassVerificacionPojoForClassName(String className) {
        Class<? extends SolicitudDeVerificacionPojo> classByClassName = null;
        try {
            classByClassName = (Class<? extends SolicitudDeVerificacionPojo>) Class.forName(className);
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            loggearMSJ("No se encontró la clase: " + className + " para generar el objeto");
        }
        return classByClassName;
    }

    private static Class<? extends SolicitudPojo> getBodyClassPojoForClassName(String className) {
        Class<? extends SolicitudPojo> classByClassName = null;
        try {
            classByClassName = (Class<? extends SolicitudPojo>) Class.forName(className);
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            loggearMSJ("No se encontró la clase: " + className + " para generar el objeto");
        }
        return classByClassName;
    }

    private static Class<? extends RespuestaPojo> getClassPojoForClassName(String className) {
        Class<? extends RespuestaPojo> classByClassName = null;
        try {
            classByClassName = (Class<? extends RespuestaPojo>) Class.forName(className);
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            loggearMSJ("No se encontró la clase: " + className + " para generar el objeto");
        }
        return classByClassName;
    }

    private static void loggearMSJ(String msj) {
        Logger logger = LoggerFactory.getLogger(ServiceSetup.class);
        logger.error(msj);
    }
}
