public class Address {

    private int addressID;
    private String addressType;
    private String country;
    private String city;
    private String town;
    private String district;
    private String postalCode;
    private String text;

    Address() {
    }

    public Address(int addressID, String addressType, String country, String city, String town, String district, String postalCode, String text) {
        this.addressID = addressID;
        this.addressType = addressType;
        this.country = country;
        this.city = city;
        this.town = town;
        this.district = district;
        this.postalCode = postalCode;
        this.text = text;
    }

    public Address(String addressType, String country, String city, String town, String district, String postalCode, String text) {
        this.addressType = addressType;
        this.country = country;
        this.city = city;
        this.town = town;
        this.district = district;
        this.postalCode = postalCode;
        this.text = text;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return addressID;
            case "TYPE":
                return addressType;
            case "COUNTRY":
                return country;
            case "CITY":
                return city;
            case "TOWN":
                return town;
            case "DISTRICT":
                return district;
            case "POSTAL_CODE":
                return postalCode;
            case "TEXT":
                return text;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", addressType='" + addressType + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", district='" + district + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
