package qa.guru.allure.homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class HomeWorkTest {
    private static final String BUTTONNAME = "New issue";
    private static final String ISSUENAME = "Control flow syntax inline spacing";
    StepsForHomework homeworksteps = new StepsForHomework();
    @Test
    public void issueHaveCorrectNameWithListener(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/angular/angular/issues");
        $("[itemprop='author']").shouldHave(text("angular"));
        $("[itemprop='name']").shouldHave(text("angular"));
        $("[href='/login?return_to=https://github.com/angular/angular/issues']").shouldHave(text(BUTTONNAME));
        $$("[data-testid='issue-pr-title-link']").shouldHave(itemWithText(ISSUENAME));
    }
    @Test
    public void issueHaveCorrectNameWithLambdaSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть страницу c issues", () -> {
            open("https://github.com/angular/angular/issues");
            $("[itemprop='author']").shouldHave(text("angular"));
            $("[itemprop='name']").shouldHave(text("angular"));
            $("[href='/login?return_to=https://github.com/angular/angular/issues']").shouldHave(text(BUTTONNAME));
        });
        step("Проверить наличие issue " + ISSUENAME, () -> {
            $$("[data-testid='issue-pr-title-link']").shouldHave(itemWithText(ISSUENAME));
        });
    }
    @Test
    public void issueHaveCorrectNameWithAnnotationSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        homeworksteps.openIssuesPage(BUTTONNAME);
        homeworksteps.checkingIssuebyName(ISSUENAME);
    }
}
