package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.EditFilePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class EditFilePageSteps {

    EditFilePage editFilePage;

    @Step
    public void edit_content(String content) {
        editFilePage.enter_new_content(content);
    }

    @Step
    public void save_changes() {
        editFilePage.click_save();
    }

    @Step
    public void should_see_saved_message() {
        assertThat(editFilePage.get_status_text(), containsString("Saved on"));
    }

    @Step
    public void back() {
        editFilePage.click_back();
    }
}

