# Genetix Arena ⚔️

Un simulador de combate por turnos con inteligencia artificial avanzada, donde criaturas genéticas luchan por la supervivencia en una arena táctica.

[![Version](https://img.shields.io/badge/version-3.0-blue)](https://github.com/Ju4nmaFd3z/Genetix_Arena)
[![Java](https://img.shields.io/badge/java-11+-green)](https://www.java.com/)
[![License](https://img.shields.io/badge/license-MIT-yellow)]()

---

## 📚 Contenido

- [Características principales](#características-principales)
- [Versiones](#versiones)
- [Instalación](#instalación)
- [Uso](#uso)
- [Roadmap](#roadmap)
- [Documentación](#documentación)
- [Contribuir](#contribuir)

---

## ✨ Características principales

- **Combate táctico por turnos**: Sistema de movimiento y ataque basado en turnos
- **IA avanzada**: Aliados con inteligencia mejorada y enemigos con tácticas de combate
- **Sistema de vida dinámico**: Batallas equilibradas con detección de aliados
- **Curandero especializado**: Personaje de soporte que puede sanar a aliados
- **Movimientos avanzados**: Desplazamientos en diagonal y movimientos complejos
- **Detección de colisiones**: Sistema de obstáculos y paredes interactivas
- **Sistema de eventos**: Registro en tiempo real de todas las acciones del juego
- **Código documentado**: Todo el código incluye documentación JavaDoc completa

---

## 📦 Versiones

### V3 (Actual)
Mejoras en el sistema de movimiento y tácticas avanzadas:
- ✅ Nueva implementación de sistema de movimiento
- ✅ Táctica de escapatoria para evitar bloqueos
- ✅ Movimientos avanzados en diagonal
- ✅ Pulido de sistema de ataque y detección de aliados

### V2
Optimización de IA y limpieza de código:
- ✅ Mejora de inteligencia de aliados (menos escapadas innecesarias)
- ✅ Implementación de curandero
- ✅ Refactorización de código y optimización de diagonales
- ✅ Documentación completa con JavaDoc

### V1
Refactorización arquitectónica:
- ✅ Eliminación de código redundante
- ✅ Movimiento de lógica a métodos de entidades
- ✅ Simplificación de estructura y flujo de control
- ✅ Mejora de mantenibilidad

### MVP
Implementación de funcionalidades core:
- ✅ Sistema de combate basado en vida
- ✅ Sistema de eventos en pantalla
- ✅ Sistema de limpieza de entidades muertas
- ✅ IA básica de aliados
- ✅ Implementación de curandero

---

## 🚀 Instalación

### Requisitos previos
- Java 11 o superior
- Maven (opcional)
- Git

### Pasos

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Ju4nmaFd3z/Genetix_Arena.git
   cd Genetix_Arena
   ```

2. **Compilar el proyecto**
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Ejecutar la aplicación**
   ```bash
   java -cp bin App
   ```

---

## 🎮 Uso

### Flujo de juego

1. Lanza la aplicación desde `App.java`
2. Observa cómo las criaturas se despliegan en la arena
3. Los turnos se procesan automáticamente
4. Consulta el sistema de eventos para seguir la batalla

### Ejemplo de ejecución
```java
// La aplicación inicializa automáticamente:
// - Matriz del mapa
// - Criaturas aliadas y enemigas
// - Sistema de IA
// - Sistema de eventos
```

---

## 🗺️ Estructura del Proyecto

```
Genetix_Arena/
├── src/
│   ├── App.java              # Punto de entrada principal
│   ├── entities/             # Clases de criaturas
│   ├── map/                  # Lógica del mapa y colisiones
│   ├── combat/               # Sistema de combate
│   ├── ai/                   # Inteligencia artificial
│   └── events/               # Sistema de eventos
├── bin/                      # Archivos compilados
├── README.md                 # Este archivo
└── .gitignore               # Archivos ignorados
```

---

## 📋 Funcionalidades por componente

### 🧠 Inteligencia Artificial
- Toma de decisiones basada en el entorno
- Esquiva de paredes y obstáculos
- Detección de enemigos y aliados
- Tácticas de escape cuando es necesario
- Comportamiento de curación inteligente

### ⚔️ Sistema de Combate
- Cálculo de daño dinámico
- Sistema de vida con regeneración (curandero)
- Detección de aliados para evitar daño amistoso
- Registro de todos los enfrentamientos

### 🗺️ Sistema de Mapa
- Matriz bidimensional representando la arena
- Detección de colisiones
- Manejo de obstáculos
- Formateo visual en consola

### 📢 Sistema de Eventos
- Registro en tiempo real de acciones
- Historial de combates
- Mensajes de estado de entidades
- Visualización de movimientos y ataques

---

## 📈 Roadmap futuro

- [ ] Interfaz gráfica (Swing/JavaFX)
- [ ] Más tipos de criaturas con habilidades únicas
- [ ] Sistema de puntuación y estadísticas
- [ ] Guardado y carga de partidas
- [ ] Modo multijugador en red
- [ ] Editor de mapas personalizado
- [ ] Sistema de logros

---

## 📖 Documentación

Toda la documentación del código está en **JavaDoc**. Para generar la documentación HTML:

```bash
javadoc -d docs src/**/*.java
```

Métodos principales documentados:
- `Entidad.mover()` - Lógica de movimiento
- `Combate.atacar()` - Sistema de ataque
- `IA.analizarEntorno()` - Análisis del mapa
- `Evento.registrar()` - Sistema de eventos

---

## 🤝 Contribuir

Las contribuciones son bienvenidas. Para cambios mayores:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

## 📞 Contacto y Enlaces

- **Versión Web**: [Genetix Arena Web Edition](https://github.com/Ju4nmaFd3z/Genetix_Arena_Web_Edition)
- **Deploy Web**: [Jugar Genetix Arena](https://ju4nmafd3z.github.io/Genetix_Arena_Web_Edition/)

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver `LICENSE` para más detalles.

---

**Última actualización**: Febrero 2026  
**Desarrollador**: [Ju4nmaFd3z](https://github.com/Ju4nmaFd3z)
