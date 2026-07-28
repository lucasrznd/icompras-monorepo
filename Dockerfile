# syntax=docker/dockerfile:1

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

COPY pom.xml .
COPY common/pom.xml common/pom.xml
COPY produtos/pom.xml produtos/pom.xml
COPY clientes/pom.xml clientes/pom.xml
COPY servicos/pom.xml servicos/pom.xml
COPY pedidos/pom.xml pedidos/pom.xml
COPY faturamento/pom.xml faturamento/pom.xml
COPY logistica/pom.xml logistica/pom.xml
COPY gateway/pom.xml gateway/pom.xml
RUN --mount=type=cache,target=/root/.m2 mvn -q -B dependency:go-offline || true

COPY common common
COPY produtos produtos
COPY clientes clientes
COPY servicos servicos
COPY pedidos pedidos
COPY faturamento faturamento
COPY logistica logistica
COPY gateway gateway
RUN --mount=type=cache,target=/root/.m2 mvn -q -B package -DskipTests

FROM eclipse-temurin:21-jre AS produtos
COPY --from=build /workspace/produtos/target/produtos-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21-jre AS clientes
COPY --from=build /workspace/clientes/target/clientes-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21-jre AS pedidos
COPY --from=build /workspace/pedidos/target/pedidos-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21-jre AS faturamento
COPY --from=build /workspace/faturamento/target/faturamento-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21-jre AS logistica
COPY --from=build /workspace/logistica/target/logistica-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21-jre AS gateway
COPY --from=build /workspace/gateway/target/gateway-*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
