package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.AccountPageSteps;
import webFTP.steps.serenity.LoginPageSteps;
import webFTP.steps.serenity.NewFilePageSteps;

import static org.junit.Assert.assertFalse;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\features\\create_new_file\\new_file_invalid.csv")
public class CreateInvalidFileParamTest {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginPageSteps user;

    @Steps
    AccountPageSteps userLoggedIn;

    @Steps
    NewFilePageSteps userNewFile;

    private String name;
    private String content;

    @Test
    public void create_file_with_invalid_data() {

        driver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        // Fisierul cu nume invalid nu exista pe server
        userLoggedIn.should_not_be_able_to_edit_file(name);

        // Numele fisierului invalid nu apare nicaieri in lista
        assertFalse(driver.getPageSource().contains(name));

        userLoggedIn.logout();
    }

}
