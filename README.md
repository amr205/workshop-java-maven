# Workshop: Java sin Maven vs. con Maven

El mismo proyecto pequeño se construye dos veces:

- **Rama `sin-maven`**: solo con `javac`, `java` y los JAR bajados a mano.
- **Rama `con-maven`**: con un `pom.xml` y los comandos de Maven.

El proyecto lee un archivo `torres.json` (las torres de un Tower Defense) con la librería **Jackson**, calcula datos del arsenal, escribe un `resumen.json` y tiene pruebas con **JUnit 5**.

La idea es hacer primero todo a mano para ver qué problemas resuelve Maven.

## Requisitos

- JDK 17 o superior (`java -version` y `javac -version`)
- `curl` (incluido en macOS, Linux y Windows 10+; en PowerShell se escribe `curl.exe`)
- Maven 3.9+ solo para la rama `con-maven` (`mvn -version`)

## Cómo recorrer los pasos

Cada paso es un commit y tiene una etiqueta (tag), así puedes saltar directo a cualquiera:

| Paso | Rama `sin-maven` | Rama `con-maven` |
| --- | --- | --- |
| 1 | `sin-maven-paso-1`: Java puro, sin librerías | `con-maven-paso-1`: `pom.xml` mínimo |
| 2 | `sin-maven-paso-2`: Jackson con JAR descargados a mano | `con-maven-paso-2`: Jackson como dependencia |
| 3 | `sin-maven-paso-3`: tests con JUnit (consola) | `con-maven-paso-3`: tests con `mvn test` |
| 4 | — | `con-maven-paso-4`: empaquetar y ejecutar con Maven |

```bash
git clone <url-de-este-repo>
cd workshop-java-maven

git checkout sin-maven          # o con-maven
git log --oneline               # ver los pasos
git checkout sin-maven-paso-1   # ir a un paso concreto (lee su README)
git checkout sin-maven          # volver al final de la rama
```

Para ver qué cambió de un paso a otro:

```bash
git diff sin-maven-paso-1 sin-maven-paso-2
```
