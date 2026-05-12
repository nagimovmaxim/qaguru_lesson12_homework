package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private final String pageUrl = "/";

    private final SelenideElement searchButton = $("button[data-target='qbsearch-input.inputButton']"),
            searchInput = $("input[data-target='query-builder.input']");

    public MainPage openPage() {
        open(this.pageUrl);
        return this;
    }

    public MainPage search(String value) {
        searchButton.click();
        searchInput.sendKeys(value);
        searchInput.submit();
        return this;
    }
}
