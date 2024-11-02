package project.apis.webservices.screenplay.global.models;


import project.apis.webservices.screenplay.global.models.postElements.Elemento;
import project.apis.webservices.screenplay.global.models.postElements.ListaStrings;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostParametersMapper {

    PostParametersMapper() {
    }

    /**
     * Método que recibe la instancia de la clase y un HashMap de objetos que representa el Body de una request tipo POST,
     * junto con las listas de parámetros recibidos desde el feature, y se encarga de mapear
     * los distintos tipos de objetos que poseen la clase y el hashmap para posteriormente ser agregados o no al json con
     * el que se realiza la request.
     *
     * @param clase
     * @param contenedorObjetos
     * @param paramName
     * @param paramValue
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static HashMap<String, Hidable> iteradorDeParametros(SolicitudPojo clase, HashMap<String, Hidable> contenedorObjetos, List<String> paramName, List<String> paramValue) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        outer:
        for (int i = 0; i < paramName.size(); i++) {
            for (Map.Entry objeto : contenedorObjetos.entrySet()) {
                if (paramName.get(i).contains(objeto.getKey().toString())) {
                    if (objeto.getValue().getClass().getName().equals(ListaStrings.class.getName())) {
                        Hidable objetoMapeado = mapearAtributoListaDeStrings((Hidable) objeto.getValue(), paramValue.get(i));
                        contenedorObjetos.replace(objeto.getKey().toString(), objetoMapeado);
                        continue outer;
                    } else {
                        Hidable objetoMapeado = mapearAtributosDeObjeto((Hidable) objeto.getValue(), paramName.get(i), paramValue.get(i));
                        contenedorObjetos.replace(objeto.getKey().toString(), objetoMapeado);
                        continue outer;
                    }
                }
            }
            mapearAtributo(clase, paramName.get(i), paramValue.get(i));
        }
        for (Map.Entry<String, Hidable> objetoMapeado : contenedorObjetos.entrySet()) {
            objetoMapeado.getValue().checkAndSetHidden();
        }
        return contenedorObjetos;
    }

    /**
     * Método que mapea los valores que se reciben desde el feature a los jsonPath's correspondientes para realizar la
     * verificación en un servicio GET de la creación de los registros que se enviaron en el servicio POST especificados
     * en el feature.
     *
     * @param mapaVerificacion
     * @param paramName
     * @param paramValue
     * @param clase
     * @return
     * @throws IllegalAccessException
     */
    public static HashMap<String, Object> mapearVerificacionGET(HashMap<String, Object> mapaVerificacion, List<String> paramName, List<String> paramValue, SolicitudDeVerificacionPojo clase) throws IllegalAccessException {
        Field[] variablesClase = clase.getClass().getDeclaredFields();

        for (int i = 0; i < paramName.size(); i++) {
            for (Field var : variablesClase) {
                if (paramName.get(i).equals(var.getName())) {
                    var.setAccessible(true);
                    Verificable varCasteada = (Verificable) var.get(clase);
                    varCasteada.setValor(paramValue.get(i));
                    mapaVerificacion.put(varCasteada.getPath(), varCasteada.getValor());
                    break;
                }
            }
        }
        return mapaVerificacion;
    }

    public static boolean mapearIsHiddenObjeto(Hidable objeto) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] paramsObjeto = objeto.getClass().getDeclaredFields();
        for (Field param : paramsObjeto) {
            if (param.getType().getName().equals(Elemento.class.getName())) {
                param.setAccessible(true);
                Method isHidden = param.getType().getDeclaredMethod("isHidden");
                if (!(Boolean) isHidden.invoke(param.get(objeto))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean mapearIsHiddenElementoLista(List<Hidable> objeto) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        for (Hidable elem : objeto) {
            if (!elem.isHidden()) {
                return false;
            }
        }
        return true;
    }

    public static void mapearAtributo(SolicitudPojo clase, String paramName, String paramValue) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field variable;
        Method setValores;
        // Busca la variable (con el nombre recibido "paramName") en la clase que se recibió por parámetro
        variable = clase.getClass().getDeclaredField(paramName);
        variable.setAccessible(true);
        // Intenta buscar el método que setea el valor del atributo que hace referencia al "paramName" de la clase que recibió como parámetro
        setValores = variable.getType().getDeclaredMethod("setValor", Object.class, boolean.class, boolean.class);


        switch (paramValue) {
            case "numeroInvalido":
                setValores.invoke(variable.get(clase), paramValue, false, false);
                break;
            case "textoInvalido":
                setValores.invoke(variable.get(clase), 123, false, false);
                break;
            case "noEnviarCampo":
                setValores.invoke(variable.get(clase), paramValue, true, false);
                break;
            default:
                if (paramName.equals("extra")) {
                    try {
                        int valor = Integer.parseInt(paramValue);
                        setValores.invoke(variable.get(clase), valor, false, false);
                        break;
                    } catch (NumberFormatException e) {
                        setValores.invoke(variable.get(clase), paramValue, false, false);
                        break;
                    }
                } else {
                    setValores.invoke(variable.get(clase), paramValue, false, true);
                    break;
                }
        }
    }

    public static Hidable mapearAtributosDeObjeto(Hidable objeto, String paramName, String paramValue) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setValores;
        // Intenta buscar el método que setea el valor del atributo que hace referencia al "paramName" del objeto que recibió como parámetro
        setValores = objeto.getClass().getDeclaredMethod("set" + paramName, Object.class, boolean.class, boolean.class);

        switch (paramValue) {
            case "numeroInvalido":
                setValores.invoke(objeto, paramValue, false, false);
                break;
            case "textoInvalido":
                setValores.invoke(objeto, 123, false, false);
                break;
            case "noEnviarCampo":
                setValores.invoke(objeto, paramValue, true, false);
                break;
            default:
                if (paramName.equals("extra")) {
                    try {
                        int valor = Integer.parseInt(paramValue);
                        setValores.invoke(objeto, valor, false, false);
                        break;
                    } catch (NumberFormatException e) {
                        setValores.invoke(objeto, paramValue, false, false);
                        break;
                    }
                } else {
                    setValores.invoke(objeto, paramValue, false, true);
                    break;
                }
        }
        return objeto;
    }

    public static Hidable mapearAtributoListaDeStrings(Hidable objeto, String paramValue) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method setValores;
        // Intenta buscar el método que setea el valor del atributo en el objeto tipo Lista
        setValores = objeto.getClass().getDeclaredMethod("setValor", Hidable.class);

        Elemento elemento = new Elemento(String.class);

        switch (paramValue) {
            case "textoInvalido":
                elemento.setValor(123, false, false);
                setValores.invoke(objeto, elemento);
                break;
            case "noEnviarCampo":
                break;
            default:
                elemento.setValor(paramValue, false, true);
                setValores.invoke(objeto, elemento);
                break;
        }
        return objeto;
    }
}
