package br.com.fiap.model.dto;

import br.com.fiap.controller.EstoqueInsumoController;
import br.com.fiap.controller.PedidoController;
import br.com.fiap.controller.PedidoInsumoController;

import javax.swing.*;
import java.util.List;

public class Fornecedor {
    private int idFornecedor;
    private String nomeFornecedor;
    private String telefone;
    private String email;
    private String cnpj;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    // Construtor vazio
    public Fornecedor() {
    }

    // Construtor parametrizado
    public Fornecedor(int idFornecedor, String nomeFornecedor, String telefone, String email,
                      String cnpj, String rua, String numero, String bairro,
                      String cidade, String estado, String cep) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // getters / setters
    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    // métodos
    public void atenderPedido(Pedido pedido) {
        try {
            // 1. Atualiza o status do pedido
            PedidoController pedidoController = new PedidoController();
            pedido.setStatus("Atendido");
            System.out.println(pedidoController.atualizarPedido(pedido));

            // Recupera a lista de insumos presentes em um pedido
            PedidoInsumoController pedidoInsumoController = new PedidoInsumoController();
            List<PedidoInsumo> listaPedidoInsumo = pedidoInsumoController.listarPorPedido(pedido.getIdDoPedido());

            // Atualiza o estoque utilizando a lista de insumos presentes para o mesmo pedido
            EstoqueInsumoController estoqueInsumoController = new EstoqueInsumoController();

            for (PedidoInsumo pedidoInsumo : listaPedidoInsumo) {
                EstoqueInsumo estoqueInsumo = estoqueInsumoController.listarUmEstoqueInsumo(1, pedidoInsumo.getIdInsumo());

                if (estoqueInsumo == null) {
                    // Se o insumo não tiver a sua quantidade registrada em EstoqueInsumo, cria um novo EstoqueInsumo e atribui a quantidade do pedido.
                    estoqueInsumo = new EstoqueInsumo(1, pedidoInsumo.getIdInsumo(), pedidoInsumo.getQuantidade());
                    estoqueInsumoController.inserirEstoqueInsumo(estoqueInsumo);
                } else {
                    // Se o insumo já tiver a sua quantidade registrada, atualiza a quantidade.
                    estoqueInsumo.setQuantidade(estoqueInsumo.getQuantidade() + pedidoInsumo.getQuantidade());
                    estoqueInsumoController.atualizarEstoqueInsumo(estoqueInsumo);
                }
            }

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(null, "O pedido já foi atendido e os insumos foram inseridos no estoque!", "Mensagem do fornecedor", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println("Erro ao atender pedido: " + e.getMessage());
        }
    }

}
