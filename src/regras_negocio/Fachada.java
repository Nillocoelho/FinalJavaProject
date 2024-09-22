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
		
		if(co.getCpf().equals(cpf))
			throw new Exception("Erro, CPF já se encontra no cadastro de correntista");
			
		if(!co.getSenha().matches("\\d{4}"))
			throw new Exception("A senha deve ter 4 números");
		 co = new Correntista(cpf, nome, senha);
		 
		 repositorio.adicionarCorrentista(co);
		 repositorio.salvarObjetos();
	}
	public static void criarConta(int id)throws Exception{
		Conta c = repositorio.localizarConta(id);
			if(c.getId() == id)
				throw new Exception("Erro, já existe uma conta vinculada a esse ID");
			
		 repositorio.adicionarConta(c);
		 repositorio.salvarObjetos();
	}
	public static void criarContaEspecial(String cpf, double limite) throws Exception{
	        Correntista co = repositorio.localizarCorrentista(cpf);
	        if (co == null) 
	            throw new Exception("Correntista com CPF " + cpf + " não encontrado.");

	        Conta contaEspecial = new Conta(cpf,limite);
	        repositorio.adicionarConta(contaEspecial);
	        repositorio.salvarObjetos();
	    }
	public static void inserirCorrentistaConta(int id, String cpf){
		
	}
	public static void removerCorrentistaConta(int id, String cpf)throws Exception{
		Correntista co = repositorio.localizarCorrentista(cpf);
		if(!co.getContas().isEmpty())
			throw new Exception("Erro ao apagar correntista: " + cpf + " ainda tem conta");
		
	}
	public static void apagarConta(int id)throws Exception{
		Conta c = repositorio.localizarConta(id);
		if (c.getSaldo() > 0)
            throw new Exception("Conta com saldo, não é possivel apagar!");
		
		repositorio.removerConta(c);
		repositorio.salvarObjetos();
	}
	public static void creditarValor(int id, String cpf, String senha, double valor)throws Exception{
        Conta c = repositorio.localizarConta(id);
        if (c == null)
            throw new Exception("Correntista com ID " + id + " não encontrado.");
	}
	public static void debitarValor(int id, String cpf, String senha, double valor)throws Exception{
		Conta c = repositorio.localizarConta(id);
        if (c == null)
            throw new Exception("Correntista com ID " + id + " não encontrado.");
        
		if (c.getSaldo() < valor) {
			throw new Exception("Conta com saldo insuficiente para realizar a transação");
		}
	}
	public static void transferirValor(int id, String cpf, String senha, double valor, int id2) throws Exception{
		Conta c = repositorio.localizarConta(id); 
		if (c == null)
			throw new Exception("Conta não encontrada.");
	}
}
