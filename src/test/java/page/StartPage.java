package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.CardInfo;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    SelenideElement buyButton = $$("button").find(exactText("Купить"));
    SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));
    ElementsCollection formInputControl = $$(".input");
    SelenideElement numberCardField = formInputControl.find(exactText("Номер карты")).$(".input__control");
    SelenideElement monthField = formInputControl.find(exactText("Месяц")).$(".input__control");
    SelenideElement yearField = formInputControl.find(exactText("Год")).$(".input__control");
    SelenideElement ownerField = formInputControl.find(exactText("Владелец")).$(".input__control");
    SelenideElement cvcCvvField = formInputControl.find(exactText("CVC/CVV")).$(".input__control");

    ElementsCollection formInputTops = $$(".input__top");
    SelenideElement cardNumberInputSub = formInputTops.find(exactText("Номер карты")).parent().$(".input__sub");
    SelenideElement yearInputSub = formInputTops.find(exactText("Год")).parent().$(".input__sub");
    SelenideElement cvcInputSub = formInputTops.find(exactText("CVC/CVV")).parent().$(".input__sub");
    SelenideElement holderInputSub = formInputTops.find(exactText("Владелец")).parent().$(".input__sub");
    SelenideElement monthInputSub = formInputTops.find(exactText("Месяц")).parent().$(".input__sub");

    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    SelenideElement paymentOkWindow = $(".notification_status_ok");
    SelenideElement paymentErrorWindow = $(".notification_status_error");

    public StartPage validBuyTour(CardInfo card) {
        numberCardField.setValue(card.number);
        monthField.setValue(card.month);
        yearField.setValue(card.year);
        ownerField.setValue(card.owner);
        cvcCvvField.setValue(card.cvcCvv);
        continueButton.click();
        return new StartPage();
    }

    public BuyPage goToBuyPage() {
        buyButton.click();
        return new BuyPage();
    }

    public CreditPage goToCreditPage() {
        creditButton.click();
        return new CreditPage();
    }

    public void assertSuccessfulPurchaseMessage() {
        paymentOkWindow.waitUntil(Condition.visible, 15000);
    }

    public void assertNotSuccessfulPurchaseMessage() {
        paymentErrorWindow.waitUntil(Condition.visible, 15000);
    }

    public void assertMessageOfIncorrectInputCard(String message) {
        cardNumberInputSub.shouldHave(exactText(message));
    }

    public void assertMessageOfIncorrectInputMonth(String message) {
        monthInputSub.shouldHave(exactText(message));
    }

    public void assertMessageOfIncorrectInputYear(String message) {
        yearInputSub.shouldHave(exactText(message));
    }

    public void assertMessageOfIncorrectInputCVC(String message) {
        cvcInputSub.shouldHave(exactText(message));
    }

    public void assertMessageOfIncorrectInputHolder(String message) {
        holderInputSub.shouldHave(exactText(message));
    }
}
