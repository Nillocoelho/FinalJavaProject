package regras_negocio;

import java.util.ArrayList;

public class Fachada {
	private static Repositorio repositorio = new repositorio();
	private Fachada() {}
	public static ArrayList<Correntista> Correntista = new ArrayList<Correntista>();
	public static ArrayList<Conta> Conta = new ArrayList<Conta>();
	public static void criarCorrentista(String cpf,String nome,String senha){
		
	}
	public static void criarConta(String cpf){
		
	}
	public static void criarContaEspecial(String cpf){
		
	}
	public static void inserirCorrentistaConta(int id, String cpf){
		
	}
	public static void removerCorrentistaConta(int id, String cpf){
		
	}
	public static void apagarConta(int id){
		
	}
	public static void creditarValor(int id, String cpf, String senha, double valor){
		
	}
	public static void debitarValor(int id, String cpf, String senha, double valor){
		
	}
	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2){
		
	}


}
