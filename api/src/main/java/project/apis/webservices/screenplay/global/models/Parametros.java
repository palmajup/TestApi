package project.apis.webservices.screenplay.global.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <h1>Parametros</h1>
 * Clase abstracta utilizada para implementar variacion del patron Strategy. Reemplaza a la interfaz en la
 * implementacion convencional del patron.
 * En esta clase se definen el comportamiento comun a todos sus descendientes, modificando el resultado final
 * en base a quien es el descendiente, ya que el descendiente utiliza este constructor pasando informacion especifica de él
 *
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public abstract class Parametros {
    protected HashMap<String, Object> parametros;
    HashMap<String, String> obligatorios;
    HashMap<String, String> opcionales;
    HashMap<String, String> extras;

    public Parametros() {
    }

    /**
     * Constructor que es utilizado por todas las clases descendientes, de forma que los datos cambion segun que descendiente
     * invoque el constructor
     *
     * @param obligatorios HashMap conteniendo nombre del campo y valor del mismo de los campos obligatorios del descendiente invocando el constructor
     * @param opcionales   HashMap conteniendo nombre del campo y valor del mismo de los campos opcionales del descendiente invocando el constructor
     * @param extras       HashMap conteniendo nombre del campo y valor del mismo de los campos extras del descendiente invocando el constructor
     */
    public Parametros(HashMap<String, String> obligatorios, HashMap<String, String> opcionales, HashMap<String, String> extras) {
        this.parametros = new HashMap<String, Object>();
        this.obligatorios = obligatorios;
        this.opcionales = opcionales;
        this.extras = extras;
    }

    /**
     * Metodo que retorna un HashMap con todos los campos mapeados
     *
     * @return HashMap con el mapeo de todos los parametros
     */
    public HashMap<String, Object> getMapa() {
        return this.parametros;
    }

    /**
     * Metodo que determina los campos y valores a mapear
     *
     * @param valores       Array de valores a mapear
     * @param camposAMapear Codigo del tipo de campo a mapear
     */
    protected void mapearCampos(String[] valores, int camposAMapear) {
        if (valores == null) return;
        HashMap<String, String> campos;
        switch (camposAMapear) {
            case MapaDeParametros.OBLIGATORIOS:
                campos = obligatorios;
                break;
            case MapaDeParametros.OPCIONALES:
                campos = opcionales;
                break;
            case MapaDeParametros.EXTRAS:
                campos = extras;
                break;
            default:
                campos = new HashMap<>();
                break;
        }
        if (valores.length == campos.size()) {
            agregarAlMapa(campos, valores);
        }
    }

    protected void mapearCampos(List<List<String>> parametros, int camposAMapear) {
        if (parametros == null) return;
        HashMap<String, String> campos;
        switch (camposAMapear) {
            case MapaDeParametros.OBLIGATORIOS:
                campos = obligatorios;
                break;
            case MapaDeParametros.OPCIONALES:
                campos = opcionales;
                break;
            case MapaDeParametros.EXTRAS:
                campos = extras;
                break;
            default:
                campos = new HashMap<>();
                break;
        }
        if (parametros.get(0).size() == campos.size()) {
            agregarAlMapa(campos, parametros);
        }
    }

    protected void agregarAlMapa(HashMap<String, String> campos, List<List<String>>  camposParaAgregar) {
        Object valor = null;
        List<String> nombre = camposParaAgregar.get(0);
        List<String> valores = camposParaAgregar.get(1);

        Object[] nombreCampos = campos.keySet().toArray();
        Object[] tipoDeDato = campos.values().toArray();
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i).equals("noEnviarCampo")) continue;
            //Si es dato correcto es un entero, no estoy intentando pasar el string de un numero y va a poder ser parseado...
            if (tipoDeDato[i].equals("Integer") && valores.get(i).matches("^[0-9]+$"))
                valor = Integer.parseInt(valores.get(i));

            if (tipoDeDato[i].equals("Long") && valores.get(i).matches("^[0-9]+$"))
                valor = Long.parseLong(valores.get(i));

            if (tipoDeDato[i].equals("Decimal") && (valores.get(i).matches("(\\d+)\\.(\\d+)") || valores.get(i).matches("^[0-9]+$")))
                valor = Float.parseFloat(valores.get(i));

            if (tipoDeDato[i].equals("Boolean") && (valores.get(i).equals("true") || valores.get(i).equals("false")))
                valor = valores.get(i).equals("true");

            if (tipoDeDato[i].equals("String"))
                valor = valores.get(i);

            if (valores.get(i).equals("null") || valores.get(i).equals("invalido") || valores.get(i).equals("") || valores.get(i).equals("extra"))
                valor = valores.get(i);

            if (tipoDeDato[i].equals("String") && valores.get(i).equals("invalido"))
                valor = 123;

            //Simular error interno - 500, solo valido para campos en los que se debe enviar un "Int"
            //al enviar un Long el servicio responde con error Interno
            if (tipoDeDato[i].equals("Integer") && valores.get(i).equals("errorInterno"))
                valor = Long.parseLong("3512315312513231");

            if (nombreCampos[i].equals("extra") && valores.get(i).matches("(\\d+)"))
                valor = Integer.parseInt(valores.get(i));

            String campoJerarquia = (String) nombreCampos[i];
            String[] camposAnidados = campoJerarquia.split("[.]");
            if (camposAnidados.length == 1) {
                if (tipoDeDato[i].equals("Array")) {
                    parametros.put(campoJerarquia, new ArrayList<HashMap<String, Object>>());
                } else {
                    parametros.put(campoJerarquia, valor);
                }
            } else
                agregar(camposAnidados, valor, (String) tipoDeDato[i]);
        }
    }

    /**
     * Metodo que agrega al mapa los nombres de los campos y sus respectivos valores
     *
     * @param campos  HashMap con los nombres y el tipo de dato correcto de los campos a mapear
     * @param valores valores de los campos a mapear
     */
    protected void agregarAlMapa(HashMap<String, String> campos, String[] valores) {
        Object valor = null;
        Object[] nombreCampos = campos.keySet().toArray();
        Object[] tipoDeDato = campos.values().toArray();
        for (int i = 0; i < valores.length; i++) {
            if (valores[i].equals("noEnviarCampo")) continue;
            //Si es dato correcto es un entero, no estoy intentando pasar el string de un numero y va a poder ser parseado...
            if (tipoDeDato[i].equals("Integer") && valores[i].matches("^[0-9]+$"))
                valor = Integer.parseInt(valores[i]);

            if (tipoDeDato[i].equals("Long") && valores[i].matches("^[0-9]+$"))
                valor = Long.parseLong(valores[i]);

            if (tipoDeDato[i].equals("Decimal") && (valores[i].matches("(\\d+)\\.(\\d+)") || valores[i].matches("^[0-9]+$")))
                valor = Float.parseFloat(valores[i]);

            if (tipoDeDato[i].equals("Boolean") && (valores[i].equals("true") || valores[i].equals("false")))
                valor = valores[i].equals("true");

            if (tipoDeDato[i].equals("String"))
                valor = valores[i];

            if (valores[i].equals("null") || valores[i].equals("invalido") || valores[i].equals("") || valores[i].equals("extra"))
                valor = valores[i];

            if (tipoDeDato[i].equals("String") && valores[i].equals("invalido"))
                valor = 123;

            //Simular error interno - 500, solo valido para campos en los que se debe enviar un "Int"
            //al enviar un Long el servicio responde con error Interno
            if (tipoDeDato[i].equals("Integer") && valores[i].equals("errorInterno"))
                valor = Long.parseLong("3512315312513231");

            if (nombreCampos[i].equals("extra") && valores[i].matches("(\\d+)"))
                valor = Integer.parseInt(valores[i]);

            String campoJerarquia = (String) nombreCampos[i];
            String[] camposAnidados = campoJerarquia.split("[.]");
            if (camposAnidados.length == 1) {
                if (tipoDeDato[i].equals("Array")) {
                    parametros.put(campoJerarquia, new ArrayList<HashMap<String, Object>>());
                } else {
                    parametros.put(campoJerarquia, valor);
                }
            } else
                agregar(camposAnidados, valor, (String) tipoDeDato[i]);
        }
    }

    private void agregar(String[] campos, Object valor, String tipo) {
        HashMap<String, Object> padre;
        String[] predecesores;
        int limiteSuperior;
        boolean newArray = false;
        int arrayElement = 0;

        for (int i = 0; i < campos.length; i++) {
            limiteSuperior = i;
            predecesores = Arrays.copyOfRange(campos, 0, limiteSuperior);

            if (campos[i].charAt(campos[i].length() - 1) == ']') {
                String element = campos[i].substring(campos[i].indexOf('[') + 1, campos[i].length() - 1);
                arrayElement = Integer.parseInt(element);
                campos[i] = campos[i].substring(0, campos[i].indexOf('['));
            }
            padre = obtenerPadre(predecesores, newArray, arrayElement);
            if (!padre.containsKey(campos[i])) {
                HashMap<String, Object> nuevoAnidado = new HashMap<>();
                ArrayList<HashMap<String, Object>> nuevaLista;
                if (i == 0) {
                    if (tipo.equals("Array") && i == campos.length - 1) {
                        parametros.put(campos[i], new ArrayList<HashMap<String, Object>>());
                        newArray = true;
                    } else
                        parametros.put(campos[i], nuevoAnidado);
                } else {
                    if (i != campos.length - 1)
                        padre.put(campos[i], nuevoAnidado);
                    else {
                        if (tipo.equals("Array")) {
                            padre.put(campos[i], nuevaLista = new ArrayList<HashMap<String, Object>>());
                            newArray = true;
                        } else {
                            padre.put(campos[i], valor);
                            newArray = false;
                            arrayElement = 0;
                        }
                    }
                }
            }
        }
    }

    private HashMap<String, Object> obtenerPadre(String[] predecesores, boolean newArray, int arrayElement) {
        HashMap<String, Object> padre = parametros;
        ArrayList<HashMap> listaAnidada;
        HashMap<String, Object> nuevoAnidado = new HashMap<>();
        for (String predecesor : predecesores) {
            if (padre.get(predecesor).getClass().getName().contains("List")) {
                listaAnidada = (ArrayList<HashMap>) padre.get(predecesor);
                if (newArray && listaAnidada.size() == 0) {
                    listaAnidada = (ArrayList<HashMap>) padre.get(predecesor);
                    listaAnidada.add(nuevoAnidado);
                }
                if (listaAnidada.size() < arrayElement + 1) {
                    listaAnidada = (ArrayList<HashMap>) padre.get(predecesor);
                    listaAnidada.add(nuevoAnidado);
                }
                listaAnidada = (ArrayList<HashMap>) padre.get(predecesor);
                padre = listaAnidada.get(arrayElement);
            } else
                padre = (HashMap<String, Object>) padre.get(predecesor);
        }
        return padre;
    }
}
