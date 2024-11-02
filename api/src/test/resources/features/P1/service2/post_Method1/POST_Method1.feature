#language:es
@servicio:Service2 @metodo:POST_Method1 @portal:P1
@historia:P1-XXXXX
Característica: Metodo POST_Method1 de servicio Service2.  "<Descripción>"

  Antecedentes:
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/api/v1/endpoins"
    Y que el usuario se encuentra autorizado para realizar la solicitud
    Y que el usuario se encuentra habilitado para realizar una solicitud con
      | Header1 | Header2 | Header3 |
      | 14      | 1       | 1       |
    Y que el usuario se encuentra habilitado para verificar los resultados en "/api2/v1/endpoint"

  @TC:P1-XXXXX
  Esquema del escenario: Servicio para envió de mail - Verificar la response de API sean idéntica a la de ESB
    Cuando el usuario realiza una solicitud a APIs de tipo POST completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Cuando el usuario realiza una solicitud a ESB de tipo POST completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el usuario obtiene los datos para  realizar la comparación de los resultados obtenidos
    Y las respuestas se corresponden correctamente
    Ejemplos:
      | param1 |
      | 1      |
