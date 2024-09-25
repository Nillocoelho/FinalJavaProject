package modelo;
import java.util.ArrayList;

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
	@Override
	public String toString() {
		String texto = "Conta: id=" + id + ", data=" + data + ", saldo=" + saldo;
		texto += ", correntista=";
		for(Correntista co : correntistas)
			texto += co.getNome() + ",";
		return texto;
	}	
	
}
