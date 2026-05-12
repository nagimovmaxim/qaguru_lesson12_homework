package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RepoPage {
    private final SelenideElement issuesTabButton = $("#issues-tab");

    public RepoPage clickOnIssuesTab() {
        issuesTabButton.click();
        return this;
    }
}
