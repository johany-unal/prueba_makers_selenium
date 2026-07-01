Feature: Smoke Test - Login SauceDemo

  Background:
    Given el usuario navega a la pagina de login

  @tc001 @smoke @login
  Scenario Outline: TC-001 | Login con usuario "<tipo_usuario>"
    When el usuario ingresa el nombre de usuario "<usuario>"
    And el usuario ingresa la contrasena "<contrasena>"
    And el usuario hace clic en el boton de login
    Then el resultado del login debe ser "<resultado>"
    And se muestra el mensaje "<mensaje>"

    Examples:
      | tipo_usuario  | usuario                  | contrasena   | resultado | mensaje                               |
      | estandar      | standard_user            | secret_sauce | exitoso   | Products                              |
      | bloqueado     | locked_out_user          | secret_sauce | fallido   | Sorry, this user has been locked out. |
      | con problemas | problem_user             | secret_sauce | exitoso   | Products                              |
      | lento         | performance_glitch_user  | secret_sauce | exitoso   | Products                              |
      | con errores   | error_user               | secret_sauce | exitoso   | Products                              |
      | visual        | visual_user              | secret_sauce | exitoso   | Products                              |

  @tc002 @smoke @login @negativo
  Scenario Outline: TC-002 | Login fallido con contrasena incorrecta para "<usuario>"
    When el usuario ingresa el nombre de usuario "<usuario>"
    And el usuario ingresa la contrasena "wrong_password"
    And el usuario hace clic en el boton de login
    Then el resultado del login debe ser "fallido"
    And se muestra el mensaje "Username and password do not match any user in this service"

    Examples:
      | usuario       |
      | standard_user |
      | error_user    |

  @tc003 @smoke @validacion @negativo
  Scenario Outline: TC-003 | Validacion de campos obligatorios - "<caso>"
    When el usuario deja el campo usuario "<usuario>"
    And el usuario deja el campo contrasena "<contrasena>"
    And el usuario hace clic en el boton de login
    Then el resultado del login debe ser "fallido"
    And se muestra el mensaje "<mensaje_error>"

    Examples:
      | caso                      | usuario       | contrasena   | mensaje_error        |
      | sin usuario ni contrasena |               |              | Epic sadface: Username is required |
      | sin contrasena            | standard_user |              | Epic sadface: Password is required |
      | sin usuario               |               | secret_sauce | Epic sadface: Username is required |