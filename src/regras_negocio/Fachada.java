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

		if (co != null && co.getCpf().equals(cpf)) {
			throw new Exception("Erro, CPF já se encontra no cadastro de correntista");
		}

		if (senha.matches("\\d{3}")) {
			throw new Exception("A senha deve ter 4 números no mínimo");
		}

		co = new Correntista(cpf, nome, senha);

		repositorio.adicionar(co);
		repositorio.salvarObjetos();
	}

	public static void criarConta(String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);

		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " precisa está cadastrado no sistema para criar uma conta.");
		}

		int id = repositorio.gerarIdConta();

		String dataAtual = repositorio.dataFormatada();

		double saldo = 0;

		Conta c = new Conta(id, dataAtual, saldo);

		// Adicionar a conta ao repositório
		co.adicionar(c);
		repositorio.adicionar(c);
		repositorio.salvarObjetos();

		System.out.println("Conta criada com sucesso para o correntista " + co.getNome() + ".");
	}

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);

		if (co == null) {
			throw new Exception("Correntista com CPF " + cpf + " precisa está cadastrado no sistema para criar uma conta.");
		}

		if (limite < 50.0) {
			throw new Exception("Erro: O limite da conta especial deve ser maior ou igual a 50 reais.");
		}

		int idConta = repositorio.gerarIdConta();

		String dataAtual = repositorio.dataFormatada();

		double saldo = 0;

		ContaEspecial contaEspecial = new ContaEspecial(idConta, dataAtual, saldo, limite);

		// Adiciona a conta Especial ao repositório
		co.adicionar(contaEspecial);
		repositorio.adicionar(contaEspecial);
		repositorio.salvarObjetos();

		System.out.println("Conta especial criada com sucesso para o correntista " + co.getNome() + ".");
	}

	public static void inserirCorrentistaConta(int id, String cpf) throws Exception {
		cpf = cpf.trim();

		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null)
			throw new Exception("Correntista com CPF " + cpf + " não encontrado. Correntista precisa está cadastrado!");

		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir!");

//		if()

		// adicionar o correntista a conta
		c.adicionar(co);
		// adicionar a conta ao correntista
		co.adicionar(c);
		// gravar repositorio
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

		if (!co.getContas().isEmpty())
			throw new Exception("Erro ao apagar correntista: O " + cpf + " ainda tem conta associadas.");

		repositorio.remover(co);
		repositorio.salvarObjetos();
	}

	public static void apagarConta(int id) throws Exception {
		System.out.println("Oi");
		Conta c = repositorio.localizarConta(id);

		// Verificar se a conta existe
		if (c == null) {
			throw new Exception("Conta com ID " + id + " não encontrada. Conta precisa existir!");
		}
		;
		// Verificar se tem saldo
		if (c.getSaldo() > 0) {
			throw new Exception("Conta com saldo, não é possivel apagar!");
		}
		// Remover o relacionamento com cada correntista
		for (Correntista co : c.getCorrentistas()) {
			// Remover a conta da lista de contas do correntista
			co.remover(c);
		}

		// Remover os correntistas relacionados
		c.getCorrentistas().clear();

		repositorio.remover(c);
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

		// Verifica se há saldo suficiente na conta de origem
		if (cOrigem.getSaldo() < valor) {
			throw new Exception("Saldo insuficiente na conta de origem para transferir R$ " + valor + ".");
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
