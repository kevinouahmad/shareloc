package main.webapp.model;

import java.io.Serializable;

/**
 * Created by VAIO on 04/02/2020 01:15
 */
import java.sql.Timestamp;

public class User {

    private Long      id;
    private String    mail;
    private String    pass;
    private String    name;
    private Timestamp registeredDate;

    public Long getId() {
        return id;
    }
    public void setId( Long id ) {
        this.id = id;
    }

    public void setMail( String email ) {
        this.mail = email;
    }
    public String getMail() {
        return mail;
    }

    public void setPass( String motDePasse ) {
        this.pass = motDePasse;
    }
    public String getPass() {
        return pass;
    }

    public void setName( String nom ) {
        this.name = nom;
    }
    public String getName() {
        return name;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }
    public void setRegisteredDate( Timestamp dateInscription ) {
        this.registeredDate = dateInscription;
    }
}
