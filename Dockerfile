# 1-qadam: Qurilish uchun Java JDK tasvirini tanlash
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
# Qolgan barcha loyihani nusxalash va qurishni amalga oshirish
COPY . .
RUN ./gradlew build --no-daemon
# 2-qadam: Foydalanish uchun minimal Java JDK tasvirini tayyorlash
FROM openjdk:17-jdk-slim
# 8080-port ochilishi
EXPOSE 8080
# Qurilgan JAR faylini nusxalash
COPY --from=build /build/libs/demo-1.jar app.jar

# JAR faylini ishga tushirish
ENTRYPOINT ["java", "-jar", "/app.jar"]


