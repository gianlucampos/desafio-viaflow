package com.br.desafio.viaflow.repository;

import com.br.desafio.viaflow.model.PontoTransporte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianlucampos
 */
@Repository
public interface PontoTransporteRepository extends JpaRepository<PontoTransporte, Long> {

    @Query("SELECT p FROM PontoTransporte p WHERE p.linhaTransporte.id = :idLinha AND p.indice = :indice")
    public PontoTransporte findByIndiceAndLinha(@Param("indice") Long indice, @Param("idLinha") Long idLinha);
    
    @Query("SELECT p FROM PontoTransporte p WHERE p.linhaTransporte.id = :idLinha")
    public List<PontoTransporte> findByLinha(@Param("idLinha") Long idLinha);
    

}
