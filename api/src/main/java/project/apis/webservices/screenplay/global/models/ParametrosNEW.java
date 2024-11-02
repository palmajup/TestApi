package project.apis.webservices.screenplay.global.models;

import project.apis.webservices.screenplay.ServiceSetup;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.BeanDescription;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.JsonSerializer;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.SerializationConfig;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.module.SimpleModule;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ParametrosNEW {
    public static int inLine(String endPoint) {
        return StringUtils.countMatches(endPoint, "%s");
    }

    public static HashMap<String, Object> mapear(List<String> paramName, List<String> paramValue) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < paramName.size(); i++) {
            if (paramValue.get(i).equals("noEnviarCampo")) continue;
            if (paramValue.get(i).equals("textoInvalido")) {
                map.put(paramName.get(i), 123);
            } else {
                map.put(paramName.get(i), paramValue.get(i));
            }
        }
        return map;
    }

    public static LinkedHashMap<String, Object> mapear(int inline, List<String> paramName, List<String> paramValue) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = inline; i < paramName.size(); i++) {
            if (paramValue.get(i).equals("noEnviarCampo")) continue;
            map.put(paramName.get(i), paramValue.get(i));
        }
        return map;
    }

    public static String[] mapearInline(int inline, List<String> paramValue) {
        String[] parametrosInLine = new String[inline];

        for (int i = 0; i < inline; i++) {
            parametrosInLine[i] = paramValue.get(i);
        }
        return parametrosInLine;
    }

    public static HashMap<String, Object> mapearPost(Collection<String> tagNames, List<String> paramName, List<String> paramValue) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        oMapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(
                            SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
                        if (Hidable.class.isAssignableFrom(desc.getBeanClass())) {
                            return new HidableSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        //Realizar el mapeo y devolver el HashMap correcto para el request
        Class<? extends SolicitudPojo> api = ServiceSetup.obtenerBody(tagNames);
        SolicitudPojo clase = api.getDeclaredConstructor().newInstance();


        //Implementar método setAtributos en la clase
        try {
            clase.setAtributos(paramName, paramValue, clase);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Mapear el POJO a HashMap
        return oMapper.convertValue(clase, HashMap.class);
    }

    public static HashMap<String, Object> mapearPost(Collection<String> tagNames, String[] ids, List<String> paramName, List<String> paramValue) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        oMapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(
                            SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
                        if (Hidable.class.isAssignableFrom(desc.getBeanClass())) {
                            return new HidableSerializer((JsonSerializer<Object>) serializer);
                        }
                        return serializer;
                    }
                });
            }
        });

        //Realizar el mapeo y devolver el HashMap correcto para el request
        Class<? extends SolicitudPojo> api = ServiceSetup.obtenerBody(tagNames);
        SolicitudPojo clase = api.getDeclaredConstructor().newInstance();


        //Implementar método setAtributos en la clase
        for (int i = 0; i < ids.length; i++) {
            paramName.remove(i);
            paramValue.remove(i);
        }
        try {
            clase.setAtributos(paramName, paramValue, clase);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //Mapear el POJO a HashMap
        return oMapper.convertValue(clase, HashMap.class);
    }

}
