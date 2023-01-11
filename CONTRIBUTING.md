# ¿Cómo contribuir a este proyecto?

## Tabla de contenido
  * [1. Desarrollo de una tarea](#2-desarrollo-de-una-tarea)
  * [2. Control de versiones (Git)](#6-control-de-versiones-git)
    * [2.1. <em>Commits</em> con errores de construcción](#61-commits-con-errores-de-construcción)
    * [2.2. <em>Push</em> con <em>commits</em> nuevos en el servidor remoto](#62-push-con-commits-nuevos-en-el-servidor-remoto)
    * [2.3. Hacer <em>pull</em> ](#63-hacer-pull)
    * [2.4. <em>Pull</em> con cambios locales no <em>commiteados</em> ](#64-pull-con-cambios-locales-no-commiteados)
  * [3. Guía de estilo](#8-guía-de-estilo)
    * [3.1. Código fuente](#81-código-fuente)
    * [3.2. Control de versiones](#82-control-de-versiones)
    * [3.3. Material adicional](#83-material-adicional)

  


## 1. Desarrollo de una tarea
El proceso habitual para realizar una tarea será, normalmente, el siguiente:

1. En **Kunagi** selecciona la tarea de la que seas responsable que deseas
desarrollar y lee bien la descripción de la misma.
2. Abre el entorno de desarrollo.
3. Verifica que te encuentras en la rama `develop`. Si no es así, cámbiate a
esta rama.
4. Haz *pull* de los últimos cambios (ver [sección 6](#6-control-de-versiones-git)).
5. Implementa la solución, incluyendo los tests (ver [sección 7](#7-tests)).
  1. Haz un *commit* con cada parte estable (completa y testeada) que desarrolles.
  2. Cada vez que hagas un *commit* envíalo al repositorio central **Gitlab**para compartirlo con el resto del equipo (ver
   [sección 6](#6-control-de-versiones-git)).
  3. Comprueba que la construcción funciona correctamente en el servidor de integración continua **Jenkins**.
  4. Si la construcción falla, sigue los pasos descritos en la [sección 6.3](#63-pull-con-cambios-locales-no-commiteados).
  5. Si la construcción es correcta, comprueba que el proyecto se ha desplegado
    y funciona correctamente en el servidor **WildFly** de pre-producción y que el
    repositorio Maven **Nexus** tiene una nueva versión del proyecto
    (ver [sección 4](#4-entorno-de-desarrollo)).
    
6. Cuando acabes la jornada de trabajo recuerda introducir las horas en la tarea
de **Kunagi**.


En las siguientes secciones encontrarás información que te ayudará a realizar
este trabajo.

## 2. Control de versiones (Git)
El modelo de control de versiones que utilizaremos inicialmente será muy
sencillo ya que solo utilizaremos dos ramas:

* `master`: a esta rama solo se enviarán los *commits* cuando se llegue a una
versión estable y publicable (una *release*). Estas versiones deberán estar
etiquetadas con el número de versión correspondiente.
* `develop`: esta será la rama principal de trabajo. Los *commits* que se envíen
deben ser estables, lo que supone que el código debe incluir tests y todos deben
superarse existosamente al construir la aplicación en local.
* `tmp-`: las ramas con el prefijo `tmp-` son ramas temporales. Cada pareja
  (*sprint* I) o desarrollador (*sprint* II) solo podrá tener una única rama
  temporal, que deberá eliminar de los repositorios local y remoto en el momento
  que ya no sean necesarias. Las ramas temporales siempre deben crearse desde la
  rama `develop`. La pareja o desarrollador propietarios de la rama podrán hacer
  `push` y `pull`, mientras que el resto solo podrán hacer `pull`. Este tipo de
  ramas admite cualquier tipo de *commit* (p.ej. código incompleto, código que no
  compila, código sin tests, etc.) y, por tanto, no serán controladas por el
  servidor de integración continua. Por último, el nombre de la rama debe ser:
  * `tmp-<pareja>`: donde `<pareja>` es el identificador de la pareja
  propietaria.
  * `tmp-<login>`: donde `<login>` es el *login* del usuario que la crea en el
  servidor Gitlab.

### 2.1. *Commits* con errores de construcción
Ambas ramas estarán controladas por el servidor de integración que ejecutará
los tests inmediatamente después de que se haga un *commit*. En el caso de que
una **construcción falle** en Jenkis es muy importante **deshacer el último
_commit_ para volver a un estado estable**.

Aunque existen varias formas de hacer esto, la forma más directa es:

```
git push origin +HEAD^:develop
```

Este comando fuerza a que la rama `develop` remota se sitúe en el *commit*
anterior a `HEAD`, ya que `HEAD` es el *commit* conflictivo. El *commit* seguirá
existiendo en local y se espera que tras corregir los errores se realice un
`git commit --amend`.
Si se desea descartar el *commit* local pero mantener el estado de los ficheros,
puede utilizarse un `git reset --mixed HEAD^`.

### 2.2. *Push* con *commits* nuevos en el servidor remoto
Si se desea hacer un *push* a un servidor remoto en el cual hay *commits* que
nuevos que no tenemos en local, entonces Git muestra un error en el que nos
indica que debemos hacer un *pull* antes de poder hacer *push*.

Dado que no nos interesa tener que añadir un *commit* de *merge* adicional,
el *pull* debe hacerse aplicando un *rebase*. Para ello debe usarse el comando:

```
git pull --rebase
```

Este comando iniciará un proceso de *rebase* entre desde la rama local hacia la
rama remota. Es decir, los *commit* locales no *pusheados* pasarán a tener como
padre el último *commit* remoto.

**Nota**: es muy recomendable configurar el proyecto para que siempre que se
haga un *pull* que traiga nuevos *commits* se haga un *rebase* en lugar de
*merge*. Es decir, para que se comporte como se ha descrito más arriba. Para
esto podemos modificar la configuración del proyecto de la siguiente manera:

```
git config pull.rebase true
```

Con esta configuración ya no tendríamos que añadir el modificador `--rebase` al
hacer *pull*.

### 2.3. Hacer *pull*
Antes de hacer un *pull* siempre se debe revisar el servidor de integración
continua. En el caso de que haya una construcción en ejecución **no debe hacerse
_pull_** hasta que finalice y se compruebe que ha sido con éxito.

En el caso de que la construcción falle, debe esperarse a que el repositorio
vuelva a un estado estable (ver [sección 6.1](#61-commits-con-errores-de-construcción))
antes de hacer *pull*.

### 2.4. *Pull* con cambios locales no *commiteados*
En caso de que nos encontremos en medio de un *commit* (no se ha completado los
cambios necesarios para realizar un *commit*) y deseemos descargar nuevos
*commits* del servidor central, podemos hacerlo utilizando los comandos:

```
git stash
git pull --rebase
git stash pop
```

## 3. Guía de estilo
Un elemento importante para poder colaborar es que exista una uniformidad en el
código y otros elementos que forman parte del desarrollo. Esta sección sirve
como una pequeña guía de estilo que debe respetarse al trabajar en el proyecto.

### 3.1. Código fuente
Para uniformizar el código fuente deben respetarse las siguientes normas:
* **Idioma**: todo el código (incluyendo la documentación) debe desarrollarse en
inglés.
* **Formato de código**: el código debe estar formateado, preferiblemente,
siguiendo la [Guía de Estilo para Java de Google](https://google.github.io/styleguide/javaguide.html)
o, al menos, utilizando el formato de código de Eclipse (`Ctrl`+`Mayus`+`F`).
* **Comentarios**: debe evitarse **completamente** el código comentado y, en la
medida de lo posible, los comentarios en el código.
* **Documentación**: todas las clases deben incluir documentación Javadoc que
describa las responsabilidades de la misma. No es obligatorio documentar los
métodos. Se recomienda que se verifique que la documentación es
correcta utilizando el comando `mvn javadoc:javadoc`. Este comando generará la
documentación en formato HTML y fallará si encuentra algún error en la
documentación.

### 3.2. Control de versiones
Una de las bases de desarrollo que utilizaremos en este proyecto es el
**integrar tan pronto como se pueda**. Para ello, deben seguirse las siguientes
normas:

* **Contenido de los _commits_**: los *commits* deben ser completos en el
sentido de que no deben romper la construcción. Además, el código debe estar
probado, incluyendo los tests descritos en la [sección 7](#7-tests), para que el
resto de desarrolladores puedan confiar en el código. Es muy recomendable
revisar los informes de tests y de cobertura antes de hacer un *commit*.
* **Formato**: el formato de los *commits* deberá respetar las siguientes
  normas:
  * Escritos en inglés.
  * Limitar el tamaño de línea a 80 columnas. Si se utiliza Eclipse, esto se
  hace de forma automática.
  * Primera línea descriptiva de lo que hace el *commit*:
    * Si está relacionado con alguna tarea concreta de las descritas en Kunagi,
    debe comenzar con el identificador de la tarea (p.ej. "tsk1 Adds...").
    * Si está relacionado con varias tareas, su número se separará con un guión
    (p.ej. "tsk1-2-13 Fixes...").
    * Debe estar redactada en tercera persona del presente (p.ej. *Adds...*,
      *Improves...*, *Modifies...*, etc.).
    * No debe llevar punto al final.
  * Cuerpo del *commit* descriptivo. Con una línea vacía de separación de la
  primera línea, debe escribirse un texto de explique claramente el trabajo
  hecho en el *commit*.
* **Frecuencia de _commit_**: los *commits* deben hacerse en pequeños pasos para
que la frecuencia sea alta. Para ello es recomendable desarrollar de una forma
ordenada, atacando partes concretas. Se espera que cada desarrollador genere,
al menos, 2-3 *commits* cada semana. Además, deberán estar distribuidos a lo
largo de toda la semana, evitando, especialmente, realizar todos los *commits*
al final de la semana, pues esto afectaría a la integración continua.
* **Frecuencia de _push_**: siempre que se haga un *commit* debe hacerse un
*push*. La única excepción a esta regla es que estemos haciendo pruebas locales
para evaluar una posible solución. En tal caso, es recomendable que esto se
haga en una rama independiente para evitar enviar *commits* accidentalmente a
la rama *develop* remota.

### 3.3. Material adicional
El proyecto incluye un módulo `additional-material` cuya función es recoger
ficheros adicionales que no forman parte de los desplegables, pero que son
necesarios para el funcionamiento de la aplicación o pueden ayudar en el
desarrollo.

Por lo tanto, el contenido de este módulo debe actualizarse cuando corresponda
durante el desarrollo. En concreto, las dos situaciones en las que se debe
actualiar son:
  * **Creación de una nueva entidad**: cuando se crear una nueva entidad en el
    módulo de dominio será necesario actualizar el contenido del subdirectorio
    `db`. Este subdirectorio debe contener los ficheros:
    * `microstories-mysql.full.sql`: este fichero debe contener las consultas
    para crear la base de datos y sus tablas, crear el usuario necesario
    para la aplicación con los permisos correspondientes y añadir datos de
    ejemplo para poder probar la aplicación. Es decir, un administrador que
    instale la aplicación debería poder, simplemente, ejecutando este *script*
    en un SGBD MySQL, empezar a probar la aplicación.
    * `microstories-mysql.creation.sql`: este fichero debe contener las
    consultas para crear la base de datos y sus tablas.
    * `microstories-mysql.data.sql`: este fichero debe contener las consultas
    para añadir datos de ejemplo para poder probar la aplicación.
    * `microstories-mysql.delete.sql`: este fichero debe contener las consultas
    para eliminar todos los datos de las tablas de la base de datos y resetear
    los contadores autoincrementales.
    * `microstories-mysql.drop.sql`: este fichero debe contener las consultas
    para eliminar todas las tablas de la base de datos.
  * **Cambio en la configuración de WildFly**: en el caso de añadir algún cambio
    en la configuración del servidor que sea necesario para la ejecución de la
    aplicación (p.ej. configurar un servidor de correo), deberán actualizarse los
    ficheros de configuración de WildFly del subdirectorio `wildfly`. En concreto,
    un administrador que instale la aplicación, debe poder copiar el fichero de
    configuración correspondiente a su servidor y arrancarla sin problemas,
    habiendo configurado la base de datos previamente.
