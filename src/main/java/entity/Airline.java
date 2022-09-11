package entity;

public enum Airline{
    QR("Qatar Airways"),
    EK("Emirates Airlines"),
    J2("Azerbaijan Airlines"),
    TK("Turkish Airlines"),
    DL("Delta Airlines"),
    BA("British Airways"),
    WN("Southwest"),
    AA("American Airlines"),
    AS("Alaska Airlines");
    private final String name;
    Airline(String code){ this.name = code;}
    public String getName(){ return this.name;}
}
