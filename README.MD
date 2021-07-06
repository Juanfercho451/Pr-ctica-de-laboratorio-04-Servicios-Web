
Se pide desarrollar una aplicación JEE usando JSF, EJB y JPA. La aplicación WEB permite realizar la facturación de una distribuidora de productos para el hogar. La distribuidora cuenta con varios productos que se encuentran almacenados en diferentes bodegas ubicadas geográficamente a lo largo del país.
El sistema estará divido en dos secciones, pública y privada: pruebas18

Sección publica:

El sistema debe permitir ver el catálogo de productos organizados por categorías y bodegas.


Para la parte publica usamos un bean que se encarga de mostrar la lista de productos en total que tiene la empresa, sumando los productos de las 3 bodegas y del inicio de sesión, también cuenta con un bean de navegación que se encarga de redirigir a las otras 3 páginas que un usuario puede ingresar y también de redirigir a la parte privada dependiendo del rol que tenga el usuario que inicio sesión.

Para los CSS del proyecto se utilizó una plantilla de Bootstrap aquí el enlace:
Now UI Dashboard: Free Bootstrap 4 Admin Template @ Creative Tim (creative-tim.com) 
  ![1](/READMEIMG/1.png?raw=true "Title")
En la parte izquierda se muestra la navegación entre bodegas y la página principal que muestra la cantidad total de productos entre todas estas. La navegación es la siguiente.
  ![2](/READMEIMG/2.png?raw=true "Title")
Aquí se hace uso del bean “navigator” este lo que hace es enviar una señal a dicho bean y ese envía una respuesta, esta respuesta se interpreta por el “faces-config.xml” y se redirecciona a la página correspondiente.
Este es el bean:
  ![3](/READMEIMG/3.png?raw=true "Title")

Y aquí está la regla de navegación:
  ![4](/READMEIMG/4.png?raw=true "Title")

Esto nos permite navegar entre las bodegas:

  ![5](/READMEIMG/5.png?raw=true "Title")

Para el listado de productos se utiliza el Bean Categoría, aquí se recibe una lista de todos los productos existentes, de todas las bodegas y se suman entre los productos con el mismo nombre, luego esto se guarda en una lista y finalmente dicha lista se manda a mostrar en la interfaz, Este es el código de la tabla.
  ![6](/READMEIMG/6.png?raw=true "Title")
El bean procesa la lista y la guarda en la variable listaArr.
  ![7](/READMEIMG/7.png?raw=true "Title")
Si el usuario hace uso de los filtros de categoría y la barra de búsqueda al momento que el usuario hace click al botón buscar, el bean recibe la categoría actualmente seleccionada y el texto de la caja, y con esto se realiza una búsqueda en la lista de productos cargados en la listaArr.
  ![8](/READMEIMG/8.png?raw=true "Title")

Otra funcionalidad que se muestra en la parte publica es la de iniciar sesión, aquí lo que hacemos es enviar dos Strings (Correo y contraseña) al Bean. El Bean lo que hará es una consulta de datos, donde ahí verificara si existe un usuario con esas credenciales, si existe según su rol, se redireccionara la página al menú de administrador o de empleado.
Método en el bean:
  ![9](/READMEIMG/9.png?raw=true "Title")

Consulta EJB: 
  ![10](/READMEIMG/10.png?raw=true "Title")

Este proceso es el mismo para el resto de páginas (Bodega 1, Bodega 2 y Bodega 3) 

Ahora vamos a iniciar sesión para acceder a la parte privada del proyecto, vamos a iniciar sesión como administrador. 
  ![11](/READMEIMG/11.png?raw=true "Title")

Ahora vamos con la explicación de la parte privada.


Sección privada:
El sistema debe permitir iniciar sesión y si tiene el rol de administrador puede:
Gestionar bodegas (consulta de inventario general)
  ![12](/READMEIMG/12.png?raw=true "Title")
Se observa el contenido de las diferentes bodegas resumido en una tabla de datos
Filtro para inventario General

Gestionar productos (aumentar stock por bodega)
Gestionar productos por bodega (consulta de inventario por bodega)
Gestión por bodega
Visualización de los productos de una de las bodegas en existencia
  ![13](/READMEIMG/13.png?raw=true "Title")
Modificación de Stock(Blue Labet)
Información que se registra en la base de datos
  ![14](/READMEIMG/14.png?raw=true "Title")
  ![15](/READMEIMG/15.png?raw=true "Title")
Información que se registra en la base de datos después del cambio
  ![16](/READMEIMG/16.png?raw=true "Title")
Eliminacion de Producto(Cubiertos)
Procedemos a eliminar el producto cubiertos
  ![17](/READMEIMG/17.png?raw=true "Title")
  ![18](/READMEIMG/18.png?raw=true "Title")
La información que se registra tanto en la página Web como en la base de datos.
  ![19](/READMEIMG/19.png?raw=true "Title")
  ![20](/READMEIMG/20.png?raw=true "Title")
Creación de producto(Prueba Prd)
Creacion de un nuevo producto
  ![21](/READMEIMG/21.png?raw=true "Title")
  ![22](/READMEIMG/22.png?raw=true "Title")
Verificación de la creación del nuevo producto con su categoria correspondiente
CODIGO:
Controlador: BodegaBean2.java
  ![23](/READMEIMG/23.png?raw=true "Title")
Se importan todas las dependencias necesarias para el funcionamiento de EJB. Se configura la versión a utilizar de JSF, asi como se configuran el alcance del Bean.
Se utilizarán las fachadas de: Bodega, Categorias y Producto
Se declaran una serie de atributos de la clase que serán utilizados dentro del Bean, para el funcionamiento de la agregación y la visualización de los resultados de este.
  ![24](/READMEIMG/24.png?raw=true "Title")
Se implementan los métodos Setter y Getter para el manejo de los atributos, además de otras funciones que servirán para múltiples actividades como: Crear, modificar y eliminar productos.
  ![25](/READMEIMG/25.png?raw=true "Title")
  ![26](/READMEIMG/26.png?raw=true "Title")
Pagina: GestionarBodegas.xhtml
  ![27](/READMEIMG/27.png?raw=true "Title")
En la página actual se observa que mediante dos tablas de datos, utilizando se para sus filtros Prime Faces, se visualizan los datos acerca de las bodegas y los productos pertenecientes a cada una de estas.
  ![28](/READMEIMG/28.png?raw=true "Title")
Form dedicado para el ingreso de la información de un nuevo producto a crearse dentro de una de la bodega que se visualiza en la página.
Pagina: informeGeneral.xhtml
  ![29](/READMEIMG/29.png?raw=true "Title")
Se visualizan los datos de todas las bodegas de manera resumida.

El sistema debe permitir iniciar sesión y si tiene el rol de empleado puede:
o Gestionar clientes
o Gestionar facturas (disminuir stock, anulación de facturas, búsqueda de facturas)


Para la parte del empleado, al iniciar sesión con este, lo que se muestra es una pantalla en blanco, y un menú de navegación, en este menú estarán disponibles todas las herramientas de gestión para el empelado.
  ![30](/READMEIMG/30.png?raw=true "Title")
Este es el código de nuestra barra de navegación, este código estará presente en toda la sección privada del empleado.
  ![31](/READMEIMG/31.png?raw=true "Title")
  ![32](/READMEIMG/32.png?raw=true "Title")

La primera opción que encontramos es la de Crear Facturas:
  ![33](/READMEIMG/33.png?raw=true "Title")
Al hacer click en esta se cargan 2 formularios, es el primero se mostrará una lista de todos los productos disponibles de la empresa ofrece una barra de búsqueda y una sección para ingresar la cantidad a comprar si se pide más de lo que hay en stock el pedido no se añadirá a la factura, el segundo carga los datos del cliente al que se le va a cobrar por medio de una búsqueda por cedula, una vez se cargan los datos se puede realizar la factura.
  ![34](/READMEIMG/34.png?raw=true "Title")

El primer formulario busca la lista de productos usando el Bean llamado “productoBean” al momento de realizar una búsqueda se hace uso de una función dentro de este bean llamada “buscarPorNombre”
  ![35](/READMEIMG/35.png?raw=true "Title")
Este es el método llamado del bean que se utiliza para esto 
  ![36](/READMEIMG/36.png?raw=true "Title")
Al momento de irse listando los productos se crean entidades llamadas “pedido” las cuales solo necesitan de una cantidad y la confirmación dada por una función llamada “addDetallesFac(ped)” dentro del bean para que estas se añadan a la factura. El momento en que estos pedidos se crean automáticamente se actualiza el display del segundo formulario haciendo que se muestren los pedidos realizado en este.
  ![37](/READMEIMG/37.png?raw=true "Title")
Este formulario lo que hace es actualizarse con la lista de pedidos creados y mostar los datos como: Subtotal, IVA, y total, estos se calculan el en Bean Factura Detalle.
  ![38](/READMEIMG/38.png?raw=true "Title")
Este es el código del segundo formulario, lo que hace este es llamar a un bean que le permite buscar por cedula a un cliente, una vez encontrado lo que se hace es mostrar los datos de dicho cliente.
  ![39](/READMEIMG/39.png?raw=true "Title")
El bean llamado “personaBean” es el que ejecuta esta función la cual hace uso de una sentencia SQL lo que permite localizar dicha persona.
  ![40](/READMEIMG/40.png?raw=true "Title")
  ![41](/READMEIMG/41.png?raw=true "Title")

Cuando exista la presencia de productos y de una persona, se puede proceder a la facturacion, para poder llevar a cabo el proceso de facturacion se llama a la funcion “guardarFactura” dentro del Bean “FacturaDetalleBean” 
  ![42](/READMEIMG/42.png?raw=true "Title")
Lo que se hace aquí es recoger los pedidos y la persona, asignar dichos pedidos a una factura cabecera y está a una persona, y finalmente guardar. 
Al hacer click sobre esta opción, la página se actualizará.

La siguiente herramienta de gestión es la de listar facturas.
  ![43](/READMEIMG/43.png?raw=true "Title")

El momento en el que se ingresa a la página se muestra una lista que contendrá todas facturas realizadas por la empresa, cuenta con una barra de búsqueda por cedula para así referirse a una persona en específico, 
Cada factura cuenta con 2 opciones, la de ver sus detalles o de cancelar la factura, si se escoge “ver” la segunda tabla mostrara los detalles de dicha factura, si se selecciona “Cancelar” la factura de cancelar.
  ![44](/READMEIMG/44.png?raw=true "Title")

La primera tabla está conformada por 2 formularios, el primero se encarga de la búsqueda por cedula del cliente, es segundo se encarga de mostrar las facturas.
  ![45](/READMEIMG/45.png?raw=true "Title")
Por defecto, al no tener objetivo de búsqueda, lo que hace la tabla es recibir todas las facturas, al buscar por una cedula, la lista de facturas se actualiza con las facturas de solo esa persona y se vuelve a mostrar esa lista en la tabla. Esto lo lleva a cabo el Bean llamado “facturaCabeceraBean”.

  ![46](/READMEIMG/46.png?raw=true "Title")
El momento que se desea cancelar una factura, lo que hace el bean es establecer el estado de esta factora como “C” y listo.
Cuando se desea ver los detalles de la factura, lo que se hace es usar el método llamado “sacarDetalles”, este lo que hace es obtener los detalles de dicha factura y guardarlos para que la tabla de detalles use esa información para mostrarla.
 
 ![47](/READMEIMG/47.png?raw=true "Title")
Continuando con el resto de herramientas, ahora mostraremos el Crear Clientes, aquí el empleado podrá crear clientes para poder registrarlos en el sistema y realizar facturas a sus nombres.

El momento que se ingresa a esta opción, un formulario carga, aquí se debe ingresar la información del cliente.

  ![48](/READMEIMG/48.png?raw=true "Title")
Al agregar cliente este queda guardado y la pagina se actualiza.
Aquí se hace uso de un único formulario, aquí se ingresa la información y al aceptarla, se guarda, esto se llva a cabo por el bean llamado “personaBean”.
  ![49](/READMEIMG/49.png?raw=true "Title")
  ![50](/READMEIMG/50.png?raw=true "Title")
Este es el código del Bean
  ![51](/READMEIMG/51.png?raw=true "Title")

Por último, tenemos la opción de listar clientes, lo que se hace aquí es listar todos los clientes registrados en la empresa, cuenta con buscador por cedula y el registro de clientes cuenta con 2 opciones, la de editar y eliminar. 

Si se quiere eliminar un cliente, simplemente se aplasta el botón “Eliminar” y se cambia el estado de dicho cliente a eliminado. 

En cambio, si se quiere editar un cliente, primero se debe pulsar el botón de editar de la lista y el formulario de la derecha se actualizará con la información del cliente, aquí se podrá editar a gusto y una vez finalizado los cambios estos se guardan y actualizan a pulsar el botón “Modificar cliente”.

  ![52](/READMEIMG/52.png?raw=true "Title")
Esta página se compone de 2 formularios el primero muestra la información de los clientes y el segundo muestra la información del cliente seleccionado.

  ![53](/READMEIMG/53.png?raw=true "Title")
  ![54](/READMEIMG/54.png?raw=true "Title")
  ![55](/READMEIMG/55.png?raw=true "Title")
Cuando los cambios se realizan al usar el método “editarPersona” los datos se guardan directamente en la base de datos.
  ![56](/READMEIMG/56.png?raw=true "Title")

Sesiones
  ![57](/READMEIMG/57.png?raw=true "Title")
En el archivo del controlador del inicio y salida de sesiones, encontramos dos métodos uno para la salida y otro para la salida de sesión, en cada uno utilizamos los FacesContext para poder utilizar los parámetros del sesiónmap dando como parámetro el número de acceso a la página para el caso del inicio de sesión y para el caso de la salida de sesión se pone un parámetro nulo como indicador de que no existe una sesión iniciada.
 
Filtros
  ![58](/READMEIMG/58.png?raw=true "Title")
  ![59](/READMEIMG/59.png?raw=true "Title")

Se inician HttpServletRequest para poder obtener un HttpSession y comparar si nuestro parámetro antes impuesto en la parte del inicio de sesión es nulo o no en el caso de ser nulo se redirigirá a la parte de la página inicial y en el caso de ser diferente de nulo se podrá seguir a la página que se desea ya que esto es un indicador de que se encuentra una sesión iniciada.

Página de error 404

  ![60](/READMEIMG/60.png?raw=true "Title")

Para utilizar una página de error en el proyecto se añade esta pieza de código en el archivo web.xml
  ![61](/READMEIMG/61.png?raw=true "Title")


Github
Repositorio: Charly1590/Practica-de-laboratorio-03-EJB-JSF-y-JPA (github.com)
Nombre de Usuario: Charly1590

