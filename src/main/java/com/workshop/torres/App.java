package com.workshop.torres;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Arsenal arsenal = new Arsenal(List.of(
                new Torre("Arquero", 10, 100),
                new Torre("Canon", 35, 250),
                new Torre("Mago", 25, 200),
                new Torre("Laser", 50, 400)));

        System.out.println("Torres: " + arsenal.getTorres().size());
        System.out.println("Mas poderosa: " + arsenal.masPoderosa());
        System.out.println("Costo total: " + arsenal.costoTotal());
        System.out.println("Con 250 de oro alcanza para: " + arsenal.alcanzables(250));
    }
}
