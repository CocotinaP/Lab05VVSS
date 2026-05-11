package webFTP.features.scenario;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.*;

@RunWith(SerenityRunner.class)
public class FileManagementScenarioTest {

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    @Steps
    public EditFilePageSteps userEditFile;

    @Steps
    public RenameFilePageSteps userRenameFile;

    @Steps
    public DeleteDirectoryPageSteps userDelete;

    @Steps
    public LogoutPageSteps userLoggedOut;

    /**
     * Scenariu de utilizare care inlantuie 4 functionalitati:
     * 1. Login valid
     * 2. Creare fisier nou
     * 3. Editare fisier
     * 4. Redenumire fisier
     * 5. Logout (cu cleanup anterior)
     */
    @Test
    public void fileManagementScenario() {
        String initialFile = "scenario_initial.txt";
        String renamedFile = "scenario_renamed.txt";
        String initialContent = "Continut initial creat in scenariu.";
        String editedContent = "Continut editat in pasul 3 al scenariului.";

        // Pas 1: Login valid
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        user.click_saveCookies();
        user.login_steps("localhost", "vvta1", "vvta1");
        userLoggedIn.should_be_in_user_directory("/home/vvta1");

        // Pas 2: Creare fisier nou
        userLoggedIn.newFile();
        userNewFile.create_file(initialFile, initialContent);
        userLoggedIn.should_be_able_to_see_new_file(initialFile);

        // Pas 3: Editare fisier
        userLoggedIn.edit_file(initialFile);
        userEditFile.edit_content(editedContent);
        userEditFile.save_changes();
        userEditFile.should_see_saved_message();
        userEditFile.back();
        userLoggedIn.should_be_able_to_see_new_file(initialFile);

        // Pas 4: Redenumire fisier
        userLoggedIn.select_file(initialFile);
        userLoggedIn.rename_selected_file();
        userRenameFile.rename_file(initialFile, renamedFile);
        userLoggedIn.should_not_be_able_to_see_new_file(initialFile);
        userLoggedIn.should_be_able_to_see_new_file(renamedFile);

        // Cleanup: stergere fisier redenumit
        userLoggedIn.select_file_to_delete(renamedFile);
        userLoggedIn.delete_selected_file();
        userDelete.delete_file(renamedFile);
        userLoggedIn.should_not_be_able_to_see_new_file(renamedFile);

        // Pas 5: Logout
        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}
