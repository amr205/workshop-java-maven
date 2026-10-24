# Paso 3 (con Maven): pruebas con `mvn test`

Se agregan las mismas 7 pruebas de la otra rama y JUnit como dependencia con `scope` **test**: está disponible para compilar y correr pruebas, pero no forma parte de la aplicación.

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.11.4</version>
    <scope>test</scope>
</dependency>
```

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
git checkout con-maven-paso-3
```

Si ya lo tienes clonado, basta con `git checkout con-maven-paso-3`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout con-maven`.

### 2. Ejecutar las pruebas

```bash
mvn test
```

Maven recorre las fases `validate → compile → test-compile → test`: compila el código, compila las pruebas y las corre con el plugin Surefire. Debe terminar con `Tests run: 7, Failures: 0` y `BUILD SUCCESS`.

Compáralo con los pasos 2 a 5 del paso 3 de `sin-maven`: aquí es un solo comando y no hay que bajar ningún JAR.

### 3. Correr la aplicación (opcional)

```bash
mvn dependency:copy-dependencies
```
```bash
java -cp "target/classes:target/dependency/*" com.workshop.torres.App torres.json     # Linux / macOS
```
```powershell
java -cp "target/classes;target/dependency/*" com.workshop.torres.App torres.json     # Windows
```

Siguiente paso: `git checkout con-maven-paso-4`.
