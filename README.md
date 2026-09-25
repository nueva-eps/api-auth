# Configuración del Microservicio

Este proyecto es un microservicio desarrollado en **Java**. Se encarga de interactuar con la base de datos centralizada del sistema.

---

## 🚀 Paso 1: Restauración de la Base de Datos

El sistema requiere una base de datos PostgreSQL activa. El nombre preconfigurado para este proyecto es **`nuevaeps`**.

### Crear la Base de Datos (psql)
Conéctate a tu servidor de PostgreSQL y ejecuta:
```sql
CREATE DATABASE nuevaeps;
```

> ⚠️ **Nota Importante:**
> Si decides utilizar un nombre diferente a `nuevaeps`, debes editar el archivo de configuración de este repositorio (usualmente `src/main/resources/application.yml` o `application.properties`) y actualizar la URL de conexión de la base de datos (`datasource.url`).

### Importar la Estructura
Abre tu terminal en la carpeta donde guardaste tu script de base de datos (`estructura_bd.sql`) y ejecuta:

```bash
psql -U postgres -h localhost -p 5432 -d nuevaeps -f estructura_bd.sql
```

---

## 💻 Paso 2: Instalación y Ejecución Local

### Prerrequisitos
* **Java JDK** (versión compatible con el proyecto).
* **Maven** o **Gradle** instalado (o usar el wrapper incluido `./mvnw` / `./gradlew`).

### Ejecución del Servicio
Abre una terminal en la raíz de este repositorio y ejecuta el comando de arranque:

```bash
# Si utilizas Maven Wrapper:
./mvnw spring-boot:run

# Si utilizas Gradle Wrapper:
./gradlew bootRun
```
