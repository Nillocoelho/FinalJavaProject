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
		return repositorio.getCorrentistas();
	}
    public static ArrayList<Correntista> listarCorrentistas(String cpf) {
        ArrayList<Correntista> listaCor = new ArrayList<>();
        for(Correntista co: repositorio.getCorrentistas())
            if(co.getCpf().equals(cpf))
                listaCor.add(co);
        return listaCor;
    }
	public static ArrayList<Conta> listarContas(){
		return repositorio.getContas();
	}
    public static ArrayList<Conta> listarContas(int id) {
        ArrayList<Conta> lista = new ArrayList<>();
        for(Conta c: repositorio.getContas())
            if(c.getId() == id)
                lista.add(c);
        return lista;
    }
	public static void criarCorrentista(String cpf,String nome,String senha)throws Exception{
		cpf = cpf.trim();
		nome = nome.trim();
		Correntista co = repositorio.localizarCorrentista(cpf);
		for(Correntista correntista : listarCorrentistas()) {
			if(correntista.getCpf().equals(cpf))
				throw new Exception("Erro, CPF já se encontra no cadastro de correntista");
				return;
		}
		if(senha.length() != 4)
			throw new Exception("A senha deve ter 4 números");
		 co = new Correntista(cpf, nome, senha);
		 
		 repositorio.adicionarCorrentista(co);
		 repositorio.salvarObjetos();
	}
	public static void criarConta(int id)throws Exception{
		Conta c = repositorio.localizarConta(id);
		for(Conta conta : listarContas()) {
			if(conta.getId() == id) {
				throw new Exception("Erro, já existe uma conta vinculada a esse CPF");
			}
		}
		 repositorio.adicionarConta(c);
		 repositorio.salvarObjetos();
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
		if (Conta.GetSaldo() < valor) {
			throw new Exception("Conta com saldo insuficiente para realizar a transação");
		}
	}
	public static void transferirValor(int id, String cpf, String senha, double valor, int id2) throws Exception{
		for 
			throw new Exception("Conta não encontrada.");
	}


}
