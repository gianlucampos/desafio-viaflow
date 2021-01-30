package com.br.desafio.viaflow.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 ** Classe que representa o ponto de parada de ônibus.
 * <ul><span style="text-decoration: underline;font-weight: bolder">Atributos</span>
 * <li>pontos - Descreve as coordenadas por onde o ônibus vai passar</li>
 * <li>latitude - Descreve a latitude</li>
 * <li>longitude - Descreve a longitude</li>
 * </ul>
 *
 *
 * @author gianlucampos
 */
@Entity
@Table(name = "PONTOTRANSPORTE")
public class PontoTransporte implements Serializable {

    @Id
    @SequenceGenerator(name = "seqpontotransporte", sequenceName = "seqpontotransporte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqpontotransporte")
    private Long id;
    @Column
    private Double latidude;
    @Column
    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatidude() {
        return latidude;
    }

    public void setLatidude(Double latidude) {
        this.latidude = latidude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
