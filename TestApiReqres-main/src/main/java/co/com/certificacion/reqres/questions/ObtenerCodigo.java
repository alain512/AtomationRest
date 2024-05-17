package co.com.certificacion.reqres.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ObtenerCodigo implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }

    public static ObtenerCodigo deRespuesta(){
        return new ObtenerCodigo();
    }
}
