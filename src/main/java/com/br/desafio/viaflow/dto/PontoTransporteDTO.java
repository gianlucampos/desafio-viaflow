package com.br.desafio.viaflow.dto;

import java.io.Serializable;

/**
 *
 * @author gianlucampos
 */
public class PontoTransporteDTO implements Serializable {

    private String id;
    private String latitude;
    private String longitude;

    public PontoTransporteDTO() {
    }

    public PontoTransporteDTO(String id, String codigo, String nome) {
        this.id = id;
        this.latitude = codigo;
        this.longitude = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PontoTransporteDTO{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
