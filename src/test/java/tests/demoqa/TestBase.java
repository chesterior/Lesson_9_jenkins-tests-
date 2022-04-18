package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

//    @BeforeAll
//    static void setUp() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//
//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities = capabilities;
//    }

    @BeforeAll
    static void setUp() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion= System.getProperty("version", "91");
        Configuration.browserSize= System.getProperty("browserSize", "1960x760");
        String login = System.getProperty("login","user1");
        String password = System.getProperty("password","123456");
        String url = "https://" + login + ":" + password + "@" + "selenoid.autotests.cloud/wd/hub";
        Configuration.remote = url;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
