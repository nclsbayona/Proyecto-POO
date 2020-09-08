# Project P.O.O 2020-3

Pontificia Universidad Javeriana Bogotá D.C 

## About the Control System of an Art Gallery

1. Se requiere hacer un programa orientado a objetos que funcionará en una galería de arte.

2. La galería tiene un catálogo de obras artísticas disponibles para la venta. Cuando se compra una obra, esta se le reserva
al cliente y se paga en la galería y hay que registrarlo en el sistema.

3. El sistema controla la información de las obras disponibles, la información de los clientes que realizan una compra, y
la información de la compra en si. El pago se realiza físicamente en la galería pero en el sistema debe quedar
registrado.

4. En el diagrama de clases UML que se muestra a continuación se observan las clases que intervienen en el problema:


Menu Funcional:

1. Ver listado de Clientes registrados en el sistema
2. Buscar un Cliente
3. Insertar Cliente
4. Modificar datos de Cliente
5. Eliminar un Cliente
6. Realizar compra de una Obra
7. Eliminar compra de obra
8. Ver listado de Compras existentes
9. Ver listado de Compras para un mes y año específico insertado por el usuario
10. Ver listado de Artistas más vendidos
11. Salir

Para este proyecto se solicita implementar las siguientes funcionalidades:

1. Al iniciar el día se debe:
a. Crear la colección de Obras llamada listaObras (método en el controlador ‘GestionObras’ que crea instancias
de Obra y llena la lista de obras de la clase ControlGaleria. 

Esta clase sólo existe para no tener que ingresar datos por pantalla de las obras y facilitar la prueba del programa. 
No obstante, las funcionalidades para insertar, modificar y eliminar una obra deben programarse y funcionar correctamente.
b. La clase GestiónObras también puede llenar la lista de Artistas.

c. Crear la colección de clientes llamada listaClientes (método en el controlador ‘GestionCliente’ que crea
instancias de Cliente y llena la lista de clientes de la clase ControlGaleria. 
Esta clase sólo existe para no tenerque ingresar datos por pantalla de clientes y facilitar la prueba del programa. 
No obstante, lasfuncionalidades para insertar, modificar y eliminar un cliente deben programarse.

d. La clase ControlGaleria debe invocar en su constructor los métodos anteriores.

NOTA: Para los métodos anteriores los objetos se pueden crear con valores literales; en la siguiente entrega esto se
cambiará por un acceso a archivos del sistema.


2. Ver listado de Obras disponibles:
a. Se debe mostrar un listado por pantalla que muestra la información básica de una obra que son título, fecha de
creación, precio de referencia, una foto (la foto será para la entrega 3) y sus dimensiones. Solo deben salir las
Obras que están disponibles para la Compra.

3. Buscar una Obra por título, artista o año:

a. Se debe pedir el criterio por el cual se desea Buscar, insertar el criterio de búsqueda y mostrar las Obras que
cumplan con el criterio de búsqueda.


4. Insertar una Obra:
a. Se debe permitir insertar una nueva obra en la lista de obras de la clase ControlGaleria. Para ello debe apoyarse
en los métodos necesarios de la clase Obra.

b. Debe listar los artistas existentes (Cedula y NombreyApellidos) y si no está debe permitir insertar un nuevo artista.

De un artista se guarda su cédula, nombre y apellidos, fecha de nacimiento y teléfono.

c. No se puede crear una obra con el mismo código de una que ya exista.

d. El código de una obra tiene 7 números. Debe validar que nunca exista una obra con más o menos números.


5. Modificar una Obra:
a. Se debe solicitar el código de la obra a modificar, si este no existe, se debe mostrar un mensaje y volver al menú
principal.

b. Si existe, se deben mostrar los datos de la obra, enumerados, y solicitar el dato que se quiere modificar.

c. No se puede asignar un código de una obra que ya exista, si esto ocurre se muestra un mensaje y no se modifica
la obra.


6. Eliminar una Obra:
a. Solicitar el código de la obra a eliminar, si este no existe, se debe mostrar un mensaje y volver al menú principal.

b. No se puede permitir eliminar una obra que esté asociado a una compra.

c. Se debe mostrar un mensaje de confirmación para eliminar la obra.


## Usage
Java SE 11 Maven Project
1. Clone and open whit Eclipse IDE.
2. 

## Main Contributors
Alejandro Sacristan &  Nicolás Bayona  & Juan S. Herrera &  Andrés R. Porras.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
