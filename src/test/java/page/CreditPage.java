package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private SelenideElement creditPageHeading = $(byText("Кредит по данным карты"));

    public CreditPage() {
        creditPageHeading.shouldBe(Condition.visible);
    }
}
