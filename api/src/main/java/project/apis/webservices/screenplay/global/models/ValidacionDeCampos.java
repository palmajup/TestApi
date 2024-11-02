package project.apis.webservices.screenplay.global.models;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * <h1>ValidacionDeCampos</h1>
 * Esta clase es una variacion del patron Strategy. En base a un objecto que implemente la interfaz CamposRespuesta
 * validara que todos los campos sean correctos. En este caso no toda la logica se encuentra en el metodo
 * que se sobreescribe de la interfaz.
 *
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class ValidacionDeCampos {
    private CamposDeRespuesta respuesta;


    public ValidacionDeCampos(CamposDeRespuesta respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Metodo que valida que cada uno de los campos de nuestra response sea el esperado
     *
     * @return boolean
     */
    public boolean validarCampos() {
        HashMap<String, String> campos = respuesta.getCampos();
        Object[] nombres = campos.keySet().toArray();
        Object[] tipos = campos.values().toArray();
        for (int i = 0; i < campos.size(); i++) {
            try {
                if (!elCampoEsNulo((String) nombres[i]) && !elCampoEsTipo((String) nombres[i], (String) tipos[i]))
                    return false;
                //if (!elCampoEsTipo((String)nombres[i], (String)tipos[i])) return false;
            } catch (Exception e) {
                System.out.println(nombres[i] + " " + tipos[i]);
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo que informa si el campo a validar es nulo
     *
     * @param campo nombre del campo a popular en .jsonPath()
     * @return boolean
     */
    private boolean elCampoEsNulo(String campo) {
        if (elCampoEstaEnUnaLista(campo)) {
            if (lastResponse().jsonPath().getList(campo).size() > 0)
                return lastResponse().jsonPath().getList(campo).get(0) == null;
            else
                return true;
        } else
            return lastResponse().jsonPath().get(campo) == null;
    }

    /**
     * Metodo que informa si el campo a validar es del tipo esperado
     *
     * @param campo nombre del campo a popular en .jsonPath()
     * @param tipo  tipo de dato
     * @return boolean
     */
    private boolean elCampoEsTipo(String campo, String tipo) {
        if (elCampoEstaEnUnaLista(campo))
            return (lastResponse().jsonPath().getList(campo).get(0).getClass().getName().contains(tipo));
        else
            return (lastResponse().jsonPath().get(campo).getClass().getName().contains(tipo));
    }

    /**
     * Metodo que verifica si el campo a validar se encuentra anidado dentro de una lista
     *
     * @param campo nombre del campo a popular en .jsonPath()
     * @return
     */
    private boolean elCampoEstaEnUnaLista(String campo) {
        return lastResponse().jsonPath().get(campo).getClass().getName().contains("Array");
    }
}
