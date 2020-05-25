# language: sv
Egenskap: SK_001

  Sambandskontroll av inkomstuppgifter.
  Värde får finnas i endast ett av skattefälten (antingen AvdrPrelSkatt_iu, AvdrSkattSINK_iu, AvdrSkattASINK_iu,
  EjskatteavdragEjbeskattningSv_iu, SkattebefrEnlAvtal_iu, Lokalanstalld_iu eller AmbassadanstISvMAvtal_iu)
 # Här kan en mer utförlig beskrivning för en egenskap (feature) infogas. Beskrivningen ignoreras av Cucumber vid körning men ingår vid rapportering.

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