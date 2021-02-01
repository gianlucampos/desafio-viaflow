package com.br.desafio.viaflow.controller;

import com.br.desafio.viaflow.model.LinhaTransporte;
import com.br.desafio.viaflow.model.PontoTransporte;
import com.br.desafio.viaflow.service.LinhaTransporteService;
import com.br.desafio.viaflow.service.PontoTransporteService;
import com.br.desafio.viaflow.service.client.MobilidadeClient;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

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
//        serviceLinha.importLinhasTranporte();
//        servicePonto.importItinerario();
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
        List<LinhaTransporte> linhas = serviceLinha.findByName(nome);
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
            MobilidadeClient cliente = new MobilidadeClient();
            return ResponseEntity.ok(cliente.buscaLinhasTransporte());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "itinerario/import")
    public ResponseEntity testaImportItinerario() {
        try {
            MobilidadeClient cliente = new MobilidadeClient();
            return ResponseEntity.ok(cliente.buscaItinerario());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
