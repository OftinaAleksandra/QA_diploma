package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Cards;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
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
    //SelenideElement inputError = $(".input_invalid");

    public StartPage validBuyTour(Cards card) {
        numberCardField.setValue(card.number);
        monthField.setValue(card.month);
        yearField.setValue(card.year);
        ownerField.setValue(card.owner);
        cvcCvvField.setValue(card.cvcCvv);
        continueButton.click();
        return new StartPage();
    }


    public BuyPage buyPage() {
        buyButton.click();
        return new BuyPage();
    }

    public CreditPage creditPage() {
        creditButton.click();
        return new CreditPage();
    }

    public void messageOfSuccessBuy() {
        paymentOkWindow.waitUntil(Condition.visible, 15000);
    }

    public void failureMessage() {
        paymentErrorWindow.waitUntil(Condition.visible, 15000);
    }

    public void messageOfIncorrectInputCard(String message) {
        cardNumberInputSub.shouldHave(exactText(message));
    }

    public void messageOfIncorrectInputMonth(String message) {
        monthInputSub.shouldHave(exactText(message));
    }

    public void messageOfIncorrectInputYear(String message) {
        yearInputSub.shouldHave(exactText(message));
    }

    public void messageOfIncorrectInputCVC(String message) {
        cvcInputSub.shouldHave(exactText(message));
    }



    public void messageOfIncorrectInputHolder(String message) {
        holderInputSub.shouldHave(exactText(message));
    }
}
