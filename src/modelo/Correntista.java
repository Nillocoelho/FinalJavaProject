
package modelo;

import java.util.ArrayList;

public class Correntista implements Comparable <Correntista> {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> contas = new ArrayList<>();

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

	public void removerConta(Conta c) {
		contas.remove(c);
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public ArrayList<Conta> getContas() {
		return this.contas;
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
	@Override
	public int compareTo(Correntista outro) {	
		return this.getCpf().compareTo(outro.getCpf());
	}

}
