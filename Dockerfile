# 1. Define a imagem base (com JRE)
FROM eclipse-temurin:21

# 2. Define o diretório de trabalho dentro do container
WORKDIR /app

# 3. Copia o arquivo JAR do seu projeto para o diretório /app no container
# Substitua 'seu-app.jar' pelo nome real do seu arquivo JAR
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 4. Expõe a porta que sua aplicação Java usa (ex: 8080)
EXPOSE 3000

# 5. Define o comando para executar a aplicação ao iniciar o container
ENTRYPOINT ["java", "-jar", "app.jar"]
