package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

@RunWith(SerenityRunner.class)
public class DownloadValidFileTest {

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    @Steps
    public DeleteDirectoryPageSteps userDelete;

    @Steps
    public LogoutPageSteps userLoggedOut;

    @Test
    public void downloadValidFile() throws InterruptedException {
        String filename = "download_test_file.txt";

        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        userLoggedIn.newFile();
        userNewFile.create_file(filename, "continut pentru test download");

        userLoggedIn.should_be_able_to_see_new_file(filename);

        userLoggedIn.select_file(filename);
        userLoggedIn.download_selected_file();

        Thread.sleep(2000);

        webdriver.navigate().refresh();
        Thread.sleep(1000);

        userLoggedIn.should_be_able_to_see_new_file(filename);

        userLoggedIn.select_file(filename);
        userLoggedIn.delete_selected_file();
        userDelete.delete_file(filename);

        userLoggedIn.should_not_be_able_to_see_new_file(filename);

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}