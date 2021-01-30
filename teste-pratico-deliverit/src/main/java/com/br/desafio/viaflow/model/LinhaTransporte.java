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
    @SequenceGenerator(name = "seqlinhatransporte", sequenceName = "seqlinhatransporte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqlinhatransporte")
    private Long id;
    @Column
    private Long codigo;
    @Column
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
