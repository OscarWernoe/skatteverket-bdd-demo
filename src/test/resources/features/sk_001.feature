# language: sv
@SK001
Egenskap: SK_001

  Inläsning
  SIBU läser in inkomstuppgifterna för att sedan kunna kontrollera dem i senare steg.
  Samtliga fält finns i  Termförteckningen   Se fliken Termförteckning. Beskriver termernas namnsättning i SIBU och vilka termer som SIBU behöver.
  Formaliekontroll

  Formaliekontroll delas in i tre områden:
  1.  Formatkontroll, dvs kontrollerar fälttyp enligt fliken: Fälttyp i Termförteckningen
  2.  Kontroll av obligatoriska fält, enligt fliken Termförteckning
  3.  Sambandskontroller Samtliga sambandskontroller ska genomföras, vilket medför att en IU kan fastna i flera stoppande kontroller. enligt fliken Kontroller i Termförteckningen

  Efter att en IU har gått igenom formaliekontrollerna med godkänt resultat så sparas IU med processtatus: Inkommen

  I de fall IUn inte går igenom formaliekontrollerna så ska det loggas. Logga inkomsttagarens person- organisationsnummer, vilka/vilken kontroller som inte har gått igenom och dagens datum.

  En IU får aldrig ha fel datatyp eller sakna obligatoriska fält (se Termförteckningen, kolumn obligatorisk vid intag) vid intaget av IU.

  Definitioner
  ...
  ...


  @beteende
  Scenario: Lyckad inläsning av IU
    Givet en IU med gilltig information
    När IU läses in
    Så sparas IU som inkommen

  @beteende
  Scenario: Misslyckad inläsning av IU
    Givet en IU med ogilltig information
    När IU läses in
    Så loggas IU med orgnr, misslyckade kontroller, dagens datum

  @beteende
  Scenario: Misslyckad inläsning av IU vid formatkontroll
    Givet en IU med felaktig datatyp
    När IU läses in
    Så utförs formatkontroll
    * kontroll av obligatoriska fält
    * sambandskontroll
    Och IU loggas med orgnr, misslyckade kontroller, dagens datum

  @beteende
  Scenario: Misslyckad inläsning av IU vid kontroll av obligatoriska fält
    Givet en IU som saknar obligatoriska fält
    När IU läses in
    Så utförs formatkontroll
    * kontroll av obligatoriska fält
    * sambandskontroll
    Och IU loggas med orgnr, misslyckade kontroller, dagens datum

  @beteende
  Scenario: Misslyckad inläsning av IU vid sambandskontroll
    Givet en IU med ogilltiga samband
    När IU läses in
    Så utförs formatkontroll
    * kontroll av obligatoriska fält
    * sambandskontroll
    Och IU loggas med orgnr, misslyckade kontroller, dagens datum

  @sambandskontroll
  Abstrakt Scenario: Värde förekommer endast i ett av skattefälten
    Givet en IU med gilltig information
    Och <skattefält> förekommer
    Och inga av <skattefältLista> förekommer
    När IU kontrolleras
    Så godkänns kontrollen

    Exempel:
      | skattefält         | skattefältLista                                                            |
      | "AvdrPrelSkatt_iu" | AvdrSkattSINK_iu, AvdrSkattASINK_iu, EjskatteavdragEjbeskattningSv_iu, ... |
#      | AvdrSkattSINK_iu                 |
#      | AvdrSkattASINK_iu                |
#      | EjskatteavdragEjbeskattningSv_iu |
#      | SkattebefrEnlAvtal_iu            |
#      | Lokalanstalld_iu                 |
#      | AmbassadanstISvMAvtal_iu         |

  @sambandskontroll
  Scenario: Värde förekommer i flera av skattefälten
    Givet en IU med värde i feler av skattefälten
    När sambandskontroll utförs
    Så stoppas inläsning
    Och IU loggas med orgnr, misslyckade kontroller, dagens datum

  @formatkontroll
  Abstrakt Scenario: Lyckad formatkontroll på IU
    Givet en IU läses in
    När formatkontroll utförs
    Och <fält> av <fälttyp> i <domän> med <data> kontrolleras
    Så godkänns kontrollen

    Exempel:
      | fält                 | fälttyp              | domän     | data                       |
      | idGruppIu            | ID_GRUPP_IU          | HELTAL    | 1                          |
      | redovisningsperiod   | REDOVISNINGSPERIOD   | PERIOD    | 201901                     |
      | uppgiftslamnare      | IDENTITET            | PERSORGNR | YYYYYYYYYYYY               |
      | inkomsttagare        | IDENTITET            | PERSORGNR | XXXXXXXXXXXX               |
      | specifikationsnummer | SPECIFIKATIONSNUMMER | HELTAL    | 9999999999                 |
      | aktuellFomIu         | TIDPUNKT             | DATUMTID  | 2019-03-05T15:40:04.123456 |
      | kontrollstatusIu     | KONTROLLSTATUS       | TEXT      | FARDIGBEARBETAD            |
      | ankomsttidIu         | TIDPUNKT             | DATUMTID  | 2019-03-05T15:40:04.123456 |


  @formatkontroll
  Scenario: Misslyckad formatkontroll på IU
    Givet en IU med fel datatyp
    När formatkontroll utförs
    Så godkänns inte kontrollen

  @obligatoriskafält
  Scenario: Obligatoriska fält
    Givet en IU med gilltig information
    När kontroll av obligatoriska fält görs
    Och fält "idGruppIu", "redovisningsperiod" ... finns
    Så godkänns kontrollen

  @obligatoriskafält
  Scenario: Obligatoriska fält saknas
    Givet en IU som saknar ett eller fler obligatoriska fält
    När kontroll av obligatoriska fält utförs
    Så godkänns inte kontrollen