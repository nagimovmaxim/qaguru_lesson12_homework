package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.MainPage;
import pages.RepoIssuesPage;
import pages.RepoPage;
import pages.SearchPage;
import steps.GithubSteps;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("Homework9")
public class GithubTests extends BaseTests {

    @ParameterizedTest(name = " by name: {1} in repo: {0}")
    @CsvFileSource(resources = "/csv/testData.csv")
    @DisplayName("Check Issue")
    @Feature("Work with Issues in Github repo")
    @Story("Check Issue by name")
    @Owner("NagimovMV")
    @Severity(SeverityLevel.BLOCKER)
    public void checkIssueNameTest(String repoName, String issueName) {
        new MainPage()
                .openPage()
                .search(repoName);
        new SearchPage()
                .clickOnSearchResult(repoName);
        new RepoPage()
                .clickOnIssuesTab();
        List<String> listOfIssues = new RepoIssuesPage()
                .getListOfIssues();
        assertThat(listOfIssues).contains(issueName);
    }

    @ParameterizedTest(name = " by name: {1} in repo: {0} via lambda steps")
    @CsvFileSource(resources = "/csv/testData.csv")
    @DisplayName("Check Issue")
    @Feature("Work with Issues in Github repo")
    @Story("Check Issue by name")
    @Owner("NagimovMV")
    @Severity(SeverityLevel.BLOCKER)
    public void checkIssueNameTestLambdaSteps(String repoName, String issueName) {
        step("Enter " + repoName + " on Github main page", () -> {
            new MainPage()
                    .openPage()
                    .search(repoName);
        });
        step("Click to  " + repoName + " on search page", () -> {
            new SearchPage()
                    .clickOnSearchResult(repoName);
        });
        step("Click to Issue Tab on " + repoName + " page", () -> {
            new RepoPage()
                    .clickOnIssuesTab();
        });
        step("Assert that there is issue with name: " + issueName, () -> {
            List<String> listOfIssues = new RepoIssuesPage()
                    .getListOfIssues();
            assertThat(listOfIssues).contains(issueName);
        });
    }

    @ParameterizedTest(name = " by name: {1} in repo: {0} via steps")
    @CsvFileSource(resources = "/csv/testData.csv")
    @DisplayName("Check Issue")
    @Feature("Work with Issues in Github repo")
    @Story("Check Issue by name")
    @Owner("NagimovMV")
    @Severity(SeverityLevel.BLOCKER)
    public void checkIssueNameTestSteps(String repoName, String issueName) {
        GithubSteps githubSteps = new GithubSteps();
        githubSteps.searchOnMainPage(repoName);
        githubSteps.clickOnSearchResult(repoName);
        githubSteps.clickOnIssuesTab();
        githubSteps.assertThatIssueExistInRepo(issueName);

    }

}
