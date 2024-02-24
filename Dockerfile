# Используйте базовый образ OpenJDK
FROM openjdk:21-jdk-slim

# Аргумент для jar файла
ARG JAR_FILE=target/*.jar

# Копируем jar файл из локальной системы в файловую систему образа
COPY ${JAR_FILE} app.jar

# Запускаем приложение
ENTRYPOINT ["java","-jar","/app.jar"]
