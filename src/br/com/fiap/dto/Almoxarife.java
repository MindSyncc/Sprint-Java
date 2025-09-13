package br.com.fiap.dto;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Almoxarife extends Funcionario {
    private int qtdOperacoesDia;
    private LocalDate dataUltimoReabastecimento;
    private int qtdInsumosReabastecidos;

    public Almoxarife() {

    }

    public Almoxarife(String funcional, String nome, String senha, LocalDate dataDeNascimento,
                      String cpf, float salario, LocalDate dataInicio, String turno,
                      String cargo, String permissao,
                      String rua, String numero, String bairro, String cidade, String estado, String cep,
                      int idUnidade) {
        super(funcional, nome, senha, dataDeNascimento, cpf, salario, dataInicio, turno,
                cargo, permissao, rua, numero, bairro, cidade, estado, cep, idUnidade);
        this.qtdOperacoesDia = 0;
        this.qtdInsumosReabastecidos = 0;
        this.dataUltimoReabastecimento = null;
    }

    // Construtor que recebe um funcionário
    public Almoxarife(Funcionario f) {
        super(f.getFuncional(), f.getNome(), f.getSenha(), f.getDataDeNascimento(),
                f.getCpf(), f.getSalario(), f.getDataDeInicio(), f.getTurno(),
                f.getCargo(), f.getPermissao(),
                f.getRua(), f.getNumero(), f.getBairro(), f.getCidade(), f.getEstado(), f.getCep(),
                f.getIdUnidade());
        this.setIdFuncionario(f.getIdFuncionario()); // mantém o ID original
        this.qtdOperacoesDia = 0;
        this.qtdInsumosReabastecidos = 0;
        this.dataUltimoReabastecimento = null;
    }

    public int getQtdOperacoesDia() {
        return qtdOperacoesDia;
    }

    public void setQtdOperacoesDia(int qtdOperacoesDia) {
        this.qtdOperacoesDia = qtdOperacoesDia;
    }

    public LocalDate getDataUltimoReabastecimento() {
        return dataUltimoReabastecimento;
    }

    public void setDataUltimoReabastecimento(LocalDate dataUltimoReabastecimento) {
        this.dataUltimoReabastecimento = dataUltimoReabastecimento;
    }

    public int getQtdInsumosReabastecidos() {
        return qtdInsumosReabastecidos;
    }

    public void setQtdInsumosReabastecidos(int qtdInsumosReabastecidos) {
        this.qtdInsumosReabastecidos = qtdInsumosReabastecidos;
    }

    public void exibirInformacoesDoFuncionario() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dados = String.format(
                "ID: %d%nFuncional: %s%nNome: %s%nCPF: %s%nData Nascimento: %s%n" +
                        "Salário: %.2f%nData Início: %s%nData Término: %s%nTurno: %s%nCargo: %s%nPermissão: %s%n" +
                        "Endereço: %s, %s, %s, %s - %s%nCEP: %s%nID Unidade: %d%n" +
                        "Qtd Movimentos Hoje: %d%nQtd Insumos Reabastecidos: %d",
                getIdFuncionario(),
                getFuncional(),
                getNome(),
                getCpf(),
                getDataDeNascimento().format(dtf),
                getSalario(),
                getDataDeInicio().format(dtf),
                getDataTermino() != null ? getDataTermino().format(dtf) : "N/A",
                getTurno(),
                getCargo(),
                getPermissao(),
                getRua(),
                getNumero(),
                getBairro(),
                getCidade(),
                getEstado(),
                getCep(),
                getIdUnidade(),
                qtdOperacoesDia,
                qtdInsumosReabastecidos
        );
        JOptionPane.showMessageDialog(null, dados, "Informações do Almoxarife", JOptionPane.INFORMATION_MESSAGE);
    }


    public QRCode gerarQRCode() {
        JOptionPane.showMessageDialog(null, "Gerando um QRCode...");

        // Informações do QRCode Mocadas
        int IdQRCode = 22;
        LocalDate dataDeCriacao = LocalDate.now();
        boolean statusQRCode = true;

        return new QRCode(IdQRCode, dataDeCriacao, null, statusQRCode);
    }

    public List<Insumo> retirarInsumo(List<Insumo> listaDeInsumos, String nomeDoInsumo) {
        Insumo insumoBuscado = null;
        boolean encontrou = false;

        for (Insumo insumo: listaDeInsumos) {
            if (insumo.getNome().equalsIgnoreCase(nomeDoInsumo)) {
                encontrou = true;
                insumoBuscado = insumo;
                break;
            }
        }

        listaDeInsumos.remove(insumoBuscado);
        if (encontrou) {
            JOptionPane.showMessageDialog(null, "Produto Encontrado!");
            JOptionPane.showMessageDialog(null, insumoBuscado.exibirInformacoesDoInsumo(), "INFORMAÇÕES DO INSUMO", JOptionPane.INFORMATION_MESSAGE);
            return listaDeInsumos;
        }

        JOptionPane.showMessageDialog(null, "Produto não encontrado, por favor verifique se o nome do insumo foi digitado corretamente", "ERRO",  JOptionPane.WARNING_MESSAGE);
        return listaDeInsumos;
    }

    public List<Insumo> retirarInsumo(List<Insumo> listaDeInsumos, String nomeDoInsumo, String motivoDeRetirada) {
        Insumo insumoBuscado = null;
        boolean encontrou = false;

        for (Insumo insumo: listaDeInsumos) {
            if (insumo.getNome().equalsIgnoreCase(nomeDoInsumo)) {
                encontrou = true;
                insumoBuscado = insumo;
                break;
            }
        }

        if (encontrou) {
            listaDeInsumos.remove(insumoBuscado);
            JOptionPane.showMessageDialog(null, "Produto Encontrado!");
            String mensagem = "Motivo de retirada: " + motivoDeRetirada + "\n" + insumoBuscado.exibirInformacoesDoInsumo();
            JOptionPane.showMessageDialog(null, mensagem, "INFORMAÇÕES DO INSUMO", JOptionPane.INFORMATION_MESSAGE);
            return listaDeInsumos;
        }

        JOptionPane.showMessageDialog(null, "Produto não encontrado, por favor verifique se o nome do insumo foi digitado corretamente", "ERRO",  JOptionPane.WARNING_MESSAGE);
        return listaDeInsumos;
    }

    public Insumo registrarEntradaDeInsumo() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, "Para registar um novo insumo e necessário preencher algumas informações relevantes", "PREENCHIEMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Dados para a criação do objeto Insumo
        int idInsumo = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Insumo recebido: "));
        String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
        String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
        String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (Caso o insumo não possua uma unidade de medida digite 'Unitário'): ");
        LocalDate dataDeValidade = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido: "), dtf);

        boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?","ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        CategoriaInsumo categoriaInsumo = null;
        if (registrarCategoriaDoInsumo) {
            int idCategoria = 20;
            String tipoCategoria = JOptionPane.showInputDialog("Digite a categoria do insumo recebido: ");
            String responsavelPeloRegistro = getNome();

            categoriaInsumo = new CategoriaInsumo(idCategoria, tipoCategoria, responsavelPeloRegistro);
        }

        QRCode qrCode = null;
        boolean etiquetarQRCode = JOptionPane.showConfirmDialog(null, "Gostaria de gerar um QRCode para etiquetar o insumo recebido?","GERAR QRCODE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        if (etiquetarQRCode) {
            qrCode = gerarQRCode();
        }

        setDataUltimoReabastecimento(LocalDate.now());

        JOptionPane.showMessageDialog(null, "Insumo registrado com sucesso!", "EXITO", JOptionPane.INFORMATION_MESSAGE);

        return new Insumo(idInsumo, loteDoInsumo, dataDeValidade, nomeDoInsumo, unidadeDeMedida, categoriaInsumo, qrCode);
    }

    public Insumo registrarEntradaDeInsumo(String motivo) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JOptionPane.showMessageDialog(null, "Para registar um novo insumo e necessário preencher algumas informações relevantes", "PREENCHIEMENTO DE DADOS", JOptionPane.WARNING_MESSAGE);

        // Dados para a criação do objeto Insumo
        int idInsumo = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Insumo recebido: "));
        String nomeDoInsumo = JOptionPane.showInputDialog("Digite o nome do insumo recebido: ");
        String loteDoInsumo = JOptionPane.showInputDialog("Digite o lote do insumo recebido: ");
        String unidadeDeMedida = JOptionPane.showInputDialog("Digite a unidade de medida do insumo (Caso o insumo não possuia uma unidade de medida digite Unitário): ");
        LocalDate dataDeValidade = LocalDate.parse(JOptionPane.showInputDialog("Digite a data de validade prescrita no insumo recebido: "), dtf);

        boolean registrarCategoriaDoInsumo = JOptionPane.showConfirmDialog(null, "Gostaria de detalhar a categoria do insumo recebido?","ADICIONAR CATEGORIA DO INSUMO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        // registra a categoria caso o usuário queira inseri-la
        CategoriaInsumo categoriaInsumo = null;
        if (registrarCategoriaDoInsumo) {
            int idCategoria = 20;
            String tipoCategoria = JOptionPane.showInputDialog("Digite a categoria do insumo recebido: ");
            String responsavelPeloRegistro = getNome();

            categoriaInsumo = new CategoriaInsumo(idCategoria, tipoCategoria, responsavelPeloRegistro);
        }

        // registra um QRCode caso o usuário queira inseri-lo
        QRCode qrCode = null;
        boolean etiquetarQRCode = JOptionPane.showConfirmDialog(null, "Gostaria de gerar um QRCode para etiquetar o insumo recebido?","GERAR QRCODE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;

        if (etiquetarQRCode) {
            qrCode = gerarQRCode();
        }

        setQtdInsumosReabastecidos(qtdInsumosReabastecidos + 1);
        String mensagem = "Insumo registrado com sucesso! \n" + "Motivo do registo" + motivo;
        JOptionPane.showMessageDialog(null, mensagem, "EXITO", JOptionPane.INFORMATION_MESSAGE);

        return new Insumo(idInsumo, loteDoInsumo, dataDeValidade, nomeDoInsumo, unidadeDeMedida, categoriaInsumo, qrCode);
    }
}
