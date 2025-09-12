package com.jesica.springboot.curso.webapp.spring_webapp.models;

public class User {
    private String name;
    private String lastname;
    private String mail;
    
    public User(){
    }

    public User(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }


    public User(String name, String lastname, String email){
        this(name,lastname);
        this.mail = email;
    }

    public String getName(){
        return this.name;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
