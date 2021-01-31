package com.br.desafio.viaflow.service.client;

import com.br.desafio.viaflow.dto.LinhaTransporteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author gianlucampos
 */
public class LinhaTransporteClient {

    private final Client client;
    private static final String URL_LISTAR_LINHAS = "http://www.poatransporte.com.br";

    public LinhaTransporteClient() {
        this.client = ClientBuilder.newClient();
    }

    public void close() {
        this.client.close();
    }

    public List<LinhaTransporteDTO> listLinhasTransporte() throws Exception {
        WebTarget resource = this.client.target(URL_LISTAR_LINHAS).path("php/facades/process.php");
        Map<String, Object> templateValues = new HashMap<>();
        templateValues.put("a", "nc");
        templateValues.put("p", "%");
        templateValues.put("t", "o");
        //Por algum motivo o resolveTemplates não adiciona os params
        for (Map.Entry<String, Object> query : templateValues.entrySet()) {
            resource = resource.queryParam(query.getKey(), query.getValue());
        }
        Response response = resource.request("application/json;charset=UTF-8").get(Response.class);
        String respString = response.readEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(respString, LinhaTransporteDTO[].class));

    }

}
