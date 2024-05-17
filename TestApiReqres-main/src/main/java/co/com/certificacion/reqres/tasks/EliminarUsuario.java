package co.com.certificacion.reqres.tasks;

import co.com.certificacion.reqres.utils.Constantes;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EliminarUsuario implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        String id = actor.recall("id");
        actor.attemptsTo(Delete.from(Constantes.ENDPOINT_UPDATE_AND_DELETE+id).with(requestSpecification -> requestSpecification
                .log().all().relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
    }

    public static EliminarUsuario conApiReqres(){
        return instrumented(EliminarUsuario.class);
    }
}
