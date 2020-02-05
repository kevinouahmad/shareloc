package main.webapp.model;

public class Address {
    private int id;
    private int number;
    private String street;
    private int zipCode;
    private String town;
    private int idColoc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getIdColoc() {
        return idColoc;
    }

    public void setIdColoc(int idColoc) {
        this.idColoc = idColoc;
    }
}
