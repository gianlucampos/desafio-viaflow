package com.br.desafio.viaflow.service;

import com.br.desafio.viaflow.dto.ItinerarioDTO;
import com.br.desafio.viaflow.dto.PontoTransporteDTO;
import com.br.desafio.viaflow.model.LinhaTransporte;
import com.br.desafio.viaflow.model.PontoTransporte;
import com.br.desafio.viaflow.repository.LinhaTransporteRepository;
import com.br.desafio.viaflow.repository.PontoTransporteRepository;
import com.br.desafio.viaflow.service.client.MobilidadeClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianlucampos
 */
@Service
public class PontoTransporteService {

    @Autowired
    private PontoTransporteRepository pontoRepository;
    @Autowired
    private LinhaTransporteRepository linhaRepository;

    //Busca da API os Itinerarios de ônibus
    public void importItinerario() {
        try {
            MobilidadeClient cliente = new MobilidadeClient();
            ItinerarioDTO itinerarioDTO = cliente.buscaItinerario();

            for (PontoTransporteDTO pontoDTO : itinerarioDTO.getPontos()) {
                PontoTransporte ponto = pontoRepository.findByIndiceAndLinha(
                        Long.parseLong(itinerarioDTO.getIdlinha()),
                        Long.parseLong(pontoDTO.getId()));
                if (ponto == null) {
                    ponto = new PontoTransporte();
                }
                ponto.setIndice(Long.parseLong(pontoDTO.getId()));
                ponto.setLatidude(Double.parseDouble(pontoDTO.getLatitude()));
                ponto.setLongitude(Double.parseDouble(pontoDTO.getLongitude()));
                ponto.setLinhaTransporte(linhaRepository.findById(Long.parseLong(itinerarioDTO.getIdlinha())).get());
                pontoRepository.save(ponto);
            }

            cliente.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<PontoTransporte> listAll() {
        return pontoRepository.findAll();
    }

}
