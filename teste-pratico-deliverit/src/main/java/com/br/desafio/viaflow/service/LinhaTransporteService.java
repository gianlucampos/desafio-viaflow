package com.br.desafio.viaflow.service;

import com.br.desafio.viaflow.dto.LinhaTransporteDTO;
import com.br.desafio.viaflow.model.LinhaTransporte;
import com.br.desafio.viaflow.repository.LinhaTransporteRepository;
import com.br.desafio.viaflow.service.client.LinhaTransporteClient;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianlucampos
 */
@Service
public class LinhaTransporteService {

    @Autowired
    private LinhaTransporteRepository repository;

    //Busca da API as linhas de ônibus
    public void importLinhasTranporte() {
        try {
            LinhaTransporteClient cliente = new LinhaTransporteClient();
            List<LinhaTransporteDTO> linhas = cliente.listLinhasTransporte();
            for (LinhaTransporteDTO linhaDTO : linhas) {
                Optional<LinhaTransporte> optional = repository.findById(Long.parseLong(linhaDTO.getId()));
                LinhaTransporte linha = new LinhaTransporte();
                if (optional.isEmpty()) {
                    linha.setId(Long.parseLong(linhaDTO.getId()));
                } else {
                    linha = optional.get();
                }
                linha.setCodigo(linhaDTO.getCodigo());
                linha.setNome(linhaDTO.getNome());
                repository.save(linha);
            }
            cliente.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<LinhaTransporte> listAll() {
        return repository.findAll();
    }

    public List<LinhaTransporte> listAllByName(String name) {
        return repository.findByName(name);
    }

}
