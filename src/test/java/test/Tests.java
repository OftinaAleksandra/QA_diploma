package test;

import data.Cards;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;

public class Tests {

    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Покупка тура с валидными данными")
    void tourPurchaseShouldCorrect() {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        startPage.validBuyTour(approvedCard);
        startPage.messageOfSuccessBuy();
    }
}
