# Workshop — rama `sin-maven`

Compilar, probar y correr un proyecto Java **sin Maven**, solo con `javac` y `java`.
Los comandos van para Linux/macOS (bash) y para Windows (PowerShell).

> Diferencia clave en Windows: el classpath se separa con `;` en lugar de `:`.

## Estructura

```
src/main/java/com/workshop/torres/   código de la aplicación
src/test/java/com/workshop/torres/   pruebas
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
