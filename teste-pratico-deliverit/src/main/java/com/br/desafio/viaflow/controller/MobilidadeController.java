package com.br.desafio.viaflow.controller;

import com.br.desafio.viaflow.model.LinhaTransporte;
import com.br.desafio.viaflow.service.LinhaTransporteService;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author gianlucampos
 */
@RestController
@RequestMapping(path = "mobilidade")
public class MobilidadeController {

    @Autowired
    private LinhaTransporteService service;

    @GetMapping()
    public List<LinhaTransporte> init() {
        service.importLinhasTranporte();
        return listLinhasTransporte();
    }

    @GetMapping(path = "linhas")
    public List<LinhaTransporte> listLinhasTransporte() {
        return service.listAll();
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
