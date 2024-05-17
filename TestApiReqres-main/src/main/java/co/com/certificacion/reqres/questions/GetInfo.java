package co.com.certificacion.reqres.questions;

import co.com.certificacion.reqres.models.ResponseGetUser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetInfo implements Question {
    @Override
    public Object answeredBy(Actor actor) {
            ResponseGetUser response = SerenityRest.lastResponse().jsonPath().getObject("", ResponseGetUser.class);
            actor.remember("emailUser", response.getData().getEmail());
            actor.remember("firstNameUser", response.getData().getFirst_name());
            actor.remember("lastNameUser", response.getData().getLast_name());
        return response.getData().getId();
    }

    public static GetInfo user() {
        return new GetInfo();
    }
}
