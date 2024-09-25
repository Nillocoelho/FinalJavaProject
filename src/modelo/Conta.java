package modelo;
import java.util.ArrayList;
import java.util.List;

public class Conta {
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> correntistas = new ArrayList<Correntista>();
	
	public Conta(int id, String data, double saldo){
		super();
		this.id = id;
        this.data = data;
		this.saldo = saldo;
	}
	public void creditar(double valor) {
		this.saldo += valor;
	}
	public void debitar(double valor) {
		this.saldo -= valor;
	}
	public void transferir(double valor, Conta destino) {
		debitar(valor);
		destino.creditar(valor);
	}
	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	public double getSaldo() {
		return saldo;
	}
	public void remover(Correntista co){
		correntistas.remove(co);
	}
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}
	public void adicionar(Correntista co) {
		correntistas.add(co);
	}
	public boolean contemCorrentista(Correntista correntista) {
        return correntistas.contains(correntista);
    }
	@Override
	public String toString() {
		String texto = "Conta: IdConta=" + id + ", Data=" + data + ", Saldo=" + saldo;
		texto += ", Titular: ";

	    List<Correntista> correntistas = getCorrentistas();
	    if (!correntistas.isEmpty()) {
	        texto += correntistas.get(0).getNome(); // Titular 
	        if (correntistas.size() > 1) {
	            texto += ", Co-titulares: ";
	            for (int i = 1; i < correntistas.size(); i++) {
	                texto += correntistas.get(i).getNome();
	                if (i < correntistas.size() - 1) {
	                    texto += ", "; // Adiciona vírgula entre co-titulares
	                }
	            }
	        }
	    } else {
	        texto += "Nenhum correntista cadastrado.";
	    }

	    return texto;
	}
	
}
