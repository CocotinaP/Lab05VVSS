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

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\features\\create_new_file\\new_file_valid.csv")
public class CreateValidFileParamTest {

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
    public void create_file_with_valid_data() {

        driver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");
        userLoggedIn.newFile();

        userNewFile.create_file(name, content);

        userLoggedIn.should_be_able_to_see_new_file(name);

        userLoggedIn.logout();
    }
}
