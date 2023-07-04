# ¿Cómo contribuir a este proyecto?

## Tabla de contenido
  * [1. Desarrollo de una tarea](#1-desarrollo-de-una-tarea)
  * [2. Control de versiones (Git)](#2-control-de-versiones-git)
    * [2.1. *Push* con *commits* nuevos en el servidor remoto](#21-Push-con-commits-nuevos-en-el-servidor-remoto)
    * [2.2. <em>Pull</em> con cambios locales no <em>commiteados</em> ](#22-pull-con-cambios-locales-no-commiteados)
  * [3. Guía de estilo](#3-guía-de-estilo)
    * [3.1. Código fuente](#31-código-fuente)
    * [3.2. Control de versiones](#32-control-de-versiones)
    * [3.3. Convenciones de Prefijos de Commits](#33-convenciones-de-Prefijos-de-Commits)
    * [3.4 Vincular una tarea de Jira a un issue de GitHub](#34-vincular-una-tarea-de-Jira-a-un-issue-de-GitHub)

  


## 1. Desarrollo de una tarea
El proceso habitual para realizar una tarea será, normalmente, el siguiente:

1. En **Jira** selecciona la tarea de la que seas responsable que deseas
desarrollar y lee bien la descripción de la misma y márcala en curso.
2. Desde la propia tarea le daremos a crear rama, el nombre de la rama tendrá el siguiente estilo: (nombre de la incidencia)-(branch-name).
3. Verifica que te encuentras en la rama `develop`. Si no es así, cámbiate a
esta rama y hacer un git checkout -b (nombre de la incidencia)-<branch-name>.
4. Haz *pull* de los últimos cambios (ver [sección 2](#2-control-de-versiones-git)).
5. Implementa la solución, incluyendo los tests (ver manual de instalación).
6. Haz un *commit* con cada parte estable (completa y testeada) que desarrolles.
7. Cada vez que hagas un *commit* envíalo al repositorio central **Github** para compartirlo con el resto del equipo (ver
   [sección 2](#2-control-de-versiones-git)).
8. Una vez terminada la tarea se deberá hacer una pull-request a `develop`, se deberá comprobar que ha pasado todos los actions de github correctamente.
9. Una vez que todo está correcto marcar la tarea de **Jira** como finalizada y ser mergeará la rama a develop desde **Github**.
10. Cuando acabes la jornada de trabajo recuerda introducir las horas en la tarea
de **Jira**.


En las siguientes secciones encontrarás información que te ayudará a realizar
este trabajo.

## 2. Control de versiones (Git)
El modelo de control de versiones que utilizaremos inicialmente será muy
sencillo ya que solo utilizaremos dos ramas:

* `master`: a esta rama solo se enviarán los *commits* cuando se llegue a una
versión estable y publicable (una *release*). Estas versiones deberán estar
etiquetadas con el número de versión correspondiente.Existe un action que se encargará de etiquetar la release
una vez se suba algún cambio a `master`.
* `develop`: esta será la rama principal de trabajo. A develop solo se podrán mergear otras ramas
mediante pull-request.
* `<número de la incidencia>-<branch-name>`: las ramas tendrán la siguiente estructura: nombre de la incidencia
seguido del nombre que le queramos poner a la rama. Estas ramas serán creadas a partir de la rama `develop`.
Este tipo de ramas admite cualquier tipo de *commit* (p.ej. código incompleto, código que no
compila, código sin tests, etc.) y, por tanto, no serán controladas por el
servidor de integración continua. Por último, una vez se mergea la rama se debe eliminar esta, esto se podrá
realizar directamente desde **Github** que nos ofrecerá está opción una vez mergeemos.


### 2.1. *Push* con *commits* nuevos en el servidor remoto
Si se desea hacer un *push* a **Github** en el cual hay *commits* que
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


### 2.2. *Pull* con cambios locales no *commiteados*
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
probado, incluyendo los tests , para que el
resto de desarrolladores puedan confiar en el código. Es muy recomendable
revisar los informes de tests y de cobertura antes de hacer un *commit*.
* **Formato**: el formato de los *commits* deberá respetar las siguientes
  normas:
  * Escritos en inglés.
  * Limitar el tamaño de línea a 80 columnas. Si se utiliza Eclipse, esto se
  hace de forma automática.
  * Primera línea descriptiva de lo que hace el *commit*:
    * Si está relacionado con alguna tarea concreta de las descritas en Jira,
    debe comenzar con el identificador de la tarea (p.ej. "XCS-1 Adds...").
    * Si está relacionado con varias tareas, su número se separará con un guión
    (p.ej. "XCS-1-2-13 Fixes...").
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
*push*.
* El propio **Github** mediante los actions se encargará de que si existen errores en las pull-request
no deje mergear la rama a develop.

### 3.3 Convenciones de Prefijos de Commits

Al contribuir a un proyecto, es común utilizar prefijos en los mensajes de commit para proporcionar una estructura clara y consistente en el historial de cambios. Estos prefijos ayudan a identificar rápidamente el propósito y el tipo de cambio realizado en un commit. A continuación se presentan algunos prefijos comunes y sus significados:

- **feat**: Se utiliza para nuevos desarrollos o características agregadas al proyecto.

  Ejemplo de mensaje de commit: `feat: Add user authentication functionality`

- **fix**: Indica la corrección de un error o un problema existente en el código.

  Ejemplo de mensaje de commit: `fix: Fix validation error in registration form`

- **docs**: Se utiliza para cambios o mejoras en la documentación del proyecto.

  Ejemplo de mensaje de commit: `docs: Update requirements section in README file`

- **style**: Indica cambios que no afectan la lógica del código, sino que mejoran su formato, estructura o estilo.

  Ejemplo de mensaje de commit: `style: Apply consistent code formatting using linter`

- **refactor**: Se utiliza cuando se realiza una modificación en el código que no soluciona un error ni agrega una nueva funcionalidad, pero mejora su estructura o rendimiento.

  Ejemplo de mensaje de commit: `refactor: Rearrange project directory structure`

- **test**: Indica cambios en las pruebas unitarias o en la configuración de las pruebas.

  Ejemplo de mensaje de commit: `test: Add unit tests for navigation component`

- **chore**: Se utiliza para cambios en tareas administrativas, actualizaciones de dependencias, ajustes de configuración u otras tareas generales.

  Ejemplo de mensaje de commit: `chore: Update project dependencies to the latest versions`


### 3.4 Vincular una tarea de Jira a un issue de GitHub

Para mantener un seguimiento y una trazabilidad claros entre el trabajo realizado en Jira y los cambios correspondientes en GitHub, es útil vincular una tarea de Jira a un issue específico en GitHub. Esto permite tener una conexión directa entre los elementos de trabajo en Jira y los cambios de código en GitHub. Aquí se explica cómo realizar esta vinculación:

1. **Crear una tarea en Jira**: En Jira, crea una tarea o identifica una tarea existente que esté relacionada con los cambios que planeas realizar en GitHub.

2. **Copiar la URL del issue en GitHub**: En GitHub, navega hasta el issue correspondiente o crea un nuevo issue para vincularlo con la tarea de Jira. Copia la URL del issue.

3. **Agregar la URL del issue en la descripción de la tarea en Jira**: En la tarea de Jira, agrega la URL del issue de GitHub en la descripción o en un comentario. Esto proporcionará un enlace directo a la discusión y los cambios en GitHub.

4. **Realizar los cambios en GitHub**: Utiliza la URL del issue en GitHub para acceder directamente al issue y realizar los cambios de código necesarios.

5. **Comunicar el progreso en Jira**: A medida que trabajes en los cambios en GitHub, es importante mantener actualizada la tarea de Jira. Comunica el progreso y los comentarios relevantes en la tarea, y menciona el issue de GitHub en los comentarios para facilitar la referencia cruzada.

6. **Vincular la rama creada en Jira a la issue**: Desde **Github** tendremos la opción en la issue de development donde podremos indicar la rama creada en **Jira**.
Con estos pasos, has vinculado una tarea de Jira a un issue específico en GitHub. Esto permite mantener una conexión clara y rastreable entre el trabajo en Jira y los cambios en GitHub, lo que facilita la colaboración y la trazabilidad en el desarrollo del proyecto.

