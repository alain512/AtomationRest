package co.com.certificacion.reqres.questions;

import co.com.certificacion.reqres.models.ResponseListarUsuarios;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ObtenerValor implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        ResponseListarUsuarios response = SerenityRest.lastResponse().jsonPath().getObject("",ResponseListarUsuarios.class);
        return response.getTotal();
    }

    public static ObtenerValor totalDeUsuarios(){
        return new ObtenerValor();
    }
}
