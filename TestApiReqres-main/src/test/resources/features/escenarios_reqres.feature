Feature: Como certificador
  Yo quiero validar las apis de reqres

  Background:
    Given que el usuario cuenta con la api reqres

  @testRead
  Scenario: Obtener listado de usuarios
    When se realiza la peticion para listar usuarios
    Then la respuesta de la api presenta el codigo 200
    And en la respuesta debe presentar el campo llamado total con valor 12

  @testCreate
  Scenario Outline: Crear usuario exitoso
    When se envian los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    Then la respuesta de la api presenta el codigo 201
    And en la respuesta la fecha de creacion debe ser la fecha actual
    Examples:
      | name     | job        |
      | santiago | futbolista |
#      | natalia  | analista   |

  @TestGetUserIdSuccess
  Scenario Outline: Obtener usuario por id exitoso
    When se realiza la consulta por id
      | id   |
      | <id> |
    Then la respuesta de la api presenta el codigo 200
    And la respuesta de la api presenta la informacion del usuario
      | id   | email   | first_name   | last_name   |
      | <id> | <email> | <first_name> | <last_name> |
    Examples:
      | id | email                    | first_name | last_name |
      | 11 | george.edwards@reqres.in | George     | Edwards   |


  @TestGetUserIdFailed
  Scenario: Obtener usuario por id fallido
    When se realiza la consulta por id 0
    Then la respuesta de la api presenta el codigo 404

  @testUpdate
  Scenario Outline: actualizar usuario exitoso
    When se envian los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    And el usuario luego envia los datos del usuario a actualizar
      | name   | job   |
      | <name> | <job> |
    Then la respuesta de la api presenta el codigo 200
    And en la respuesta la fecha de actualizacion debe ser la fecha actual
    Examples:
      | name     | job        |
      | santiago | futbolista |

  @testDelete
  Scenario Outline: Eliminar usuario exitoso
    When se envian los datos de usuario a crear
      | name   | job   |
      | <name> | <job> |
    And el usuario envia la peticion para eliminar usuario
    Then la respuesta de la api presenta el codigo 204
    Examples:
      | name   | job           |
      | carlos | desarrollador |


