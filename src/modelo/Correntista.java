package modelo;

import java.util.ArrayList;

public class Correntista {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> contas = new ArrayList<Conta>();

	public Correntista(String cpf, String nome, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}

	public double getSaldoTotal(double saldo) {
		return saldo;
	}

	public void adicionar(Conta c) {
		contas.add(c);
	}
	public boolean temConta() {
        return !contas.isEmpty();
    }

    public boolean temContaEspecial() {
    	for (Conta conta : contas) {
            if (conta instanceof ContaEspecial) {
                return true; // Retorna true se encontrar uma conta especial
            }
        }
        return false;
    }

	public void remover(Conta c) {
		contas.remove(c);
	}

	public void inserirCorrentistaConta(Conta c) {
		contas.add(c);
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	@Override
	public String toString() {
		String texto = "Correntista: CPF=" + cpf + ", Nome=" + nome + ", Senha=" + senha;
	    texto += ", Contas: ";

	    if (contas.isEmpty()) {
	        texto += "Nenhuma conta cadastrada.";
	    } else {
	        for (int i = 0; i < contas.size(); i++) {
	            Conta c = contas.get(i);
	            if (i == 0) {
	                texto += "Titular da conta ID " + c.getId(); // Primeira conta como titular
	            } else {
	                texto += ", Co-titular da conta ID " + c.getId(); // Contas seguintes como co-titular
	            }
	        }
	    }

	    return texto;
	}

}
