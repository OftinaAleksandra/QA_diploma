package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyPage {
    private SelenideElement buyHeading = $(byText("Оплата по карте"));

    public BuyPage() {
        buyHeading.shouldBe(Condition.visible);
    }
}
