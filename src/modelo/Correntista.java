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
	public void criarCorrentista(String cpf, String nome, String senha) {
		
	}
	public void inserirCorrentistaConta(Conta c) {
		contas.add(c);
	}
	public void removerCorrentista(Conta c) {
		contas.remove(c);
	}
	public String getcpf(String cpf) {
		return this.cpf;
	}
}
