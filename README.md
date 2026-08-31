# URL Shortener

API en Spring Boot para acortar URLs. Guarda las URLs en Postgres y redirige al visitar el código corto.

Demo: https://url-shortener-springboot-rg0s.onrender.com

(Está en el free tier de Render, así que si nadie la usó en un rato, la primera request tarda ~30-50s en responder mientras el servidor arranca de nuevo.)

## Stack

Java 21, Spring Boot, PostgreSQL (JDBC directo, sin JPA), JUnit + Mockito.

## Correrlo local

Necesitás Docker instalado.

```bash
docker-compose up
```

Levanta la app y Postgres juntos, y crea la tabla `urls` sola. Queda en `http://localhost:8080`.

## Endpoints

**Acortar:**
```
POST /acortar
{ "url": "https://ejemplo.com/pagina-larga" }
```
Devuelve 201 con `{ "shortUrl": "abc123", "originalUrl": "..." }`. 400 si la URL es inválida.

**Redirigir:**
```
GET /{codigo}
```
Redirige a la URL original. 404 si el código no existe.

## Tests

```bash
mvn test
```

Tests de `UrlService` (validaciones, generación de código) y `UrlController` (respuestas HTTP, manejo de errores).

## Estructura

```
controller/   endpoints
service/      lógica de negocio
repository/   acceso a datos (JDBC puro)
model/        entidades
dto/          request/response
exception/    manejo de errores
database/     conexión
```

## Deploy

Render, deployado directo desde el Dockerfile. Cada push a main redeploya solo.

## Ideas para después

- Migrar a Spring Data JPA
- Generar códigos con Base62 en vez de UUID
- Contador de clicks
- Expiración de links
