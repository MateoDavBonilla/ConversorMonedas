# Conversor de Monedas en Java 💱  

Este proyecto es una aplicación de consola desarrollada en Java cuyo objetivo es realizar conversiones de moneda basadas en tasas de cambio reales obtenidas desde una API externa.  
Permite consultar tasas de conversión, interpretar la respuesta en formato JSON y procesarla para ofrecer al usuario valores precisos y actualizados.

---

## 🚀 Características Principales

- Consumo de la API pública **ExchangeRate-API** para obtener tasas de cambio actualizadas.
- Uso de `HttpClient` (Java 11+) para realizar solicitudes HTTP.
- Conversión de respuestas JSON a objetos Java mediante la librería **Gson**.
- Organización del código utilizando una arquitectura modular con paquetes para:
  - API (comunicación con el servidor externo)
  - DTOs (representación de datos)
  - Lógica de negocio (servicios)
  - Interfaz de usuario (consola)
  - Excepciones y modelos

---

## 🔧 Tecnologías Utilizadas

- **Java 17**
- **HttpClient** para comunicación HTTP
- **Gson** para procesamiento JSON
- **IntelliJ IDEA** como entorno de desarrollo

---

## 📦 Estructura del Proyecto

src/
└── com_mateo_conversor
├── api
│ ├── ExchangeRateApiClient.java
│ └── dto
│ └── ExchangeRateResponse.java
├── service
├── ui
├── domain
├── exception
└── Main.java

yaml
Copiar código

### ¿Qué hace cada módulo?

- **api**  
  Contiene las clases encargadas de conectarse a la API de tasas de cambio y procesar las respuestas crudas.

- **api/dto**  
  Objetos que representan la estructura exacta del JSON devuelto por la API.

- **service**  
  Lógica para procesar las tasas recibidas y realizar conversiones entre monedas.

- **ui**  
  Interfaz por consola que guía al usuario durante la conversión.

- **domain**  
  Modelos internos del sistema (como solicitudes o resultados de conversión).

- **exception**  
  Excepciones personalizadas para manejo más claro de errores.

---

## 📡 Funcionamiento General

1. El usuario indica la moneda base y la moneda objetivo.  
2. La aplicación solicita a ExchangeRate-API las tasas de cambio actualizadas.  
3. La respuesta JSON se convierte en un objeto Java usando Gson.  
4. El sistema calcula la conversión basada en la tasa correspondiente.  
5. Se muestran los resultados de forma clara al usuario.

---

## ▶️ Ejemplo de Uso

```java
ExchangeRateApiClient client = new ExchangeRateApiClient();
ExchangeRateResponse response = client.fetchRates("USD");

double eurRate = response.getConversion_rates().get("EUR");
System.out.println("1 USD equivale a " + eurRate + " EUR");
🧑‍💻 Autor
Proyecto educativo desarrollado por Mateo, con enfoque en buenas prácticas, arquitectura clara y uso de tecnologías modernas de Java.

📄 Licencia
Este proyecto puede ser utilizado con fines educativos o personales.
