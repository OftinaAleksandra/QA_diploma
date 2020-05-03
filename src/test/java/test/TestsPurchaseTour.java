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
        assertEquals(SqlHelper.getStatusPurchase(), "APPROVED");
    }

    @Test
    @DisplayName("2. Покупка тура с невалидными данными")
    void tourPurchaseShouldUncorrected() throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val declinedCards = Cards.getDeclinedCards();
        startPage.validBuyTour(declinedCards);
        startPage.failureMessage();
        startPage.messageOfIncorrectInputMonth("Неверный формат");

        assertEquals(SqlHelper.getStatusPurchase(), "DECLINED");
    }

    @Test
    @DisplayName("3. Отправка формы с незаполненным полем Номер карты")
    void tourShouldNotPurchaseWithoutNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCard("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("4. Отправка формы с незаполненным полем Месяц")
    void tourShouldNotPurchaseWithoutMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setMonth("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputMonth("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("5. Отправка формы с незаполненным полем Год")
    void tourShouldNotPurchaseWithoutYear () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setYear("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputYear("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("6. Отправка формы с незаполненным полем Владелец")
    void tourShouldNotPurchaseWithoutHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setOwner("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputHolder("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("7. Отправка формы с незаполненным полем CVC/CVV")
    void tourShouldNotPurchaseWithoutCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("8. Отправка пустой формы")
    void tourShouldNotPurchaseWithoutAll () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("");
        approvedCard.setMonth("");
        approvedCard.setYear("");
        approvedCard.setOwner("");
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCard ("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputMonth("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputYear("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputHolder("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("9. Отправка формы с с невалидным значением \"Номера карты\" (14 знаков)")
    void tourShouldNotPurchaseWithIncorrectNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("4444 4444 4444 41");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("10. Отправка формы с с невалидным значением \"Месяца\" (число больше 12)")
    void tourShouldNotPurchaseWithIncorrectMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setMonth("23");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("11. Отправка формы с  невалидным значением \"Год\" (число меньше 20)")
    void tourShouldNotPurchaseWithIncorrectYear () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setYear("18");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Истёк срок действия карты");
    }

    @Test
    @DisplayName("12. Отправка формы с невалидным значением в поле CVC/CVV (2 знака)")
    void tourShouldNotPurchaseWithIncorrectCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setCvcCvv("12");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("13. Отправка формы с полем Владелец, заполненным кириллицей")
    void tourShouldNotPurchaseWithIncorrectHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.buyPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setOwner("Петров Иван");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
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

    @Test
    @DisplayName("16. Покупка тура в кредит. Отправка формы с незаполненным полем Номер карты")
    void tourShouldNotPurchaseByCreditWithoutNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCard("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("17. Покупка тура в кредит. Отправка формы с незаполненным полем Месяц")
    void tourShouldNotPurchaseByCreditWithoutMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setMonth("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputMonth("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("18. Покупка тура в кредит. Отправка формы с незаполненным полем Год")
    void tourShouldNotPurchaseByCreditWithoutYear () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setYear("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputYear("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("19. Покупка тура в кредит. Отправка формы с незаполненным полем Владелец")
    void tourShouldNotPurchaseByCreditWithoutHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setOwner("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputHolder("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("20. Покупка тура в кредит. Отправка формы с незаполненным полем CVC/CVV")
    void tourShouldNotPurchaseByCreditWithoutCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("21. Покупка тура в кредит. Отправка пустой формы")
    void tourShouldNotPurchaseByCreditWithoutAll () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("");
        approvedCard.setMonth("");
        approvedCard.setYear("");
        approvedCard.setOwner("");
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCard ("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputMonth("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputYear("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputHolder("Поле обязательно для заполнения");
        startPage.messageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("22. Покупка тура в кредит. Отправка формы с с невалидным значением \"Номера карты\" (14 знаков)")
    void tourShouldNotPurchaseByCreditWithIncorrectNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setNumber("4444 4444 4444 41");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("23. Покупка тура в кредит. Отправка формы с с невалидным значением \"Месяца\" (число больше 12)")
    void tourShouldNotPurchaseByCreditWithIncorrectMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setMonth("56");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("24. Покупка тура в кредит. Отправка формы с  невалидным значением \"Год\" (число меньше 20)")
    void tourShouldNotPurchaseByCreditWithIncorrectYear () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setYear("19");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Истёк срок действия карты");
    }

    @Test
    @DisplayName("25. Покупка тура в кредит. Отправка формы с невалидным значением в поле CVC/CVV (2 знака)")
    void tourShouldNotPurchaseByCreditWithIncorrectCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setCvcCvv("99");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("26. Покупка тура в кредит. Отправка формы с полем Владелец, заполненным кириллицей")
    void tourShouldNotPurchaseByCreditWithIncorrectHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.creditPage();
        val approvedCard = Cards.getApprovedCards();
        approvedCard.setOwner("Ио Ким");
        startPage.validBuyTour(approvedCard);
        startPage.messageOfIncorrectInputCVC("Неверный формат");
    }

}
