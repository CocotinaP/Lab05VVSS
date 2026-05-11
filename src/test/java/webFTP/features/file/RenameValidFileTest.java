package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

@RunWith(SerenityRunner.class)
public class RenameValidFileTest {

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
    public void renameValidFile() {
        String initialFile = "rename_initial_test.txt";
        String renamedFile = "rename_final_test.txt";

        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");

        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");

        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        userLoggedIn.newFile();
        userNewFile.create_file(initialFile, "continut initial pentru test rename");

        userLoggedIn.should_be_able_to_see_new_file(initialFile);

        userLoggedIn.select_file(initialFile);
        userLoggedIn.rename_selected_file();

        userRenameFile.rename_file(initialFile, renamedFile);

        userLoggedIn.should_not_be_able_to_see_new_file(initialFile);
        userLoggedIn.should_be_able_to_see_new_file(renamedFile);

        userLoggedIn.select_file_to_delete(renamedFile);
        userLoggedIn.delete_selected_file();
        userDelete.delete_file(renamedFile);

        userLoggedIn.should_not_be_able_to_see_new_file(renamedFile);

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}