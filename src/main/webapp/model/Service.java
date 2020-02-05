package main.webapp.model;

public class Service {
    private int id;
    private int idColocation;
    private String title;
    private String description;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdColocation() {
        return idColocation;
    }

    public void setIdColocation(int idColocation) {
        this.idColocation = idColocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
