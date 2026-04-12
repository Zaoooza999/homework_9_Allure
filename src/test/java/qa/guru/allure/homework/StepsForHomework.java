package qa.guru.allure.homework;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StepsForHomework {
    @Step("Открыть страницу c issues")
    public void openIssuesPage(String button) {
        open("https://github.com/angular/angular/issues");
        $("[itemprop='author']").shouldHave(text("angular"));
        $("[itemprop='name']").shouldHave(text("angular"));
        $("[href='/login?return_to=https://github.com/angular/angular/issues']").shouldHave(text(button));
    }
    @Step("Проверить наличие issue Control flow...")
    public void checkingIssuebyName(String issue) {
        $$("[data-testid='issue-pr-title-link']").shouldHave(itemWithText(issue));
    }
}
