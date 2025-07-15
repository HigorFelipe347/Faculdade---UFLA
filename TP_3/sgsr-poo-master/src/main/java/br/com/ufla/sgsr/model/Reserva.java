/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ufla.sgsr.model;

/**
 *
 * @author raiva
 */
public class Reserva {
    private int id;
    private int idUsuario;
    private int idSala;
    private String dataReserva;   // formato yyyy-MM-dd
    private String horaInicio;    // formato HH:mm
    private String horaFim;       // formato HH:mm
    private String status;        // "Confirmada" ou "Cancelada"

    // Construtor completo
    public Reserva(String usuario, int idUsuario, String dataReserva, String horaInicio, String horaFim, String status) {
        this.idUsuario = idUsuario;
        this.idSala = idSala;
        this.dataReserva = dataReserva;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = status;
    }

    // Construtor vazio (para usar com setters se quiser)
    public Reserva() {
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public String getDataReserva() { return dataReserva; }
    public void setDataReserva(String dataReserva) { this.dataReserva = dataReserva; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFim() { return horaFim; }
    public void setHoraFim(String horaFim) { this.horaFim = horaFim; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
}
