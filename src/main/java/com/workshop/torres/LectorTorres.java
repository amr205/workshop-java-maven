package com.workshop.torres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Convierte JSON a objetos Java y de vuelta usando Jackson.
 */
public class LectorTorres {

    private final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /** Lee un arreglo JSON de torres. */
    public Arsenal leer(String json) throws IOException {
        List<Torre> torres = mapper.readValue(json, new TypeReference<List<Torre>>() { });
        return new Arsenal(torres);
    }

    public Arsenal leerArchivo(Path archivo) throws IOException {
        return leer(Files.readString(archivo));
    }

    /** Genera un JSON con el resumen del arsenal. */
    public String resumenJson(Arsenal arsenal) throws IOException {
        Map<String, Object> resumen = new LinkedHashMap<>();
        resumen.put("cantidad", arsenal.getTorres().size());
        resumen.put("costoTotal", arsenal.costoTotal());
        resumen.put("masPoderosa", arsenal.masPoderosa());
        return mapper.writeValueAsString(resumen);
    }
}
