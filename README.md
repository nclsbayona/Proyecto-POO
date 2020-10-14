# Project P.O.O 2020-3

Pontificia Universidad Javeriana Bogotá D.C 

## About the Control System of an Art Gallery
**El Sistema**

1. Se requiere hacer un programa orientado a objetos que funcionará en una galería de arte.

2. La galería tiene un catálogo de obras artísticas disponibles para la venta. Cuando se compra una obra, esta se le reserva
al cliente y se paga en la galería y hay que registrarlo en el sistema.

3. El sistema controla la información de las obras disponibles, la información de los clientes que realizan una compra, y
la información de la compra en si. El pago se realiza físicamente en la galería pero en el sistema debe quedar
registrado.

4. En el diagrama de clases UML que se muestra a continuación se observan las clases que intervienen en el problema:

 ![UML](https://i.ibb.co/4NFhFCJ/image.png)

**Menu Funcional:**

1. Ver listado de Obras disponibles
2. Buscar una Obra por título, artista o año
3. Insertar una Obra
4. Modificar una Obra
5. Eliminar una Obra
6. Ver listado de Clientes registrados en el sistema
7. Buscar un Cliente
8. Insertar Cliente
9. Modificar datos de Cliente
10. Eliminar un Cliente
11. Realizar compra de una Obra
12. Eliminar compra de obra
13. Ver listado de Compras existentes
14. Ver listado de Compras para un mes y año específico insertado por el usuario
15. Ver listado de Artistas más vendidos
16. Salir

**Funcionalidades:**

Para este proyecto se solicita implementar las siguientes funcionalidades:

**1.** [5] Al iniciar el día se debe:

**a.** Crear la colección de Obras llamada listaObras (método en el controlador ‘GestionObras’ que crea instancias de Obra y llena la lista de obras de la clase ControlGaleria. 

Esta clase sólo existe para no tener que ingresar datos por pantalla de las obras y facilitar la prueba del programa. No obstante, las funcionalidades para insertar, modificar y eliminar una obra deben programarse y funcionar correctamente.

**b.** La clase GestiónObras también puede llenar la lista de Artistas.

**c.** Crear la colección de clientes llamada listaClientes (método en el controlador ‘GestionCliente’ que crea instancias de Cliente y llena la lista de clientes de la clase  ControlGaleria. Esta clase sólo existe para no tener que ingresar datos por pantalla de clientes y facilitar la prueba del programa. No obstante, las funcionalidades para insertar, modificar y eliminar un cliente deben programarse.

**d.** La clase ControlGaleria debe invocar en su constructor los métodos anteriores.

**NOTA: Para los métodos anteriores los objetos se pueden crear con valores literales; en la siguiente entrega esto se cambiará por un acceso a archivos del sistema.**

**2.** [5] Ver listado de Obras disponibles

**a.** Se debe mostrar un listado por pantalla que muestra la información básica de una obra que son título, fecha de creación, precio de referencia, una foto (la foto será para la entrega 3) y sus dimensiones. Solo deben salir las Obras que están disponibles para la Compra.

**3.** [5] Buscar una Obra por título, artista o año

**a.** Se debe pedir el criterio por el cual se desea Buscar, insertar el criterio de búsqueda y mostrar las Obras que cumplan con el criterio de búsqueda.

**4.** [5] Insertar una Obra:

**a.** Se debe permitir insertar una nueva obra en la lista de obras de la clase ControlGaleria. Para ello debe apoyarse
en los métodos necesarios de la clase Obra.

**b.** Debe listar los artistas existentes (Cedula y NombreyApellidos) y si no está debe permitir insertar un nuevo artista. De un artista se guarda su cédula, nombre y apellidos, fecha de nacimiento y teléfono.

**c.** No se puede crear una obra con el mismo código de una que ya exista

**d.** El código de una obra tiene 7 números. Debe validar que nunca exista una obra con más o menos números.

**5.** [5] Modificar una Obra:

**a.** Se debe solicitar el código de la obra a modificar, si este no existe, se debe mostrar un mensaje y volver al menú
principal.

**b**. Si existe, se deben mostrar los datos de la obra, enumerados, y solicitar el dato que se quiere modificar.

**c.** No se puede asignar un código de una obra que ya exista, si esto ocurre se muestra un mensaje y no se modifica
la obra.

**6.** [5] Eliminar una Obra:

**a.** Solicitar el código de la obra a eliminar, si este no existe, se debe mostrar un mensaje y volver al menú principal.

**b.** No se puede permitir eliminar una obra que esté asociado a una compra.

**c.** Se debe mostrar un mensaje de confirmación para eliminar la obra.

**7.** [5] Ver listado de Clientes registrados en el sistema:

**a.** Se debe mostrar un listado por pantalla que muestra la información de un cliente. Su cédula, nombre completo,
dirección y teléfono.

**8.** [5] Insertar Cliente:

**a.** Se debe permitir insertar un nuevo cliente en la lista de clientes de la clase ControlGaleria. Para ello debe apoyarse en los métodos necesarios de la clase Cliente.

**b.** No se puede crear un cliente con el mismo código de identificación de uno que ya exista.

**9.** [10] Modificar datos de Cliente:

**a.** Se debe solicitar el código de identificación del cliente a modificar, si este no existe, se debe mostrar un mensaje y volver al menú principal.                      

**d.** Si existe, se deben mostrar los datos del cliente, enumerados, y solicitar el dato que se quiere modificar.

**a.** No se puede asignar un número de identificación de un cliente que ya exista, si esto ocurre se muestra un mensaje y no se modifica el cliente.

**10.** [5] Eliminar un cliente:

**a.** Solicitar el número de identificación del cliente a eliminar, si este no existe, se debe mostrar un mensaje y volver al menú principal.

**d.** No se puede permitir eliminar un cliente que esté asociado a una compra.

**a.** Se debe mostrar un mensaje de confirmación para eliminar el cliente.

**11.** [20] Realizar compra de una Obra

**a.** Para comprar una obra ya deben existir la obra y el cliente, estos datos se van a solicitar usando los códigos de ambos. Se debe autogenerar un código de compra que no exista previamente.

**b.** Se debe solicitar toda la información de la compra y validar que no exista una compra de ese cliente para esa obra. Si es así mostrar mensaje de error.

**12.** [5] Eliminar compra de obra

**a.** Solicitar el número de compra a eliminar, si este no existe, se debe mostrar un mensaje y volver al menú principal.

**b.** Se debe mostrar un mensaje de confirmación para eliminar la compra.

**13.** [5] Ver listado de Compras existentes:

**a.** Se debe mostrar una lista de todas las Obras que han sido compradas, cliente que la compró, fecha y precio.

**14.** [5] Ver listado de Compras para un mes y año específico insertado por el usuario

**a.** Se debe solicitar mes y año al usuario y mostrar listado de Obras que hayan sido compradas, cliente que la compró, fecha y precio.

**15.** [5] Ver listado de Artistas más vendidos

**a.** Mostrar los artistas más vendidos ordenados de mayor a menor ventas

**16.** Se debe crear en el main de la clase ‘PantallaGaleria’ métodos que permitan invocar e imprimir el resultado (valor de retorno) de cada una de las funcionalidades del controlador de ‘ControlGaleria’, usted debe:

**a.** proveer de manera literal los valores de prueba

**b.** solicitar por pantalla los valores:

**i.** aquí se requiere de un menú interactivo para cada opción.

**NOTA: debe probar todos los caminos de negocio.**

:trident: :trident: :trident: :trident: :trident: :trident: :trident: :trident: :trident: :trident:
# Funcionalidades de la Entrega 2
**1** .[15] Para esta entrega se deben ajustar las funcionalidades implementadas en la entrega 1 para ajustarlas a las nuevas condiciones especificadas en el diagrama de clases: 

    a)Ver listado de Obras disponibles
    b)Buscar una Obra por título,artista o año
    c)Insertar una Obra
    d)Modificar una Obra
    e)Eliminar una Obra
    f)Ver listado de Clientes registrados en el sistema
    g)Buscar un Cliente
    h)Insertar Cliente
    i)Modificar datos de Cliente
    j)Eliminar un Cliente
    k)Realizar compra de una Obra
    l)Eliminar compra de obra 
    m)Ver listado de Compras existentes
    n)Ver listado de Compras para un mes y año específico insertado por el usuario
    o)Ver listado de Artistas más vendidos
    p)Salir
## Creación de Objetos
**2** .[10] Al iniciar la aplicación se debe:

    a)Crear el mapa de artistas llamado listaArtistas(método en el controlador que crea instancias de Artista y llena el mapa de artistas de la clase ControlGalería. 
    b)Crear el mapa de clientes llamada listaClientes (métodoen el controlador que crea instancias de Cliente y llena el mapa de clientes de la clase ControlGalería. 
    c)Crear las listas de obras y compras. 
    d)Finalmente, la clase PantallaGalería debe usar las funcionalidades del controlador para poblar las colecciones con varios objetos de prueba de cada clase.Incluyendo las nuevas clases de la jerarquía.
### NOTA: Para los métodos anteriores los objetos se crean con valores literalesen la clase de interfaz. Enla tercera entrega de este proyecto, se usarán archivos del sistema. ###
## Enumerados
**3** .[10]Una nueva clase de Obra es Cuadro. Esta clase tiene un atributo Clasificación que se puede ser de dos tipos, Obra Maestra u Obra Representativa. Maneje esta información a través de una enumeración asociada a un tipo de dato de la clase Cuadro.
## Herencia y Polimorfismo
**4** .[25]La clase Obra ahora se puede extender en tres tipos: Cuadro, Escultura e Instalación. Para esto, se deben implementar las clases con el método calcularPrecio de la siguiente manera

    a.En el Cuadro, el método calcularPrecio utiliza la Clasificación de la Obra para calcular el precio. Si es una Obra Maestra, al precio de referencia se le añade un 5% y si es un Obra Representativa se le añade un 3%.
    b.En la Escultura, el método calcularPrecio utiliza el peso de la escultura, si la escultura pesa menos de 10Kg, el precio es el mismo que el precio de referencia, si pesa más de 10Kg, se le añade un 1%del precio de referenciapor cada Kg adicional.
    c.En la Instalación, el método calcularPrecio tienen en cuenta la cantidad de materiales diferentes utilizadosen la Instalación, por cada material diferente utilizado, se añade al precio final un 5% del precio de referencia.
## Interacción de Objetos
**5** .[20]Cree los métodos descritos a continuación e invóquelos desde la interfaz

    a.Defina un método en la clase ControlGaleria que retorne todos las Obras de tipo Escultura
    b.Defina un método en la clase ControlGaleria que permita filtrar todas las compras que estén asociadas a Obras de tipo Cuadro. 
    c.Defina un método que permita calcular el precio total de todas las obras que han sido compradas, para conocer la ganancia total obtenida.
## Pruebas
**6** .[20] Cree archivos de pruebas unitarias para el controlador del negocio (en el diagrama se llama “ControlGaleriaTest”)
    a.Por cada método expuesto en la fachada(Controlador)debe crear un método de prueba b.En cada prueba de cada método se debe crear una prueba exitosa y otra no exitosa

## Usage
First Download Java SE 11 

That is a Maven Project
1. Clone and open with Eclipse IDE or Intellij IDE.
2. Configure the project structure to compile on java 11.
3. Go to src/main/java/poo/project/Control/main.java
4. Run.
5. You can also execute the whole project at the same time, in order to do this, you will need to run the command **"mvn exec:java -Dexec.mainClass="View.PantallaGaleria"** inside the [Project](https://github.com/bayona-n/Proyecto-POO/tree/master/Project) package. **Note that you'll need to have Maven installed to be able to perform this**.

## Main Contributors
**Alejandro Sacristan** &  **Nicolás Bayona**  & **Juan S. Herrera** &  **Andrés R. Porras.** & **_**

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
