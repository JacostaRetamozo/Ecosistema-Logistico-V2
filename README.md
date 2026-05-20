# Ecosistema Logístico Inteligente — Versión 2.0

Este repositorio contiene la segunda versión del proyecto **Ecosistema Logístico V2**, desarrollado para la cátedra de **Programación II** del Segundo Semestre de la carrera de **Ingeniería Informática** en la **Universidad Nacional de Itapúa (UNI)**. 

La finalidad de esta entrega es expandir el modelo original mediante la implementación de un controlador transaccional (CRUD), la incorporación del paradigma funcional a través de **Expresiones Lambda**, y la manipulación avanzada de colecciones con la API **Java Streams**.

---

## Características y Requerimientos Resueltos

El sistema ha sido estructurado y optimizado, priorizando la legibilidad, la encapsulación defensiva y la reducción de la complejidad ciclomática:

* **Ejercicio 1: Implementación de CRUD Completo**
    * Se refactorizó el identificador único a tipo `String` para garantizar compatibilidad estricta con las firmas solicitadas.
    * Operaciones de alta con verificación preventiva de duplicados.
    * Búsquedas utilizando contenedores `Optional<Vehiculo>` para mitigar excepciones de tipo `NullPointerException`.
    * Listados basados en copias defensivas para proteger la colección maestra.
* **Ejercicio 2: Expresiones Lambda**
    * Recorrido y despliegue atómico de la flota mediante el método iterador `forEach` acoplado a expresiones lambda funcionales.
* **Ejercicio 3: Procesamiento Avanzado con Streams**
    * Filtrado dinámico de unidades que poseen capacidades IoT utilizando operaciones reflexivas sobre la interfaz `IConectable`.
    * Extracción y aislamiento de identificadores mediante mapeos funcionales (`.map(Vehiculo::getId)`).
    * Contención y conteo directo sobre el flujo de datos del ecosistema.
* **Ejercicio 4: Ordenamiento Multidimensional**
    * Clasificación secuencial de la flota por identificador alfanumérico (orden ascendente) y por tipo de componente mediante la inyección de fábricas de comparadores (`Comparator.comparing`).
* **Ejercicio 6: Propuesta de Mejora (Métricas y Clasificación)**
    * **Clasificación Automática:** Agrupamiento en tiempo real de los vehículos según su tipo dinámico (clase concreta) utilizando `Collectors.groupingBy`.
    * **Búsquedas Avanzadas:** Aislamiento de unidades críticas en línea mediante predicados encadenados.
    * **Estadísticas Operativas:** Reporte centralizado de la tasa de conectividad de la flota lograda.

---

## Estructura del Proyecto

El código fuente está modularizado siguiendo el patrón arquitectónico de separación de responsabilidades:

```text
src/
└── logistica/
    ├── modelo/
    │   ├── Vehiculo.java         # Clase abstracta base (ID inmutable como String)
    │   ├── IConectable.java      # Interfaz para capacidades de telemetría IoT
    │   ├── CamionAutonomo.java   # Subclase concreta (Camión de carga pesada)
    │   └── DronTransporte.java   # Subclase concreta (Unidad aérea no tripulada)
    ├── controlador/
    │   └── GestorLogistico.java  # Controlador central (Lógica CRUD y Streams API)
    └── vista/
        └── CentroControl.java    # Orquestador del sistema (Punto de entrada / Main)
