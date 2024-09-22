package repositorio;

import java.io.File;
import java.io.FileWriter;
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
	
	public void adicionar(Conta conta)	{
		contas.add(conta);
	}
	public void remover(Conta conta)	{
		contas.remove(conta);
	}
	public void adicionar(Correntista corren)	{
		correntistas.add(corren);
	}
	public void remover(Correntista corren)	{
		correntistas.remove(corren);
	}

<<<<<<< HEAD
	public Correntista localizarCorrentista(String cpf)	{
		for(Correntista c : correntistas)
			if(c.getCPF().equals(cpf))
=======
	public void remover(Conta c)	{
		conta.remove(c);
	}

	public Conta localizarContas(int id){
		for(Conta c : conta)
			if(c.getid(id) == id)
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
				return c;
		return null;
	}

<<<<<<< HEAD
	public Conta localizarConta(int id)	{
		for(Conta con : contas)
			if(con.getId() == id)
				return con;
		return null;
	}
	public ArrayList<Conta> getContas() 	{
		return contas;
	}
	
	public ArrayList<Correntista> getCorrentistas() 	{
		return correntistas;
=======
	public void adicionar(Correntista e)	{
		correntista.add(e);
	}
	public void remover(Correntista e)	{
		correntista.remove(e);
	}

	public Correntista localizarEvento(int id)	{
		for(Correntista e : correntista)
			if(e.getId() == id)
				return e;
		return null;
	}
	public Correntista localizarEvento(String data)	{
		for(Correntista c : correntista)
			if(c.getData().equals(data))
				return c;
		return null;
	}

	public ArrayList<Conta> getConta() 	{
		return conta;
	}
	
	public ArrayList<Correntista> getCorrentista() 	{
		return correntista;
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
	}

	public int getTotalConta()	{
		return contas.size();
	}

	public int getTotalCorrentista()	{
		return correntistas.size();
	}

	public int gerarIdConta() {
		if (contas.isEmpty())
			return 1;
		else {
			Conta ultimo = contas.get(contas.size()-1);
			return ultimo.getId() + 1;
		}
	}
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\correntistas.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\contas.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		Correntista co;
		Conta c;

		try	{
			String cpf, nome, senha ;
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath());
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				co = new Correntista(Integer.parseInt(cpf), nome, senha);
				this.adicionar(co);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de conta:"+ex.getMessage());
		}

		try	{
			String tipo, id, data, saldo, limite, cpfs;
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];
				limite= partes.length > 4 ? partes[4] : "50";
				cpfs = "";
				if(tipo.equals("ESPECIAL")) {
					double limiteDouble = Double.parseDouble(limite);
		            if (limiteDouble < 50) {
		                limiteDouble = 50; 
		            }
		            c = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo), limiteDouble);
					this.adicionar(c);
					cpfs = partes[5];
					
				}
				else {
					limite = partes[4];
					c = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionar(c);
					cpfs = partes[5];
				}

				// Relacionar correntista com suas contas
	            if (!cpfs.isEmpty()) {
	                for (String cpfCorrentista : cpfs.split(",")) {
	                    Correntista con = this.localizarCorrentista(cpfs);
	                    con.adicionar(c); // Adiciona a conta ao correntista
	                    c.adicionar(con); // Adiciona o correntista à conta
	               
	                }
	            }
	        }
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de contas:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Correntista con : correntistas) 	{
				arquivo1.write(con.getCPF()+";"+con.getNome()+";"+con.getSenha()+";"+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo correntista "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listacpf;
			for(Conta c : contas) {
				//montar uma lista com os id das contas do correntista
				lista = new ArrayList<>();
<<<<<<< HEAD
				for(Correntista con : c.getCorrentistas()) {
					lista.add(con.getCPF()+"");
=======
				for(Evento e : p.getCorrentista()) {
					lista.add(e.getId()+"");
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
				}
				listacpf = String.join(",", lista);

				if(c instanceof ContaEspecial e )
					arquivo2.write("CONTA;" +c.getId() +";" + c.getData() +";" 
							+ c.getSaldo() +";"+ e.getLimite() +";"+ listacpf +"\n");	
				else
					arquivo2.write("CONTAESPECIAL;" +c.getId() +";" + c.getData() +";" 
							+ c.getSaldo() +";"+ listacpf +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo contas "+e.getMessage());
		}

	}
}

