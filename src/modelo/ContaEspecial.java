package modelo;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}

	// confusão aqui
	public void debitar(double valor) {
		super.debitar(valor);
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double novoLimite) {
		this.limite = novoLimite;
	}

	@Override
	public String toString() {
		String texto = "id=" + id + ", data=" + data + ", saldo=" + saldo + ", limite=" + getLimite();
		return texto;
	}

}
