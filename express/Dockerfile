# Etapa 1: Construcción (Build)
# Utilizamos una imagen JDK completa (con Maven o Gradle) para compilar el código.
FROM eclipse-temurin:17-jdk-focal AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo POM (o Gradle build file) y obtener las dependencias
# Esto ayuda a que el cache de Docker no se invalide si solo cambia el código fuente
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente completo
COPY src src

# Construir el proyecto, generando el JAR final
RUN ./mvnw package -DskipTests

# Etapa 2: Ejecución (Run)
# Utilizamos una imagen JRE (solo el entorno de ejecución) para un tamaño mínimo
FROM eclipse-temurin:17-jre-focal

# Establecer argumentos para usar el JAR generado en la etapa de construcción
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} viajesExpress.jar

# El puerto que tu aplicación Spring Boot escucha
EXPOSE 8080

# Comando para ejecutar la aplicación JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
