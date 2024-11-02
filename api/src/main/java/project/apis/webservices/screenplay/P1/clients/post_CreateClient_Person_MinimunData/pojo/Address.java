package project.apis.webservices.screenplay.P1.clients.post_CreateClient_Person_MinimunData.pojo;

import project.apis.webservices.screenplay.global.models.Hidable;

import java.lang.reflect.InvocationTargetException;

public class Address implements Hidable {
    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void checkAndSetHidden() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

    }
}
