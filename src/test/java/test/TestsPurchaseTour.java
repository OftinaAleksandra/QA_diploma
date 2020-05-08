package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardInfo;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.StartPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static data.SqlHelper.*;
import static org.junit.jupiter.api.Assertions.*;

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
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        startPage.validBuyTour(approvedCard);
        startPage.assertSuccessfulPurchaseMessage();
        assertEquals(SqlHelper.getStatusPurchase(), "APPROVED");
        assertEquals(SqlHelper.getDataFromOrderEntity().getPayment_id(), SqlHelper.getTransactionIdFromPaymentEntity());
    }

    @Test
    @DisplayName("2. Покупка тура с невалидными данными")
    void tourPurchaseShouldUncorrected() throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val declinedCards = CardInfo.getDeclinedCard();
        startPage.validBuyTour(declinedCards);
        startPage.assertNotSuccessfulPurchaseMessage();
        startPage.assertMessageOfIncorrectInputMonth("Неверный формат");
        assertEquals(SqlHelper.getStatusPurchase(), "DECLINED");
        assertEquals(SqlHelper.getDataFromOrderEntity().getPayment_id(), SqlHelper.getTransactionIdFromPaymentEntity());
    }

    @Test
    @DisplayName("3. Отправка формы с незаполненным полем Номер карты")
    void tourShouldNotPurchaseWithoutNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("4. Отправка формы с незаполненным полем Месяц")
    void tourShouldNotPurchaseWithoutMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setMonth("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputMonth("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("5. Отправка формы с незаполненным полем Год")
    void tourShouldNotPurchaseWithoutYear () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setYear("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputYear("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("6. Отправка формы с незаполненным полем Владелец")
    void tourShouldNotPurchaseWithoutHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setOwner("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputHolder("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("7. Отправка формы с незаполненным полем CVC/CVV")
    void tourShouldNotPurchaseWithoutCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("8. Отправка пустой формы")
    void tourShouldNotPurchaseWithoutAll () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("");
        approvedCard.setMonth("");
        approvedCard.setYear("");
        approvedCard.setOwner("");
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputMonth("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputYear("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputHolder("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("9. Отправка формы с с невалидным значением \"Номера карты\" (14 знаков)")
    void tourShouldNotPurchaseWithIncorrectNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("4444 4444 4444 41");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Неверный формат");
    }

    @Test
    @DisplayName("10. Отправка формы с с невалидным значением \"Месяца\" (число больше 12)")
    void tourShouldNotPurchaseWithIncorrectMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setMonth("23");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("11. Отправка формы с  невалидным значением \"Год\" (число меньше 20)")
    void tourShouldNotPurchaseWithIncorrectYear () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setYear("18");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Истёк срок действия карты");
    }

    @Test
    @DisplayName("12. Отправка формы с невалидным значением в поле CVC/CVV (2 знака)")
    void tourShouldNotPurchaseWithIncorrectCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setCvcCvv("12");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("13. Отправка формы с полем Владелец, заполненным кириллицей")
    void tourShouldNotPurchaseWithIncorrectHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.goToBuyPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setOwner("Петров Иван");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("14. Покупка тура в кредит с валидными данными")
    void tourPurchaseByCreditShouldCorrect() throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        startPage.validBuyTour(approvedCard);
        startPage.assertSuccessfulPurchaseMessage();
        assertEquals(SqlHelper.getStatusPurchaseByCredit(), "APPROVED");
        assertEquals(SqlHelper.getBankIdFromPaymentEntity(), SqlHelper.getDataFromOrderEntity().getCredit_id());
    }

    @Test
    @DisplayName("15. Покупка тура в кредит с невалидными данными")
    void tourPurchaseByCreditShouldUncorrected() throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getDeclinedCard();
        startPage.validBuyTour(approvedCard);
        startPage.assertNotSuccessfulPurchaseMessage();
        assertEquals(SqlHelper.getStatusPurchaseByCredit(), "DECLINED");
        assertEquals(SqlHelper.getDataFromOrderEntity().getCredit_id(), SqlHelper.getBankIdFromPaymentEntity());
}

    @Test
    @DisplayName("16. Покупка тура в кредит. Отправка формы с незаполненным полем Номер карты")
    void tourShouldNotPurchaseByCreditWithoutNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("17. Покупка тура в кредит. Отправка формы с незаполненным полем Месяц")
    void tourShouldNotPurchaseByCreditWithoutMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setMonth("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputMonth("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("18. Покупка тура в кредит. Отправка формы с незаполненным полем Год")
    void tourShouldNotPurchaseByCreditWithoutYear () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setYear("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputYear("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("19. Покупка тура в кредит. Отправка формы с незаполненным полем Владелец")
    void tourShouldNotPurchaseByCreditWithoutHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setOwner("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputHolder("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("20. Покупка тура в кредит. Отправка формы с незаполненным полем CVC/CVV")
    void tourShouldNotPurchaseByCreditWithoutCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("21. Покупка тура в кредит. Отправка пустой формы")
    void tourShouldNotPurchaseByCreditWithoutAll () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("");
        approvedCard.setMonth("");
        approvedCard.setYear("");
        approvedCard.setOwner("");
        approvedCard.setCvcCvv("");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputMonth("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputYear("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputHolder("Поле обязательно для заполнения");
        startPage.assertMessageOfIncorrectInputCVC("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("22. Покупка тура в кредит. Отправка формы с с невалидным значением \"Номера карты\" (14 знаков)")
    void tourShouldNotPurchaseByCreditWithIncorrectNumber () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setNumber("4444 4444 4444 41");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCard("Неверный формат");
    }

    @Test
    @DisplayName("23. Покупка тура в кредит. Отправка формы с с невалидным значением \"Месяца\" (число больше 12)")
    void tourShouldNotPurchaseByCreditWithIncorrectMonth () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setMonth("56");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputMonth("Неверный формат");
    }

    @Test
    @DisplayName("24. Покупка тура в кредит. Отправка формы с  невалидным значением \"Год\" (число меньше 20)")
    void tourShouldNotPurchaseByCreditWithIncorrectYear () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setYear("19");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputYear("Истёк срок действия карты");
    }

    @Test
    @DisplayName("25. Покупка тура в кредит. Отправка формы с невалидным значением в поле CVC/CVV (2 знака)")
    void tourShouldNotPurchaseByCreditWithIncorrectCVC () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setCvcCvv("99");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputCVC("Неверный формат");
    }

    @Test
    @DisplayName("26. Покупка тура в кредит. Отправка формы с полем Владелец, заполненным кириллицей")
    void tourShouldNotPurchaseByCreditWithIncorrectHolder () throws SQLException {
        val startPage = new StartPage();
        startPage.goToCreditPage();
        val approvedCard = CardInfo.getApprovedCard();
        approvedCard.setOwner("Ио Ким");
        startPage.validBuyTour(approvedCard);
        startPage.assertMessageOfIncorrectInputHolder("Неверный формат");
    }
}
