package br.com.fiap.view;

import br.com.fiap.controller.FornecedorController;
import br.com.fiap.model.dto.Fornecedor;

import java.sql.SQLException;

public class TesteFornecedor {
    public static void main(String[] args) {
        try {
            FornecedorController fornecedorController = new FornecedorController();

            // --- INSERIR FORNECEDOR ---
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNomeFornecedor("Laboratório XYZ");
            fornecedor.setTelefone("11987654321");
            fornecedor.setEmail("contato@laboratorioxyz.com");
            fornecedor.setCnpj("12345678000199");
            fornecedor.setRua("Rua das Flores");
            fornecedor.setNumero("123");
            fornecedor.setBairro("Centro");
            fornecedor.setCidade("São Paulo");
            fornecedor.setEstado("SP");
            fornecedor.setCep("01001-000");

            String resultadoInserir = fornecedorController.inserirFornecedor(fornecedor);
            System.out.println("RESULTADO: " + resultadoInserir);

            // LISTAR FORNECEDOR
            // pega o último inserido e lista ele
            System.out.println("\nLISTA DE TODOS OS FORNECEDORES");
            fornecedorController.listarTodosFornecedores().forEach(f -> {
                try {
                    System.out.println(fornecedorController.listarUmFornecedor(f.getIdFornecedor()));

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("--------------------------------------------------");
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
