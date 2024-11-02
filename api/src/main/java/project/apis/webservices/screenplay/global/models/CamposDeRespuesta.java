package project.apis.webservices.screenplay.global.models;

import java.util.HashMap;

/**
 * <h1>CamposDeRespuesta</h1>
 * La interfaz CamposDeRespuesta se utiliza para lograr tener un solo metodo que pueda checkear los campos
 * de todas las responses que implementen esta interfaz.
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public interface CamposDeRespuesta {

    HashMap<String, String> getCampos();
}
