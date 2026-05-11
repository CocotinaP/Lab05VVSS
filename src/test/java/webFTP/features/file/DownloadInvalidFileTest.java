package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SerenityRunner.class)
public class DownloadInvalidFileTest {

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public LogoutPageSteps userLoggedOut;

    @Test
    public void downloadWithoutSelectingFileShouldFail() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        JavascriptExecutor js = (JavascriptExecutor) webdriver;

        js.executeScript(
                "window.lastAlertMessage = ''; " +
                        "window.alert = function(message) { window.lastAlertMessage = message; };"
        );

        userLoggedIn.download_selected_file();

        String alertText = (String) js.executeScript("return window.lastAlertMessage;");

        assertThat(alertText, containsString("Please select at least one directory or file."));

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}