package com.jesica.curso.springboot.app.restful.springboot_crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
ya no es necesario indicar en el application.properties el dialecto de la base de datos, ya que usamos hibernate version 7
y detecta automaticamente el dialecto de la base de datos, por lo que podemos eliminar la propiedad spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
 */

/*
En el controlador,usamos la anotación @Valid para indicar que se debe validar el objeto antes de procesarlo.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //jejej como c# xD
    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 4, max = 20, message = "El nombre debe tener entre 4 y 20 caracteres")
    private String name;

    @Min(value = 500, message = "El precio debe ser mayor a 500")
    @NotNull(message = "El precio no puede ser nulo")
    private Integer price;

    //@NotEmpty (message = "La descripción no puede estar vacía")
    @NotBlank (message = "La descripción no puede estar vacía") 
    private String description;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    

}
