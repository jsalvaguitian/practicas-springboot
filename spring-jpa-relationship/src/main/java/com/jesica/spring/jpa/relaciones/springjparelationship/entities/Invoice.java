package com.jesica.spring.jpa.relaciones.springjparelationship.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/*
 * TEORIA RELACION MANY TO ONE
 * Muchas facturas
 * Un cliente
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    @ManyToOne
    @JoinColumn(name = "id_cliente_temp") //cambia el nombre de la fk por defecto client_id
    private Client cliente;
    
    public Invoice() {
    }
    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getCliente() {
        return cliente;
    }
    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "{id=" + id + ", description=" + description + ", total=" + total + ", cliente=" + cliente + "}";
    }
    

    
    

}
