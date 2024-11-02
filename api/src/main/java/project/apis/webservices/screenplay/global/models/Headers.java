package project.apis.webservices.screenplay.global.models;

import java.util.LinkedHashMap;
import java.util.List;

public class Headers {
    private static List<String> headerName, headerValue;
    private static List<String> securityHeaderName, securityHeaderValue;

    public static void setHeaders(List<String> headerName, List<String> headerValue) {
        Headers.headerName = headerName;
        Headers.headerValue = headerValue;
    }

    public static void setSecurityHeaders(List<String> securityHeaderName, List<String> securityHeaderValue) {
        Headers.securityHeaderName = securityHeaderName;
        Headers.securityHeaderValue = securityHeaderValue;
    }

    public static LinkedHashMap<String, String> mapear(boolean headers, boolean token) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        if (headers) {
            for (int i = 0; i < headerName.size(); i++) {
                map.put(headerName.get(i), headerValue.get(i));
            }
        }
        if (!token) {
            map.put("Authorization", "");
        } else {
            for (int i = 0; i < securityHeaderName.size(); i++) {
                map.put(securityHeaderName.get(i), securityHeaderValue.get(i));
            }
        }
        return map;
    }
}
