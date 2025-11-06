# TAREA: Retrofit en Android (PokeAPI)

## Objetivo
Investigar y comprender qué es Retrofit, para qué se utiliza y cómo se integra en un proyecto Android. Implementar un ejemplo práctico en Kotlin que haga uso de Retrofit y subirlo a GitHub.

## 1) Investigación sobre Retrofit
- **Qué es Retrofit:** Retrofit es una librería de cliente HTTP para Android y Java desarrollada por Square. Facilita la comunicación con APIs REST convirtiendo las llamadas HTTP en interfaces Kotlin/Java simples.
- **Para qué se utiliza:** Realizar solicitudes HTTP (GET, POST, PUT, DELETE), mapear respuestas JSON a objetos Kotlin/Java, manejar errores, y soportar convertidores (Gson, Moshi).
- **Principales características y ventajas:**
  - Conversión automática de JSON a objetos mediante convertidores (Gson, Moshi).
  - Integración con coroutines (suspend functions) o Callbacks.
  - Simple definición de endpoints con anotaciones.
  - Extensible (interceptors, adaptadores).
  - Buena integración con OkHttp.

## 2) Implementación práctica (incluye proyecto en el ZIP)
- Proyecto en Kotlin con Jetpack Compose.
- Usa Retrofit + Gson converter y OkHttp logging interceptor.
- Endpoint: `https://pokeapi.co/api/v2/pokemon/{name}`

**Archivos clave entregados**
- `PokeApiService.kt` — interfaz Retrofit.
- `NetworkModule.kt` — crea Retrofit con base URL.
- `PokeRepository.kt` — capa de repositorio.
- `PokeViewModel.kt` — ViewModel que usa coroutines.
- `MainActivity.kt` — UI en Compose que carga "pikachu".
- Test con MockWebServer y Strikt: `PokeApiTest.kt`

## 3) Comparación con Volley
- **Volley**
  - Orientado a peticiones HTTP simples y cache.
  - Usa RequestQueue y callbacks.
  - Menos cómodo para deserialización de JSON complejos.
  - Buen para solicitudes simples y manejo de imágenes (con ImageLoader).
- **Retrofit**
  - Más adecuado para APIs REST complejas y mapeo a modelos.
  - Integrable con Gson/Moshi, coroutines/RxJava.
  - Mejor separación de responsabilidades.
- **Ventajas/desventajas resumidas**
  - Retrofit: + mapeo nativo, + fácil testing, - mayor abstracción.
  - Volley: + rápido para requests simples, - menos conveniente para modelos complejos.

## 4) Exploración adicional: Ktor
- **Qué es Ktor:** Framework asíncrono para Kotlin, permite tanto cliente HTTP como servidor.
- **Integración en Android:** Ktor Client puede reemplazar Retrofit; se integra con coroutines y permite configurar JSON serialization, logging y timeouts.
- **Uso con Compose:** Usar Ktor Client en repositorio, inyectarlo en ViewModel similar a Retrofit.
- **Ejemplo corto:** `val client = HttpClient(Android) { install(ContentNegotiation) { json() } }`

## 5) Pruebas de API
- **Cómo probar APIs consumidas con Retrofit:**
  - Unit tests con `MockWebServer` para simular respuestas HTTP.
  - Usar `Retrofit` con baseUrl apuntando al MockWebServer.
  - Aserciones con JUnit/Strikt.
- **Strikt:** biblioteca de aserciones Kotlin. Alternativas: AssertJ (Java), kotest-assertions, Truth.
- **Prueba incluida:** `PokeApiTest.kt` que valida parsing con MockWebServer.

## 6) Entrega
- Dentro del ZIP encontrarás el proyecto listo para abrir en Android Studio.
- **Pasos para subir a GitHub**
  1. Crear repositorio en GitHub (sin README).
  2. En tu terminal dentro de la carpeta `pokeapp_project`:
     ```bash
     git init
     git add .
     git commit -m "Initial PokeAPI Retrofit project"
     git branch -M main
     git remote add origin https://github.com/USUARIO/REPO.git
     git push -u origin main
     ```
  3. Si usas token, usa `https://<TOKEN>@github.com/USUARIO/REPO.git`

## Bibliografía
- Retrofit official docs: https://square.github.io/retrofit/
- OkHttp docs: https://square.github.io/okhttp/
- PokeAPI docs: https://pokeapi.co/docs/v2
- Ktor docs: https://ktor.io/
- Strikt: https://strikt.io/

---
