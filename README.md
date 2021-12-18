# Proyecto: Juego del ahorcado

> Este proyecto nace de la realización del producto final de la asignatura "P7 - (ICC02_P7) Aplicación backend con tecnologia Java en servidores de aplicaciones"


## Información general

- El producto final del proyecto es la creación de un juego del ahoracado donde los jugadores tienen que  adivinar la palabra antes de que se finalice el muñeco del ahorcado.


## Tecnologia usada

- IntellJ
- Java 11
- Maven
- Apache Tomcat 
- OpenLDAP 
- Hibernate 
- Ajax JQuery 
- Spring Boot H2 Database
- JPA
- JDBC


## Caracteristicas principales

- Uso de un UID del LDAP y el LDAP implementado.
- Dispone de un server Apache Tomcat.
- Tiene palabras disponibles en la base de datos.
- Cuenta con grados de dificultad
- Consta con un ranking de los nombres de los usuarios con mejores resultados

![imagen](https://user-images.githubusercontent.com/62967242/142778702-412546b0-0654-47ba-a58c-ae72f1734db9.png)
![imagen](https://user-images.githubusercontent.com/62967242/142778686-df16e5aa-adb2-46a1-b4e4-df8db7ccc81a.png)
![imagen](https://user-images.githubusercontent.com/62967242/142778690-c53096db-d690-4581-aeef-d83abb7aafa2.png)
![imagen](https://user-images.githubusercontent.com/62967242/142778692-3f0e0a9c-04d3-4de6-a118-56bf4de849c4.png)



## Setup

Tener implementado un directorio con protocolo Lightweight Directory Access Protocol (LDAP)
Disponer de un servidor de aplicaciones Tomcat
Correr la BBDD necesaria para la persistencía del juego



## Utilización

El objetivo trata de adivinar la palabra sin llegar al número máximo de errores, 6 en este caso.
Ganar: si se consigue adivinar la palabra sin llegar al máximo de fallos se ganará. Se almacenará la puntuación obtenida, mostrando así la mejor puntuación por usuario.
Perder: si se alcanzan los seis fallos se pierde y aparecerá “¡YOU DIED!”, de esta manera no se almacenará ninguna puntuación.


## Mejoras

La carga automática de los datos con el primer arranque.
Actualmente sólo crea la BBDD pero sin cargar datos iniciales, hay que insertar posteriormente las palabras en la BBDD.

Implementar el diseño del muñeco por medio de CSS, no por medio de imágenes .JPG


## Creditos

Estudiantes de la UOC-Jesuites
• ALAIN ROVIRA
• ELVIS BUCATARIU
• ELENA PERIS MERCÉ
• VICTOR FUENTES NOGUERA



