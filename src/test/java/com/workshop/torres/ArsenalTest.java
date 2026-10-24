package com.workshop.torres;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArsenalTest {

    private Arsenal arsenal;

    @BeforeEach
    void preparar() {
        arsenal = new Arsenal(List.of(
                new Torre("Arquero", 10, 100),
                new Torre("Canon", 35, 250),
                new Torre("Laser", 50, 400)));
    }

    @Test
    void masPoderosaEsLaDeMayorDanio() {
        assertEquals("Laser", arsenal.masPoderosa().nombre());
    }

    @Test
    void costoTotalSumaTodasLasTorres() {
        assertEquals(750, arsenal.costoTotal());
    }

    @Test
    void alcanzablesFiltraPorPresupuesto() {
        List<String> nombres = arsenal.alcanzables(250).stream().map(Torre::nombre).toList();
        assertEquals(List.of("Arquero", "Canon"), nombres);
    }

    @Test
    void arsenalVacioNoTieneMasPoderosa() {
        Arsenal vacio = new Arsenal(List.of());
        assertThrows(IllegalStateException.class, vacio::masPoderosa);
    }
}
