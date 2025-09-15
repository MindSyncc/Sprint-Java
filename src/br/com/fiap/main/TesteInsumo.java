package br.com.fiap.main;

import br.com.fiap.controller.InsumoController;

import br.com.fiap.dto.Insumo;

import java.time.LocalDate;

public class TesteInsumo {
    public static void main(String[] args) {
        try {
            InsumoController insumoController = new InsumoController();

            // INSERIR UM INSUMO

            Insumo insumo = new Insumo();
            insumo.setNome("Seringuinhas");
            insumo.setLote("25A");
            insumo.setDataValidade(LocalDate.of(2026, 5, 15)); // formato americano
            insumo.setUnidadeMedida("Metros");
            insumo.setQRCode("2423DFD");
            insumo.setIdCategoriaInsumo(14);

            String resultadoInserir = insumoController.inserirInsumo(insumo);
            System.out.println("Resultado Inserir: " + resultadoInserir);

            // PROCURAR POR UM INSUMO

            System.out.println("\n--- Buscar insumo pelo ID ---");
            int idParaBuscar = 14; // troque pelo ID que você quer buscar
            String insumoEncontrado = insumoController.listarUmInsumo(idParaBuscar);
            System.out.println(insumoEncontrado);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
