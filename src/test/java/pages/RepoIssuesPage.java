package pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class RepoIssuesPage {
    private final ElementsCollection issuesTextCollection = $$("[data-testid='issue-pr-title-link']");

    public List<String> getListOfIssues() {
        sleep(1000);
        return issuesTextCollection.texts();
    }
}