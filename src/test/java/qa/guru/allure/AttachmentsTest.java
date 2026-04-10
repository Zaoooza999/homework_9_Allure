package qa.guru.allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {
    @Test
    public void testLambdaAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
            attachment(
                    "Source", webdriver().driver().source());//source - html, разметка
                        Allure.getLifecycle().addAttachment("Исходники страницы","text/html","html", WebDriverRunner.getWebDriver().getPageSource().getBytes((StandardCharsets.UTF_8))//снапшот
            );

        });
    }
    @Test
    public void testAnnotatedAttachments() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMainPage();
        steps.takeScreenshot();
    }
}
