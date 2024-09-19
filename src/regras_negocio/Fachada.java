package regras_negocio;

import java.util.ArrayList;

public class Fachada {
	private static Repositorio repositorio = new repositorio();
	private Fachada() {}
	public static ArrayList<Correntista> Correntista = new ArrayList<Correntista>();
	public static ArrayList<Conta> Conta = new ArrayList<Conta>();
	public static ArrayList<Correntista> listarCorrentistas(){
		return repositorio.getCorrentista;
	}
	public static ArrayList<Conta> listarContas(){
		return repositorio.getConta;
	}
	public static void criarCorrentista(String cpf,String nome,String senha)throws Exception{

	}
	public static void criarConta(String cpf){
		for(Conta c : getConta) {
			if(cpf(c).equals(cpf)) {
				throw new Exception("Erro, já existe uma conta vinculada a esse CPF");
				return;
			}
		}
	}
	public static void criarContaEspecial(String cpf){
		
	}
	public static void inserirCorrentistaConta(int id, String cpf){
		
	}
	public static void removerCorrentistaConta(int id, String cpf){
		
	}
	public static void apagarConta(int id){
		
	}
	public static void creditarValor(int id, String cpf, String senha, double valor)throws Exception{
		
	}
	public static void debitarValor(int id, String cpf, String senha, double valor)throws Exception{
		if (Correntista.saldo < valor) {
			throw new Exception("Conta com saldo insuficiente para realizar a transação");
		}
	}
	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2){
		
	}


}
