# QA_diploma

Для запуска:
1. Убедиться, что Docker Toolbox запущен
2. В терминале InteliJ Idea выполнить команду: docker-compose up -d
3. В DBeaver создать подключение к БД (Сервер 192.168.99.100, БД - app, пользователь - app, пароль - pass)  
4. Запустить SUT командой: java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass
5. Запустить тесты командой: gradlew test
