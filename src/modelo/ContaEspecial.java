package modelo;

public class ContaEspecial extends Conta{
	private double limite;
	public ContaEspecial(int id, String data, double saldo, double limite){
		super(id,data,saldo);
		this.limite = limite;
	}
	public void debitar(double valor) {
		this.limite = limite - valor;
	}
}
