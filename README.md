# 💱 Conversor de Monedas en Java  
Aplicación de consola construida en Java 17 que permite convertir monedas en tiempo real utilizando la API pública **ExchangeRate API**.  
Este proyecto combina conceptos esenciales de programación con buenas prácticas de arquitectura, manejo de dependencias, consumo de APIs, deserialización JSON, validaciones de dominio y experiencia de usuario.

---

## 🚀 Características principales

- ✔ Conversión en tiempo real entre más de **150 monedas**
- ✔ Base fija en **USD** para optimizar rendimiento y claridad
- ✔ **Historial de conversiones** manejado en memoria
- ✔ **Exportación del historial a un archivo** `.txt`
- ✔ **Soporte para dos idiomas:** Español e Inglés
- ✔ Visualización de:
  - ▸ Todas las monedas disponibles desde la API  
  - ▸ Las monedas más usadas, incluyendo nombre del país  
- ✔ Validación automática de códigos de moneda
- ✔ Arquitectura modular siguiendo buenas prácticas (Clean-ish Architecture)

---

## 🧱 Estructura del Proyecto

src/
└── com_mateo_conversor
├── api
│ ├── dto
│ │ └── ExchangeRateResponse.java
│ └── ExchangeRateApiClient.java
│
├── domain
│ ├── ConversionRecord.java
│ └── PopularCurrencies.java
│
├── service
│ ├── CurrencyConversionService.java
│ └── CurrencyFilterService.java
│
├── ui
│ ├── ConsoleUI.java
│ └── Language.java
│
└── Main.java

yaml
Copiar código

---

## 🏗 Arquitectura y responsabilidades

### **1. api/**
Encargado de interactuar con APIs externas y manejar la comunicación HTTP.

- `ExchangeRateApiClient`  
  Consume la API **ExchangeRate API** usando `HttpClient` y retorna datos JSON.

- `ExchangeRateResponse`  
  DTO para almacenar la respuesta de la API de manera tipada.

---

### **2. domain/**
Contiene entidades del dominio de la aplicación.

- `ConversionRecord`: Representa una conversión realizada por el usuario.  
- `PopularCurrencies`: Lista predefinida de monedas más usadas con nombre del país.

---

### **3. service/**
Capa lógica de negocio.

- `CurrencyConversionService`:  
  Contiene la fórmula de conversión usando tasas respecto a USD.

- `CurrencyFilterService`:  
  Valida si una moneda existe en la respuesta de la API y entrega el listado disponible.

---

### **4. ui/**
Funcionalidad de interacción con el usuario.

- `ConsoleUI`:  
  Menú principal, lectura de inputs, visualización de resultados, funciones avanzadas:
  - selección de idioma  
  - loop de ejecución  
  - exportación a archivo  
  - impresión de historial  

- `Language`:  
  Enum para manejar ES/EN.

---

## 🖥 Ejemplo de Uso

```text
===================================
 Conversor de Monedas - Java
===================================
Base currency: USD (fixed)

Elige una opción:
1. Convertir moneda
2. Ver monedas disponibles
3. Ver monedas más usadas
4. Ver historial
5. Exportar historial a archivo
6. Cambiar idioma
7. Salir
Conversión:

text

From currency: USD
To currency: MXN
Amount: 100

Result: 100 USD = 1820.50 MXN
Exportación del historial:

text

Historial exportado a: /ConversorMonedas/conversion-history.txt
📡 API utilizada
ExchangeRate API (Free Tier)
https://www.exchangerate-api.com/

La aplicación usa llamadas como:

bash

https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/USD
🧪 Cómo ejecutar el proyecto
Requisitos
Java 17+

IntelliJ IDEA (recomendado)

Clonar o descargar este repositorio

Ejecución
bash
Copiar código
javac Main.java
java Main
o desde IntelliJ:

Click derecho en Main.java → Run 'Main'

📚 Conceptos aplicados
Programación modular

Consumo de API REST con HttpClient

Manejo de JSON con Gson

DTOs y separación de capas

Validación de datos

Persistencia simple (exportación a .txt)

Buenas prácticas de arquitectura

Interfaz por consola amigable

Implementación de selección de idiomas (i18n simplificada)

🌟 Mejoras futuras
Soporte para más idiomas

Exportación del historial en formato CSV o JSON

Integración con una base de datos ligera (SQLite)

Interfaz gráfica con JavaFX o Swing

Tests unitarios con JUnit

Sistema de logs con Log4j o SLF4J

👨‍💻 Autor
Mateo Bonilla
Desarrollador Java | UI/UX | Entusiasta de proyectos prácticos

Este proyecto fue construido como parte de un desafío educativo para practicar Java aplicado a APIs reales.

📂 Licencia
Este proyecto está bajo la licencia MIT. Puedes usarlo y modificarlo libremente.
