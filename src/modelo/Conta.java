package modelo;
import java.util.ArrayList;

public class Conta {
	private int id;
	private String data;
	private double saldo;
	private ArrayList<Correntista> correntistas = new ArrayList<Correntista>();
	
	public Conta(int id, String data, double saldo){
		super();
		this.id = id;
		this.data = data;
		this.saldo = 0;
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
	public ArrayList<Correntista> getCorrentista() {
		return correntistas;
	}
	public void adicionarCorrentista(Correntista co) {
		correntistas.add(co);
	}
	@Override
	public String toString() {
		return "Conta [id=" + id + ", data=" + data + ", saldo=" + saldo + ", correntista=" + correntistas + "]";
	}	
	
}
