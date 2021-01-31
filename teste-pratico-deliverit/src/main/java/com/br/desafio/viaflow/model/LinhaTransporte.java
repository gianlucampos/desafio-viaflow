package com.br.desafio.viaflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa a linha de viagem de cada ônibus.
 * <ul><span style="text-decoration: underline;font-weight: bolder">Atributos</span>
 * <li>codigo - Código da linha do ônibus</li>
 * <li>linha - Nome da linha do ônibus</li>
 * </ul>
 *
 * @author gianlucampos
 */
@Entity
@Table(name = "LINHATRANSPORTE")
public class LinhaTransporte implements Serializable {

    @Id
//    @SequenceGenerator(name = "seqlinhatransporte", sequenceName = "seqlinhatransporte", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqlinhatransporte")
    private Long id;
    @Column
    private String codigo;
    @Column
    private String nome;
    @OneToMany
    @JoinColumn(name = "LINHATRANSPORTEID", referencedColumnName = "ID")
    private List<PontoTransporte> pontos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PontoTransporte> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontoTransporte> pontos) {
        this.pontos = pontos;
    }

}
