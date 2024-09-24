package appconsole;
import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.criarCorrentista("64554","Gabriel Silva", "1111"); //cpf,nome,senha
			Fachada.criarCorrentista("4354","maria de fatima", "2222");
			Fachada.criarCorrentista("3432","jose de  ribamar", "3333");
			Fachada.criarCorrentista("3434","ana do bessa", "4444");
			System.out.println("Cadastrou correntistas ");

			Fachada.criarConta("3434");					//gera id=1
			Fachada.criarConta("3434");					//gera id=2
			Fachada.criarContaEspecial("3434", 1000); 	//gera id=3
			
			System.out.println("Cadastrou contas ");
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
		
	}

	public static void main (String[] args) 
	{
		new Cadastrar();
	}
}


