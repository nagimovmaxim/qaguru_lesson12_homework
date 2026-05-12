package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTests {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void setup() {
        String browser = System.getProperty("browser", "chrome");
        String baseUrl = System.getProperty("baseUrl", "https://github.com");
        String remote = System.getProperty("remote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        Configuration.browser = browser;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.savePageSource = true;
        Configuration.screenshots = true;
        Configuration.baseUrl = baseUrl;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = remote;


        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            String[] possiblePaths = {
                    "/usr/bin/chromedriver",
                    "/usr/local/bin/chromedriver",
                    "/snap/bin/chromedriver"
            };

            for (String path : possiblePaths) {
                if (new java.io.File(path).exists()) {
                    System.setProperty("webdriver.chrome.driver", path);
                    break;
                }
            }
        }

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }


    @AfterEach
    void down() {
        closeWebDriver();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
//        Attach.attachAsText("Some file", "Some content");
        closeWebDriver();
    }
}