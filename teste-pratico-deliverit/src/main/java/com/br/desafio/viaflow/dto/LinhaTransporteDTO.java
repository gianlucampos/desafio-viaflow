package com.br.desafio.viaflow.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gianlucampos
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LinhaTransporteDTO implements Serializable {

    @XmlElement
    private String id;
    @XmlElement
    private String codigo;
    @XmlElement
    private String nome;

    public LinhaTransporteDTO() {
    }

    public LinhaTransporteDTO(String id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "LinhaTransporteDTO{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + '}';
    }

}
