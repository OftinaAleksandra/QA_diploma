#### План тестирования
##### Перечень автоматизируемых сценариев

##### 1. Оплата по дебетовой карте
######  1. Позитивный сценарий: отправка валидных данных. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   На экране открыто всплывающее окно с надписью "Успешно". 
   В БД в таблицу payment_entity добавлена новая строка, поле status которой содержит значение "APPROVED".
   
###### 2. Позитивный сценарий: отправка не валидных данных. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4442"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   На экране открыто всплывающее окно с надписью "Ошибка! Банк отказал в проведении операции". 
   В БД в таблицу payment_entity добавлена новая строка, поле status которой содержит значение "DECLINED".
   
###### 3. Негативный сценарий: отправка формы с незаполненным полем "Номер карты". 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Поле "Номер карты" оставить не заполненным; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Номер карты"" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 4. Негативный сценарий: отправка формы с незаполненным полем "Месяц".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Поле "Месяц" оставить не заполненным;
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Месяц" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 5. Негативный сценарий: отправка формы с незаполненным полем "Год". 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Поле "Год" оставить не заполненным;
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Год" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 6. Негативный сценарий: отправка формы с незаполненным полем "Владелец".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Поле "Владелец" оставить не заполненым;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Владелец" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 7. Негативный сценарий: отправка формы с незаполненным полем "CVC/CVV".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Поле "CVC/CVV" оставить не заполненым;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "CVC/CVV" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 8. Негативный сценарий: отправка пустой формы. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Оставить все поля незаполенными;
   2. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, все поля подсвечены красным, под полями напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу payment_entity не добавлена новая строка.      

###### 9. Негативный сценарий: отправка формы с невалидным значением "Номера карты" (14 знаков). 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 41"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12"";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Номер карты" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
  
###### 10. Негативный сценарий: отправка формы с невалидным значением "Месяца" (число больше 12). 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" значением "15";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Месяц" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
  
###### 11. Негативный сценарий: отправка формы с невалидным значением "Год" (число меньше 20).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "19";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Год" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 

###### 12. Негативный сценарий: отправка формы с невалидным значением "CVC/CVV" (введено 2 знака).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" ввести значение "56"";
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "CVC/CVV" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
   
###### 13. Негативный сценарий: отправка формы с невалидным значением в поле "Владелец" (русскими буквами).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" русскими буквами значением "Петров Иван"";
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Купить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Владелец" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 

##### 2. Выдача кредита по данным банковской карты
###### 14. Позитивный сценарий: отправка валидных данных. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   На экране открыто всплывающее окно с надписью "Успешно". 
   В БД в таблицу credit_request_entity добавлена новая строка, поле status которой содержит значение "APPROVED".
   
###### 15. Позитивный сценарий: отправка не валидных данных. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4442"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   На экране открыто всплывающее окно с надписью "Ошибка! Банк отказал в проведении операции". 
   В БД в таблицу credit_request_entity добавлена новая строка, поле status которой содержит значение "DECLINED".
   
###### 16. Негативный сценарий: отправка формы с незаполненным полем "Номер карты". 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Поле "Номер карты" оставить не заполненным; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Номер карты"" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 17. Негативный сценарий: отправка формы с незаполненным полем "Месяц".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Поле "Месяц" оставить не заполненным;
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Месяц" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 18. Негативный сценарий: отправка формы с незаполненным полем "Год". 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Поле "Год" оставить не заполненным;
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Год" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 19. Негативный сценарий: отправка формы с незаполненным полем "Владелец".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Поле "Владелец" оставить не заполненым;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Владелец" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 20. Негативный сценарий: отправка формы с незаполненным полем "CVC/CVV".
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Поле "CVC/CVV" оставить не заполненым;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "CVC/CVV" подсвечено красным, под полем напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 21. Негативный сценарий: отправка пустой формы. 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Оставить все поля незаполенными;
   2. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, все поля подсвечены красным, под полями напись "Поле обязательно для заполнения!". 
   На экране открыто всплывающее окно с надписью "Ошибка!". В БД в таблицу credit_request_entity не добавлена новая строка.      

###### 22. Негативный сценарий: отправка формы с невалидным значением "Номера карты" (14 знаков). 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 41"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Номер карты" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
  
###### 23. Негативный сценарий: отправка формы с невалидным значением "Месяца" (число больше 12). 
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" значением "56";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Месяц" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
  
###### 24. Негативный сценарий: отправка формы с невалидным значением "Год" (число меньше 20).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "19";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Год" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 

###### 25. Негативный сценарий: отправка формы с невалидным значением "CVC/CVV" (введено 2 знака).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"
   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" латинскими буквами;
   5. Заполнить поле "CVC/CVV" ввести значение "99"";
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "CVC/CVV" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
   
###### 26. Негативный сценарий: отправка формы с невалидным значением в поле "Владелец" (русскими буквами).
   **Предварительные действия:**
   * Открыть страницу http://localhost:8080/
   * Нажать кнопку "Купить в кредит"   
   **Шаги для воспроизведения:**
   1. Заполнить поле "Номер карты" значением "4444 4444 4444 4441"; 
   2. Заполнить поле "Месяц" любым значением из списка "01,02,03,04,05,06,07,08,09,10,11,12";
   3. Заполнить поле "Год" значением "текущий год + произвольное число, не более 5";
   4. Заполнить поле "Владелец" русскими буквами значением "Ио Ким"";
   5. Заполнить поле "CVC/CVV" произвольным значением от 100 до 999;
   6. Нажать кнопку "Продолжить".   
   **Ожидаемый результат:** 
   Запрос не отправлен, поле "Владелец" подсвечено красным, под полем напись "Неверный формат"
   На экране открыто всплывающее окно с надписью "Ошибка!". 
   
##### Перечень используемых инструментов
1. Java 8. Кроссплатформенность, большой выбор библиотек, автоматическое управление памятью.
2. IntelliJ IDEA. Автодополнение и анализ кода в реальном времени и надежные рефакторинги.
3. Junit. Удобный запуск нескольких тестов, а также, возможность создавать параметризованные тесты.
4. Selenide. Автоматическое управление браузером, удобный синтаксис, по сранению с Selenium.
5. Faker. Удобно генерирует уникальные данные.
6. DbUtils. Библиотека для работы с базами данных, упрощает работу с JDBC.
7. Allure. Наглядные отчёты.

##### Перечень возможных рисков при автоматизации
1. Возникновение проблем с запуском контейнеров, SUT и симулятора;
2. Отсутствие документации к сервису и БД;
3. Настройка тестовых сценариев для разных БД (Postgres и MySQL)

##### Интервальная оценка с учётом рисков (в часах)
1. Настройка тестового окружения - 12
2. Написание и отладка автотестов - 50  
3. Прогон тестов и описание багов - 15  
4. Отчёт по автоматизации и итогам тестирования - 15  
Итого: 92 часа  

##### План сдачи работ 
1. Написание и выполнение авто-тестов до 04.05.2020г.
2. Подготовка отчётных документов по итогам автоматизированного тестирования до 08.05.2020г.
3. Подготовка отчётных документов по итогам автоматизации до 10.05.2020г.
