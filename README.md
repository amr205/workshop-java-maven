# Paso 4 (con Maven): empaquetar y ejecutar con Maven

Se agregan dos cosas en `<build>` del `pom.xml`:

- `<finalName>torres</finalName>`: el JAR se llama `target/torres.jar`.
- `exec-maven-plugin` con la clase principal y el argumento `torres.json`, para correr la app con un solo comando.

| Paso | Tag | Qué agrega |
| --- | --- | --- |
| 1 | `con-maven-paso-1` | `pom.xml` mínimo |
| 2 | `con-maven-paso-2` | Jackson como dependencia |
| 3 | `con-maven-paso-3` | Pruebas con `mvn test` |
| 4 | `con-maven-paso-4` | Empaquetar y ejecutar con Maven |

## Requisitos

- JDK 17 o superior: `java -version`
- Maven 3.9 o superior: `mvn -version`
- Git: `git --version`

## Desde cero

### 1. Clonar el repo e ir a este paso

```bash
git clone https://github.com/amr205/workshop-java-maven.git
cd workshop-java-maven
git checkout con-maven-paso-4
```

Si ya lo tienes clonado, basta con `git checkout con-maven-paso-4`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout con-maven`.

### 2. Empaquetar

```bash
mvn clean package
```

Limpia `target/`, compila, corre las 7 pruebas y crea `target/torres.jar`. Para empaquetar sin correr las pruebas: `mvn -DskipTests package`.

### 3. Correr

```bash
mvn exec:java
```

Imprime las torres y escribe `resumen.json`, sin escribir ningún classpath.

## Sin Maven vs. con Maven

| Tarea | Sin Maven | Con Maven |
| --- | --- | --- |
| Conseguir Jackson | Bajar 3 JAR a mano | 1 dependencia en el `pom.xml` |
| Dependencias transitivas | Investigarlas tú | Las resuelve Maven (`mvn dependency:tree`) |
| Compilar | `javac -d out -cp ...` | `mvn compile` |
| Pruebas | Bajar el launcher, compilar las pruebas y armar el classpath | `mvn test` |
| Classpath `:` vs `;` | Cambia según el sistema operativo | No lo escribes |
| Empaquetar y correr | `jar` y `java -cp ...` a mano | `mvn package` y `mvn exec:java` |
