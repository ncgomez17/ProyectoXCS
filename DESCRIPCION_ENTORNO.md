# Entorno de desarrollo

## Tabla de contenido
* [1. Empezando](#1-empezando)
* [2. Estructura del proyecto](#3-estructura-del-proyecto)
* [3. Entorno de desarrollo](#4-entorno-de-desarrollo)

## 1. Empezando

El proyecto μStories se desarrolla en un entorno de integración continua con
despliegue continuo en un servidor de pre-producción (*staging*). Este entorno
está compuesto por varias herramientas que automatizan el proceso, todas ellas
dirigidas por el POM de este proyecto.

En este documento encontrarás una descripción de este entorno y las
instrucciones para saber cómo contribuir correctamente a este proyecto.


![Flujo de vida de desarrollo](additional-material/images/lifecycle.jpg)

En las siguientes secciones encontrarás información que te ayudará a realizar
este trabajo.


## 2. Estructura del proyecto
Este proyecto está estructurado en 7 módulos:

* **tests**:
  módulo que contiene utilidades para realizar los tests. Este módulo será
  importado por el resto de módulos con el *scope* `test` para hacer uso de sus
  utilidades.
* **domain**:
  módulo que contiene las clases de dominio (entidades).
* **service**:
  módulo que contiene los EJB del sistema, que serán utilizados tanto por la capa
  JSF como por la capa REST. Además, será en esta capa en la que se harán los
  controles de acceso.
* **rest**:
  módulo que contiene una capa de servicios REST.
* **jsf**:
  módulo que contiene la interfaz Web del sistema implementada con Java Server
  Faces (JSF).
* **ear**:
  módulo destinado, únicamente, a la construcción del EAR desplegable del
  sistema.
* **additional-material**:
  no es realmente un módulo del proyecto. Simplemente es un directorio
  que acompaña al resto del proyecto en el que se almacenarán ficheros adicionales
  que puedan resultar de utilidad. Algunos ejemplos de ficheros que pueden ir en
  este directorio son plantillas HTML, ficheros de configuración del servidor
  WildFly, ficheros para la creación y gestión de la base de datos, etc.


## 3. Entorno de desarrollo
Las herramientas que componen el entorno de integración continua son las
siguientes:

* **Maven 3**:
  es un entorno de construcción de proyectos para Java. Esta será una herramienta
  clave, ya que es quien dirigirá todo el proyecto. Es necesario que tengas
  instalado Maven 3 en tu equipo de desarrollo para poder construir el proyecto.
* **Jira**:
  es una herramienta de gestión de proyectos Scrum. En ella encontrarás toda la
  información sobre las funcionalidades desarrolladas y por desarrollar, el
  alcance de las publicaciones, el estado de desarrollo, etc. Puedes acceder a
  través del siguiente [enlace](http://www.sing-group.org/dt/kunagi).
* **Git y Github**:
  Git es el sistema de control de versiones que se utiliza en el proyecto. Es un
  sistema de control de versiones distribuido que facilita la colaboración entre
  desarrolladores. Es necesario que tengas instalado Git en tu sistema para poder
  realizar cambios en el proyecto y colaborar con el resto del equipo.
  Por otro lado, Gitlab es un *front-end* del repositorio Git común. Esta
  herramienta facilita la visualización de los *commits* y ficheros del proyecto,
  además de proporcionar alguna otra funcionalidad que mejora la colaboración.
  Puedes acceder a través del siguiente
  [enlace](http://www.sing-group.org/dt/gitlab).
* **Github-Actions**:
  es un servidor de integración continua. Este servidor está configurado para
  vigilar el repositorio Git y, ante cualquier cambio en la rama `develop`,
  lanzar una construcción completa del sistema. Para cada construcción se
  ejecutarán todos los tests incluidos y se generarán varios informes finales a
  los que podrás acceder a través de la web. Además, si la construcción ha tenido
  éxito, el sistema se desplegará en un WildFly de pre-producción (explicado más
  adelante).
  Si la construcción no ha tenido éxito, entonces se enviará un email de aviso a
  todo el equipo de desarrollo y resolver esta situación se convertirá en la
  mayor prioridad del equipo.
  Puedes acceder a través del siguiente
  [enlace](http://www.sing-group.org/dt/jenkins).
* **Nexus**:
  es un gestor de repositorios Maven. Lo utilizaremos para publicar el proyecto
  empaquetado en formato Maven. Puedes acceder a través del siguiente
  [enlace](http://www.sing-group.org/dt/nexus)
  Utilizaremos dos de los repositorios de este servidor:
    * `Snapshots`: en el que se publicarán las versiones de trabajo del proyecto.
    * `Releases`: en el que se publicarán las versiones publicables del proyecto.
* **WildFly de pre-producción**:
  en este contenedor se desplegará el sistema cada vez que una construcción se
  complete con éxito en Jenkins. Las URLs en las que se desplegarán las
  aplicaciones del proyecto son:
    * JSF: http://www.sing-group.org/microstories-2223-teamA/jsf
    * REST: http://www.sing-group.org/microstories-2223-teamA/rest
* **WildFly local**:
  Aunque el proyecto está configurado para ser ejecutado directamente desde
  Maven, también es posible desplegarlo en un servidor WildFly local. En la
  [sección 5.1](#511-ejecución-en-un-wildfly-local) encontrarás una explicación
  de cómo instalarlo y configurarlo.
* **MySQL 5+**:
  es el sistema gestor de base de datos (SGDB) que utilizará el sistema
  definitivo. En la explicación de cómo ejecutar el sistema en local utilizaremos
  este SGBD, por lo que deberás tenerlo instalado en tu equipo.

En el siguiente diagrama se detallan las distintas secciones que componen el
entorno de desarrollo, junto con las distintas tecnologías y herramientas
empleadas en cada sección.
