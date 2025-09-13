package br.com.fiap.dto;

import java.time.LocalDate;
import java.time.Period;
import javax.swing.*;

public class Funcionario {
    private static int sequencial = 1;

    private int idFuncionario;
    private String funcional;
    private String nome;
    private String cpf;
    private String senha;
    private LocalDate dataDeNascimento;
    private float salario;
    private LocalDate dataDeInicio;
    private LocalDate dataTermino;
    private String turno;
    private String cargo;
    private String permissao;

    // Endereço
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    private int idUnidade;

    // Construtores
    public Funcionario() {}

    public Funcionario(String funcional, String nome, String senha, LocalDate dataDeNascimento,
                       String cpf, float salario, LocalDate dataInicio, String turno,
                       String cargo, String permissao,
                       String rua, String numero, String bairro, String cidade, String estado, String cep,
                       int idUnidade) {
        this.idFuncionario = sequencial++;
        setFuncional(funcional);
        this.nome = nome;
        setSenha(senha);
        setDataDeNascimento(dataDeNascimento);
        setCpf(cpf);
        this.salario = salario;
        this.dataDeInicio = (dataInicio != null ? dataInicio : LocalDate.now());
        this.turno = turno;
        this.cargo = cargo;
        this.permissao = permissao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        setEstado(estado);
        setCep(cep);
        this.idUnidade = idUnidade;
    }

    // ===== GETTER e SETTER alternados =====
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getFuncional() { return funcional; }
    public void setFuncional(String funcional) {
        try {
            if(funcional != null && funcional.length() == 8) {
                this.funcional = funcional;
            } else {
                throw new Exception("Funcional inválido. Deve ter 8 dígitos.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        try {
            if(cpf != null && cpf.length() == 11) {
                this.cpf = cpf;
            } else {
                throw new Exception("CPF inválido. Deve ter 11 dígitos.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        try {
            if (senha.length() >= 5) {
                this.senha = senha;
            } else {
                throw new Exception("A senha precisa ter pelo menos 5 digitos. Tente novamente mais tarde");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public LocalDate getDataDeNascimento() { return dataDeNascimento; }
    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        try {
            LocalDate minDate = LocalDate.of(1900,1,1);
            LocalDate today = LocalDate.now();
            int idade = Period.between(dataDeNascimento, today).getYears();

            if(dataDeNascimento.isAfter(minDate) && !dataDeNascimento.isAfter(today) && idade >= 18) {
                this.dataDeNascimento = dataDeNascimento;
            } else {
                throw new Exception("Data de nascimento inválida ou menor que 18 anos.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public float getSalario() { return salario; }
    public void setSalario(float salario) {
        try {
            if(salario > 0) {
                this.salario = salario;
            } else {
                throw new Exception("Salário deve ser maior que 0.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public LocalDate getDataDeInicio() { return dataDeInicio; }
    public void setDataDeInicio(LocalDate dataDeInicio) { this.dataDeInicio = dataDeInicio; }

    public LocalDate getDataTermino() { return dataTermino; }
    public void setDataTermino(LocalDate dataTermino) { this.dataTermino = dataTermino; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) {
        try {
            if(turno.equals("Manhã") || turno.equals("Tarde") || turno.equals("Noite")) {
                this.turno = turno;
            } else {
                throw new Exception("Turno inválido. Deve ser: Manhã, Tarde ou Noite.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getPermissao() { return permissao; }
    public void setPermissao(String permissao) {
        try {
            if(permissao.equals("Analista Local") || permissao.equals("Analista Corporativo") ||
                    permissao.equals("Almoxarife") || permissao.equals("Colaborador Interno")) {
                this.permissao = permissao;
            } else {
                throw new Exception("Permissão inválida.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        try {
            if(estado != null && estado.length() == 2) {
                this.estado = estado;
            } else {
                throw new Exception("Estado deve ter 2 caracteres.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public String getCep() { return cep; }
    public void setCep(String cep) {
        try {
            if(cep != null && (cep.length() == 8 || cep.length() == 9)) {
                this.cep = cep;
            } else {
                throw new Exception("CEP inválido.");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public int getIdUnidade() { return idUnidade; }
    public void setIdUnidade(int idUnidade) { this.idUnidade = idUnidade; }

    // métodos da classe
    public void exibirInformacoesDoFuncionario() {
        String dados = String.format(
                "ID: %d%nFuncional: %s%nNome: %s%nCPF: %s%nData Nascimento: %s%n" +
                        "Salário: %.2f%nData Início: %s%nData Término: %s%nTurno: %s%nCargo: %s%nPermissão: %s%n" +
                        "Endereço: %s, %s, %s, %s - %s%nCEP: %s%nID Unidade: %d",
                idFuncionario, funcional, nome, cpf, dataDeNascimento, salario, dataDeInicio,
                dataTermino != null ? dataTermino : "N/A", turno, cargo, permissao,
                rua, numero, bairro, cidade, estado, cep, idUnidade
        );
        JOptionPane.showMessageDialog(null, dados, "Informações do Funcionário", JOptionPane.INFORMATION_MESSAGE);
    }
}
