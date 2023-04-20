package com.example.integradorSpring.entity;

import com.example.integradorSpring.dto.ClienteDTO;
import com.example.integradorSpring.dto.PaqueteDTO;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;


@Entity
@Table(name = "envio")
@ApiModel(description = "Entidad que representa un envio")
public class Envio {
    @Id
    @Column( nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numGuia;

    @Column(name = "ciudadOrigen")
    private String ciudadOrigen;
    @Column(name = "ciudadDestino")
    private String ciudadDestino;
    @Column(name = "dirDestino")
    private  String dirDestino;
    @Column(name = "nombreRecibe")
    private String nombreRecibe;
    @Column(name = "celularRecibe")
    private String celularRecibe;
    @Column(name = "horaEntrega")
    private String horaEntrega;
    @Column(name = "estado")
    private String estado;
    @Column(name = "valorEnvio")
    private double valorEnvio;

    @OneToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "paquete")
    private Paquete paquete;

    public Envio() {
    }

    public Envio( String ciudadOrigen, String ciudadDestino, String dirDestino, String nombreRecibe, String celularRecibe, String horaEntrega, String estado, double valorEnvio, Cliente cliente, Paquete paquete) {

        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.dirDestino = dirDestino;
        this.nombreRecibe = nombreRecibe;
        this.celularRecibe = celularRecibe;
        this.horaEntrega = horaEntrega;
        this.estado = estado;
        this.valorEnvio = valorEnvio;
        this.cliente = cliente;
        this.paquete = paquete;

    }

    public Integer getNumGuia() {
        return numGuia;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public String getDirDestino() {
        return dirDestino;
    }

    public String getNombreRecibe() {
        return nombreRecibe;
    }



    public String getCelularRecibe() {
        return celularRecibe;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValorEnvio() {
        return valorEnvio;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setNumGuia(Integer numGuia) {
        this.numGuia = numGuia;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public void setDirDestino(String dirDestino) {
        this.dirDestino = dirDestino;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public void setCelularRecibe(String celularRecibe) {
        this.celularRecibe = celularRecibe;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public void setValorEnvio(double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
