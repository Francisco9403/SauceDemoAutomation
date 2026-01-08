# Usamos Maven con Java 17
FROM maven:3.9.5-eclipse-temurin-17-alpine

WORKDIR /app

# Copiamos archivos
COPY pom.xml .
COPY src ./src

# Descargamos dependencias (Cache)
RUN mvn dependency:resolve

# Comando por defecto (Ojo: Aquí pasaremos la URL del navegador remoto)
CMD ["mvn", "test"]