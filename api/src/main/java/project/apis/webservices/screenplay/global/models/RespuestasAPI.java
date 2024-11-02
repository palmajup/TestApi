package project.apis.webservices.screenplay.global.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Clase que obtiene los mensajes de respuesta para cada Portal
 *
 * @author Gonzalo Rojas
 * @since 22/09/2020
 */

public class RespuestasAPI {

    /**
     * Metodo para obtener los mensajes de cada portal en funcion del metodo especificado en la clase ValidacionesAPI
     * y el portal especificado en el archivo feature y mapearlos en un HashMap
     * Consulta los mensajes declarados para cada método en los archivos de configuración .properties declarados para cada portal
     * Si el método no se encuentra especificado en los archivos properties devuelve null
     *
     * @param portal, metodo, mensajes
     * @return HashMap
     * @author Gonzalo Rojas
     * @since 22/09/2020
     */
    public static HashMap<String, String> getCamposValidaciones(String portal, String metodo, HashMap<String, String> mensajes) {
        FileInputStream archivoProperties = null;
        Logger logger = LoggerFactory.getLogger(RespuestasAPI.class);

        try {
            if (metodo.contains("SinProcesar")) {
                try {
                    archivoProperties = new FileInputStream("src/test/resources/validacionesESB/" + portal + "_ESB.properties");
                } catch (FileNotFoundException e) {
                    logger.error("No se encontró el archivo de validaciones '" + portal + "_ESB.properties' en la ruta src/test/resources/validacionesESB/");
                }
            } else {
                try {
                    archivoProperties = new FileInputStream("src/test/resources/validacionesAPI/" + portal + ".properties");
                } catch (FileNotFoundException e) {
                    logger.error("No se encontró el archivo de validaciones '" + portal + ".properties' en la ruta src/test/resources/validacionesAPI/");
                }
            }

            Properties prop = new Properties();
            prop.load(archivoProperties);
            if (prop.containsKey(metodo + ".code")) {
                mensajes.put("httpCode", prop.getProperty(metodo + ".httpCode"));
                mensajes.put("status", prop.getProperty(metodo + ".status"));
                mensajes.put("code", prop.getProperty(metodo + ".code"));
                mensajes.put("text", prop.getProperty(metodo + ".text"));
                mensajes.put("help", prop.getProperty(metodo + ".help"));
            } else {
                mensajes = null;
            }
            archivoProperties.close();
        } catch (Exception ex) {
            mensajes = null;
        }
        return mensajes;
    }
}
