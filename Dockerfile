# --- Etapa 1: compilar la app ---
# Usamos una imagen que ya tiene Java 21 y Maven instalados,
# solo para compilar el proyecto (no queda en la imagen final)
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos primero el pom.xml y descargamos dependencias.
# Esto acelera builds futuros: si no cambiaste el pom.xml,
# Docker reutiliza las dependencias ya descargadas.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# --- Etapa 2: correr la app ---
# Imagen liviana, solo con Java (sin Maven, sin código fuente)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto en el que corre Spring Boot por defecto
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
