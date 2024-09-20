package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;

public class Repositorio {
	private ArrayList<Conta> conta = new ArrayList<>();
	private ArrayList<Correntista> correntista = new ArrayList<>(); 

	public Repositorio() {
		carregarObjetos();
	}
	public void adicionar(Conta c)	{
		conta.add(c);
	}

	public void remover(Conta c)	{
		conta.remove(c);
	}

	public Conta localizarContas(int id){
		for(Conta c : conta)
			if(c.getid(id) == id)
				return c;
		return null;
	}

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
	}

	public int getTotalParticipante()	{
		return conta.size();
	}

	public int getTotalEventos()	{
		return correntista.size();
	}

	public int gerarIdEvento() {
		if (correntista.isEmpty())
			return 1;
		else {
			Correntista ultimo = correntista.get(correntista.size()-1);
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
			String data, descricao, id, preco ;
			File f = new File( new File(".\\correntista.csv").getCanonicalPath());
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				descricao = partes[2];
				preco = partes[3];
				co = new Correntista(Integer.parseInt(id), descricao, data, Double.parseDouble(preco));
				this.adicionar(co);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de eventos:"+ex.getMessage());
		}

		try	{
			String tipo,nome, email, empresa, idade, ids;
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				email = partes[1];
				nome = partes[2];
				idade = partes[3];
				ids="";
				if(tipo.equals("PARTICIPANTE")) {
					p = new Participante(email,nome,Integer.parseInt(idade));
					this.adicionar(p);
					if(partes.length>4)
						ids = partes[4];		//ids dos eventos separados por ","
				}
				else {
					empresa = partes[4];
					p = new Convidado(email,nome,Integer.parseInt(idade),empresa);
					this.adicionar(p);
					if(partes.length>5)
						ids = partes[5];		//ids dos eventos separados por ","
				}

				//relacionar participante com os seus eventos
				if(!ids.isEmpty()) {	
					for(String idevento : ids.split(",")){	//converter string em array
						ev = this.localizarEvento(Integer.parseInt(idevento));
						ev.adicionar(p);
						p.adicionar(ev);
					}
				}
			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		try	{
			File f = new File( new File(".\\eventos.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Evento e : correntista) 	{
				arquivo1.write(e.getId()+";"+e.getData()+";"+e.getDescricao()+";"+e.getPreco()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na cria��o do arquivo  eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listaId;
			for(Participante p : conta) {
				//montar uma lista com os id dos eventos do participante
				lista = new ArrayList<>();
				for(Evento e : p.getCorrentista()) {
					lista.add(e.getId()+"");
				}
				listaId = String.join(",", lista);

				if(p instanceof Convidado c )
					arquivo2.write("CONVIDADO;" +p.getEmail() +";" + p.getNome() +";" 
							+ p.getIdade() +";"+ c.getEmpresa() +";"+ listaId +"\n");	
				else
					arquivo2.write("PARTICIPANTE;" +p.getEmail() +";" + p.getNome() +";" 
							+ p.getIdade() +";"+ listaId +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na cria��o do arquivo  participantes "+e.getMessage());
		}

	}
}

