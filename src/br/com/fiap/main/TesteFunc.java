package br.com.fiap.main;

import br.com.fiap.controller.FuncionarioController;
import br.com.fiap.dto.Funcionario;

import java.time.LocalDate;

public class TesteFunc {
    public static void main(String[] args) {
        try {
            FuncionarioController controller = new FuncionarioController();

            // ===== 1. Criar funcionário mock =====
            Funcionario funcionario = new Funcionario();
            funcionario.setFuncional("12345679");
            funcionario.setNome("João da Silva");
            funcionario.setCpf("12345678902");
            funcionario.setSenha("senha123");
            funcionario.setSalario(3500.50f);
            funcionario.setTurno("Manhã");
            funcionario.setCargo("Analista");
            funcionario.setPermissao("Analista Local");
            funcionario.setRua("Rua Exemplo");
            funcionario.setNumero("100");
            funcionario.setBairro("Centro");
            funcionario.setCidade("São Paulo");
            funcionario.setEstado("SP");
            funcionario.setCep("01001-000");
            funcionario.setIdUnidade(1);
            funcionario.setIdFuncionario(1);

            // Datas
            funcionario.setDataDeNascimento(LocalDate.of(1990, 5, 20));
            funcionario.setDataDeInicio(LocalDate.now());
            funcionario.setDataTermino(null); // opcional


            // ===== 2. Inserir =====
            boolean resultadoInserir = controller.inserirFuncionario(funcionario);
            System.out.println("Resultado Inserir: " + resultadoInserir);

            // ===== 3. Listar =====
            String resultadoListar = controller.listarUmFuncionario(funcionario.getIdFuncionario());
            System.out.println("Resultado Listar: " + resultadoListar);



// cirar as classes dao faltantes
            // fornecedor, unidade, estoque
            // controller de cada classe
            // teste do banco de dados
            // remover qrcode
            // verificar se essa categoriaInsumo existe antes de criá-la.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
