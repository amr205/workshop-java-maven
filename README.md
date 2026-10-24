# Paso 3 (sin Maven): pruebas con JUnit 5 desde la consola

Se agregan las pruebas en `src/test/java`: `ArsenalTest` (4 pruebas) y `LectorTorresTest` (3 pruebas). Usan el patrón Arrange / Act / Assert.

| Paso | Tag | Qué agrega |
| --- | --- | --- |
| 1 | `sin-maven-paso-1` | Java puro, sin librerías |
| 2 | `sin-maven-paso-2` | Jackson con JAR descargados a mano |
| 3 | `sin-maven-paso-3` | Pruebas con JUnit 5 desde la consola |

## Requisitos

- JDK 17 o superior: `java -version` y `javac -version`
- Git: `git --version`
- `curl` (viene en macOS, Linux y Windows 10+; en PowerShell se escribe `curl.exe`)

## Desde cero

### 1. Clonar el repo e ir a este paso

```bash
git clone https://github.com/amr205/workshop-java-maven.git
cd workshop-java-maven
git checkout sin-maven-paso-3
```

Si ya lo tienes clonado, basta con `git checkout sin-maven-paso-3`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout sin-maven`.

### 2. Descargar Jackson a `lib/`

Jackson no viene con el JDK. Hay que bajar `jackson-databind` **y las dos librerías de las que depende**: `jackson-core` y `jackson-annotations`. Las tres deben ser de la misma versión (2.18.2).

```bash
# Linux / macOS
mkdir -p lib
BASE=https://repo1.maven.org/maven2/com/fasterxml/jackson/core
for a in jackson-databind jackson-core jackson-annotations; do
  curl -L -o lib/$a-2.18.2.jar $BASE/$a/2.18.2/$a-2.18.2.jar
done
```
```powershell
# Windows PowerShell
mkdir lib -Force
$BASE = "https://repo1.maven.org/maven2/com/fasterxml/jackson/core"
foreach ($a in "jackson-databind","jackson-core","jackson-annotations") {
  curl.exe -L -o "lib/$a-2.18.2.jar" "$BASE/$a/2.18.2/$a-2.18.2.jar"
}
```

`lib/` está en el `.gitignore`: los JAR no se suben al repo.

Además, el **JUnit Console Launcher**: un solo JAR que trae JUnit Jupiter y el motor para correr las pruebas.

```bash
# Linux / macOS
curl -L -o lib/junit-platform-console-standalone-1.11.4.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
```
```powershell
# Windows PowerShell
curl.exe -L -o lib/junit-platform-console-standalone-1.11.4.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.11.4/junit-platform-console-standalone-1.11.4.jar
```

Revisa con `ls lib` (o `dir lib`) que estén los 4 archivos y que ninguno pese casi nada.

### 3. Compilar el código

```bash
javac -d out -cp "lib/*" src/main/java/com/workshop/torres/*.java
```

### 4. Compilar las pruebas

Las pruebas necesitan las clases de `out/` y los JAR (JUnit y Jackson):

```bash
javac -d out-test -cp "out:lib/*" src/test/java/com/workshop/torres/*.java      # Linux / macOS
```
```powershell
javac -d out-test -cp "out;lib/*" src/test/java/com/workshop/torres/*.java      # Windows
```

### 5. Ejecutar las pruebas

```bash
java -cp "out:out-test:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-class-path      # Linux / macOS
```
```powershell
java -cp "out;out-test;lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-class-path      # Windows
```

Al final debe decir `7 tests successful` y `0 tests failed`.

### 6. Correr la aplicación (opcional)

```bash
java -cp "out:lib/*" com.workshop.torres.App torres.json      # Linux / macOS
```
```powershell
java -cp "out;lib/*" com.workshop.torres.App torres.json      # Windows
```

## Lo que tuvimos que hacer a mano

1. Averiguar qué JAR necesita Jackson y en qué versión, incluidas sus dependencias transitivas.
2. Descargar cada JAR y guardarlo en `lib/`.
3. Armar el classpath en cada comando, con `:` o `;` según el sistema operativo.
4. Compilar el código y las pruebas por separado, en el orden correcto.
5. Bajar y ejecutar a mano el launcher de JUnit.

Todo esto lo automatiza Maven: compara con la rama `con-maven` (`git checkout con-maven-paso-1`).
