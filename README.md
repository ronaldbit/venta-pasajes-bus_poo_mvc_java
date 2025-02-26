# Sistema de Ventas de Pasajes Terrestres

## Resumen

El objetivo de este proyecto es el desarrollo de un Sistema de Ventas de Pasajes Terrestres, que nos ayude a gestionar de manera eficiente la venta de boletos, la administración de rutas, la asignación de asientos y el registro de viajes. El sistema está dirigido a transportes terrestres que actualmente operan con métodos manuales o tradicionales, lo que generaría errores sobre la asignación de asientos, retrasos en la atención al cliente y confusiones con el trayecto de rutas y registro de choferes.

## Introducción

Las agencias de transporte terrestre tienen problemas comunes, como la venta manual de pasajes, errores en la asignación de asientos y retrasos en la atención. Este proyecto propone un Sistema de Venta de Pasajes moderno y automatizado. Con ello, se busca simplificar la venta de boletos, gestionar asientos, y ofrecer un servicio más rápido y seguro para los usuarios.

## Fundamentos

El desarrollo de un Sistema de Ventas de Pasajes Terrestres se basa en la necesidad de optimizar la gestión de empresas de transporte terrestre que actualmente operan con métodos manuales. La digitalización de estos procesos permitirá mejorar la eficiencia operativa, minimizar errores y garantizar una mejor experiencia para los clientes.

## Justificación del Proyecto

El presente proyecto se justifica en la necesidad de mejorar la gestión del transporte terrestre mediante el uso de tecnología, permitiendo a las empresas modernizar sus procesos y brindar un mejor servicio a los usuarios.

## Objetivos

### Objetivo General

Desarrollar un Sistema de Ventas de Pasajes Terrestres que automatice y optimice la gestión de boletos, asignación de asientos, administración de rutas y registro de choferes, mejorando la eficiencia operativa y la experiencia del usuario.

### Objetivos Específicos

1. Implementar un sistema digital que permita la compra de boletos en línea y en puntos físicos.
2. Desarrollar un módulo para la gestión de asientos que evite errores en la asignación y permita el control en tiempo real.
3. Diseñar una interfaz que facilite la administración de rutas y horarios de viaje.
4. Implementar un registro digital de choferes y su asignación a los diferentes viajes.
5. Garantizar la seguridad y fiabilidad del sistema mediante controles de acceso y encriptación de datos sensibles.

## Definición y Alcance

El sistema de reserva y alquiler de buses está diseñado para gestionar los alquileres de vehículos y los servicios de conducción, cubriendo todo el proceso desde el registro del cliente hasta la finalización del servicio solicitado.

Se consideraron los siguientes módulos:

### Módulos del Sistema

1. **Módulo de Autenticación (Login)**
   - **Función:** Permite a los usuarios iniciar sesión y redirige según el rol.
   - **Interacciones:**
     - Tabla usuario → Verifica credenciales (USUARIO, CONTRASEÑA)
     - Redirige a:
       - Panel de Administrador si el ROL es "ADMIN".
       - Panel de Vendedor si el ROL es "VENDEDOR".

2. **Módulo de Gestión de Choferes**
   - **Función:** Listar choferes, registrar un nuevo chofer.
   - **Interacciones:**
     - Tabla chofer → CRUD (IDCOD, CHONOM, CHOFIN, CHOCAT, CHOSBA)

3. **Módulo de Gestión de Viajes**
   - **Función:** Listar todos los viajes realizados, registrar un nuevo viaje, registrar el bus dentro del viaje.
   - **Interacciones:**
     - Tabla viaje → CRUD (VIANRO, BUSNRO, RUTCOD, IDCOD, VIAHRS, VIAFCH, COSVIA)
     - Tabla bus → CRUD (BUSNRO, PLACA, CAPACIDAD)
     - Tabla ruta → Relación (RUTCOD)

4. **Módulo de Gestión de Rutas y Viajes**
   - **Función:** Listar rutas disponibles, listar viajes según la ruta seleccionada.
   - **Interacciones:**
     - Tabla ruta → (RUTCOD, RUTNOM, RUTCOS)
     - Tabla viaje → (VIANRO, RUTCOD, BUSNRO, IDCOD, VIAHRS, VIAFCH, COSVIA)

5. **Módulo de Gestión de Pasajeros y Boletos**
   - **Función:** Permite añadir pasajeros a un viaje, seleccionar asiento.
   - **Interacciones:**
     - Tabla pasajeros → CRUD (BOLNRO, VIANRO, NOMPASA, NROASIE, TIPO, PAGO)
     - Tabla viaje → Verifica VIANRO para el viaje del pasajero.

6. **Módulo de Clientes y Boletas**
   - **Función:** Buscar clientes según el código de su boleta, listar detalles del pasajero.
   - **Interacciones:**
     - Tabla pasajeros → (BOLNRO, NOMPASA, NROASIE, PAGO)

### Módulos y Tablas Relacionadas

| Módulo                          | Tablas Relacionadas          |
|----------------------------------|------------------------------|
| 🔑 **Login**                     | usuario                      |
| 🏁 **Gestión de Choferes**       | chofer                       |
| 🚌 **Gestión de Viajes**         | viaje, bus, ruta             |
| 📍 **Gestión de Rutas y Viajes** | ruta, viaje                  |
| 🎟️ **Gestión de Pasajeros y Boletos** | pasajeros, viaje        |
| 👥 **Gestión de Clientes**       | pasajeros                    |

## Clases y Atributos

### Clase `Bus`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| BUSNRO     | decimal(2,0)| Clave primaria  |
| PLACA      | char(10)    |                 |
| CAPACIDAD  | decimal(2,0)|                 |

### Clase `Chofer`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| IDCOD      | char(4)     | Clave primaria  |
| CHONOM     | varchar(30) |                 |
| CHOFIN     | date        |                 |
| CHOCAT     | varchar(1)  |                 |
| CHOSBA     | decimal(8,1)|                 |

### Clase `Pasajeros`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| BOLNRO     | char(6)     | Clave primaria  |
| VIANRO     | char(6)     | Clave foránea de viaje |
| NOMPASA    | varchar(30) |                 |
| NROASIE    | decimal(2,0)|                 |
| TIPO       | char(1)     |                 |
| PAGO       | decimal(8,1)|                 |

### Clase `Ruta`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| RUTCOD     | varchar(4)  | Clave primaria  |
| RUTNOM     | varchar(20) |                 |
| RUTCOS     | decimal(8,1)|                 |

### Clase `Usuario`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| CODE       | varchar(10) | Clave primaria  |
| USUARIO    | varchar(30) |                 |
| NOMBRE     | varchar(29) |                 |
| CONTRASEÑA | varchar(20) |                 |
| CORREO     | varchar(30) |                 |
| TELEFONO   | varchar(11) |                 |
| ROL        | varchar(10) |                 |
| PAIS       | varchar(30) |                 |

### Clase `Viaje`
| Atributo   | Tipo        | Observación     |
|------------|-------------|-----------------|
| VIANRO     | char(6)     | Clave primaria  |
| BUSNRO     | decimal(2,0)| Clave foránea de bus |
| RUTCOD     | varchar(4)  | Clave foránea de ruta |
| IDCOD      | char(4)     | Clave foránea de chofer |
| VIAHRS     | time(6)     |                 |
| VIAFCH     | date        |                 |
| COSVIA     | decimal(8,1)|                 |
