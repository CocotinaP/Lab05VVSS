package webFTP.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RenameFilePage extends PageObject {

    public void enter_new_name(String newName) {
        WebElement input = getDriver().findElement(By.name("newNames[1]"));
        input.clear();
        input.sendKeys(newName);
    }

    public void click_submit() {
        WebElement submitButton = getDriver().findElement(
                By.xpath("//img[contains(@alt, 'Submit')]/ancestor::a[1]")
        );

        submitButton.click();
        waitABit(1500);
    }

    public void go_back_to_file_list() {
        waitABit(1500);

        WebElement backButton = getDriver().findElement(
                By.xpath("//img[contains(@alt, 'Back')]/ancestor::a[1]")
        );

        backButton.click();
        waitABit(1000);
    }
}