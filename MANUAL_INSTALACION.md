# Manual de instalación

## Tabla de contenido
* [1. Configuración de un entorno de desarrollo](#1-configuración-de-un-entorno-de-desarrollo)
    * [1.1. MySQL](#11-mysql)
        * [1.1.1. Instalación con apt (Ubuntu)](#111-instalación-con-apt-ubuntu)
        * [1.1.2 Instalación con Docker(Linux/Windows)](#112-instalación-con-docker(linux/windows))
    * [1.2. WildFly](#12-wildfly)
        * [1.2.1. Ejecución en un WildFly local](#121-ejecución-en-un-wildfly-local)
        * [1.2.2. Ejecución en un WildFly con Maven](#122-ejecución-en-un-wildfly-con-maven)
        * [1.2.3. Cambios que afecten a la configuración de WildFly](#123-cambios-que-afecten-a-la-configuración-de-wildfly)
* [2. Tests](#2-tests)
    * [2.1 Tests por módulo](#21-tests-por-módulo)
    * [2.2 El módulo tests](#22-el-módulo-tests)
    * [2.3 Ejecución de los tests](#23-ejecución-de-los-tests)
        * [2.3.1 Ejecución de los tests en Maven](#231-ejecución-de-los-tests-en-maven)
        * [2.3.2 Ejecución de los tests en Eclipse](#232-ejecución-de-los-tests-en-eclipse)
        * [2.3.3 Ejecución de los tests en IntelliJ](#233-ejecución-de-los-tests-en-intelliJ)
    * [2.4 Análisis de los resultados de los tests](#24-análisis-de-los-resultados-de-los-tests)

  
## 1. Configuración de un entorno de desarrollo
Empezar a trabajar en el proyecto es tan sencillo como seguir los siguientes
pasos:

1. Instala Git y Maven. Si estás en un entorno Ubuntu es tan sencillo como
   ejecutar `sudo apt-get install git maven`. Si estás usando Windows es tan fácil como 
   seguir el siguiente tutorial http://codigoelectronica.com/blog/instalar-maven-en-windows
   También es recomendable que instales algún visor de Git como `gitk` o `qgit`.
2. Clona el repositorio Git utilizando el comando:
   `git clone https://github.com/ncgomez17/ProyectoXCS.git`
3. Instala Eclipse for Java EE (opcional pero recomendado) o puedes usar también el IDE IntelliJ IDEA:
4. Descarga el IDE desde https://www.eclipse.org/downloads/eclipse-packages/
   o si quieres el IntelliJ https://www.jetbrains.com/es-es/idea/download/
5. Importa el proyecto en Eclipse utilizando `Import...->Existing Maven
   projects`, selecciona el directorio del proyecto root y marca
   todos los proyectos que aparezcan. Con IntelliJ sería utilizando `Open` indicamos la ruta
   principal de nuestro proyecto y le damos a OK.

En la [sección 2.3.2](#232-ejecución-de-los-tests-en-eclipse) aparece detallada la
configuración necesaria para ejecutar los tests desde Eclipse.

En la [sección 2.3.3](#233-ejecución-de-los-tests-en-intelliJ) aparece detallada la
configuración necesaria para ejecutar los tests desde IntelliJ.

Con esto ya sería suficiente para poder empezar a trabajar en el proyecto. Si,
además, quieres poder ejecutarlo de forma local, deberás seguir las siguientes
instrucciones.

### 1.1 MySQL
Cuando se ejecute la aplicación en local se utilizará una base de datos MySQL
para almacenar los datos y que, de este modo, sean persistentes, tal y como
ocurriría en una ejecución real. Además, aunque los tests se ejecutan por
defecto utilizando una base de datos en memoria, también es posible ejecutarlos
utilizando una base de datos MySQL.

Este proyecto está configurado para funcionar con una base de datos MySQL 5+.
Dependiendo de la distribución que estés utilizando, la versión de MySQL que
se instalará podrá variar entre una 5+ o una 8+, por lo que, a continuación se
explicará como instalar MySQL utilizando `apt` y utilizando Docker, para así
poder aplicar el método que resulte más adecuado para cada situación.

#### 1.1.1 Instalación con apt (Ubuntu)
La instalación con `apt` es tan sencilla como hacer: `sudo apt install mysql`

Cuando ejecutes esto se mostrará información sobre la versión concreta de MySQL
que se instalará y podrás decidir si contiuar o no.

Puedes consultar otras alternativas ejecutando:
`sudo apt-cache search mysql-server`

Este comando te mostrará una lista de paquetes con `mysql-server` en su nombre,
con lo que podrás saber qué versiones están disponibles.

Una vez instalado MySQL debes importar la base de datos. En el directorio `db`
del proyecto `additional-material` están los  almacenados los *scripts* de
creación de la base de datos (inicialmente solo habrá uno). El *script*
`microstories-mysql.full.sql` contiene la creación completa de la base de
datos, incluyendo la creación del esquema y del usuario usado por la
aplicación. Por lo tanto, si estás en un sistema Ubuntu, puedes realizar la
importación desde la raíz del proyecto con el siguiente comando:
```bash
mysql -u root -p < additional-material/db/microstories-mysql.full.sql
```

Dependiendo de la versión de Ubuntu, es posible que, en lugar de este comando, debas ejecutar:

```bash
sudo mysql < additional-material/db/microstories-mysql.full.sql
```



#### 1.1.2 Instalación con Docker(Linux/Windows)

En el caso de que no dispongas de una versión 5+ de MySQL para instalar, que ya
tengas una versión instalada o que prefieras no instalarla directamente en tu
equipo, podrás utilizar la instalación con Docker.

En primer lugar, deberás instalar Docker siguiendo los pasos del
[manual](https://docs.docker.com/get-docker/).

Una vez lo hayas instalado, podrás ejecutar un contenedor con MySQL 5.7
utilizando el siguiente comando:
```
docker run -d --name microstories-mysql -e MYSQL_ROOT_PASSWORD=dgsspass -v $PWD/mysql:/var/lib/mysql -v $PWD/additional-material/db/microstories-mysql.full.sql:/docker-entrypoint-initdb.d/microstories-mysql.full.sql -p 3306:3306 mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

Este comando creará un contenedor llamado `microstories-mysql` con un MySQL 5.7
y, además, se encargará de ejecutar el *script*
`additional-material/db/microstories-mysql.full.sql` durante el primer
arranque, con lo que no será necesario que lo importes manualmente.

Este contenedor almacenará la base de datos en el directorio `mysql` del
proyecto para que sea persistente y no se pierda si se elimina el contenedor.
El proyecto está configurado para que este directorio no se envíe al sistema de
control de versiones.

Podrás controlar el ciclo de vida de este contenedor con los siguientes comandos:
* Iniciar el contenedor (necesario al reiniciar el equipo):
  `docker start microstories-mysql`
* Detener el contenedor:
  `docker stop microstories-mysql`
* Acceder a MySQL como `root` (password: `dgsspass`):
  `docker exec -it microstories-mysql mysql -uroot -p`
* Acceder a MySQL como `microstories` (usuario utilizado por la aplicación con password `microstoriespass`):
  `docker exec -it microstories-mysql mysql -umicrostories -p microstoriespass`

### 1.2. WildFly
El proyecto está configurado y preparado para ser ejecutado en un servidor
WildFly 10.x (preferiblemente 10.1.0) o 8.x (preferiblemente 8.2.1). Sin
embargo, en el caso concreto de la ejecución de los tests y de la ejecución del
servidor directamente desde Maven, se utilizará la versión 10.1.0 de WildFly.

**Importante**: para evitar conflictos entre la ejecución de WildFly en los
tests y la ejecución del WildFly local o de pre-producción, estos primeros están
configurados para usar un *port-offset* de 20000. Esto significa que al número de
los puertos por defecto habrá que sumarles 20000. Por ejemplo, el puerto HTTP
será el puerto 28080 en lugar del 8080 habitual.

En cualquiera de las siguientes configuraciones, la aplicación estará disponible en:
* JSF: http://localhost:8080/microstories-2223-teamA/jsf
* REST: http://localhost:8080/microstories-2223-teamA/rest


#### 1.2.1. Ejecución en un WildFly local
La ejecución del proyecto en un WildFly local requiere la instalación del propio
servidor, de un SGBD MySQL y la configuración del servidor WildFly.

Por lo tanto, para configurar un WildFly local debes instalar WildFly como
servidor local. Para ello, debes descargar una versión
compatible de http://wildfly.org/downloads/ y descomprimirla en un directorio
local.

A continuación, sustituye el fichero `standalone/configuration/standalone.xml`
por el fichero `standalone.xml` correspondiente de los disponibles en el
directorio del proyecto `additional-material/wildfly`.

Para finalizar, despliega el *driver* MySQL en el servidor WildFly. Puedes
descargar el *driver* manualmente desde el [repositorio de Maven](https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.27/mysql-connector-java-8.0.27.jar).
Debes guardar este fichero en el directorio `standalone/deployments` del
servidor WildFly.

Con esto ya estaría configurado el WildFly local y ejecutarlo es tan sencillo
como invocar los siguientes comandos desde el directorio de WildFly:

```
[Linux] bin/standalone.sh
[Windows] bin\standalone.bat
```

Una vez arrancado el proyecto podemos desplegar el fichero EAR generado en el
módulo `ear` al realizar la construcción. Existen dos formas de hacer esto:
* **Opción A**: copiar el fichero EAR directamente al directorio
  `standalone/deployments` del servidor WildFly. El servidor debería reconocer
  inmediatamente la aplicación e iniciar el despliegue.
* **Opción B**: acceder a la interfaz de gestión del servidor, que debería
  estar en http://localhost:29990, e irse al panel "Deployments". En este panel
  tendremos la posibilidad de desplegar el fichero EAR.
  Para poder realizar esta opción, es necesario añadir un usuario administrador
  al servidor, para lo cual se debe invocar el siguiente comando desde el
  directorio de WildFly:

```
[Linux] bin/add-user.sh
[Windows] bin\add-user.bat
```

#### 1.2.2. Ejecución en un WildFly con Maven
El POM de este proyecto se ha configurado para que sea posible construir y
ejecutar la aplicación sin más dependencia externa que la base de datos MySQL.

Para ello, debe haberse configurado la base de datos correctamente (ver punto
anterior) y ejecutar el comando Maven:

```bash
mvn install -P wildfly-mysql-run,-wildfly-embedded-h2
```

Para detener la ejecución debe utilizarse el comando:

```bash
mvn wildfly:shutdown
```

Adicionalmente, en el caso de querer redesplegar la aplicación, puede usarse el
comando:

```bash
mvn wildfly:shutdown install -P wildfly-mysql-run,-wildfly-embedded-h2
```

#### 1.2.3 Cambios que afecten a la configuración de WildFly
Los cambios en la configuración del servidor WildFly afectan en varios niveles
al proyecto.

Si, por ejemplo, queremos añadir algún elemento a la configuración de los
servidores WildFly (fichero `standalone.xml`), entonces este cambio afectará a:
* Configuración para los servidores locales que se almacena en
  `additional-material/wildfly/v10.1.0/standalone.xml` y
  `additional-material/wildfly/v8.2.1/standalone.xml`.
* Configuración de los servidores de test que se almacena en los directorios
  `src/test/resources-wildfly-embedded-h2` y `src/test/resources-wildfly-embedded-mysql`.

Otro caso sería que fuese necesario desplegar algún artefacto adicional en el
servidor. En este caso el cambio afectaría a:
* **Servidor local**. Habría que desplegar este nuevo artefacto en él.
* **Servidores de test**. Es probable que solo sea necesario modificar el
  fichero POM del proyecto padre para añadir el recurso del mismo modo que se
  añade el *driver* de MySQL en el perfil `wildfly-embedded-mysql`.
## 2. Tests
Lo primero que se debe tener en cuenta a la hora de realizar tests es la
existencia del módulo `tests`. Este proyecto está pensado para recoger las
clases de utilidad que puedan ser compartidas por los tests de los distintos
módulos que forman el proyecto. Por lo tanto, siempre que exista una clase o
fichero que sea compartido por varios proyectos, debería almacenarse en este
módulo.

En segundo lugar, es importante ser consciente de que, dependiendo del módulo en
el que nos encontremos, deberemos hacer diferentes tipos de test.

Por último, como norma general, los métodos de prueba deben ser **lo más
sencillos posible**, de modo que sea sencillo comprender qué es lo que se está
evaluando. En base a esta regla, no añadiremos documentación Javadoc a los
métodos de prueba (esto no se aplica a las clases de utilidad del módulo
`tests`, que sí que deben estar documentadas con Javadoc).

A continuación se detalla el proceso de realización de tests.

### 2.1 Tests por módulo
Los tests que se deben hacer varían según el módulo en el que nos encontremos.
En concreto, los tests que habrá que hacer serán los siguientes:

* `domain`: tests de unidad para probar las entidades. Solo se testearán los
  constructores y los métodos con una cierta lógica, como pueden ser los métodos
  de las relaciones (p.ej. `getOnwer()`, `getPets()`, etc.). No será necesario
  testear los constructores vacíos definidos para JPA.
* `service`: tests de integración con Arquillian y la extensión Persistence.
  Además de la lógica, deben testear la seguridad.
* `rest`: tests de integración con Arquillian y las extensiones Persistence y
  REST Client. **Opcionalmente**, pueden hacerse los tests de unidad con
  EasyMock.
* `jsf`: no es obligatorio hacer ningún tipo de tests. **Opcionalmente**,
  pueden hacerse tests funcionales con Arquillian y las extensiones Persistence,
  Drone y Graphene 2.

### 2.2 El módulo tests
En el módulo `tests` se añadirán varias utilidades para realizar los tests,
entre las que encontraremos, principalmente, tres tipos distintos:

* **_Test Doubles_**: clases que sustituyan a otras implementando una lógica que
  sea útil para los tests. Por ejemplo, la clase `TestPrincipal` permite sustituir
  el `Principal` de la aplicación para poder modificar el usuario que ejecuta los
  tests.
* **Clases `Datasets`**: estas clases representan un conjunto de datos de
  pruebas. Contienen métodos para obtener a entidades que resultan de utilidad en
  los tests. Estas clases deben ubicarse en el mismo paquete que las entidades que
  contienen. El contenido de estas clases debe ser equivalente al contenido de los
  *datasets* de DBUnit que se describe a continuación.
* **_Datasets_ DBUnit**: los `datasets` DBUnit son representaciones en forma de
  XML de conjuntos de datos usados en los tests y pueden ser utilizados
  directamente por Arquillian con las anotaciones `@UsingDataSet` y
  `@ShouldMatchDataSet`. El contenido de estos ficheros debe ser el equivalente al
  de las clases *dataset*. Estos ficheros deben almacenarse en el directorio
  `src/main/resources/datasets`.
* **_Matchers_ Hamcrest para entidades**: cada entidad debería tener un
  *matcher* de Hamcrest que permita compararla con otras entidades. Para facilitar
  el desarrollo de estos *matchers* se incluye la clase `IsEqualsToEntity`, que
  actúa como clase base para comparar dos entidades por sus propiedades.

### 2.3 Ejecución de los tests
#### 2.3.1 Ejecución de los tests en Maven
Este proyecto está configurado para ejecutar, únicamente, los tests de aquellas
clases cuyo nombre acabe en `TestSuite`. La intención es que estas clases sean
*suites* de tests que agrupen los casos de prueba del proyecto. Por lo tanto,
**es importante que todos los casos de prueba que se deseen ejecutar en el
proyecto estén asociados a una _suite_ de pruebas**.

Todos los tests del proyecto están configurados para ser ejecutados como tests
normales y no como tests de integración. Esto significa que se pueden lanzar
todos simplemente ejecutando el comando:

```
mvn test
```

#### 2.3.2 Ejecución de los tests en Eclipse
La ejecución de los test con Arquillian desde Eclipse, necesita de una pequeña
configuración adicional en las configuraciones de ejecución para que incluyan
los siguientes parámetros como propiedades del sistema:

```
-Darquillian.launch=wildfly-embedded
-Dwildfly.version=10.1.0.Final
-Dwildfly.jbossHome=target/wildfly-10.1.0.Final
-Dwildfly.modulePath=target/wildfly-10.1.0.Final/modules
-Djava.util.logging.manager=org.jboss.logmanager.LogManager
-Djboss.socket.binding.port-offset=20000
-Dwildfly.http.port=28080
-Dwildfly.management.port=29990
```

Si, además, queremos ejecutar los tests utilizando el perfil de MySQL, debemos
añadir las propiedades del sistema:

```
-Dmysql.version=8.0.27
-Darquillian.launch=wildfly-embedded-mysql
```

Por último, aunque los tests para el módulo JSF están desactivados por defecto,
si se desean utilizar serán necesario descargar el *driver* Gecko
([enlace](https://github.com/mozilla/geckodriver/releases)) y añadir el siguiente parámetro con el que se indica la ubicación del mismo en el sistema:

```
-Dwebdriver.gecko.driver=<ruta al driver nativo>
```

Estos parámetros pueden establecerse en el diálogo `Run->Run Configurations...`,
donde seleccionaremos la configuración de ejecución o crearemos una nueva. En
el panel de configuración de la configuración de ejecución debemos seleccionar
la pestaña `Arguments` e introducir estos parámetros en el campo `VM Arguments`.

#### 2.3.3 Ejecución de los tests en IntelliJ
La ejecución de los test con Arquillian desde IntelliJ, solo necesitan de 
una configuracion de lanzamiento específica que podemos conseguir mediante la
interfaz gráfica de este IDE:

Para ejecutar los test tenemos que ir->`EditConfigurations->Add New Configuration`,
seleccionamos Maven y luego en el run indicamos test.
Si queremos añadir las configuraciones para arquillian solo tenemos que añadir las 
siguientes lineas en el método run:

```
-Darquillian.launch=wildfly-embedded
-Dwildfly.version=10.1.0.Final
-Dwildfly.jbossHome=target/wildfly-10.1.0.Final
-Dwildfly.modulePath=target/wildfly-10.1.0.Final/modules
-Djava.util.logging.manager=org.jboss.logmanager.LogManager
-Djboss.socket.binding.port-offset=20000
-Dwildfly.http.port=28080
-Dwildfly.management.port=29990
```

Si, además, queremos ejecutar los tests utilizando el perfil de MySQL, debemos
añadir las propiedades del sistema:

```
-Dmysql.version=8.0.27
-Darquillian.launch=wildfly-embedded-mysql
```

Por último, aunque los tests para el módulo JSF están desactivados por defecto,
si se desean utilizar serán necesario descargar el *driver* Gecko
([enlace](https://github.com/mozilla/geckodriver/releases)) y añadir el siguiente parámetro con el que se indica la ubicación del mismo en el sistema:

```
-Dwebdriver.gecko.driver=<ruta al driver nativo>
```

### 2.4 Análisis de los resultados de los tests
Cada vez que se ejecutan los tests se generarán varios ficheros con información
sobre los resultados. Concretamente, se generarán dos tipos de informes:
* **JUnit**: genera informes sobre el éxito o fracaso de los tests. Estos informes
  se almacenan en `<module>/target/surefire-reports`. Son ficheros XML que pueden
  abrirse con Eclipse.
* **JaCoCo**: genera informes sobre la cobertura de los tests. Estos informes se
  almacenan en `<module>/target/site/jacoco/index.html`.

Si realizamos la ejecución desde Eclipse, la misma información que muestran los
informes de JUnit nos aparecerá directamente en la vista de JUnit.