package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

@RunWith(SerenityRunner.class)
public class RenameInvalidFileTest {

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    @Steps
    public RenameFilePageSteps userRenameFile;

    @Steps
    public DeleteDirectoryPageSteps userDelete;

    @Steps
    public LogoutPageSteps userLoggedOut;

    @Test
    public void renameFileWithEmptyNameShouldFail() {
        String filename = "rename_invalid_test.txt";

        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        userLoggedIn.newFile();
        userNewFile.create_file(filename, "continut pentru test rename invalid");

        userLoggedIn.should_be_able_to_see_new_file(filename);

        userLoggedIn.select_file(filename);
        userLoggedIn.rename_selected_file();

        userRenameFile.rename_file_with_invalid_empty_name(filename);

        userLoggedIn.should_be_able_to_see_new_file(filename);

        userLoggedIn.select_file_to_delete(filename);
        userLoggedIn.delete_selected_file();
        userDelete.delete_file(filename);

        userLoggedIn.should_not_be_able_to_see_new_file(filename);

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}