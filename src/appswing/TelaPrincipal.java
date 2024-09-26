package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class TelaPrincipal {
	private JFrame frmSistemaBanko;
	private JMenu mnParticipante;
	private JMenu mnEvento;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmSistemaBanko.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
		frmSistemaBanko.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaBanko = new JFrame();
		frmSistemaBanko.setTitle("Sistema Banko - PereiraCoelho");
		frmSistemaBanko.setBounds(100, 100, 450, 363);
		frmSistemaBanko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaBanko.getContentPane().setLayout(null);

		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("Inicializando...");
		label.setBounds(0, 0, 450, 313);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/banco.jpg"));
		imagem = new ImageIcon(
				imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));// label.setIcon(fotos);
		label.setIcon(imagem);
		frmSistemaBanko.getContentPane().add(label);
		frmSistemaBanko.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		frmSistemaBanko.setJMenuBar(menuBar);

		mnEvento = new JMenu("Conta");
		mnEvento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaContas tela = new TelaContas();
			}
		});
		menuBar.add(mnEvento);
		mnParticipante = new JMenu("Correntista");
		mnParticipante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCorrentistas tela = new TelaCorrentistas();
			}
		});
		menuBar.add(mnParticipante);
		
				JMenu mnCaixa = new JMenu("Caixa");
				menuBar.add(mnCaixa);

		// Adicionar o MouseListener no JMenu
		mnCaixa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Quando o menu for clicado, abrir a TelaConta
				TelaCaixa TelaCaixa = new TelaCaixa();
				TelaCaixa.setVisible(true); // Tornar a TelaConta visível
			}
		});
	}
}
