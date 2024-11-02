#language:es
@servicio:Service1 @metodo:GET_Method2 @portal:P1
@historia:P1-XXXXXX
Característica: Metodo GET_Method2 de servicio Service1.  "<Descripción>"

  Antecedentes:
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/api/v1/endpoins"
    Y que el usuario se encuentra autorizado para realizar la solicitud
    Y que el usuario se encuentra habilitado para realizar una solicitud con
      | Header1 | Header2 | Header3 |
      | 14      | 1       | 1       |
    Y que el usuario se encuentra habilitado para verificar los resultados en "/api2/v1/endpoint"

  @TC:P1-XXXX
  Escenario:  Descripción - Verificar la response de API sean idéntica a la de ESB
    Cuando el usuario realiza una solicitud a APIs de tipo GET sin completar ningún campo del método
    Entonces el servicio devuelve los datos solicitados
    Cuando el usuario realiza una solicitud a ESB de tipo GET sin completar los campos del método
    Entonces el usuario obtiene los datos para  realizar la comparación de los resultados obtenidos
    Y las respuestas se corresponden correctamente
