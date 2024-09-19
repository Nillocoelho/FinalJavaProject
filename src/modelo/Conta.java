package modelo;

import java.util.ArrayList;

public class Conta {
	private int id;
	private String data;
	private double saldo;
	private ArrayList<Correntista> correntista = new ArrayList<Correntista>();
	
	public Conta(int id, String data, double saldo){
		super();
		this.id = id;
		this.data = data;
		this.saldo = saldo;
	}
	public void creditar(double valor) {
		this.saldo = saldo + valor;
		
	}
	public void debitar(double valor) {
		this.saldo = saldo - valor;
	}
	public void transferir(double valor, destino Conta) {
		
	}
	
}
