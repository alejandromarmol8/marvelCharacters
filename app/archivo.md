He realizado los requerimientos que se pedían en el enunciado:
Una app que, utilizados los dos endpoints dados, devolviese un listado de los personajes de Marvel, y al pinchar sobre ellos, ver el detalle de cada uno.

Para ello he utilizado una arquitectura MVVM.
El proyecto está dividido en tres capas:
Datos - Datos obtenidos por servicio con retrofit
Domain - Casos de uso
Presentation - Manejo de vistas y actualización de datos de las mismas.

Así mismo, quería reseñar que se utilizan dos endpoints porque lo especifica en el enunciado: el que devuelve los personajes y el que devuelve un personaje en concreto. Pero si el enunciado no lo hubiera
aclarado, yo hubiera utilizado únicamente el endpoint que devuelve todos los personajes, ya que ese también tiene el detalle. De esta manera, nos ahorraríamos una llamada innecesaria a servicio, con el importante impacto
en rendimiento que esto conlleva.
Otra consideración que he tenido en cuenta es que, en la navegación, al ir hacia atrás desde el detalle, se vuelva a cargar la lista de Personajes (podría haber alguno nuevo). Otra buena implementación sería no cargarlos, sino mostrar los que
ya teníamos, y dar al usuario la opción de hacer swipe down para refrescar los personajes.