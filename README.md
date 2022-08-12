# MarvelComics (Clean Architecture )

Este es un proyecto demo desarrollado en Kotlin usando Clean Architecture

## Experiencia de usuario

Este proyecto contiene las siguientes características.

* Una pantalla inicial con un listado de los personajes del mundo de MARVEL
* Una pantalla de detalle del personaje que muestra información adicional.


## Capturas de pantalla

<p align="center">
  <img width="270" height="555" src="screenshots/screenshot_1.png">
  <img width="270" height="555" src="screenshots/screenshot_3.png">
  <img width="270" height="555" src="screenshots/screenshot_4.png">
</p>

## Guía de implementación

### API MARVEL

Este proyecto implementa el API oficial de MAARVEL (https://developer.marvel.com)

### Arquitectura

Este proyecto sigue buenas prácticas de la arquitectura limpia (Clean Architecture) planteada por Robert C. Martin para hacer un código más independiente, mantenible y sencillo para generar pruebas unitarias.

#### Capas

* Presentación: Activity, View Models.
* Casos de uso: GetAllCharactersUseCase, GetACharacterUseCas
* Dominio: Character.
* Datos: CharacterRepository, CharacterDataSourceRemote

Este proyecto usa ViewModel para almacenar y manejar datos, así como comunicar cambios hacia la vista.

Este proyecto usa LiveData para manejar la navegación y los valores en la aplicación.



### Administrador de peticiones de red: Retrofit

Esta demo utiliza Retrofit para la comunicación entre la API y la app.

### Biblioteca de imágenes - Glide

Este proyecto utiliza Glide para cargar las imágenes de los personajes. Para el uso de placeholders se manejan íconos proporcionados por la página de Material Design (https://material.io/resources/icons/).

### Inyección de dependencia - Hilt

Este proyecto utiliza Hilt para gestionar la inyección de dependencia.


