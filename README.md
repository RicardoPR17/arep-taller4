# **Taller 4 - Microframework web con reflexión**
### *Hecho por Ricardo Pulido Renteria*

En este taller, se extiende servidor web creado en el taller anterior utilizando Java y archivos almacenados en el disco, además de poder controlar peticiones GET y POST como un servidor de aplicaciones.
Ahora, se convierte en un servidor de aplicaciones con la posibilidad de usar anotaciones de forma similar a SpringBoot.

## **Descarga y ejecución**

Para poder ejecutar este proyecto, el cual se ejecutará en tu ambiente local por fines de desarrollo y pruebas, debes contar con algunos elementos que serán indicados a continuación.


## **Prerequisitos**

La ejecución de este proyecto requiere de:
- `Java (versión 8 o superior)`
- `Maven (3.8.1 o superior)`
- `Conexión a internet`


## **Instalación**

Para poder trabajar con el proyecto, es necesario descargarlo desde GitHub. Para esto puede clonar el repositorio en su máquina o puede descargarlo en formato zip. Luego, una vez acceda al directorio del proyecto, debe ejecutar el comando `mvn install` para descargar las dependencias del proyecto, ya sea desde la terminal de comandos o desde la terminal que le brinde el intérprete de código de su preferencia (VS Code, IntelliJ, NetBeans, etc).

Para ejecutarlo, podrá hacerlo desde la terminal de comandos como se explica a continuación. O desde el intérprete de código de su elección, haciendo `run` o ejecutando el código de la clase _MyWebServices_.


- **_Ejecución usando terminal de comandos_**
  
  En caso de realizar la ejecución desde la terminal de comandos, se debe realizar lo siguiente:
  1. Acceder al directorio del proyecto usando el comando `cd arep-taller4`.
  2. Una vez dentro del directorio del proyecto, se ejecuta el comando `mvn package` para generar la carpeta _target_.
  3. Desde la terminal, ejecutamos el comando `java -cp .\target\classes com.example.taller4.myspark.MyWebServices`.
  4. Listo, el servidor web estará corriendo y verás un mensaje diciendo que está listo para recibir peticiones.


## **Uso**

Primero, vamos a acceder a través de un navegador web a nuestro servidor creado. Veremos los recursos que se encuentran por defecto en el disco:

+ Página web con formularios: http://localhost:17000/public.html
+ Código JavaScript de los formularios: http://localhost:17000/public.js
+ Página web API películas: http://localhost:17000/movie.html
+ Código JavaScript API películas: http://localhost:17000/movieRequest.js
+ Hoja de estilos página web API películas: http://localhost:17000/movie.css
+ Imagen camara de cine:  http://localhost:17000/camera.png
+ Imagen cubo de rubik: http://localhost:17000/cube.jpg
+ GIF cubo de rubik: http://localhost:17000/scramble.gif

Ahora, si queremos ver el servicio de ejemplo que podría crear un usuario, accedemos por la ruta http://localhost:17000/action/ seguido del archivo que queramos consultar o la ruta que nuestro usuario haya especificado. Estos son:

+ Imagen camara fotográfica: http://localhost:17000/action/camera.png
+ Página web API nueva: http://localhost:17000/action/films.html
+ Código JavaScript API películas: http://localhost:17000/action/movieRequest.js
+ Hoja de estilos página web API películas: http://localhost:17000/action/movie.css
+ Imagen pruebas unitarias: http://localhost:17000/action/testing.png
+ Página simple AREP, puede enviar parámetros: http://localhost:17000/action/arep?param=a | http://localhost:17000/action/arep
+ Página simple ARSW: http://localhost:17000/action/arsw
+ Página simple IETI: http://localhost:17000/action/ieti
+ Página simple Queries, puede enviar parámetros: http://localhost:17000/action/queries?test=taller3

Si el usuario creó sus rutas con anotaciones, se accede por la ruta http://localhost:17000/component/ seguido de la ruta especificada por el usuario:

+ Saludo normal: http://localhost:17000/component/hello
+ Saludo con nombre: http://localhost:17000/component/helloname?val=Ricardo
+ Cuadrado de un número: http://localhost:17000/component/square?val=7
+ Piso de un decimal: http://localhost:17000/component/floor?val=7.5

## **Diseño**

Para este proyecto se manejan una clase que es _HttpServer_, siendo la clase del taller 3 que vamos a extender con el fin de actuar como el servicio que actúa por detrás para que el usuario pueda crear sus aplicaciones. Aquí, añadimos el manejo de rutas especiales que indique el usuario junto a las acciones que deben ejecutarse o por medio de anotaciones.

Para esto, se crea un mapa que permita relacionar la ruta (ejemplo: /square) con la acción que desea el usuario. Esta acción, se define como un método con anotaciones como Component para indicar que esa clase responderá a peticiones y con GetMapping(ruta) para indicar el método que recibe un parámetro solamente y retorna String. Esto ya sea con o sin parámetro, pero solo permite el manejo de peticiones GET.

![Muestra anotaciones](<Imágenes README/anotaciones.png>)

Las funcionalidades de asignar la carpeta y el tipo de respuesta se manejan como en la entrega anterior, en una clase donde se creen las asginaciones por medio de funciones lambda. Al iniciar el servidor de esta forma, se recorre el classpath con el fin de encontrar las clases que posean la anotacion de Component y de sus métodos cuáles tienen GetMapping.

Para el manejo de estas etiquetas, está su definición en el directorio _annotations_. De modo que, si se requiere añadir una nueva o modificar alguna existente se puedan diferenciar de las demás clases.

Además, en el directorio _resources/public_ encontramos todos los recursos que el servidor ofrece y envía cuando son solicitados. Pero, si el usuario quiere manejar otro directorio para sus archivos, puede crearlo manualmente dentro de _resources_ con el nombre que quiera y tal como se ve en la última línea de la imagen anterior, pasando el nombre del directorio, quedará asignado como espacio de búsqueda para los archivos solicitados para su implementación. Es decir, las rutas que inicien con el path */action*

Gracias a esta distribución de archivos en carpetas, es posible diferenciar los recursos manejados por el servidor de forma nativa y los recursos que el usuario quiera añadir y trabajar para su implementación particular usando nuestro servidor de aplicaciones.

## **Pruebas**

Para estas pruebas, vamos a acceder a cada uno de los recursos añadidos por el usuario con las anotaciones. Para eso, se usará el navegador de Firefox y el apartado de red de su inspección de recursos.

+ Saludo normal: http://localhost:17000/component/hello

![Hello](<Imágenes README/Hello.png>)

+ Saludo con nombre: http://localhost:17000/component/helloname?val=Ricardo

![HelloName](<Imágenes README/HelloName.png>)

+ Cuadrado de un número: http://localhost:17000/component/square?val=7

![Square](<Imágenes README/Square.png>)

+ Piso de un decimal: http://localhost:17000/component/floor?val=7.5

![Floor](<Imágenes README/floor.png>)


También, verificamos que las funcionalidades anteriores aún actúen de forma correcta.

+ Página web API películas: http://localhost:17000/movie.html

![API](<Imágenes README/movies.png>)

+ GIF cubo de rubik: http://localhost:17000/scramble.gif

![GIF](<Imágenes README/gif.png>)

+ Imagen pruebas unitarias: http://localhost:17000/action/testing.png

![Test](<Imágenes README/test.png>)

+ Página simple ARSW: http://localhost:17000/action/arsw

![ARSW](<Imágenes README/arsw.png>)

+ Página simple AREP, puede enviar parámetros: http://localhost:17000/action/arep?param=a | http://localhost:17000/action/arep

  + Sin parámetros

  ![AREP a](<Imágenes README/arepa.png>)

  + Con parámetros

  ![AREP](<Imágenes README/arep.png>)

Tal como en el taller anterior, estas rutas también funcionan al acceder desde Linux

+ Imágen cubo: http://`<IP servidor>`:17000/cube.jpg

![Cubo Linux](<Imágenes README/linux cube.png>)

+ Imagen cámara fotográfica: http://`<IP servidor>`:17000/action/camera.png

![Linux cámara usuario](<Imágenes README/linux cam.png>)

## **Construido con**
  - [Git](https://git-scm.com) - Control de versiones
  - [Maven](https://maven.apache.org) - Administrador de dependencias
