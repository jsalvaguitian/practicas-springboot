package com.jesica.springboot.curso.webapp.spring_webapp.models.dto;

import com.jesica.springboot.curso.webapp.spring_webapp.models.User;

public class UserDto {

    private String title;
    private User user;//este puede ser asi anidando el user o sino tener directamente en el dto el name y lastname y no usar clase User
    /*por ej: 
     * name
     * lastname
     */
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    


}
