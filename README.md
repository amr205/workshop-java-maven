# Paso 1 (sin Maven): Java puro, sin librerías

Tres clases: `Torre` (un record), `Arsenal` (la lógica) y `App` (el `main`). No se usa ninguna librería externa, así que basta con `javac` y `java`.

| Paso | Tag | Qué agrega |
| --- | --- | --- |
| 1 | `sin-maven-paso-1` | Java puro, sin librerías |
| 2 | `sin-maven-paso-2` | Jackson con JAR descargados a mano |
| 3 | `sin-maven-paso-3` | Pruebas con JUnit 5 desde la consola |

## Requisitos

- JDK 17 o superior: `java -version` y `javac -version`
- Git: `git --version`

## Desde cero

### 1. Clonar el repo e ir a este paso

```bash
git clone https://github.com/amr205/workshop-java-maven.git
cd workshop-java-maven
git checkout sin-maven-paso-1
```

Si ya lo tienes clonado, basta con `git checkout sin-maven-paso-1`. Git va a decir que estás en *detached HEAD*: es normal, solo estás viendo este commit. Para volver al final de la rama usa `git checkout sin-maven`.

### 2. Compilar

`-d out` deja los `.class` en la carpeta `out/`. El comando es igual en Linux, macOS y Windows (PowerShell o CMD):

```bash
javac -d out src/main/java/com/workshop/torres/*.java
```

### 3. Correr

`-cp out` le dice a la JVM dónde están las clases compiladas:

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

## Notas

- El comodín `*.java` funciona porque todas las clases están en la misma carpeta. Con varios paquetes habría que listar cada carpeta o usar `find` / `Get-ChildItem`.
- Siguiente paso: `git checkout sin-maven-paso-2`.
