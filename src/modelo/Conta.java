package modelo;

import java.util.ArrayList;
import 

public class Conta {
	private int id;
	private String data;
	private double saldo;
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
		if (saldo >= valor) { // Verifica se há saldo suficiente
			this.saldo -= valor;
	    } else {
	    	throw new IllegalArgumentException("Saldo insuficiente.");
	    }
	}
	public void transferir(double valor, destino Conta) {
		 if (saldo >= valor) {
			 this.debitar(valor); 
			 destino.creditar(valor); 
	     } else {
	    	 throw new IllegalArgumentException("Saldo insuficiente para transferir.");
	     }
	}
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
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
    public void adicionar(Correntista c){
    	correntistas.add(c);
	}
	public void remover(Correntista c){
		correntistas.remove(c);
	}
	public void setSaldo(double novosaldo) {
		this.saldo = novosaldo;
	}

	
}
