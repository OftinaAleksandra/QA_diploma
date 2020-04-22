package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Cards;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    SelenideElement buyButton = $$("button").find(exactText("Купить"));
    SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));
    ElementsCollection form = $$(".input");
    public SelenideElement numberCardField = form.find(exactText("Номер карты")).$(".input__control");
    public SelenideElement monthField = form.find(exactText("Месяц")).$(".input__control");
    public SelenideElement yearField = form.find(exactText("Год")).$(".input__control");
    public SelenideElement ownerField = form.find(exactText("Владелец")).$(".input__control");
    public SelenideElement cvcCvvField = form.find(exactText("CVC/CVV")).$(".input__control");

    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    SelenideElement paymentOkWindow = $(".notification_status_ok");
    SelenideElement paymentErrorWindow = $(".notification_status_error");

    public StartPage validBuyTour(Cards card) {
        numberCardField.setValue(card.number);
        monthField.setValue(card.month);
        yearField.setValue(card.year);
        ownerField.setValue(card.owner);
        cvcCvvField.setValue(card.cvcCvv);
        continueButton.click();
        return new StartPage();
    }

    public void messageOfSuccessBuy() {
        paymentOkWindow.waitUntil(Condition.visible, 15000);
    }

    public BuyPage buyPage() {
        buyButton.click();
        return new BuyPage();
    }

    public CreditPage creditPage() {
        creditButton.click();
        return new CreditPage();
    }
}
