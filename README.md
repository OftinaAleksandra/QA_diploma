# QA_diploma

Для запуска в MySQL:
1. Запустить  docker-machine start default
2. В терминале InteliJ Idea выполнить команду: docker-compose -f docker-compose-mysql.yml up -d
3. Запустить SUT командой: java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://192.168.99.100:3306/app -P:jdbc.user=app -P:jdbc.password=pass
4. Запустить тесты командой: gradlew test
5. Остановить машину: docker-machine stop default
   
Для запуска на Postgres: 
1. Запустить  docker-machine start default
2. В терминале InteliJ Idea выполнить команду: docker-compose -f docker-compose-postgres.yml up -d
3. Запустить SUT: java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar artifacts/aqa-shop.jar
4. Запустить тесты:gradlew -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app test
5. Остановить контейнеры: docker-compose -f docker-compose-postgres.yml down
6. Остановить машину: docker-machine stop default
