# Makers Solutions – Smoke Test Login SauceDemo
## Selenium WebDriver + Cucumber + Java

**Candidato:** Johanny Amaya Uchamocha  
**Cargo:** QA Automation Engineer  
**Fecha:** Julio 2026

---

## Descripcion

Proyecto de automatizacion de pruebas para el modulo de Login de [SauceDemo](https://www.saucedemo.com), 
desarrollado como parte de la prueba tecnica para Makers Solutions.

Implementa un Smoke Test completo del flujo de inicio de sesion usando el patron 
**Page Object Model (POM)** y escenarios **BDD** con Cucumber en lenguaje Gherkin.

---

## Tecnologias utilizadas

| Tecnologia | Version | Uso |
|---|---|---|
| Java | 11+ | Lenguaje de programacion |
| Maven | 3.8+ | Gestion de dependencias y ejecucion |
| Selenium WebDriver | 4.20.0 | Automatizacion del navegador |
| Cucumber | 7.17.0 | Framework BDD |
| JUnit | 4.13.2 | Framework de pruebas |
| WebDriverManager | 5.8.0 | Gestion automatica de drivers |
| Google Chrome | Cualquier version reciente | Navegador de pruebas |

---

## Estructura del proyecto

```
makers-selenium-cucumber/
│
├── pom.xml                                         <- Dependencias Maven
│
└── src/
    └── test/
        ├── java/
        │   └── com/makers/
        │       ├── pages/
        │       │   └── LoginPage.java              <- Page Object Model
        │       ├── steps/
        │       │   └── LoginSteps.java             <- Step Definitions
        │       ├── runner/
        │       │   └── CucumberRunner.java         <- Punto de entrada
        │       └── utils/
        │           ├── ConfigReader.java           <- Lector de configuracion
        │           └── DriverManager.java          <- Gestion del WebDriver
        │
        └── resources/
            ├── config.properties                   <- Datos de entrada configurables
            ├── junit-platform.properties           <- Configuracion Cucumber
            └── com/makers/features/
                └── login.feature                   <- Escenarios BDD en Gherkin
```

---

## Requisitos previos

Antes de ejecutar el proyecto verifica que tienes instalado:

**Java 11 o superior**
```bash
java -version
```

**Maven 3.8 o superior**
```bash
mvn -version
```

**Google Chrome** instalado en tu maquina (WebDriverManager descarga el driver automaticamente).

---

## Configuracion

Todos los datos de entrada se configuran en el archivo:

```
src/test/resources/config.properties
```

```properties
# URL de la aplicacion
base.url=https://www.saucedemo.com

# Navegador: chrome, firefox, edge
browser=chrome

# Modo headless: true = sin abrir ventana, false = abre el navegador
headless=false

# Usuarios disponibles
user.standard=standard_user
user.locked=locked_out_user
user.problem=problem_user
user.performance=performance_glitch_user
user.error=error_user
user.visual=visual_user

# Contrasena compartida para todos los usuarios
password=secret_sauce
```

---

## Casos de prueba

| ID | Escenario | Tipo | Usuarios cubiertos |
|---|---|---|---|
| TC-001 | Login con diferentes tipos de usuario | Funcional / Happy path / Negativo | 6 usuarios |
| TC-002 | Login fallido con contrasena incorrecta | Negativo | standard_user, error_user |
| TC-003 | Validacion de campos obligatorios | Validacion | Sin usuario, sin contrasena, sin ambos |

**Total: 11 casos de prueba automatizados**

### Tags disponibles

| Tag | Descripcion |
|---|---|
| @tc001 | Escenario TC-001 |
| @tc002 | Escenario TC-002 |
| @tc003 | Escenario TC-003 |
| @smoke | Todos los escenarios |
| @login | Escenarios de login |
| @negativo | Escenarios con resultado fallido |
| @validacion | Escenarios de validacion de campos |

---

## Como ejecutar las pruebas

### Ejecutar todos los casos

```bash
mvn clean test
```

### Ejecutar por tag en PowerShell (Windows)

```bash
# Un escenario especifico
mvn test "-Dcucumber.filter.tags=@tc001"
mvn test "-Dcucumber.filter.tags=@tc002"
mvn test "-Dcucumber.filter.tags=@tc003"

# Solo los negativos
mvn test "-Dcucumber.filter.tags=@negativo"

# Solo validaciones
mvn test "-Dcucumber.filter.tags=@validacion"

# Todos los smoke
mvn test "-Dcucumber.filter.tags=@smoke"
```

### Ejecutar por tag en Linux / Mac

```bash
mvn test -Dcucumber.filter.tags=@tc001
```

### Ejecutar en modo headless (sin abrir el navegador)

Cambia en `config.properties`:
```properties
headless=true
```
Luego ejecuta:
```bash
mvn clean test
```

### Limpiar y ejecutar desde cero

```bash
mvn clean test
```

---

## Ver el reporte

Al terminar la ejecucion el reporte HTML queda en:

```
target/cucumber-report/report.html
```

Abrir en Windows:
```bash
start target\cucumber-report\report.html
```

Abrir en Mac:
```bash
open target/cucumber-report/report.html
```

El reporte muestra cada escenario con su resultado, tiempo de ejecucion y detalle de cada paso.


---

## Contacto

**Johanny Amaya Uchamocha**  
jamayau@unal.edu.co 
