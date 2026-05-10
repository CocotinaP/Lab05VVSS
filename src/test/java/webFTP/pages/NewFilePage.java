package webFTP.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class NewFilePage extends PageObject {

    @FindBy(name = "entry")
    private WebElementFacade fileName;

    @FindBy(name = "text")
    private WebElementFacade textContent;

    @FindBy(xpath = "//*[@id='EditForm']//a[2]/img")
    private WebElementFacade saveButton;

    @FindBy(xpath = "//*[@id='EditForm']//a[1]/img")
    private WebElementFacade backButton;

    @FindBy(xpath = "//span[contains(text(),'Status')]")
    private WebElementFacade statusMessage;

    @FindBy(css = "p.error-box")
    private WebElementFacade errorMessage;

    public String get_error_text() {
        return errorMessage.waitUntilVisible().getText();
    }


    public String get_status_text() {
        return statusMessage.waitUntilVisible().getText();
    }

    public void enter_file_name(String name) {
        fileName.waitUntilVisible().type(name);
    }

    public void enter_file_content(String content) {
        textContent.waitUntilVisible().type(content);
        textContent.sendKeys(" ");
    }

    public void click_save() {
        saveButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void click_back() {
        backButton.waitUntilClickable().click();
    }

    public List<String> getContent() {
        WebElementFacade definitionList = find(By.tagName("div"));
        return definitionList.findElements(By.tagName("form")).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}
