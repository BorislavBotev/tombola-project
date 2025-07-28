FROM eclipse-temurin:17
COPY target/*.jar tombola_project.jar

# Starting the application
ENTRYPOINT ["java", "-jar", "tombola_project.jar"]