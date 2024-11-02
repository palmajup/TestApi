#language:es
@servicio:Service1 @metodo:GET_Method1 @portal:P2
@historia:P2-XXXXXX
Característica: Metodo GET_Method1 de servicio Service1. "<Descripción>"

  Antecedentes:
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/api/v1/endpoint"
    Y que el usuario se encuentra autorizado para realizar la solicitud
    Y que el usuario se encuentra habilitado para realizar una solicitud con
      | Header1 | Header2 | Header3 |
      | 14      | 1       | 1       |
    Y que el usuario se encuentra habilitado para verificar los resultados en "/api2/v1/endpoint"

  @TC:P2-XXXX
  Esquema del escenario: Descripción - Verificar la response
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Cuando el usuario realiza una solicitud a ESB de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el usuario obtiene los datos para  realizar la comparación de los resultados obtenidos
    Y las respuestas se corresponden correctamente
    Ejemplos:
      | param1 |
      | 1      |

