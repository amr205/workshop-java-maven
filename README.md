# Workshop — rama `sin-maven`

Compilar, probar y correr un proyecto Java **sin Maven**, solo con `javac` y `java`.
Los comandos van para Linux/macOS (bash) y para Windows (PowerShell).

> Diferencia clave en Windows: el classpath se separa con `;` en lugar de `:`.

## Estructura

```
src/main/java/com/workshop/torres/   código de la aplicación
src/test/java/com/workshop/torres/   pruebas
lib/                                  JAR descargados a mano (no se suben a git)
torres.json                           datos de entrada
```

## Paso 1 — Java puro, sin librerías

Solo hay tres clases: `Torre` (un record), `Arsenal` (la lógica) y `App` (el `main`). No se usa ninguna librería externa.

**Compilar** (`-d out` deja los `.class` en la carpeta `out/`):

```bash
# Linux / macOS
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
```
```powershell
# Windows PowerShell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
```

**Correr** (`-cp out` le dice a la JVM dónde están las clases):

```bash
java -cp out com.workshop.torres.App
```

Salida esperada:

```
Torres: 4
Mas poderosa: Torre[nombre=Laser, danio=50, costo=400]
Costo total: 950
Con 250 de oro alcanza para: [Torre[nombre=Arquero, ...], Torre[nombre=Canon, ...], Torre[nombre=Mago, ...]]
```

## Paso 2 — Usar Jackson bajando los JAR a mano

Ahora las torres vienen de `torres.json` y la clase `LectorTorres` usa **Jackson** para leerlo y para escribir `resumen.json`.

Jackson no viene con el JDK. Sin Maven hay que bajar los JAR uno por uno, **y también los que Jackson necesita**: `jackson-databind` depende de `jackson-core` y de `jackson-annotations`, todos en la misma versión (2.18.2).

**Descargar los JAR a `lib/`:**

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
mkdir lib
$BASE = "https://repo1.maven.org/maven2/com/fasterxml/jackson/core"
foreach ($a in "jackson-databind","jackson-core","jackson-annotations") {
  curl.exe -L -o "lib/$a-2.18.2.jar" "$BASE/$a/2.18.2/$a-2.18.2.jar"
}
```

**Compilar** (ahora `javac` necesita los JAR en el classpath):

```bash
# Linux / macOS
javac -encoding UTF-8 -d out -cp "lib/*" $(find src/main/java -name "*.java")
```
```powershell
# Windows PowerShell
javac -encoding UTF-8 -d out -cp "lib/*" (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
```

**Correr** (el classpath lleva tus clases **y** los JAR):

```bash
java -cp "out:lib/*" com.workshop.torres.App torres.json      # Linux / macOS
```
```powershell
java -cp "out;lib/*" com.workshop.torres.App torres.json      # Windows
```

Se genera `resumen.json`:

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

**Prueba esto:** borra `lib/jackson-core-2.18.2.jar` y vuelve a compilar o a correr. Vas a ver un error de compilación o un `NoClassDefFoundError`: así se ve una dependencia transitiva que falta.
