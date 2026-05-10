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

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\features\\edit_file\\edit_file_valid.csv")
public class EditValidFileParamTest {

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
    public void edit_file_with_valid_data() {

        driver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        userLoggedIn.edit_file(filename);
        userEditFile.edit_content(content);
        userEditFile.save_changes();
        userEditFile.should_see_saved_message();
        userEditFile.back();

        userLoggedIn.logout();

    }
}

