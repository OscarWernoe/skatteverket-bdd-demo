# language: sv
Egenskap: SK_001

  Sambandskontroll av inkomstuppgifter.
  Värde får finnas i endast ett av skattefälten (antingen AvdrPrelSkatt_iu, AvdrSkattSINK_iu, AvdrSkattASINK_iu,
  EjskatteavdragEjbeskattningSv_iu, SkattebefrEnlAvtal_iu, Lokalanstalld_iu eller AmbassadanstISvMAvtal_iu)

  @beteende
  Scenario: Lyckad inläsning av IU
    Givet en komplett IU
    Och med gilltig information
    När IU skickas
    Så utförs formaliekontroll
    Och IU sparas

  Scenario: Misslyckad inläsning av IU
    Givet en komplett IU
    Men med ogilltig information
    När IU skickas
    Så utförs formaliekontroll
    Och IU loggas med relevant information

  # värde får endast förekomma i ett av fälten ...
  @sambandskontroll
  Abstrakt Scenario: Värde förekommer endast i ett av skattefälten
    Givet en komplett IU
    Och <skattefält> förekommer
    När IU valideras
    Så lyckas valideringen

    Exempel:
      | skattefält         |
      | "AvdrPrelSkatt_iu" |
#      | AvdrSkattSINK_iu                 |
#      | AvdrSkattASINK_iu                |
#      | EjskatteavdragEjbeskattningSv_iu |
#      | SkattebefrEnlAvtal_iu            |
#      | Lokalanstalld_iu                 |
#      | AmbassadanstISvMAvtal_iu         |

  @formatkontroll
  Abstrakt Scenario: Värde förekommer i flera av skattefälten
    Givet en komplett IU
    Och <skattefält> förekommer
#    Men <skattefalt> forekommer
    Så mysslyckas valideringen stoppande

    Exempel:
      | skattefält                       |
      | "AvdrPrelSkatt_iu"               |
#      | AvdrSkattSINK_iu                 |
#      | AvdrSkattASINK_iu                |
#      | EjskatteavdragEjbeskattningSv_iu |
#      | SkattebefrEnlAvtal_iu            |
#      | Lokalanstalld_iu                 |
#      | AmbassadanstISvMAvtal_iu         |

  # formatkontroll
  Abstrakt Scenario: Formatkontroll på IU
    Givet en komplett IU läses in
    Så utförs formaliekontroll
    När <fält> av <fälttyp> i <domän> med <data> kontrolleras
    Så lyckas formatkontrollen

    Exempel:
      | fält               | fälttyp            | data   | domän  |
      | idGruppIu          | ID_GRUPP_IU        | 1      | HELTAL |
      | redovisningsperiod | REDOVISNINGSPERIOD | 201901 | PERIOD |

  # formatkontroll all inkorrekt
#  {
#  "idGruppIu": 1,
#  "redovisningsperiod": "201901",
#  "uppgiftslamnare": "YYYYYYYYYYYY",
#  "inkomsttagare": "XXXXXXXXXXXX",
#  "specifikationsnummer": 9999999999,
#  "aktuellFomIu": "2019-03-05T15:40:04.123456",
#  "kontrollstatusIu": "FARDIGBEARBETAD",
#  "ankomsttidIu": "2019-03-05T15:40:04.123456",
#  "attribut": {
#  "avdrPrelSkattIu": 1000
#  }
#  }