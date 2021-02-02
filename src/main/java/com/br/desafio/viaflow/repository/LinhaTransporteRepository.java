package com.br.desafio.viaflow.repository;

import com.br.desafio.viaflow.model.LinhaTransporte;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianlucampos
 */
@Repository
public interface LinhaTransporteRepository extends JpaRepository<LinhaTransporte, Long> {

    @Override
    public Optional<LinhaTransporte> findById(Long id);

    @Query("SELECT l FROM LinhaTransporte l WHERE lower(l.nome) like lower(:nome)")
    public List<LinhaTransporte> findByName(@Param("nome") String nome);

    @Query(value = "SELECT l.* FROM pontotransporte p INNER JOIN linhatransporte l ON l.id = p.linhatransporteid "
            + "WHERE calculate_distance(p.latidude, p.longitude, :latitude, :longitude, 'K') <= :raio "
            + "GROUP BY l.id", nativeQuery = true)
    List<LinhaTransporte> findByRadious(@Param("latitude") Double latitude, @Param("longitude") Double longitude,
            @Param("raio") Long raio);

}
