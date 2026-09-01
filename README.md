# URL Shortener

API en Spring Boot para acortar URLs. Guarda las URLs en Postgres y redirige al visitar el código corto.

Demo: https://url-shortener-springboot-rg0s.onrender.com

(Está en el free tier de Render, así que si nadie la usó en un rato, la primera request tarda ~30-50s en responder mientras el servidor arranca de nuevo.)

También tiene una interfaz simple en `/` para probar sin Postman.

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

## Ejemplos con curl

Acortar una URL válida:
```bash
curl -X POST http://localhost:8080/acortar \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.google.com"}'
```
```json
{"shortUrl":"a1b2c3","originalUrl":"https://www.google.com"}
```

URL vacía (400):
```bash
curl -X POST http://localhost:8080/acortar \
  -H "Content-Type: application/json" \
  -d '{"url":""}'
```
```json
{"message":"La URL no puede estar vacia","status":400,"error":"Bad Request"}
```

URL sin http/https (400):
```bash
curl -X POST http://localhost:8080/acortar \
  -H "Content-Type: application/json" \
  -d '{"url":"www.google.com"}'
```
```json
{"message":"La URL debe usar http o https","status":400,"error":"Bad Request"}
```

URL con sintaxis inválida (400):
```bash
curl -X POST http://localhost:8080/acortar \
  -H "Content-Type: application/json" \
  -d '{"url":"http://esto es una url invalida"}'
```
```json
{"message":"La URL no es valida","status":400,"error":"Bad Request"}
```

Redirigir con un código existente (302):
```bash
curl -i http://localhost:8080/a1b2c3
```
```
HTTP/1.1 302
Location: https://www.google.com
```

Código que no existe (404):
```bash
curl -i http://localhost:8080/noexiste
```
```json
{"message":"La URL no existe","status":404,"error":"Not Found"}
```

## Tests
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
