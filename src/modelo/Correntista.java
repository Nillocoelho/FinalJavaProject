package modelo;

import java.util.ArrayList;

public class Correntista {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	public Correntista(String cpf, String nome, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}
	public double getSaldoTotal() {
		return 
	}
}
