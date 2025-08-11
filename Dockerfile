# Etapa 1 (build): usa uma imagem com Maven + JDK 21 para COMPILAR o projeto.
# Motivo: o build precisa de ferramentas (mvn, javac), que não queremos levar para a imagem final.
FROM maven:3.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container desta etapa.
WORKDIR /app

# Copia só o pom.xml primeiro para aproveitar cache de dependências.
# Motivo: se o código mudar, mas o pom não, o Docker reaproveita as deps já baixadas.
COPY pom.xml ./

# Baixa as dependências sem compilar testes, para aquecer o cache.
# Assim, na próxima build, só baixa de novo se o pom mudar.
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia o código-fonte (src) para dentro do container (agora sim).
COPY src ./src

# Compila o projeto e empacota o JAR (sem rodar testes para acelerar).
RUN mvn -q -DskipTests package


# Etapa 2 (runtime): usa uma imagem somente com JRE 21 (sem ferramentas de build).
# Motivo: imagem menor, mais segura e mais rápida para subir em produção.
FROM eclipse-temurin:21-jre

# Define uma variável de ambiente indicando o diretório da app.
ENV APP_HOME=/app

# Entra no diretório da app.
WORKDIR $APP_HOME

# Copia o JAR gerado na etapa "build" para esta etapa final (apenas o artefato).
COPY --from=build /app/target/ms-avaliacao-0.0.1-SNAPSHOT.jar app.jar

# (Opcional, mas recomendado) cria um usuário sem privilégios de root para rodar a app.
# Motivo: boa prática de segurança em containers.
RUN useradd -u 10001 appuser
USER 10001

# Expõe a porta 8080 (padrão do Spring Boot) — informativo para quem usa a imagem.
EXPOSE 8080

# Variável para passar opções de JVM sem fixar no Dockerfile (podemos sobrescrever no "docker run").
ENV JAVA_OPTS=""

# Comando de entrada: roda a app com java, permitindo injetar JAVA_OPTS (heap, GC, etc).
# "sh -c" permite interpolar a variável JAVA_OPTS na linha.
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
