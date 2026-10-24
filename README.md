# Paso 2 (con Maven): Jackson como dependencia

En lugar de bajar JAR a mano, se declara **una** dependencia en el `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.18.2</version>
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
git checkout con-maven-paso-2
```

Si ya lo tienes clonado, basta con `git checkout con-maven-paso-2`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout con-maven`.

### 2. Compilar

```bash
mvn compile
```

Maven descarga Jackson **y sus dependencias** a `~/.m2/repository` y las pone en el classpath por ti.

### 3. Ver el árbol de dependencias

```bash
mvn dependency:tree
```

Aparecen `jackson-core` y `jackson-annotations` debajo de `jackson-databind`: las **dependencias transitivas** que en la otra rama bajamos a mano.

### 4. Correr

Todavía corremos con `java`. Para eso le pedimos a Maven que copie los JAR a `target/dependency`:

```bash
mvn dependency:copy-dependencies
```
```bash
java -cp "target/classes:target/dependency/*" com.workshop.torres.App torres.json     # Linux / macOS
```
```powershell
java -cp "target/classes;target/dependency/*" com.workshop.torres.App torres.json     # Windows
```

Salida esperada:

```
Torres leidas de torres.json: 4
Mas poderosa: Torre[nombre=Laser, danio=50, costo=400]
Costo total: 950
Con 250 de oro alcanza para: [Torre[nombre=Arquero, ...], Torre[nombre=Canon, ...], Torre[nombre=Mago, ...]]
Resumen escrito en resumen.json
```

Y se crea `resumen.json`:

```json
{
  "cantidad" : 4,
  "costoTotal" : 950,
  "masPoderosa" : {
    "nombre" : "Laser",
    "danio" : 50,
    "costo" : 400
  }
}
```

Siguiente paso: `git checkout con-maven-paso-3`.
