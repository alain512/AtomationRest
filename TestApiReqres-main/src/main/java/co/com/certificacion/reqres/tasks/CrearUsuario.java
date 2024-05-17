package co.com.certificacion.reqres.tasks;

import co.com.certificacion.reqres.models.DatosUsuario;
import co.com.certificacion.reqres.utils.ReadFile;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.List;

import static co.com.certificacion.reqres.utils.Constantes.ENDPOINT_CREATE;
import static co.com.certificacion.reqres.utils.Constantes.RUTA_JSON_CREATE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearUsuario implements Task {
    private String name;
    private String job;

    public CrearUsuario(List<DatosUsuario> data) {
        this.name = data.get(0).getName();
        this.job = data.get(0).getJob();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String body = String.format(ReadFile.returnFile(RUTA_JSON_CREATE), name, job);
        actor.attemptsTo(Post.to(ENDPOINT_CREATE).with(req -> req
                .header("Content-Type", "application/json")
                .body(body).log().all()
                .relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
        actor.remember("id",SerenityRest.lastResponse().jsonPath().get("id").toString());
    }

    public static CrearUsuario conApiReqres(List<DatosUsuario>data){
        return instrumented(CrearUsuario.class,data);
    }
}
