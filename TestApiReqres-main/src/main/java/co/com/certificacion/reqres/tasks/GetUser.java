package co.com.certificacion.reqres.tasks;

import co.com.certificacion.reqres.utils.Constantes;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static co.com.certificacion.reqres.utils.Constantes.ENDPOINT_SINGLE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUser implements Task {
    private String id;

    public GetUser(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(ENDPOINT_SINGLE_USER).with(req->req
                .pathParam("id",id).log()
                .all().relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
    }

    public static GetUser infoById(String id){
        return instrumented(GetUser.class,id);
    }
}
