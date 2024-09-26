package modelo;
import java.util.ArrayList;

public class Conta {
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> correntistas = new ArrayList<>();
	
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
	public void adicionarCorrentistaTitular(Correntista co){
        if (correntistas.isEmpty()){
            correntistas.add(co);
        }
    }
    public void adicionarCorrentista(Correntista co){
        correntistas.add(co);
    }
	public void removerCorrentista(Correntista co){
		correntistas.remove(co);
	}
	public ArrayList<Correntista> getCorrentistas() {
		return this.correntistas;
	}
	@Override
	public String toString() {
		String texto = "Conta: IdConta=" + id + ", Data=" + data + ", Saldo=" + saldo;
		return texto;
	}
	
}
