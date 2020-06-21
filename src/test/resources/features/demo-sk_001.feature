# language: sv
#noinspection CucumberUndefinedStep
Egenskap:

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


  Scenario: Demo
    Givet att alla obligatoriska fält är i fyllda
    Och att avdragen preliminär skatt är angiven
    Och att ... fält inte förekomma

    När jag skickar mina inokomstuppgifter
    Så sparas inkomstuppgifterna som inkommna
    Och jag får veta hur mycket skatt blabla

  # A -> B
  @beteende
  Scenario: Lyckad inläsning av IU
    Givet en IU med gilltig information
    När IU läses in
    Så sparas IU som inkommen

  @beteende
  Scenario: Misslyckad inläsning av IU
    Givet en IU med ogilltig information
    När IU läses in
#    Så loggas IU med orgnr, misslyckade kontroller, dagens datum
    Så loggas IU med information

  # A -> (abc) -> B
  @beteende
  Scenario: Lyckad inläsning av IU
    Givet en IU med gilltig information
    När IU läses in
    Så utförs formatkontroll
    * kontroll av obligatoriska fält
    * sambandskontroll
    Så sparas IU som inkommen

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
