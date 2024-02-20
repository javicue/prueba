package com.javiersan.prueba.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ApiModel("Model Nave")
@Entity
@Table(name="naves")
public class Nave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "El id de la nave", required = true)
    private Long id;

    @ApiModelProperty(value = "El nombre de la nave", required = true)
    @NotEmpty(message = "No puede estar vacío")
    @Size(min=4,max=12,message="El tamaño tiene que estar entre 4 y 12")
    @Column(nullable=false)
    private String nombre;

    @ApiModelProperty(value = "El modelo de la nave", required = true)
    @NotEmpty(message = "No puede estar vacío")
    private String modelo;


    @ApiModelProperty(value = "Fecha creación de la nave", required = true)
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 1L;
    

}
