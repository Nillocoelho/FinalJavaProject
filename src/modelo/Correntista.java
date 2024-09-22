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
	public void adicionarConta(Conta c) {
		contas.add(c);
	}
	public void inserirCorrentistaConta(Conta c) {
		contas.add(c);
	}
	public void removerCorrentista(Conta c) {
		contas.remove(c);
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
		return "Correntista [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + ", contas=" + contas + "]";
	}
	
}
