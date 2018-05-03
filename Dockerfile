FROM maven:3.5-jdk-8-alpine
ADD ./ ./
RUN mvn package -DskipTests
EXPOSE 8880
CMD ["java", "-jar", "target/participationSystem0-0.0.1.jar"]