package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import modelo.ContaEspecial;
import modelo.Correntista;
import regras_negocio.Fachada;


public class TelaContas {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnCriarContasimples;
	private JButton btnApagarConta;
	private JButton button_4;
	private JButton btnAdicionarremover;
	private JTextField textCPF;
	private JLabel label;
	private JLabel lblCpf;
	private JLabel label_8;
	private JLabel lblLimite;
	private JTextField textLIMITE;
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
	public TelaContas() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));

		frame.setTitle("Conta");
		frame.setBounds(100, 100, 912, 351);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42, 844, 120);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		btnCriarContasimples = new JButton("Criar Conta");
		btnCriarContasimples.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textCPF.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cpf = textCPF.getText();
					String limiteStr = textLIMITE.getText();
					double limite;
					if (limiteStr.isEmpty()) {
						limite = 0; // Defina o limite como 0 se o campo estiver vazio
					} else {
						limite = Double.parseDouble(limiteStr); // Converta para double se houver valor
					}
					System.out.println(limite);
					if (limite == 0)
						Fachada.criarConta(cpf);
					else
						Fachada.criarContaEspecial(cpf, limite);

					label.setText("participante criado: ");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarContasimples.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarContasimples.setBounds(77, 208, 150, 23);
		frame.getContentPane().add(btnCriarContasimples);

		btnApagarConta = new JButton("Apagar Conta");
		btnApagarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String id = (String) table.getValueAt(table.getSelectedRow(), 0);

						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null,
								"Confirma delete na conta de id : " + id, "Alerta", JOptionPane.DEFAULT_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if (escolha == 0) {
							Fachada.apagarConta(Integer.parseInt(id));
							label.setText("Conta apagada " + id);
							listagem();
						} else
							label.setText("nao apagou a conta " + id);
					} else
						label.setText("selecione uma linha");
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});

		btnApagarConta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagarConta.setBounds(565, 179, 114, 23);
		frame.getContentPane().add(btnApagarConta);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.RED);
		label.setBounds(26, 287, 830, 14);
		frame.getContentPane().add(label);

		lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCpf.setBounds(26, 183, 71, 14);
		frame.getContentPane().add(lblCpf);

		textCPF = new JTextField();
		textCPF.setFont(new Font("Dialog", Font.PLAIN, 12));
		textCPF.setColumns(10);
		textCPF.setBounds(67, 180, 169, 20);
		frame.getContentPane().add(textCPF);

		label_8 = new JLabel("selecione");
		label_8.setBounds(26, 163, 561, 14);
		frame.getContentPane().add(label_8);

		button_4 = new JButton("Listar");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setBounds(410, 8, 95, 23);
		frame.getContentPane().add(button_4);

		btnAdicionarremover = new JButton("Adicionar Correntista");
		btnAdicionarremover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String id = (String) table.getValueAt(table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf");
						Correntista co = Fachada.localizarCorrentista(cpf);
						Conta c = Fachada.localizarConta(Integer.parseInt(id));
//						double valor = co.getContas(c.getSaldo());

						JOptionPane.showMessageDialog(frame, "Saldo da conta" + c.getSaldo());

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma inscricao do evento " + id, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if (escolha == 0) {
							Fachada.inserirCorrentistaConta(Integer.parseInt(id), cpf);
							label.setText(co.getNome() + " Correntista inserido na conta " + c.getId());
							listagem();
						} else
							label.setText("nao adicionou conta " + id);

					} else
						label.setText("correntista nao selecionado");
				} catch (NumberFormatException ex) {
					label.setText("formato do id invalido");
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnAdicionarremover.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdicionarremover.setBounds(689, 180, 150, 23);
		frame.getContentPane().add(btnAdicionarremover);

		lblLimite = new JLabel("Limite");
		lblLimite.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimite.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLimite.setBounds(255, 183, 71, 14);
		frame.getContentPane().add(lblLimite);

		textLIMITE = new JTextField();
		textLIMITE.setFont(new Font("Dialog", Font.PLAIN, 12));
		textLIMITE.setColumns(10);
		textLIMITE.setBounds(301, 180, 169, 20);
		frame.getContentPane().add(textLIMITE);

		btnRemoverCorrentista = new JButton("Remover Correntista");
		btnRemoverCorrentista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRemoverCorrentista.setBounds(689, 207, 150, 23);
		frame.getContentPane().add(btnRemoverCorrentista);
		btnRemoverCorrentista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String id = (String) table.getValueAt(table.getSelectedRow(), 0);
						String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf");
						Conta c = Fachada.localizarConta(Integer.parseInt(id));

						JOptionPane.showMessageDialog(frame, "Saldo da conta =" + c.getSaldo());

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma remocao do evento " + id, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if (escolha == 0) {
							Fachada.removerCorrentistaConta(Integer.parseInt(id), cpf);
							label.setText("A conta " + id + " Pertecente a:" + cpf + " foi excluida");
							listagem();
						} else
							label.setText("nao removeu a conta " + id);

					} else
						label.setText("Correntista nao selecionado");
				} catch (NumberFormatException ex) {
					label.setText("formato do id invalido");
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});

	}

	// *****************************
	public void listagem() {
		try {
			List<Conta> listaContas = Fachada.listarContas();

			// model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			// colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("saldo");

			// linhas
			for (Conta c : listaContas) {
				if(c instanceof ContaEspecial ce)
					model.addRow(new Object[]{c.getId() + "", c.getData(), (-ce.getLimite())});
				else
					model.addRow(new Object[]{c.getId() + "", c.getData(), c.getSaldo()});
			}
			

			table.setModel(model);
			label_8.setText("resultados: " + listaContas.size() + " Contas  - selecione uma linha");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}

	}
}
