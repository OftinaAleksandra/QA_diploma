package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.Cards;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.StartPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static data.SqlHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestsPurchaseTour {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openPage() {
        open("http://localhost:8080");
    }

    @AfterEach
    void cleanTables() throws SQLException {
        clearDBTables();
    }

    @Test
    @DisplayName("1. Покупка тура с валидными данными")
    void tourPurchaseShouldCorrect() throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        startPage.validBuyTour(approvedCard);
        startPage.messageOfSuccessBuy();
        System.out.println(SqlHelper.getStatusPurchase());
        assertEquals(SqlHelper.getStatusPurchase(), "APPROVED");
    }

    @Test
    @DisplayName("2. Покупка тура с невалидными данными")
    void tourPurchaseShouldUncorrected() throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getDeclinedCards();
        startPage.validBuyTour(approvedCard);
        startPage.failureMessage();
        assertEquals(SqlHelper.getStatusPurchase(), "DECLINED");
    }
    @Test
    @DisplayName("14. Покупка тура в кредит с валидными данными")
    void tourPurchaseByCreditShouldCorrect() throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        startPage.validBuyTour(approvedCard);
        startPage.messageOfSuccessBuy();
        assertEquals(SqlHelper.getStatusPurchaseByCredit(), "APPROVED");
    }

    @Test
    @DisplayName("15. Покупка тура в кредит с невалидными данными")
    void tourPurchaseByCreditShouldUncorrected() throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getDeclinedCards();
        startPage.validBuyTour(approvedCard);
        startPage.failureMessage();
        assertEquals(SqlHelper.getStatusPurchaseByCredit(), "DECLINED");
    }
}
