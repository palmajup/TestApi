#language:es
@servicio:Clients @metodo:POST_CreateClient_Person_MinimunData @portal:P1
@historia:P1-XXXXX
Característica: Metodo POST_CreateClient_Person_MinimunData de servicio Clients. Crear un cliente con los datos mínimos

  Antecedentes:
    Dado que el usuario se encuentra habilitado para realizar una solicitud a "/ic/api/integration/v1/flows/rest/CREATECLIENTIS/1.0/CreateClient"
    Y que el usuario se encuentra autorizado para realizar la solicitud
    Y que el usuario se encuentra habilitado para realizar una solicitud con
      | Host                                                              | Authorization                          |
      | oic-dela-sit1-frtdqilig7gw-fr.integration.ocp.oraclecloud.com:443 | Basic ZGVsYS50ZXN0OkxvbmRvbjEyMzEyMw== |

  @TC:P1-XXXXX
  Esquema del escenario: Crear un cliente con los datos mínimos - Caso exitoso
    Cuando el usuario realiza una solicitud a APIs de tipo POST completando el campo obligatorio
      | cliCRM_id   |
      | <cliCRM_id> |
    Entonces el servicio devuelve los datos solicitados
    Ejemplos:
      | cliCRM_id |
      | 1         |