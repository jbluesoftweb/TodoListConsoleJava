# TodoListConsoleJava

Aplicación de consola en Java para gestionar una lista de tareas (ToDo).

Este repositorio está pensado para usarse únicamente con herramientas de consola (sin IDE). Todas las instrucciones mostradas son manuales y están orientadas a Windows (Git Bash), aunque también incluyo alternativas multiplataforma.

**Requisitos**:
- Java JDK 21 o superior instalado (recomendado JDK 17+).
- Variable de entorno `JAVA_HOME` configurada (opcional pero recomendable).

Comprueba las versiones con:

```
java -version
javac -version
```

**Estructura sugerida**

- `code/` : código fuente Java (.java). Coloca aquí tus paquetes y clases.
- `out/` : carpeta objetivo para archivos `.class` generados (se crea al compilar).
- `TodoConsole.jar` : artefacto opcional empaquetado (creado manualmente).

Nota: la estructura internamente puede usarse con paquetes Java (recomendado), por ejemplo `com.miempresa.todolist`.

---

## Compilar desde PowerShell (Windows)

Ejemplo simple que compila todos los `.java` dentro de `code/` y coloca las clases en `out/`:

```powershell
# 1) Crear lista de fuentes y compilar
$files = Get-ChildItem -Path .\code -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files

# 2) Ejecutar (reemplaza <MainClass> por el nombre de la clase principal con su paquete)
java -cp out <paquete>.Main
```

Ejemplo concreto si la clase principal está en paquete `com.example` y la clase se llama `Main`:

```powershell
java -cp out com.example.Main
```

Si tus fuentes no usan paquetes (clases en el paquete por defecto) y el `Main` está en `code/`, puedes compilar con:

```powershell
javac -d out .\code\*.java
java -cp out Main
```

---

## Compilar desde Linux / macOS (bash)

```bash
# Crear lista de fuentes y compilar
find code -name "*.java" > sources.txt
javac -d out @sources.txt

# Ejecutar (reemplaza paquete.Main)
java -cp out paquete.Main
```

---

## Empaquetar en un JAR ejecutable

1) Asegúrate de haber compilado las clases en `out/`.
2) Crea el archivo `MANIFEST.MF` con la clase principal:

```powershell
# PowerShell
echo "Main-Class: app.Main" > MANIFEST.MF

# Bash/Git Bash
echo 'Main-Class: app.Main' > MANIFEST.MF
```

3) Empaqueta el JAR usando el MANIFEST:

```powershell
jar cfm TodoConsole.jar MANIFEST.MF -C out .
```

4) Ejecuta el JAR:

```powershell
java -jar TodoConsole.jar
```

**Nota**: Si usas paquetes diferentes (ej: `com.example.Main`), reemplaza `app.Main` con tu clase principal completa en el MANIFEST.

---

## Alternativa: Usando `--main-class` (Java 11+)

Si tu versión de Java lo soporta:

```powershell
jar --create --file TodoConsole.jar --main-class=app.Main -C out .
java -jar TodoConsole.jar
```

---

## Buenas prácticas y recomendaciones

- Usa paquetes (por ejemplo `com.tuempresa.todolist`) para evitar conflictos.
- Mantén las clases fuente en `code/` organizadas por carpetas que sigan la estructura de paquetes.
- Control de versiones: añade `.gitignore` para ignorar `out/` y artefactos (`*.jar`).
- Tests: por ahora no hay framework de tests; si quieres pruebas unitarias, podemos añadir Maven/Gradle o instrucciones para ejecutar `javac` y ejecutar tests manuales.

---

## Ejemplo mínimo (si quieres probar rápidamente)

1) Crea `code/com/example/Main.java` con contenido mínimo:

```java
package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola TodoListConsoleJava");
    }
}
```

2) Compilar y ejecutar (PowerShell):

```powershell
$files = Get-ChildItem -Path .\code -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out com.example.Main
```

---

Si quieres, puedo:
- Añadir un ejemplo de `Main.java` ya creado en `code/`.
- Añadir un `README` en inglés además del español.
- Proveer comandos simplificados para compilar un solo archivo.

Indícame qué prefieres y lo hago a continuación.
