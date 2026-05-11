package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.RenameFilePage;
import static org.junit.Assert.assertTrue;

public class RenameFilePageSteps {

    RenameFilePage renameFilePage;

    @Step
    public void rename_file(String oldName, String newName) {
        renameFilePage.enter_new_name(newName);
        renameFilePage.click_submit();
        renameFilePage.go_back_to_file_list();
    }

    @Step
    public void rename_file_with_invalid_empty_name(String oldName) {
        renameFilePage.enter_new_name("");
        renameFilePage.click_submit();

        assertTrue(
                "Mesajul de eroare pentru rename invalid nu a aparut.",
                renameFilePage.rename_error_message_is_visible(oldName)
        );

        renameFilePage.go_back_to_file_list();
    }
}