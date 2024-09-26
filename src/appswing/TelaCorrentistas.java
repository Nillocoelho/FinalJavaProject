package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Conta;
import modelo.Correntista;
import regras_negocio.Fachada;

public class TelaCorrentistas {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;
	private JLabel lblCpf;
	private JTextField textCPF;
	private JButton button;
	private JButton btnCriarCorrentista_1;
	private JLabel lblNome;
	private JTextField textField_2;
	private JLabel lblSenha;
	private JTextField textField_3;
	private JButton btnRemoverCorrentista;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// TelaEventos window = new TelaEventos();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public TelaCorrentistas() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Correntista");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("selecione");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(21, 236, 71, 14);
		frame.getContentPane().add(lblCpf);

		textCPF = new JTextField();
		textCPF.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCPF.setColumns(10);
		textCPF.setBounds(68, 233, 195, 20);
		frame.getContentPane().add(textCPF);

		btnCriarCorrentista_1 = new JButton("Criar Correntista");
		btnCriarCorrentista_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textCPF.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cpf = textCPF.getText();
					String nome = textField_2.getText();
					String senha = textField_3.getText();
					Fachada.criarCorrentista(cpf, nome, senha);
//					else
//						Fachada.criarConta(cpf,nome,Integer.parseInt(senha),empresa);

					label.setText("Correntista criado: ");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarCorrentista_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarCorrentista_1.setBounds(135, 293, 128, 23);
		frame.getContentPane().add(btnCriarCorrentista_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(279, 9, 89, 23);
		frame.getContentPane().add(button);

		lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(21, 266, 63, 14);
		frame.getContentPane().add(lblNome);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(68, 262, 168, 20);
		frame.getContentPane().add(textField_2);

		lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(21, 293, 38, 14);
		frame.getContentPane().add(lblSenha);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(68, 291, 45, 20);
		frame.getContentPane().add(textField_3);

		btnRemoverCorrentista = new JButton("Apagar Conta");
		btnRemoverCorrentista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String cpf = (String) table.getValueAt(table.getSelectedRow(), 0);
						String id = JOptionPane.showInputDialog(frame, "Digite o id");

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null,
								"Confirma delete no correntista de cpf: " + cpf, "Alerta", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if (escolha == 0) {
							Fachada.removerCorrentistaConta(Integer.parseInt(id), cpf);
							label.setText("Conta apagada do " + cpf);
							listagem();
						} else
							label.setText("nao apagou a conta " + cpf);
					} else
						label.setText("selecione uma linha");
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		btnRemoverCorrentista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemoverCorrentista.setBounds(550, 213, 145, 23);
		frame.getContentPane().add(btnRemoverCorrentista);
	}

	public void listagem() {
		try{
			List<Correntista> lista = Fachada.listarCorrentistas();

			//			//***************************************************************
			//			
						//Alternativa de ordenacao 1 (por nome)
						Collections.sort(lista, new Comparator<Correntista>() {
					        @Override
					        public int compare(Correntista c1, Correntista c2) {
					            return c1.getNome().compareTo(c2.getNome());
					        }
					    });
			//						
			//			//Alternativa de ordenacao 2
			//			Collections.sort(lista, new Comparator<Participante>() {
			//				public int compare(Participante p1, Participante p2) {
			//					int idade1 = p1.getIdade();
			//					int idade2 = p2.getIdade();
			//					return Integer.compare(idade1, idade2);
			//				}
			//			});

			//			//Alternativa de ordenacao 3
			//			Collections.sort(lista, new Comparator<Participante>() {
			//				public int compare(Participante p1, Participante p2) {
			//					String nome1 = p1.getNome();
			//					String nome2 = p2.getNome();
			//					return nome1.compareTo(nome2);
			//				}
			//			});
			//
			//			//Alternativa de ordenacao 4
			//			Collections.sort(lista, (p1,p2) -> p1.getNome().compareTo(p2.getNome()));
			//			
			//			//***************************************************************

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
	

			//colunas
			model.addColumn("cpf");
			model.addColumn("nome");
			model.addColumn("id");

			//linhas
			String texto;
			for(Correntista co : lista) {
					if (co.getContas().isEmpty())
						texto = "sem contas ";
					else {
						texto = " ";
						for (Conta c : co.getContas())
							texto += c.getId() + " ";
					}
					model.addRow(new Object[] { co.getCpf() + "", co.getNome(), texto});
				}

			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " participantes   - selecione uma linha");
		}catch(

	Exception erro)
	{
		label.setText(erro.getMessage());
	}
}

}
