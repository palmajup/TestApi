package project.apis.webservices.screenplay.global.models;

import java.util.HashMap;
import java.util.List;

/**
 * <h1>MapaDeParametros</h1>
 * Clase utilizada para implementar variacion del patron Strategy. Posee un atributo de tipo Parametros
 * y ejecuta un metodo de este objeto, de forma que obtendremos un resultado diferente dependiendo de
 * cual haya sido el objecto heredado de Parametros que se paso.
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class MapaDeParametros {
    Parametros parametros;
    public static final int OBLIGATORIOS = 1;
    public static final int OPCIONALES = 2;
    public static final int EXTRAS = 3;

    public MapaDeParametros(Parametros parametros){
        this.parametros = parametros;
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param opcionales array de parametros opcionales ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearOpcionales(List<List<String>> opcionales){
        parametros.mapearCampos(opcionales, OPCIONALES);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param obligatorios lista de parametros obligatorios con el nombre y el valor del parametro pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearObligatorios(List<List<String>> obligatorios){
        parametros.mapearCampos(obligatorios, OBLIGATORIOS);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param obligatorios array de parametros obligatorios ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @param extras array de parametros extras ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearObligatoriosMasExtra(List<List<String>> obligatorios, List<List<String>> extras){
        parametros.mapearCampos(obligatorios, OBLIGATORIOS);
        parametros.mapearCampos(extras, EXTRAS);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param obligatorios array de parametros obligatorios ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @param opcionales array de parametros opcionales ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearCompleto(List<List<String>> obligatorios, List<List<String>> opcionales){
        parametros.mapearCampos(obligatorios, OBLIGATORIOS);
        parametros.mapearCampos(opcionales, OPCIONALES);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param obligatorios array de parametros obligatorios ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @param opcionales array de parametros opcionales ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @param extras array de parametros extras ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearCompletoMasExtra(List<List<String>> obligatorios, List<List<String>> opcionales, List<List<String>> extras){
        parametros.mapearCampos(obligatorios, OBLIGATORIOS);
        parametros.mapearCampos(opcionales, OPCIONALES);
        parametros.mapearCampos(extras, EXTRAS);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param opcionales array de parametros opcionales ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @param extras array de parametros extras ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearOpcionalesMasExtra(List<List<String>> opcionales, List<List<String>> extras){
        parametros.mapearCampos(opcionales, OPCIONALES);
        parametros.mapearCampos(extras, EXTRAS);
        return parametros.getMapa();
    }

    /**
     * El valor almacenado en el Hash es de tipo Objeto ya que se utilizan varios tipo de dato en diferentes pruebas
     * @param extras array de parametros extras ordenados de igual forma que en el objeto heredado de Parametros pasado
     * @return HashMap de String y Objeto. Siendo el nombre del campo y el valor del mismo respectivamente.
     */
    public HashMap<String, Object> mapearExtra(List<List<String>> extras){
        parametros.mapearCampos(extras, EXTRAS);
        return parametros.getMapa();
    }
}
