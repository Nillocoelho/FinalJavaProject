package appswing;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import regras_negocio.Fachada;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class TelaCaixa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textVALOR;
	private JTextField textSENHA;
	private JTextField textDESTINO;
	private JTextField textID;
	private JTextField textCPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa frame = new TelaCaixa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCaixa() {
		setTitle("TelaCaixa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 342);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 208, 228));
		contentPane.setLayout(null);  // Definir o layout como nulo para posicionamento manual
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Criar e posicionar manualmente o JButton
		JButton btnNewButton = new JButton("Creditar");
		btnNewButton.setBounds(316, 127, 85, 30);  // Definir posição e tamanho
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Captura os valores dos campos
	        	String cpf = textCPF.getText();
	        	int id = Integer.parseInt(textID.getText());
	            String senha = textSENHA.getText();
	            double valor = Double.parseDouble(textVALOR.getText());
	     
	            // Tenta converter o valor para double
	            try {
////	                valor = Double.parseDouble(textValor.getText());
////	                int id = Integer.parseInt(textID.getText()); // Presumindo que você tenha o ID em textCONTA
//	                
//	                // Chama o método creditarValor
	                Fachada.creditarValor(id, cpf, senha, valor);
	                JOptionPane.showMessageDialog(null, "Valor creditado com sucesso!");

	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido.");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage()); // Exibe a mensagem de erro
	            }
	        }
	    });

		contentPane.add(btnNewButton);

		// Criar e posicionar manualmente o JTextField
		textVALOR = new JTextField();
		textVALOR.setBounds(316, 86, 85, 30);  // Definir posição e tamanho
		contentPane.add(textVALOR);
		textVALOR.setColumns(10);
		
		JButton btnDebitar = new JButton("Debitar");
		btnDebitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	String cpf = textCPF.getText();
		        String senha = textSENHA.getText();
		        double valor = Double.parseDouble(textVALOR.getText());
		        int id = Integer.parseInt(textID.getText());
		        try {
		        	Fachada.debitarValor(id, cpf, senha, valor);
	                JOptionPane.showMessageDialog(null, "Valor creditado com sucesso!");
		        }catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido.");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage()); // Exibe a mensagem de erro
	            }
			}
		});
		btnDebitar.setBounds(316, 167, 85, 30);
		contentPane.add(btnDebitar);

		
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	String cpf = textCPF.getText();
		        String senha = textSENHA.getText();
		        double valor = Double.parseDouble(textVALOR.getText());
		        int id = Integer.parseInt(textID.getText());
		        int id2 = Integer.parseInt(textDESTINO.getText());
		        try {
		        	Fachada.transferirValor(id, cpf, senha, valor, id2);
	                JOptionPane.showMessageDialog(null, "Valor transferido com sucesso para a conta "+ id2 +"!");
		        }catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido.");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, ex.getMessage()); // Exibe a mensagem de erro
	            }
			}
		});
		btnTransferir.setBounds(424, 127, 98, 30);
		contentPane.add(btnTransferir);
		
		textSENHA = new JTextField();
		textSENHA.setColumns(10);
		textSENHA.setBounds(206, 127, 85, 30);
		contentPane.add(textSENHA);
		
		textDESTINO = new JTextField();
		textDESTINO.setColumns(10);
		textDESTINO.setBounds(424, 86, 98, 30);
		contentPane.add(textDESTINO);
		
		textID = new JTextField();
		textID.setBounds(205, 85, 86, 30);
		contentPane.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(155, 133, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(173, 92, 22, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Valor:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(316, 61, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("ID destino:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(424, 61, 80, 14);
		contentPane.add(lblNewLabel_1_1_2);
		
		textCPF = new JTextField();
		textCPF.setColumns(10);
		textCPF.setBounds(206, 44, 85, 30);
		contentPane.add(textCPF);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("CPF:");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_3.setBounds(161, 50, 40, 14);
		contentPane.add(lblNewLabel_1_1_3);

	}	
}
