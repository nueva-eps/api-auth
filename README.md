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

## 🚀 Configuración del Backend (APIs)

El backend está desarrollado en **Java utilizando Spring Boot** bajo una arquitectura limpia (Hexagonal). Sigue los pasos correspondientes para configurar cada microservicio:

---

### 1. Servicio: api-auth

Este componente gestiona el ciclo de vida de las sesiones y registros en la plataforma.

#### ⚙️ Variables de Entorno y Configuración
* Dirígete a `api-auth/src/main/resources/`.
* Revisa el archivo `application.yml` (o `application.properties`).
* Asegúrate de que las credenciales de PostgreSQL apunten a tu base de datos local `nuevaeps`. Si modificaste el nombre de la base de datos en el paso de restauración, actualiza la propiedad `url`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/nuevaeps # <-- Cambiar si no usas 'nuevaeps'
    username: tu_usuario_postgres
    password: tu_contraseña_postgres
```

#### 🛠️ Endpoints Disponibles (API Reference)
Por defecto, este servicio corre en el puerto `8081` (o el configurado en tu `application.yml`). El controlador base responde en la ruta `/auth`:

* **`POST /auth/register`**: Registra un nuevo usuario en el sistema.
  * **Body (JSON)**: Requiere datos como `idTipoDocumento`, `numeroDocumento`, `primerNombre`, `email`, `password`, etc.
  * **Respuesta**: `201 Created` con el objeto del usuario creado.
* **`POST /auth/login`**: Autentica a un usuario existente.
  * **Body (JSON)**: Requiere `email` y `password`.
  * **Respuesta**: `200 OK` devolviendo el token de autenticación (JWT) en formato texto.

#### 🏃 Ejecución Local
Abre una terminal en la raíz de la carpeta `api-auth` y levanta el servicio usando el Maven Wrapper de Spring Boot:

```bash
cd api-auth
./mvnw spring-boot:run
```

---

### 2. Servicio: api-solicitudes

Este microservicio se encarga del catálogo de medicamentos y del procesamiento de las transacciones.

#### ⚙️ Variables de Entorno y Configuración
* Dirígete a `api-solicitudes/src/main/resources/`.
* Al igual que con el servicio anterior, verifica que los parámetros de conexión en el `application.yml` coincidan con tu base de datos local `nuevaeps`.

#### 🏃 Ejecución Local
Abre una segunda terminal en la raíz de la carpeta `api-solicitudes` y ejecuta:

```bash
cd api-solicitudes
./mvnw spring-boot:run
```
