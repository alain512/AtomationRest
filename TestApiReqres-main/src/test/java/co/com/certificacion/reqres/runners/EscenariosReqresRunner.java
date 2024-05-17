package co.com.certificacion.reqres.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/escenarios_reqres.feature",
        glue = "co.com.certificacion.reqres.stepdefinitions",
        snippets = SnippetType.CAMELCASE,
        tags = "")
public class EscenariosReqresRunner {
}
