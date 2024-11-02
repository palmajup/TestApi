package project.apis.webservices.screenplay;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * Clase que obtiene la url del archivo environment.properties para los distintos metodos
 *
 * @author Federico Pflüger
 * @since 23/01/2020
 */
public class EnvironmentSetup {
    private static String environment;
    private static String esbEnvironment;
    private static String portal;
    private static String metodo;
    private static String URI;
    private static String ESBURI;
    private static boolean checkPuertoEspecifico;
    private static boolean checkDNSEspecifico;

    /**
     * Metodo para obtener la url en funcion del metodo y el entorno
     * Si el metodo no posee una url en el entorno especificado devuelve null
     *
     * @param tagNames, String
     * @return String
     * @author Federico Pflüger
     * @since 23/01/2020
     */
    public static String obtenerURI(Collection<String> tagNames, String newEnvironment) {
        String portalTagName = getPortalFromTag(tagNames);

        if (verifyNewEnvironment(portalTagName, newEnvironment)) {
            environment = (newEnvironment == null) ? "RUNNERENV" : newEnvironment;
            portal = portalTagName;
            updateURI();
        }
        return URI;
    }

    public static String obtenerESBURI(Collection<String> tagNames, String newEnvironment) {
        String metodoTagName = getMetodoFromTag(tagNames);
        Boolean puertoEspecificado = getPuertoFlagFromTag(tagNames);
        Boolean dnsEspecificado = getDNSFlagFromTag(tagNames);
        if (verifyNewESBEnvironment(metodoTagName, newEnvironment)) {
            esbEnvironment = (newEnvironment == null) ? "RUNNERENV" : newEnvironment;
            metodo = metodoTagName;
            checkPuertoEspecifico = puertoEspecificado;
            checkDNSEspecifico = dnsEspecificado;
            updateESBURI();
        }
        return ESBURI;
    }

    public static String obtenerPortal(Collection<String> tagNames) {
        return getPortalFromTag(tagNames);
    }

    /**
     * Metodo para obtener el nombre de un metodo a partir de la lista de tags
     *
     * @param tagNames, String
     * @return String
     * @author Federico Pflüger
     * @since 23/01/2020
     */
    private static String getPortalFromTag(Collection<String> tagNames) {
        String portalTagName = "";

        for (String tag : tagNames) {
            if (tag.contains("portal:")) {
                portalTagName = tag.substring(8);
                break;
            }
        }
        if (portalTagName.equals("")) {
            // si no se agrego el tag del metodo en el feature
            Logger logger = LoggerFactory.getLogger(EnvironmentSetup.class);
            logger.error("No se encontró el tag del portal en el archivo feature");
        }
        return portalTagName;
    }

    private static String getMetodoFromTag(Collection<String> tagNames) {
        String metodoTagName = "";

        for (String tag : tagNames) {
            if (tag.contains("metodo:")) {
                metodoTagName = tag.substring(8);
                break;
            }
        }
        if (metodoTagName.equals("")) {
            // si no se agrego el tag del metodo en el feature
            Logger logger = LoggerFactory.getLogger(EnvironmentSetup.class);
            logger.error("No se encontró el tag del metodo en el archivo feature");
        }
        return metodoTagName;
    }

    private static Boolean getPuertoFlagFromTag(Collection<String> tagNames) {
        boolean puertoEspecificado = false;

        for (String tag : tagNames) {
            if (tag.contains("puertoEspecifico")) {
                puertoEspecificado = true;
                break;
            }
        }
        return puertoEspecificado;
    }

    private static Boolean getDNSFlagFromTag(Collection<String> tagNames) {
        boolean dnsEspecificado = false;

        for (String tag : tagNames) {
            if (tag.contains("dnsEspecifico")) {
                dnsEspecificado = true;
                break;
            }
        }
        return dnsEspecificado;
    }

    /**
     * Metodo para obtener verificar si el nuevo entorno es diferente del anterior
     *
     * @param portalTagName, newEnvironment
     * @return boolean
     * @author Federico Pflüger
     * @since 23/01/2020
     */
    private static boolean verifyNewEnvironment(String portalTagName, String newEnvironment) {
        try {
            return (!newEnvironment.equals(environment)) || (!portalTagName.equals(portal));
        } catch (Exception ignored) {
            //En caso de que no se pueda validar si el puerto sigue siendo el mismo, se debe actualizar
        }
        return true;
    }

    private static boolean verifyNewESBEnvironment(String metodoTagName, String newEnvironment) {
        try {
            return (!newEnvironment.equals(esbEnvironment)) || (!metodoTagName.equals(metodo));
        } catch (Exception ignored) {
            //En caso de que no se pueda validar si el puerto sigue siendo el mismo, se debe actualizar
        }
        return true;
    }

    /**
     * Metodo para actualizar la url segun el metodo y el entorno utilizados
     * Conulta los datos en el archivo de configuracion environment.properties
     *
     * @author Federico Pflüger
     * @since 23/01/2020
     */
    private static void updateURI() {
        String baseURI, finalURI;
        try {
            FileInputStream ip = new FileInputStream("environment.properties");
            Properties prop = new Properties();
            prop.load(ip);

            environment = (environment.equals("RUNNERENV")) ? prop.getProperty("RUNNERENV") : environment;
            baseURI = prop.getProperty(portal);
            finalURI = prop.getProperty(environment);
            ip.close();
        } catch (Exception ex) {
            baseURI = null;
            finalURI = null;
        }
        //Si el portal o el entorno no están definidos
        Assert.assertNotEquals("El puerto ", baseURI, "null");
        Assert.assertNotEquals("El puerto ", finalURI, "null");
        URI = baseURI + finalURI;
    }

    private static void updateESBURI() {
        String baseURI, finalURI;
        try {
            FileInputStream ip = new FileInputStream("environment.properties");
            Properties prop = new Properties();
            prop.load(ip);

            esbEnvironment = (esbEnvironment.equals("RUNNERENV")) ? prop.getProperty("RUNNERENV") : esbEnvironment;
            if (checkDNSEspecifico) {
                baseURI = prop.getProperty(metodo + "." + esbEnvironment);
            } else {
                baseURI = prop.getProperty("ESB_" + esbEnvironment);
            }
            if (checkPuertoEspecifico) {
                finalURI = prop.getProperty("Port_" + metodo + "." + esbEnvironment);
            } else {
                finalURI = "";
            }
            ip.close();
        } catch (Exception ex) {
            baseURI = null;
            finalURI = null;
        }
        //Si el portal o el entorno no están definidos
        Assert.assertNotEquals("El puerto ", baseURI, null);
        Assert.assertNotEquals("El puerto ", finalURI, null);
        ESBURI = baseURI + finalURI;
    }

    public static String obtenerEnv(String environment) {
        if (environment != null)
            return environment;
        else {
            try {
                FileInputStream ip = new FileInputStream("environment.properties");
                Properties prop = new Properties();
                prop.load(ip);
                return prop.getProperty("RUNNERENV");
            } catch (Exception e) {
                throw new EnvironmentError("Error al leer el archivo: 'environment.properties'");
            }
        }
    }

    private static class EnvironmentError extends RuntimeException {
        EnvironmentError(String msj) {
            super(msj);
        }
    }
}
