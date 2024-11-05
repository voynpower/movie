# 1-qadam: Qurilish uchun Java JDK tasvirini tanlash
FROM eclipse-temurin:17-jdk AS build

# Ishchi katalogni o'rnating
WORKDIR /app

# Gradle Wrapper va loyihani qurish uchun zarur fayllarni nusxalash
COPY gradlew ./
RUN chmod +x gradlew
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Qolgan barcha loyihani nusxalash va qurishni amalga oshirish
COPY . .
RUN ./gradlew build --no-daemon

# 2-qadam: Foydalanish uchun minimal Java JDK tasvirini tayyorlash
FROM eclipse-temurin:17-jdk

# Qurilgan JAR faylini nusxalash
COPY --from=build /app/build/libs/*.jar app.jar

# JAR faylini ishga tushirish
ENTRYPOINT ["java", "-jar", "/app.jar"]

# 8080-port ochilishi
EXPOSE 8080
