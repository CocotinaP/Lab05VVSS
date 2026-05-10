package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import webFTP.pages.AccountPage;
 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountPageSteps {

    AccountPage accountPage;

    @Step
    public void should_be_in_user_directory(String userDirectory) {

        //assertThat("current directory", userDirectory.equals(accountPage.getCurrentDirectoryName()));
        //Assert.assertTrue(accountPage.getCurrentDirectoryName(), userDirectory.equals(accountPage.getCurrentDirectoryName()));
        assertTrue(userDirectory.contains(accountPage.getCurrentDirectoryName()));
    }

    @Step
    public void logout() {

        accountPage.click_Logout();
    }

    @Step
    public void newDirectory() {
        accountPage.click_new_directory();
    }

    @Step
    public void newFile() {
        accountPage.click_new_file();
    }


    @Step
    public void should_be_able_to_see_new_directory(String createdDirectory) {
        assertThat(accountPage.getContent(), hasItem(containsString(createdDirectory)));
    }

//    @Step
//    public void should_be_able_to_see_new_file(String createdFile) {
//        assertThat(accountPage.getContent(), hasItem(containsString(createdFile)));
//    }

    @Step
    public void should_not_be_able_to_see_new_directory(String createdDirectory) {
        assertThat(accountPage.getContent(), not(hasItem(containsString(createdDirectory))));
    }

    @Step
    public void select_directory_to_delete(String directory) {

        accountPage.check_directory_to_delete(directory);
    }

    @Step
    public void delete_selected_directory() {
        accountPage.deleteDirectory();
    }

    @Step
    public void select_file_to_delete(String filename) {
        accountPage.check_file_to_delete(filename);
    }

    @Step
    public void delete_selected_file() {
        accountPage.click_delete();
    }

    @Step
    public void should_be_able_to_see_new_file(String filename) {
        assertTrue(accountPage.file_exists(filename));
    }

    @Step
    public void should_not_be_able_to_see_new_file(String filename) {
        assertTrue(accountPage.file_not_exists(filename));
    }

    @Step
    public void edit_file(String filename) {
        accountPage.click_edit_for_file(filename);
    }

    @Step
    public void should_not_be_able_to_edit_file(String filename) {
        boolean exists = accountPage.file_exists(filename);
        assertFalse("Fisierul NU ar trebui sa existe, dar exista!", exists);
    }

}