# Entorno de desarrollo

## Tabla de contenido
* [1. Empezando](#1-empezando)
* [2. Estructura del proyecto](#2-estructura-del-proyecto)
* [3. Entorno de desarrollo](#3-entorno-de-desarrollo)
  * [3.1 Maven 3](#31-maven-3)
  * [3.2 Jira](#32-jira)
  * [3.3 Git y GitHub](#33-git-y-github)
  * [3.4 Github-Actions](#34-github-actions)
  * [3.5 WildFly local](#35-wildfly-local)
  * [3.6 MySQL 5+](#36-mysql-5-)


## 1. Empezando

El proyecto μStories se desarrolla en un entorno de integración en GitHub. Este entorno
está compuesto por varias herramientas que automatizan el proceso, todas ellas
dirigidas por el POM de este proyecto.En total se usarán unas seis herramientas diferentes.

En este documento encontrarás una descripción de este entorno.

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
  WildFly, ficheros para la creación y gestión de la base de datos, etc.Actualmente 
  solo se encuentran archivos de configuracion de bd y del servidor wildfly local.


## 3. Entorno de desarrollo
Las herramientas que componen el entorno de integración continua son las
siguientes:

### 3.1 Maven 3
  Es un entorno de construcción de proyectos para Java. Esta será una herramienta
  clave, ya que es quien dirigirá todo el proyecto. Es necesario que tengas
  instalado Maven 3 en tu equipo de desarrollo para poder construir el proyecto.
  (en el manual de instalación se encuentra como puede instalarlo en su sistema)
  Esta herramienta nos proporcionará las siguientes ventajas:

  - Una estructura estándar para proyectos.
  - La posibilidad de reutilizar código a través de la gestión de dependencias.
  - La capacidad de automatizar tareas comunes de construcción, como compilación, pruebas y empaquetado.

  Además, Maven proporciona una interfaz de línea de comandos fácil de usar y una gran cantidad de plugins disponibles para extender sus funciones.
  
  En resumen, Maven es una herramienta muy útil para ayudar a los desarrolladores a:
  - Automatizar y simplificar el proceso de construcción de proyectos Java.
  - Gestión de sus dependencias.

  En nuestro caso utilizaremos Maven por las siguientes razones:
  - La gestión de las dependencias se gestionan a través de POM's que son archivos xml fáciles de manejar y entender.
  - Es la herramienta más usada para la gestión y configuración de proyectos java y nos proporcionara una mayor flexibilidad
    que otras herramientas a la hora de gestionar nuestro proyecto.

### 3.2 Jira
  Jira es una herramienta popular de gestión de proyectos y seguimiento de incidencias.
  Algunas de las principales ventajas por la que usaremos Jira son las siguientes:
- **Gestión de tareas:** Jira permite la creación, asignación y seguimiento de tareas y proyectos de manera eficiente.

- **Seguimiento de incidencias:** Jira permite registrar, asignar y seguir incidencias o problemas, lo que facilita la resolución de problemas y el seguimiento del progreso.

- **Integración con herramientas de colaboración:** Jira se integra con otras herramientas de colaboración como Slack, Trello, GitHub, entre otros, lo que permite una mejor comunicación y colaboración
  como **Slack**, **Trello**,**Github** (que es un herramienta que también utilizaremos), entre otros, lo que permite una mejor comunicación y colaboración entre los miembros del equipo.
- **Informes y seguimiento de proyectos:** Jira ofrece una variedad de informes y métricas para ayudar a los equipos a entender el progreso y el estado de sus proyectos.
- **Personalización:** Jira permite la personalización de plantillas, flujos de trabajo y campos para adaptarse a las necesidades específicas de un equipo o proyecto.
- **Accesibilidad:** Jira ofrece una interfaz web y una aplicación móvil, lo que permite a los miembros del equipo acceder a la información y colaborar desde cualquier lugar.


  Puedes acceder a través del siguiente [enlace](https://www.atlassian.com/es/software/jira) a la herramienta.Si quieres acceder al proyecto en Jira el administrador
  tiene que enviarte una invitación por correo.
### 3.3 Git y GitHub
  **Git** es el sistema de control de versiones que se utiliza en el proyecto.
  Es necesario que tengas instalado Git en tu sistema para poder
  realizar cambios en el proyecto y colaborar con el resto del equipo.

**Git** es un sistema de control de versiones que permite a los desarrolladores llevar un registro de los cambios realizados en el código fuente de un proyecto. Algunas de las ventajas de utilizar Git incluyen:

- **Control de versiones:** Git permite llevar un registro de los cambios realizados en el código fuente, lo que facilita la recuperación de versiones anteriores en caso de errores o problemas.

- **Colaboración:** Git permite a varios desarrolladores trabajar en un mismo proyecto de manera simultánea, lo que facilita la colaboración y el trabajo en equipo.

- **Rama y merge:** Git permite trabajar en varias ramas del proyecto de manera simultánea, lo que permite realizar cambios y pruebas sin afectar al código principal.

- **Seguridad:** Git almacena los cambios en forma de instantáneas, lo que permite deshacer cambios y comparar versiones anteriores.

- **Integración:** Git se integra con otras herramientas como GitHub, GitLab, Bitbucket, entre otras, lo que permite el trabajo colaborativo y el despliegue continuo.

**GitHub** es una plataforma web de alojamiento de proyectos y colaboración basada en Git. Algunas de las ventajas de usar GitHub con Git incluyen:

- **Alojamiento de proyectos:** GitHub permite alojar proyectos de manera gratuita o paga, lo que facilita el trabajo colaborativo y la gestión de proyectos.

- **Colaboración:** GitHub permite a varios desarrolladores trabajar en un mismo proyecto de manera simultánea, lo que facilita la colaboración y el trabajo en equipo.

- **Control de versiones:** GitHub permite llevar un registro de los cambios realizados en el código fuente, lo que facilita la recuperación de versiones anteriores en caso de errores o problemas.

- **Rama y merge:** GitHub permite trabajar en varias ramas del proyecto de manera simultánea, lo que permite realizar cambios y pruebas sin afectar al código principal.

- **Seguridad:** GitHub almacena los cambios en forma de instantáneas, lo que permite deshacer cambios y comparar versiones anteriores.

- **Integración:** GitHub se integra con otras herramientas como GitLab, Bitbucket, entre otras, lo que permite el trabajo colaborativo y el despliegue continuo(usando también github-actions que es
  una herramienta que usaremos también en este proyecto.


  Por otro lado, Github tiene una fácil integración con otras herramientas que usamos en el proyecto, por ejemplo:
  GitHub-Actions o Jira. Puedes acceder a través del siguiente [enlace](https://github.com/) a la herramienta y al proyecto en
  específico desde aquí [aquí](https://github.com/ncgomez17/ProyectoXCS)

  Principalmente usamos está herramienta principalmente por lo siguiente:
  - Por su interfaz muy amigable con el usuario y en la que se encuentran un montón de funcionalidades
  - Integración con otras herramientas del proyecto, por ejemplo **Github-Actions**
### 3.4 Github-Actions
**GitHub Actions** es una herramienta de automatización de flujos de trabajo en **GitHub**.
Con ella se pueden automatizar tareas como compilación, pruebas, despliegue y otras tareas relacionadas con el ciclo de vida de un proyecto. Algunas de las ventajas de usar **GitHub Actions** incluyen:
- **Automatización de flujos de trabajo:** **GitHub Actions** permite automatizar tareas como compilación, pruebas, despliegue y otras tareas relacionadas con el ciclo de vida de un proyecto.
- **Integración con GitHub:** GitHub Actions esta integrado con **GitHub**, lo que permite automatizar tareas en respuesta a eventos en GitHub, como nuevos commits o aperturas de pull requests.
- **Flexibilidad:** GitHub Actions se basa en un sistema de "actions" o acciones, que son pequeños scripts que pueden ser compartidos y reutilizados en diferentes proyectos.

Principalmente usamos está herramienta principalmente por lo siguiente:
- La gestión de los workflows es mediante ficheros yml muy fáciles de configurar .
- Integración directa con **Github**.

  Esta herramienta está configurado para
  vigilar el repositorio **GitHub** y, ante cualquier cambio en la rama `develop`,
  lanzar una construcción completa del sistema. Para cada construcción se
  ejecutarán todos los tests incluidos y se generarán varios informes finales. 
  Luego también se pueden generar releases que subiendo tags a máster, antes de generar
  la release el workflow lanzará una construcción del proyecto para ver que todo funciona
  correctamente, y si se cumple se generará release.

### 3.5 WildFly local
WildFly es un servidor de aplicaciones Java de código abierto. Fue anteriormente conocido como JBoss AS. Algunas de las ventajas de usar WildFly incluyen:

- **Funcionalidad completa:** WildFly ofrece una funcionalidad completa para desarrollar y ejecutar aplicaciones Java, incluyendo soporte para Java Enterprise Edition (EE) y JavaServer Faces (JSF).

- **Alta escalabilidad:** WildFly es altamente escalable y permite la ejecución de aplicaciones en múltiples nodos, lo que permite aprovechar al máximo la capacidad de los servidores.

- **Alta disponibilidad:** WildFly tiene una arquitectura diseñada para la alta disponibilidad, lo que permite a las aplicaciones continuar funcionando incluso en caso de fallos en los servidores.

- **Seguridad:** WildFly proporciona una gran cantidad de car
  Aunque el proyecto está configurado para ser ejecutado directamente desde
  Maven, también es posible desplegarlo en un servidor WildFly local. En el
  manual de instalación encontrarás una explicación de cómo instalarlo y configurarlo.

Principalmente usamos está herramienta principalmente por lo siguiente:
- **Automatización de tareas:** Maven permite automatizar tareas como la compilación, empaquetado y despliegue de aplicaciones en **WildFly** mediante plugins específicos.
- **Gestión de dependencias:** Maven permite gestionar fácilmente las dependencias de un proyecto, lo que facilita la gestión de las librerías y componentes necesarios para una aplicación en WildFly.
- **Portabilidad:** Al usar Maven con WildFly, se puede crear un proyecto que puede ser fácilmente desplegado en cualquier servidor que soporte Java, lo que facilita la portabilidad de la aplicación.
### 3.6 MySQL 5+:
  Es el sistema gestor de base de datos (SGDB) que utilizará el sistema
  definitivo. En la explicación de cómo ejecutar el sistema en local utilizaremos
  este SGBD, por lo que deberás tenerlo instalado en tu equipo(en el manual de instalación se indica como instalarlo).

Principalmente usamos está herramienta principalmente por lo siguiente:

-**Integración automatizada:** Maven permite automatizar tareas de configuración y creación de base de datos mediante plugins específicos. Esto permite a los desarrolladores centrarse en escribir código en lugar de preocuparse por la configuración manual de la base de datos.

-**Gestión de dependencias:** Maven permite gestionar fácilmente las dependencias de un proyecto, incluyendo las dependencias de la base de datos. Esto facilita la gestión de las librerías y componentes necesarios para una aplicación que utiliza MySQL.

-**Portabilidad:** Al usar Maven con MySQL, se puede crear un proyecto que puede ser fácilmente desplegado en cualquier entorno que soporte Java y MySQL, lo que facilita la portabilidad de la aplicación.

-**Soporte de versiones:** MySQL 5+ cuenta con una gran cantidad de características y mejoras en comparación con versiones anteriores, como mejoras en la seguridad, rendimiento, escalabilidad, entre otros.



