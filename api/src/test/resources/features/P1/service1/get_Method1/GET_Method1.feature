#language:es
@servicio:Service1 @metodo:GET_Method1 @portal:P1
@historia:P1-XXXXXX
Característica: Metodo GET_Method1 de servicio Service1. "<Descripción>"

  Antecedentes:
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/api/v1/endpoint"
    Y que el usuario se encuentra autorizado para realizar la solicitud
    Y que el usuario se encuentra habilitado para realizar una solicitud con
      | Header1 | Header2 | Header3 |
      | 14      | 1       | 1       |
    Y que el usuario se encuentra habilitado para verificar los resultados en "/api2/v1/endpoint"

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Verificar la response
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | prefparam1 |
      | <param1>   |
    Entonces el servicio devuelve los datos solicitados
    Cuando el usuario realiza una solicitud a ESB de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el usuario obtiene los datos para  realizar la comparación de los resultados obtenidos
    Y las respuestas se corresponden correctamente
    Ejemplos:
      | param1 |
      |        |

  @TC:P1-XXXX
  Escenario: Descripción - Sin algún campo obligatorio
    Cuando el usuario realiza una solicitud a APIs de tipo GET sin completar el campo obligatorio
    Entonces el servicio devuelve los datos solicitados

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Con campos obligatorios vacío/null
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 |
      |        |
      | null   |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Con campo extra no especificado
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio y campo extra no especificado
      | param1   | extra   |
      | <param1> | <extra> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 | extra |
      | 1      | 1     |
      | 1      | valor |
      | 1      |       |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Persona Indefinida
    Dado que el usuario se encuentra habilitado para realizar una solicitud con
      | ApplicationId | CompanyCode | ClientTypeId |
      | 14            | 1           | 3            |
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 |
      | 1      |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Token vacío
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio y con token vacío
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 |
      | 1      |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Validar error método de API no especificado
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/api/v42/endpoint"
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 |
      | 1      |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Sin headers
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio y sin headers
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | param1 |
      | 1      |

  @TC:P1-XXXX
  Esquema del escenario: Descripción - Persona físicas y jurídicas
    Dado que el usuario se encuentra habilitado para realizar una solicitud con
      | Header1 | Header2 | Header3   |
      | 14      | 1       | <Header3> |
    Cuando el usuario realiza una solicitud a APIs de tipo GET completando el campo obligatorio
      | param1   |
      | <param1> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | Header3 | param1 |
      | 1       | 1      |
      | 2       | 1      |
