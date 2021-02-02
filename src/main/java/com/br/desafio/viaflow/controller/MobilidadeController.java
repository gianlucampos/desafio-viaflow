package com.br.desafio.viaflow.controller;

import com.br.desafio.viaflow.model.LinhaTransporte;
import com.br.desafio.viaflow.model.PontoTransporte;
import com.br.desafio.viaflow.service.LinhaTransporteService;
import com.br.desafio.viaflow.service.PontoTransporteService;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gianlucampos
 */
@RestController
@RequestMapping(path = "mobilidade")
public class MobilidadeController {

    @Autowired
    private LinhaTransporteService serviceLinha;
    @Autowired
    private PontoTransporteService servicePonto;

    @GetMapping
    public ResponseEntity init() {
        return listLinhasTransporte();
    }

    @GetMapping(path = "linhas")
    public ResponseEntity listLinhasTransporte() {
        if (serviceLinha.listAll().isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(serviceLinha.listAll());
    }

    @GetMapping(path = "linhas/{nome}")
    public ResponseEntity listLinhasTransporte(@PathVariable("nome") String nome) {
        List<LinhaTransporte> linhas = serviceLinha.findByName("%" + nome + "%");
        if (linhas.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(linhas);
    }

    @GetMapping(path = "linhas/{latitude}/{longitude}/{raio}")
    public ResponseEntity listLinhasTransporte(@PathVariable("latitude") Double latitude,
            @PathVariable("longitude") Double longitude, @PathVariable("raio") Long raio) {
        List<LinhaTransporte> linhas = serviceLinha.findByRadious(latitude, longitude, raio);
        if (linhas.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(linhas);
    }

    @GetMapping(path = "itinerario")
    public ResponseEntity listItinerario() {
        List<PontoTransporte> pontos = servicePonto.listAll();
        if (pontos.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(pontos);
    }

    @GetMapping(path = "linhas/import")
    public ResponseEntity testaImportLinhas() {
        try {
            serviceLinha.importLinhasTranporte();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "itinerario/import")
    public ResponseEntity testaImportItinerario() {
        try {
            servicePonto.importItinerario();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
