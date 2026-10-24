package com.workshop.torres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class LectorTorresTest {

    private final LectorTorres lector = new LectorTorres();

    @Test
    void leeUnArregloJsonDeTorres() throws IOException {
        // Arrange
        String json = "[{\"nombre\":\"Mago\",\"danio\":25,\"costo\":200},"
                + "{\"nombre\":\"Canon\",\"danio\":35,\"costo\":250}]";
        // Act
        Arsenal arsenal = lector.leer(json);
        // Assert
        assertEquals(2, arsenal.getTorres().size());
        assertEquals("Canon", arsenal.masPoderosa().nombre());
    }

    @Test
    void resumenIncluyeCantidadCostoYMasPoderosa() throws IOException {
        Arsenal arsenal = new Arsenal(List.of(new Torre("Arquero", 10, 100)));

        JsonNode resumen = new ObjectMapper().readTree(lector.resumenJson(arsenal));

        assertEquals(1, resumen.get("cantidad").asInt());
        assertEquals(100, resumen.get("costoTotal").asInt());
        assertEquals("Arquero", resumen.get("masPoderosa").get("nombre").asText());
    }

    @Test
    void jsonInvalidoLanzaExcepcion() {
        assertThrows(IOException.class, () -> lector.leer("esto no es json"));
    }
}
