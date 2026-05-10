package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.NewFilePage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class NewFilePageSteps {

    NewFilePage newFilePage;

    @Step
    public void enter_file_name(String name) {
        newFilePage.enter_file_name(name);
    }

    @Step
    public void enter_file_content(String content) {
        newFilePage.enter_file_content(content);
    }

    @Step
    public void click_save() {
        newFilePage.click_save();
    }

    @Step
    public void click_back() {
        newFilePage.click_back();
    }

    @Step
    public void create_file(String name, String content) {
        enter_file_name(name);
        enter_file_content(content);
        click_save();
        should_see_message("Saved on");
        click_back();
    }

    @Step
    public void should_see_error(String message) {
        assertThat(newFilePage.get_error_text(), containsString(message));
    }


    @Step
    public void should_see_message(String message) {
        assertThat(newFilePage.get_status_text(), containsString(message));
    }

}
