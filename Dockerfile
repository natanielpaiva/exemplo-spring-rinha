# Use uma imagem base do OpenJDK
FROM amazoncorretto:17.0.12-al2023-headful

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho
COPY target/rinha-nataniel-1.0.0.jar /app/rinha-nataniel-1.0.0.jar

# Expõe a porta em que a aplicação Spring Boot vai rodar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/rinha-nataniel-1.0.0.jar"]