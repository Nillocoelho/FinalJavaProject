package regras_negocio;

import java.util.ArrayList;
import repositorio.Repositorio;
import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();
	private Fachada() {}
	public static ArrayList<Correntista> Correntista = new ArrayList<Correntista>();
	public static ArrayList<Conta> Conta = new ArrayList<Conta>();
	public static ArrayList<Correntista> listarCorrentistas(){
		return repositorio.getCorrentista();
	}
	public static ArrayList<Conta> listarContas(){
		return repositorio.getConta();
	}
	public static void criarCorrentista(String cpf,String nome,String senha)throws Exception{
		cpf = cpf.trim();
		nome = nome.trim();
		Correntista co = repositorio.listarCorrentistas(cpf,nome);
		for(Correntista correntista : getCorrentistas()) {
			if(correntista.getcpf(cpf).equals(cpf))
				throw new Exception("Erro, CPF já se encontra no cadastro de correntista");
				return;
		}
		if(senha.length() != 4)
			throw new Exception("A senha deve ter 4 números");
		 co = new Correntista(cpf, nome, senha);
		 
		 repositorio.adicionar(co);
		 repositorio.salvarObjetos();
	}
	public static void criarConta(int id){
		Correntista co = repositorio.listarContas(id);
		for(Conta conta : getConta()) {
			if(conta.getcpf(id).equals(id)) {
				throw new Exception("Erro, já existe uma conta vinculada a esse CPF");
				return;
			}
			c = new Conta(id);
			
		}
	}
	public static void criarContaEspecial(String cpf, double limite){
		
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
