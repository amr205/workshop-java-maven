package com.workshop.torres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {

    public static void main(String[] args) throws IOException {
        Path entrada = Path.of(args.length > 0 ? args[0] : "torres.json");
        Path salida = Path.of(args.length > 1 ? args[1] : "resumen.json");

        LectorTorres lector = new LectorTorres();
        Arsenal arsenal = lector.leerArchivo(entrada);

        System.out.println("Torres leidas de " + entrada + ": " + arsenal.getTorres().size());
        System.out.println("Mas poderosa: " + arsenal.masPoderosa());
        System.out.println("Costo total: " + arsenal.costoTotal());
        System.out.println("Con 250 de oro alcanza para: " + arsenal.alcanzables(250));

        Files.writeString(salida, lector.resumenJson(arsenal));
        System.out.println("Resumen escrito en " + salida);
    }
}
