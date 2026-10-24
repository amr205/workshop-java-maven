# Paso 2 (sin Maven): Jackson descargando los JAR a mano

Las torres ahora vienen de `torres.json`. La clase nueva `LectorTorres` usa **Jackson** para leerlo y para escribir un `resumen.json`.

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
git checkout sin-maven-paso-2
```

Si ya lo tienes clonado, basta con `git checkout sin-maven-paso-2`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout sin-maven`.

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

### 3. Compilar

Ahora `javac` necesita los JAR de Jackson en el classpath (`-cp "lib/*"` toma todos los JAR de la carpeta):

```bash
javac -d out -cp "lib/*" src/main/java/com/workshop/torres/*.java
```

### 4. Correr

El classpath lleva tus clases **y** los JAR. En Windows se separan con `;` y en Linux/macOS con `:`.

```bash
java -cp "out:lib/*" com.workshop.torres.App torres.json      # Linux / macOS
```
```powershell
java -cp "out;lib/*" com.workshop.torres.App torres.json      # Windows
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

## Prueba esto

1. Compila **sin** `-cp "lib/*"`: `javac` falla con `package com.fasterxml.jackson.databind does not exist`.
2. Borra `lib/jackson-core-2.18.2.jar` y vuelve a compilar o correr: así se ve una dependencia transitiva que falta.

Siguiente paso: `git checkout sin-maven-paso-3`.
