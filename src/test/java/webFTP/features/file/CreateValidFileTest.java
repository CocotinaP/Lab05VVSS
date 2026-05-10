package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

@RunWith(SerenityRunner.class)
public class CreateValidFileTest {

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    @Steps
    public LogoutPageSteps userLoggedOut;

    @Test
    public void createNewFile() {

        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/" + "vvta1");

        userLoggedIn.newFile();

        userNewFile.create_file("testFile123.txt", "Acesta este un fisier creat automat.");

        userLoggedIn.should_be_able_to_see_new_file("testFile123.txt");

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}
