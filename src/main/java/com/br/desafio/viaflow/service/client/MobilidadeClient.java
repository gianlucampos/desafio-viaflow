package com.br.desafio.viaflow.service.client;

import com.br.desafio.viaflow.dto.ItinerarioDTO;
import com.br.desafio.viaflow.dto.LinhaTransporteDTO;
import com.br.desafio.viaflow.dto.PontoTransporteDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
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
public class MobilidadeClient {

    private final Client client;
    private static final String URL_BASE = "http://www.poatransporte.com.br";

    public MobilidadeClient() {
        this.client = ClientBuilder.newClient();
    }

    public void close() {
        this.client.close();
    }

    public List<LinhaTransporteDTO> buscaLinhasTransporte() throws Exception {
        WebTarget resource = this.client.target(URL_BASE).path("php/facades/process.php");
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

    public ItinerarioDTO buscaItinerario(Long idLinha) throws Exception {
        WebTarget resource = this.client.target(URL_BASE).path("php/facades/process.php");
        Map<String, Object> templateValues = new HashMap<>();
        templateValues.put("a", "il");
        templateValues.put("p", idLinha);
        //Por algum motivo o resolveTemplates não adiciona os params
        for (Map.Entry<String, Object> query : templateValues.entrySet()) {
            resource = resource.queryParam(query.getKey(), query.getValue());
        }
        Response response = resource.request("application/json;charset=UTF-8").get(Response.class);
        String json = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode nodo = mapper.readTree(json);

        List<PontoTransporteDTO> pontos = new ArrayList<>();
        for (Integer i = 0; i < nodo.size(); i++) {
            String id = i.toString();
            if (nodo.get(id) != null) {
                String latitude = nodo.get(id).get("lat").asText();
                String longitude = nodo.get(id).get("lng").asText();
                PontoTransporteDTO pontoDTO = new PontoTransporteDTO(id, latitude, longitude);
                pontos.add(pontoDTO);
            }
        }
        String nome = nodo.get("nome").asText();
        String codigo = nodo.get("codigo").asText();
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO(idLinha.toString(), codigo, nome, pontos);
        return itinerarioDTO;

    }

}
