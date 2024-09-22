package modelo;

public class ContaEspecial extends Conta{
	private double limite;
<<<<<<< HEAD
	
	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
=======
	public ContaEspecial(int id, String data, double saldo, double limite){
		super(id,data,saldo);
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
		this.limite = limite;
	}

	public void debitar(double valor) {
<<<<<<< HEAD
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
=======
		this.limite = limite - valor;
>>>>>>> bca41922c7f6feae3299f80fd4e38bb52f845c21
	}
		
	
}
