package co.com.certificacion.reqres.tasks;

import co.com.certificacion.reqres.utils.Constantes;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ListarUsuarios implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(Constantes.ENDPOINT_LIST_USERS).with(req->req
                .log().all().relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
    }

    public static  ListarUsuarios deApiReqres(){
        return instrumented(ListarUsuarios.class);
    }
}
