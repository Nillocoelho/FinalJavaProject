package regras_negocio;

import java.util.ArrayList;
import repositorio.Repositorio;
import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;

public class Fachada {
	private Fachada() {
	}

	private static Repositorio repositorio = new Repositorio();

	public static ArrayList<Correntista> listarCorrentistas() {
		return repositorio.getCorrentistas();
	}

	public static ArrayList<Conta> listarContas() {
		return repositorio.getContas();
	}

	public static Correntista localizarCorrentista(String cpf) {
		return repositorio.localizarCorrentista(cpf);
	}

	public static Conta localizarConta(int id) {
		return repositorio.localizarConta(id);
	}

	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		cpf = cpf.trim();
		nome = nome.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);
		
		if (!nome.matches("^[a-zA-Z]+$")) {
		    throw new Exception("O nome deve conter apenas letras.");
		}
		if (co != null && co.getCpf().equals(cpf)) {
			throw new Exception("Erro, CPF já se encontra no cadastro de correntista titulares");
		}
		if (!senha.matches("\\d{4}")) {
			throw new Exception("A senha deve ter 4 números");
		}
		if (!senha.matches("\\d+")) {
		    throw new Exception("A senha deve conter apenas números.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		co = new Correntista(cpf, nome, senha);

		repositorio.adicionarCorrentista(co);
		repositorio.salvarObjetos();
	}

	public static void criarConta(String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);

		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " precisa está cadastrado no sistema para criar uma conta.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		
		// Verifica se o correntista já tem uma conta normal
	    if (co.temConta()) {
	        throw new Exception("Correntista com CPF " + cpf + " já é titular de uma conta.");
	    }
	    
		int id = repositorio.gerarIdConta();

		String dataAtual = repositorio.dataFormatada();

		double saldo = 0;

		Conta c = new Conta(id, dataAtual, saldo);

		// Adicionar a conta ao repositório
        c.adicionarCorrentistaTitular(co);
		co.adicionar(c);
		repositorio.adicionarConta(c);
		repositorio.salvarObjetos();

		System.out.println("Conta criada com sucesso para o correntista " + co.getNome() + ".");
	}

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);

		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " precisa está cadastrado no sistema para criar uma conta como titular.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		// Verifica se o correntista já tem uma conta
	    if (co.temConta()) {
	        throw new Exception("Correntista com CPF " + cpf + " já é titular de uma conta e não pode criar uma conta especial.");
	    }

	    // Verifica se já existe uma conta especial
	    if (co.temContaEspecial()) {
	        throw new Exception("Correntista com CPF " + cpf + " já é titular de uma conta especial.");
	    }

		if (limite < 50.0) {
			throw new Exception("Erro: O limite da conta especial deve ser maior ou igual a 50 reais.");
		}

		int idConta = repositorio.gerarIdConta();

		String dataAtual = repositorio.dataFormatada();

		double saldo = 0;

		ContaEspecial contaEspecial = new ContaEspecial(idConta, dataAtual, saldo, limite);

		// Adiciona a conta Especial ao repositório
        contaEspecial.adicionarCorrentistaTitular(co);
		co.adicionar(contaEspecial);
		repositorio.adicionarConta(contaEspecial);
		repositorio.salvarObjetos();

		System.out.println("Conta especial criada com sucesso para o correntista " + co.getNome() + ".");
	}

	public static void inserirCorrentistaConta(int id, String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null)
			throw new Exception("Correntista com CPF " + cpf + " não encontrado. Correntista precisa está cadastrado!");
		
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}

		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir!");

		if (c.getCorrentistas().contains(co)) {
			throw new Exception("Correntista com CPF " + cpf + " já está cadastrado na conta com ID " + id + ".");
	    }

		// adicionar o correntista a conta
		c.adicionarCorrentista(co);
		// adicionar a conta ao correntista
		co.adicionar(c);
		

		repositorio.salvarObjetos();

		System.out.println("Correntista " + co.getNome() + " adicionado como cotitular na conta ID " + id + ".");
	}

	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null)
			throw new Exception("Correntista com CPF " + cpf + " não encontrado. Correntista precisa está cadastrado!");

		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir!");
		
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		
		if (co.getContas().getFirst().getId() == id)
			throw new Exception("O correntista informado é o titular da conta e não pode ser removido.");

		 // Verifica se o correntista já foi removido da conta
		 if (!c.getCorrentistas().contains(co))
		 throw new Exception("Correntista com CPF " + cpf + " já foi removido como co-titular da conta.");
		
		c.removerCorrentista(co);
		    
		co.removerConta(c);

		repositorio.salvarObjetos();
	       
		System.out.println("Correntista " + co.getNome() + " removido como cotitular na conta ID " + id + ".");
		
	   
	}

	public static void apagarConta(int id) throws Exception {
		System.out.println("Oi");
		Conta c = repositorio.localizarConta(id);

		// Verificar se a conta existe
		if (c == null) {
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir ou já foi apagada!");
		}
		;
		// Verificar se tem saldo
		if (c.getSaldo() > 0) {
			throw new Exception("Conta com saldo, não é possivel apagar!");
		}
		// Remover o relacionamento com cada correntista
		for (Correntista co : c.getCorrentistas()) {
			// Remover a conta da lista de contas do correntista
            c.removerCorrentista(co);
			co.removerConta(c);
		}

		repositorio.removerConta(c);
		repositorio.salvarObjetos();

		System.out.println("Conta ID " + id + " apagada com sucesso.");
	}

	public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
		cpf = cpf.trim();
		senha = senha.trim();

		// Localiza a conta pelo ID
		Conta c = repositorio.localizarConta(id);

		// Verifica se a conta existe
		if (c == null) {
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir!");
		}

		// Localiza o correntista pelo CPF
		Correntista co = repositorio.localizarCorrentista(cpf);

		// Verifica se o correntista existe
		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " não encontrado. Correntista precisa existir!");
		}
		// Verifica a senha do correntista
		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista com CPF " + cpf + ".");
		}
		// Verifica se o valor é positivo
		if (valor <= 0) {
			throw new Exception("O valor a ser creditado deve ser maior que zero.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		if (!senha.matches("\\d+")) {
		    throw new Exception("A senha deve conter apenas números.");
		}
		
		// Credita o valor na conta
		c.creditar(valor);
		// Salva as alterações no repositório
		repositorio.salvarObjetos();

		System.out.println("Valor de R$ " + valor + " creditado na conta ID " + id + " com sucesso.");
	}

	public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
		cpf = cpf.trim();
		senha = senha.trim();

		// Localiza a conta pelo ID
		Conta c = repositorio.localizarConta(id);

		// Verifica se a conta existe
		if (c == null) {
			throw new Exception("Conta com ID " + id + " não encontrado. Conta precisa existir!");
		}

		// Localiza o correntista pelo CPF
		Correntista co = repositorio.localizarCorrentista(cpf);

		// Verifica se o correntista existe
		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " não encontrado.");
		}

		// Verifica a senha do correntista
		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista com CPF " + cpf + ".");
		}

		// Verifica se o valor é positivo
		if (valor <= 0) {
			throw new Exception("O valor a ser debitado deve ser maior que zero.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		if (!senha.matches("\\d+")) {
		    throw new Exception("A senha deve conter apenas números.");
		}

		// Verifica se a conta é uma ContaEspecial e realiza a operação
		if (c instanceof ContaEspecial) {
			ContaEspecial contaEspecial = (ContaEspecial) c;
			if (contaEspecial.getSaldo() - valor < -contaEspecial.getLimite()) {
				throw new Exception("Saldo insuficiente. O saldo pode ficar negativo até o limite da conta especial.");
			}
		} else {
			// Para contas simples, verifica se o saldo é suficiente
			if (c.getSaldo() < valor) {
				throw new Exception("Saldo insuficiente para realizar a transação.");
			}
		}

		// Debita o valor na conta
		c.debitar(valor);

		// Salva as alterações no repositório
		repositorio.salvarObjetos();

		System.out.println("Valor de R$ " + valor + " debitado na conta ID " + id + " com sucesso.");
	}

	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2) throws Exception {
		cpf = cpf.trim();
		senha = senha.trim();

		// Localiza a conta de origem
		Conta cOrigem = repositorio.localizarConta(id1);

		if (cOrigem == null) {
			throw new Exception("Conta de origem com ID " + id1 + " não encontrada.");
		}

		// Localiza a conta de destino
		Conta cDestino = repositorio.localizarConta(id2);

		// Verifica se a conta de destino existe
		if (cDestino == null) {
			throw new Exception("Conta de destino com ID " + id2 + " não encontrada.");
		}

		// Localiza o correntista pelo CPF
		Correntista co = repositorio.localizarCorrentista(cpf);

		// Verifica se o correntista existe
		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " não encontrado.");
		}

		// Verifica a senha do correntista
		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista com CPF " + cpf + ".");
		}

		// Verifica se o valor é positivo
		if (valor <= 0) {
			throw new Exception("O valor a ser transferido deve ser maior que zero.");
		}
		if (!cpf.matches("\\d+")) {
		    throw new Exception("O cpf deve conter apenas números.");
		}
		if (!senha.matches("\\d+")) {
		    throw new Exception("A senha deve conter apenas números.");
		}

		// Verifica se a conta é uma ContaEspecial e realiza a operação
		if (cOrigem instanceof ContaEspecial) {
			ContaEspecial contaEspecial = (ContaEspecial) cOrigem;
			if (contaEspecial.getSaldo() - valor < -contaEspecial.getLimite()) {
				throw new Exception("Saldo insuficiente. O saldo pode ficar negativo até o limite da conta especial.");
			}
		} else {
			// Para contas simples, verifica se o saldo é suficiente
			if (cOrigem.getSaldo() < valor) {
				throw new Exception("Saldo insuficiente para realizar a transação.");
			}
		}

		// Debita o valor da conta de origem
		cOrigem.debitar(valor);

		// Credita o valor na conta de destino
		cDestino.creditar(valor);

		// Salva as alterações no repositório
		repositorio.salvarObjetos();

		System.out.println("Valor de R$ " + valor + " transferido da conta ID " + id1 + " para a conta ID " + id2
				+ " com sucesso.");
	}
}
