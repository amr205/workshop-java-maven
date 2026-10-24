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
