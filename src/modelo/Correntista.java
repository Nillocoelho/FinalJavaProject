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
		setSenha(senha); 
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
<<<<<<< HEAD
	public String getCPF() {
		return cpf;
	}

	public void setcpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		// Verifica se a senha tem exatamente 4 dígitos
        if (senha < 1000 || senha > 9999) {
            throw new IllegalArgumentException("A senha deve ser numérica e ter exatamente 4 dígitos.");
        }
        this.senha = senha;
	}
	
	public void adicionar(Conta c){
		contas.add(c);
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}
	
=======
	public String getcpf(String cpf) {
		return this.cpf;
	}
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
}
