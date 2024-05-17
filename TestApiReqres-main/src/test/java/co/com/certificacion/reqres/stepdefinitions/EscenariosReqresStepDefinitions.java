package co.com.certificacion.reqres.stepdefinitions;

import co.com.certificacion.reqres.models.Data;
import co.com.certificacion.reqres.models.DatosUsuario;
import co.com.certificacion.reqres.models.GenericModel;
import co.com.certificacion.reqres.questions.*;
import co.com.certificacion.reqres.tasks.*;
import co.com.certificacion.reqres.utils.Constantes;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EscenariosReqresStepDefinitions {
    Actor usuario = Actor.named("alain");
    DateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String fechaActual = formateador.format(date);

    @Given("^que el usuario cuenta con la api reqres$")
    public void queElUsuarioCuentaConLaApiReqres() {
        usuario.whoCan(CallAnApi.at(Constantes.URL_REQRES));
    }

    //LISTAR USUARIOS
    @When("^se realiza la peticion para listar usuarios$")
    public void seRealizaLaPeticionParaListarUsuarios() {
        usuario.attemptsTo(ListarUsuarios.deApiReqres());
    }

    @Then("^la respuesta de la api presenta el codigo (.*)$")
    public void laRespuestaDeLaApiPresentaElCodigo(int codigoRespuesta) {
        usuario.should(GivenWhenThen.seeThat(ObtenerCodigo.deRespuesta(), Matchers.equalTo(codigoRespuesta)));
    }

    @And("^en la respuesta debe presentar el campo llamado total con valor (.*)$")
    public void enLaRespuestaDebePresentarElCampoLlamadoTotalConValor(String valor) {
        usuario.should(GivenWhenThen.seeThat(ObtenerValor.totalDeUsuarios(), Matchers.equalTo(valor)));
    }

    //CREAR USUARIO
    @When("^se envian los datos de usuario a crear$")
    public void seEnvianLosDatosDeUsuarioACrear(List<DatosUsuario> data) {
        usuario.attemptsTo(CrearUsuario.conApiReqres(data));
    }

    @And("^en la respuesta la fecha de creacion debe ser la fecha actual$")
    public void enLaRespuestaLaFechaDeCreacionDebeSerLaFechaActual() {
        usuario.should(GivenWhenThen.seeThat(ObtenerFecha.deCreacionUsuario(), Matchers.containsString(fechaActual)));
    }

    //OBTENER INFO USUARIO POR ID EXITOSO
    @When("^se realiza la consulta por id$")
    public void seRealizaLaConsultaPorId(List<GenericModel> id) {
        usuario.attemptsTo(GetUser.infoById(id.get(0).getId()));
    }

    @And("^la respuesta de la api presenta la informacion del usuario$")
    public void laRespuestaDeLaApiPresentaLaInformacionDelUsuario(List<Data> infoUsuario) {
        usuario.should(GivenWhenThen.seeThat(GetInfo.user(),
                Matchers.equalTo(infoUsuario.get(0).getId())));
        usuario.should(GivenWhenThen.seeThat(actor -> actor.recall("emailUser"),
                Matchers.equalTo(infoUsuario.get(0).getEmail())));
        usuario.should(GivenWhenThen.seeThat(actor -> actor.recall("firstNameUser"),
                Matchers.equalTo(infoUsuario.get(0).getFirst_name())));
        usuario.should(GivenWhenThen.seeThat(actor -> actor.recall("lastNameUser"),
                Matchers.equalTo(infoUsuario.get(0).getLast_name())));
    }
    //OBTENER INFO USUARIO POR ID FALLIDO
    @When("^se realiza la consulta por id (.*)$")
    public void seRealizaLaConsultaPorId(String id) {
        usuario.attemptsTo(GetUser.infoById(id));
    }

    //ACTUALIZAR USUARIO
    @And("^el usuario luego envia los datos del usuario a actualizar$")
    public void elUsuarioLuegoEnviaLosDatosDelUsuarioAActualizar(List<DatosUsuario> data) {
        usuario.attemptsTo(ActualizarUsuario.enApiReqres(data));
    }

    @And("^en la respuesta la fecha de actualizacion debe ser la fecha actual$")
    public void enLaRespuestaLaFechaDeActualizacionDebeSerLaFechaActual() {
        usuario.should(GivenWhenThen.seeThat(ObtenerFechaDe.actualizacionUsuario(), Matchers.containsString(fechaActual)));
    }

    //METODO DELETE
    @And("^el usuario envia la peticion para eliminar usuario$")
    public void elUsuarioEnviaLaPeticionParaEliminarUsuario() {
        usuario.attemptsTo(EliminarUsuario.conApiReqres());
    }



}
