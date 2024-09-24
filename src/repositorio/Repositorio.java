package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;

public class Repositorio {
	private ArrayList<Conta> contas = new ArrayList<>();
	private ArrayList<Correntista> correntistas = new ArrayList<>();

	public Repositorio() {
		carregarObjetos();
	}

	public void adicionar(Conta c) {
		contas.add(c);
	}

	public void remover(Conta c) {
		contas.remove(c);
	}

	public void adicionar(Correntista co) {
		correntistas.add(co);
	}

	public void remover(Correntista co) {
		correntistas.remove(co);
	}

	public Correntista localizarCorrentista(String cpf) {
		for (Correntista co : correntistas)
			if (co.getCpf().equals(cpf))
				return co;
		return null;
	}

	public Conta localizarConta(int id) {
		for (Conta c : contas)
			if (c.getId() == id)
				return c;
		return null;
	}

	public String dataFormatada() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = LocalDate.now().format(formatter);
		return data;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}

	public int getTotalConta() {
		return contas.size();
	}

	public int getTotalCorrentista() {
		return correntistas.size();
	}

	public int gerarIdConta() {
		if (contas.isEmpty())
			return 1;
		else {
			Conta ultimo = contas.get(contas.size() - 1);
			return ultimo.getId() + 1;
		}
	}

	public void carregarObjetos() {
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			// caso os arquivos nao existam, serao criados vazios
			File f1 = new File(new File("correntistas.csv").getCanonicalPath());
			File f2 = new File(new File("contas.csv").getCanonicalPath());
			if (!f1.exists() || !f2.exists()) {
				// System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1);
				arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2);
				arquivo2.close();
				return;
			}
		} catch (Exception ex) {
			throw new RuntimeException("criacao dos arquivos vazios:" + ex.getMessage());
		}

		String linha;
		String[] partes;
		Correntista co;
		Conta c;

		try {
			String cpf, nome, senha, ids;
			File f = new File(new File("correntistas.csv").getCanonicalPath());
			Scanner arquivo1 = new Scanner(f);
			while (arquivo1.hasNextLine()) {
				linha = arquivo1.nextLine().trim();
				partes = linha.split(";");
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				ids = "";

				co = new Correntista(cpf, nome, senha);
				if (partes.length >= 4)
					ids = partes[3];
				this.adicionar(co);

				// Verificar se há IDS
				if (!ids.isEmpty()) {
					for (String id : ids.split(",")) { // converter string em array
						c = localizarConta(Integer.parseInt(id));
						c.adicionar(co);
						co.adicionar(c);
					}
				}
			}
			arquivo1.close();
		} catch (Exception ex) {
			throw new RuntimeException("leitura arquivo de conta:" + ex.getMessage());
		}

		try {
			String tipo, id, data, saldo, limite;
			File f = new File(new File("contas.csv").getCanonicalPath());
			Scanner arquivo2 = new Scanner(f);
			while (arquivo2.hasNextLine()) {
				linha = arquivo2.nextLine().trim();
				partes = linha.split(";");
				// System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];

				if (tipo.equals("ESPECIAL") && partes.length >= 5) {
					limite = partes[4];
					c = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo),
							Double.parseDouble(limite));
					this.adicionar(c);

				} else {
					c = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionar(c);
				}

			}
			arquivo2.close();
		} catch (Exception ex) {
			throw new RuntimeException("leitura arquivo de contas:" + ex.getMessage());
		}
	}

	// --------------------------------------------------------------------
	public void salvarObjetos() {
		// gravar nos arquivos csv os objetos que est o no reposit rio
		try {
			File f = new File(new File("correntistas.csv").getCanonicalPath());
			FileWriter arquivo1 = new FileWriter(f);
			String linha, ids;
			ArrayList<String> listaId;
			for (Correntista co : correntistas) {
				if (co.getContas().isEmpty()) {
					linha = co.getCpf() + ";" + co.getNome() + ";" + co.getSenha() + "\n";
				} else {
					listaId = new ArrayList<>();
					for (Conta c : co.getContas()) {
						listaId.add(c.getId() + "");
					}
					ids = String.join(",", listaId);
					linha = co.getCpf() + ";" + co.getNome() + ";" + co.getSenha() + ";" + ids + "\n";
					arquivo1.write(linha);
				}
			}
			arquivo1.close();
		} catch (Exception e) {
			throw new RuntimeException("problema na cria o do arquivo correntista " + e.getMessage());
		}

		try {
			File f = new File(new File("contas.csv").getCanonicalPath());
			FileWriter arquivo2 = new FileWriter(f);
			for (Conta c : contas) {
				// montar uma lista com os id das contas do correntista
				if (c instanceof ContaEspecial e)
					arquivo2.write("CONTA ESPECIAL;" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + ";"
							+ e.getLimite() + ";" + "\n");
				else
					arquivo2.write("CONTA;" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + ";" + "\n");

			}
			arquivo2.close();
		} catch (Exception e) {
			throw new RuntimeException("problema na cria  o do arquivo contas " + e.getMessage());
		}

	}
}
