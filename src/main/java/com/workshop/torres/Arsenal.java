package com.workshop.torres;

import java.util.Comparator;
import java.util.List;

/**
 * Logica de negocio sobre una lista de torres. No depende de ninguna libreria.
 */
public class Arsenal {

    private final List<Torre> torres;

    public Arsenal(List<Torre> torres) {
        this.torres = List.copyOf(torres);
    }

    public List<Torre> getTorres() {
        return torres;
    }

    /** La torre con mayor danio. */
    public Torre masPoderosa() {
        return torres.stream()
                .max(Comparator.comparingInt(Torre::danio))
                .orElseThrow(() -> new IllegalStateException("El arsenal esta vacio"));
    }

    /** Suma del costo de todas las torres. */
    public int costoTotal() {
        return torres.stream().mapToInt(Torre::costo).sum();
    }

    /** Torres que se pueden comprar con el presupuesto dado. */
    public List<Torre> alcanzables(int presupuesto) {
        return torres.stream()
                .filter(t -> t.costo() <= presupuesto)
                .toList();
    }
}
