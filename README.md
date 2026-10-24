# Workshop — rama `con-maven`

El mismo proyecto de la rama `sin-maven`, ahora con **Maven**. Los comandos de Maven son iguales en Linux, macOS y Windows.

## Estructura (convención de Maven)

```
pom.xml                               configuración del proyecto
src/main/java/com/workshop/torres/    código de la aplicación
src/test/java/com/workshop/torres/    pruebas
target/                               lo que genera Maven (no se sube a git)
```

## Paso 1 — `pom.xml` mínimo

El `pom.xml` solo define el **GAV** (`groupId`, `artifactId`, `version`), el empaquetado y la versión de Java. Aún no hay dependencias.

```bash
mvn compile
```

Maven compila `src/main/java` hacia `target/classes`, el equivalente a nuestro `javac -d out ...`. Para correr:

```bash
java -cp target/classes com.workshop.torres.App
```

## Paso 2 — Jackson como dependencia

En lugar de bajar JAR, se declara **una** dependencia en el `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.18.2</version>
</dependency>
```

```bash
mvn compile
mvn dependency:tree
```

`dependency:tree` muestra que Maven trajo solo `jackson-core` y `jackson-annotations`: las **dependencias transitivas** que en la otra rama bajamos a mano. Quedan en tu repositorio local `~/.m2/repository`.

Para correr todavía usamos `java`, pidiéndole a Maven que copie los JAR a `target/dependency`:

```bash
mvn dependency:copy-dependencies
java -cp "target/classes:target/dependency/*" com.workshop.torres.App torres.json     # Linux / macOS
java -cp "target/classes;target/dependency/*" com.workshop.torres.App torres.json     # Windows
```
