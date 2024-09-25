package modelo;

import java.util.List;

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
		String texto = "Conta Especial: IdConta=" + id + ", Data=" + data + ", Saldo=" + saldo + ", Limite=" + getLimite();
		List<Correntista> correntistas = getCorrentistas();
		texto += ", Titulares: ";
		
		if (!correntistas.isEmpty()) {
	        for (int i = 0; i < correntistas.size(); i++) {
	            texto += correntistas.get(i).getNome();
	            if (i < correntistas.size() - 1) {
	                texto += ", "; // Adiciona vírgula entre co-titulares
	            }
	        }
	    } else {
	        texto += "Nenhum correntista cadastrado.";
	    }

	    return texto;
	}
}