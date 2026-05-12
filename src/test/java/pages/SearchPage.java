package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    private final SelenideElement resultListDiv = $("div[data-testid='results-list']");

    public SearchPage clickOnSearchResult(String value) {
        resultListDiv.$(By.linkText(value)).click();
        return this;
    }
}
