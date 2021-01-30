package com.br.desafio.viaflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 ** Classe que representa o itinerário de cada ônibus, roteiro de viagem.
 * <ul><span style="text-decoration: underline;font-weight: bolder">Atributos</span>
 * <li>pontos - Descreve as coordenadas por onde o ônibus vai passar</li>
 * <li>linha - Descreve a linha do itinerário</li>
 * </ul>
 *
 *
 * @author gianlucampos
 */
@Entity
@Table(name = "ITINERARIO")
public class Itinerario implements Serializable {

    @Id
    @SequenceGenerator(name = "seqitinerario", sequenceName = "seqitinerario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqitinerario")
    private Long id;
    @OneToOne
    @JoinColumn(name = "LINHAID", referencedColumnName = "ID")
    private LinhaTransporte linha;
    @OneToMany
    @JoinColumn(name = "ITINERARIOID", referencedColumnName = "ID")
    private List<PontoTransporte> pontos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinhaTransporte getLinha() {
        return linha;
    }

    public void setLinha(LinhaTransporte linha) {
        this.linha = linha;
    }

    public List<PontoTransporte> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontoTransporte> pontos) {
        this.pontos = pontos;
    }

}
