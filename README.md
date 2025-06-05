!!!LEER IMPORTANTE!!!! Buenos Dias, En este documento dejo anotadas ciertas instrucciones relevantes para la ejecucion del codigo:

1 El repositorio cuenta de dos proyectos distintos pero que tienen relacion, uno de ellos es una api en python (Flask) y el otro es una pagina web en java.

2 Para su correcta ejecucion es necesario abrir la terminal y posicionarte en el directorio "Sistemas-Distribuidos-3\Pagina_Web" una vez ahi procedemos a realizar un "mvn clean package -DskipTests" para luego un "mvn install -DskipTests".

3 Ahora es necesario abrir tu aplicacion docker.desktop y ejecutar el archivo que se encuentra dentro dentro del directorio Pagina_Web "docker-compose.yml" una vez ejecutado deberian salir los contenedores creados en nuestra app de docker.

4 Una vez los contenedores esten creados accediendo a la url http://localhost:8080 nos aparecera una pantalla de login, el unico usuario creado por defecto es admin/admin, y tiene el rol de administrados asignado asi que con el podras observar todas las funcionalidades de las que dispone la pagina web.

5 Recalcar que un usuario con rol de user tambien puede disfrutar de nuestra pagina web pero como es de entender no podra acceder a ciertas funcionalidades solo disponibles para el admin. 

6 En la carpeta del proyecto java hay una carpeta llamada postman donde hay unas capturas que muestran como se ha usado tambien postman para comprovar el manejo de excepciones de nuestras aplicaciones.

Un saludo,

Víctor De Marco
