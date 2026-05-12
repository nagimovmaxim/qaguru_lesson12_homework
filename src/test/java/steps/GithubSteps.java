package steps;

import io.qameta.allure.Step;
import pages.MainPage;
import pages.RepoIssuesPage;
import pages.RepoPage;
import pages.SearchPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GithubSteps {

    @Step("Enter {repoName} on Github main page")
    public void searchOnMainPage(String repoName) {
        new MainPage()
                .openPage()
                .search(repoName);
    }

    @Step("Click to {repoName} on search page")
    public void clickOnSearchResult(String repoName) {
        new SearchPage()
                .clickOnSearchResult(repoName);
    }

    @Step("Click to Issue Tab")
    public void clickOnIssuesTab() {
        new RepoPage()
                .clickOnIssuesTab();
    }

    @Step("Assert that there is issue with name: {issueName}")
    public void assertThatIssueExistInRepo(String issueName) {
        List<String> listOfIssues = new RepoIssuesPage()
                .getListOfIssues();
        assertThat(listOfIssues).contains(issueName);
    }
}
