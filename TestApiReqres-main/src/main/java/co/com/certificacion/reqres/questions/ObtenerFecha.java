package co.com.certificacion.reqres.questions;

import co.com.certificacion.reqres.models.RespuestaCreate;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ObtenerFecha implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        RespuestaCreate response = SerenityRest.lastResponse().jsonPath().getObject("",RespuestaCreate.class);
        return response.getCreatedAt();
    }

    public static ObtenerFecha deCreacionUsuario(){
        return new ObtenerFecha();
    }
}
