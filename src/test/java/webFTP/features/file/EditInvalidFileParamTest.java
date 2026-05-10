package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.AccountPageSteps;
import webFTP.steps.serenity.EditFilePageSteps;
import webFTP.steps.serenity.LoginPageSteps;

import static org.junit.Assert.assertFalse;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\features\\edit_file\\edit_file_invalid.csv")
public class EditInvalidFileParamTest {
    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginPageSteps user;
    @Steps
    AccountPageSteps userLoggedIn;
    @Steps
    EditFilePageSteps userEditFile;

    private String filename;
    private String content;

    @Test
    public void edit_file_with_invalid_data() {

        driver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        // 1. Fisierul nu exista
        userLoggedIn.should_not_be_able_to_edit_file(filename);

        // 2. Verificam ca nu exista link Edit pentru el
        assertFalse(driver.getPageSource().contains(filename));

        // 3. Verificam ca nu s-a deschis pagina de editare
        assertFalse(driver.getPageSource().contains("Status:"));

        userLoggedIn.logout();
    }


}
