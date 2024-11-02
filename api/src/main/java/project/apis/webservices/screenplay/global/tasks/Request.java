package project.apis.webservices.screenplay.global.tasks;

import java.util.HashMap;

/**
 * <h1>Request</h1>
 * Clase padre de los diferentes tipos de requests. Permite utilizar polimorfismo
 * @author Luciano Cruz
 * @since 19/09/2019
 */
public class Request{
    protected String URI;
    protected HashMap<String, Object> info;
    protected boolean headers;
    protected String[] ids;
    protected boolean token;

    Request(){};
    Request(String URI, HashMap<String, Object> info, boolean headers){
        this.URI = URI;
        this.info = info;
        this.headers = headers;
    }

    Request(String URI, HashMap<String, Object> info, String[] ids, boolean headers) {
        this.URI = URI;
        this.info = info;
        this.ids = ids;
        this.headers = headers;
    }

    Request(String URI, HashMap<String, Object> info, boolean headers, boolean token){
        this.URI = URI;
        this.info = info;
        this.headers = headers;
        this.token = token;
    }

    Request(String URI, HashMap<String, Object> info, String[] ids, boolean headers, boolean token){
        this.URI = URI;
        this.info = info;
        this.ids = ids;
        this.headers = headers;
        this.token = token;
    }
}
