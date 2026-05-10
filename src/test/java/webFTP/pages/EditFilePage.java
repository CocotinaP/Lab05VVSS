package webFTP.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class EditFilePage extends PageObject {

    @FindBy(name = "text")
    private WebElementFacade textContent;

    @FindBy(xpath = "//*[@id='EditForm']//a[2]/img")
    private WebElementFacade saveButton;

    @FindBy(xpath = "//*[@id='EditForm']//a[1]/img")
    private WebElementFacade backButton;

    @FindBy(xpath = "//span[contains(text(),'Status')]")
    private WebElementFacade statusMessage;

    public void enter_new_content(String content) {
        textContent.waitUntilVisible().clear();
        textContent.type(content);
        textContent.sendKeys(" "); // activează Save
    }

    public void click_save() {
        saveButton.waitUntilClickable().click();
    }

    public String get_status_text() {
        return statusMessage.waitUntilVisible().getText();
    }

    public void click_back() {
        backButton.waitUntilClickable().click();
    }
}

