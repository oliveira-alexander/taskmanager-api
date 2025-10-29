# Etapa 1: build da aplicação
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia os arquivos de configuração e baixa dependências
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copia o código-fonte e faz o build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: imagem final mais leve
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia apenas o jar gerado da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta usada pela aplicação (geralmente 8080)
EXPOSE 3000

# Define o comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]