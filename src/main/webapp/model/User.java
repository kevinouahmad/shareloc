package main.webapp.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;

/**
 * Created by VAIO on 04/02/2020 01:15
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class User {

    private int id;
    private String    mail;
    private String    pass;
    private String    name;
    private Timestamp registeredDate;
    private String power;

    public int getId() {
        return id;
    }
    public void setId(int id ) {
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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
