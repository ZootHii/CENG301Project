import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Person {

    private int personID;
    private int isTurkish;
    private String tcPn;
    private String name;
    private String surname;
    private String eMail;
    private String phoneNumber;
    private String phoneNumber2;
    private String fax;
    private int gender;
    private String birthDate;
    private int age;
    private int addressID;
    private String password;

    Person() {

    }

    public Person(int isTurkish, String tcPn, String name, String surname, String eMail, String phoneNumber, String phoneNumber2, String fax, int gender, String birthDate, String password) {
        this.isTurkish = isTurkish;
        this.tcPn = tcPn;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
    }

    public Person(int personID, int isTurkish, String tcPn, String name, String surname, String eMail, String phoneNumber, String phoneNumber2, String fax, int gender, String birthDate, int age, int addressID) {
        this.personID = personID;
        this.isTurkish = isTurkish;
        this.tcPn = tcPn;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.phoneNumber2 = phoneNumber2;
        this.fax = fax;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
        this.addressID = addressID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getIsTurkish() {
        return isTurkish;
    }

    public void setIsTurkish(int isTurkish) {
        this.isTurkish = isTurkish;
    }

    public String getTcPn() {
        return tcPn;
    }

    public void setTcPn(String tcPn) {
        this.tcPn = tcPn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ID":
                return personID;
            case "IS_TURKISH":
                return isTurkish;
            case "TC_PN":
                return tcPn;
            case "NAME":
                return name;
            case "SURNAME":
                return surname;
            case "EMAIL":
                return eMail;
            case "PHONE":
                return phoneNumber;
            case "PHONE2":
                return phoneNumber2;
            case "FAX":
                return fax;
            case "GENDER":
                return gender;
            case "BIRTHDATE":
                return birthDate;
            case "AGE":
                return age;
            case "ADDRESS_ID":
                return addressID;
            case "PASSWORD":
                return password;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", isTurkish=" + isTurkish +
                ", tcPn='" + tcPn + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                ", fax='" + fax + '\'' +
                ", gender=" + gender +
                ", birthDate='" + birthDate + '\'' +
                ", age=" + age +
                ", addressID=" + addressID +
                ", password='" + password + '\'' +
                '}';
    }
}
