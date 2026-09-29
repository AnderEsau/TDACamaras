# TDA Cámara CCTV

**Autor:** Ander Esau Hernández Jiménez

## Descripción

Aplicación Android desarrollada en Java que implementa un Tipo de Dato Abstracto (TDA) para simular una cámara de seguridad CCTV.
Permite registrar una cámara, calcular el costo del cableado UTP, diagnosticar la conexión según la distancia y estimar la degradación de la señal de video.

## Conceptos aplicados

- TDA y encapsulamiento.
- Herencia mediante `clsDispositivoRed` y `clsCamaraModelo`.
- Constructores con validaciones de modelo y distancia.
- Recursividad para calcular la degradación de la señal por metro de cable.
- Validaciones de datos (modelo no vacío, distancia dentro de rango).
- Patrón MVP para separar Modelo, Vista y Presentador.

## Funcionalidades

- Registrar una cámara con su modelo y distancia de cable.
- Activar o desactivar el uso de balunes de video.
- Calcular el costo total del cableado UTP.
- Diagnosticar el estado de la conexión.
- Calcular la calidad final de la señal de video.

## Estructura principal

- **Modelo:** `clsDispositivoRed` y `clsCamaraModelo`.
- **Presentador:** `clsCamaraPresentador`.
- **Vista:** `MainActivity`.

## Tecnologías

- Java
- Android Studio
- XML (ConstraintLayout)
- Git y GitHub
