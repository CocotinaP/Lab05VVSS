package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.RenameFilePage;

public class RenameFilePageSteps {

    RenameFilePage renameFilePage;

    @Step
    public void rename_file(String oldName, String newName) {
        renameFilePage.enter_new_name(newName);
        renameFilePage.click_submit();
        renameFilePage.go_back_to_file_list();
    }
}