# Paso 1 (con Maven): `pom.xml` mínimo

El mismo código del paso 1 de `sin-maven`, ahora con un `pom.xml` que solo define el **GAV** (`groupId`, `artifactId`, `version`), el empaquetado y la versión de Java. Todavía no hay dependencias.

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

## Estructura (convención de Maven)

```
pom.xml                               configuración del proyecto
src/main/java/com/workshop/torres/    código de la aplicación
src/test/java/com/workshop/torres/    pruebas
target/                               lo que genera Maven (no se sube a git)
```

## Desde cero

### 1. Clonar el repo e ir a este paso

```bash
git clone https://github.com/amr205/workshop-java-maven.git
cd workshop-java-maven
git checkout con-maven-paso-1
```

Si ya lo tienes clonado, basta con `git checkout con-maven-paso-1`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout con-maven`.

### 2. Compilar

```bash
mvn compile
```

Maven compila `src/main/java` hacia `target/classes`: el equivalente a `javac -d out ...`. La primera vez descarga sus plugins a `~/.m2/repository`, por eso tarda más.

### 3. Correr

```bash
java -cp target/classes com.workshop.torres.App
```

Salida esperada:

```
Torres: 4
Mas poderosa: Torre[nombre=Laser, danio=50, costo=400]
Costo total: 950
Con 250 de oro alcanza para: [Torre[nombre=Arquero, ...], Torre[nombre=Canon, ...], Torre[nombre=Mago, ...]]
```

Siguiente paso: `git checkout con-maven-paso-2`.
