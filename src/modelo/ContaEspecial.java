package modelo;

public class ContaEspecial extends Conta{
	private double limite;
	
	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}

	public void debitar(double valor) {
        if (getSaldo() + limite < valor) {
            throw new IllegalArgumentException("Saldo insuficiente, incluindo o limite.");
        }
        setSaldo(getSaldo() - valor); // Debita o valor da conta
    }
	public double getLimite() {
        return this.limite;
    }
	public void setLimite(double limite) {
        if (limite < 50) {
            throw new IllegalArgumentException("O limite deve ser maior ou igual a 50 reais.");
        }
        this.limite = limite;
    }
	@Override
	public String toString() {
	    return "Conta Especial {" +
	            "ID: " + getId() + 
	            ", Data: " + getData() +
	            ", Saldo: " + getSaldo() +
	            ", Limite: " + limite +
	            '}';
	}
		
	
}
