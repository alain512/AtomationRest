package co.com.certificacion.reqres.questions;

import co.com.certificacion.reqres.models.ResponseUpdate;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ObtenerFechaDe implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        ResponseUpdate response = SerenityRest.lastResponse().jsonPath().getObject("",ResponseUpdate.class);
        return response.getUpdatedAt();
    }

    public static ObtenerFechaDe actualizacionUsuario(){
        return new ObtenerFechaDe();
    }
}
