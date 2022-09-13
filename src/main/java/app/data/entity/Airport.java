package app.data.entity;

public enum Airport {
    JFK("John F. Kennedy International Airport", "New York", "United States"),
    LAX("Los Angeles International Airport", "Los Angeles", "United States"),
    ORD("Chicago O'Hare International Airport", "Chicago", "United States"),
    HND("Tokyo Haneda Airport", "Tokyo", "Japan"),
    LHR("London Heathrow Airport", "London", "United Kingdom"),
    CDG("Paris Charles de Gaulle Airport", "Paris", "France"),
    FRA("Frankfurt Airport", "Frankfurt", "Germany"),
    DXB("Dubai International Airport", "Dubai", "United Arab Emirates"),
    SYD("Sydney Airport", "Sydney", "Australia"),
    MEL("Melbourne Airport", "Melbourne", "Australia"),
    AKL("Auckland Airport", "Auckland", "New Zealand"),
    DFW("Dallas/Fort Worth International Airport", "Dallas", "United States"),
    DEN("Denver International Airport", "Denver", "United States"),
    SFO("San Francisco International Airport", "San Francisco", "United States"),
    SEA("Seattle-Tacoma International Airport", "Seattle", "United States"),
    ATL("Hartsfield-Jackson Atlanta International Airport", "Atlanta", "United States"),
    CLT("Charlotte Douglas International Airport", "Charlotte", "United States"),
    GYD("Heydar Aliyev International Airport", "Baku", "Azerbaijan"),
    IST("Istanbul Ataturk Airport", "Istanbul", "Turkey"),
    SAW("Istanbul Sabiha Gokcen Airport", "Istanbul", "Turkey");

    private final String name;
    private final String city;
    private final String country;

    Airport(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getLocation(){
        return city + ", " + country;
    }
}
