package br.com.musicboxd.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import br.com.musicboxd.model.Usuario;

public class TelaCadastroCantor {

	private JFrame frame;

	private JTextField lblNome;
	private JTextField lblGenero;
	private JTextField lblAnos;
	private Usuario usuarioLogado;

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCantor window = new TelaCadastroCantor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} */

	/**
	 * Create the application.
	 */
	public TelaCadastroCantor(Usuario usuario) {
		usuarioLogado = usuario;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(74, 70, 43, 14);
		frame.getContentPane().add(lblNewLabel);

		lblNome = new JTextField();
		lblNome.setBounds(121, 67, 199, 20);
		frame.getContentPane().add(lblNome);
		lblNome.setColumns(10);

		JLabel lblGeneroMusical = new JLabel("Genero Musical:");
		lblGeneroMusical.setBounds(28, 95, 89, 14);
		frame.getContentPane().add(lblGeneroMusical);

		lblGenero = new JTextField();
		lblGenero.setBounds(121, 95, 199, 20);
		frame.getContentPane().add(lblGenero);
		lblGenero.setColumns(10);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(72, 120, 45, 14);
		frame.getContentPane().add(lblIdade);

		lblAnos = new JTextField();
		lblAnos.setBounds(121, 120, 55, 20);
		frame.getContentPane().add(lblAnos);
		lblAnos.setColumns(10);

		JButton salvarLabel = new JButton("Salvar");
		salvarLabel.addActionListener(e ->  {
			String nome = lblNome.getText();
			String genero = lblGenero.getText();
			int idade = Integer.parseInt(lblIdade.getText());
		});
		salvarLabel.setBounds(112, 151, 89, 23);
		frame.getContentPane().add(salvarLabel);

		JButton labelLimpar = new JButton("Limpar");
		labelLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNome.setText("");
				lblGenero.setText("");
				lblAnos.setText("");
			}
		});
		labelLimpar.setBounds(231, 151, 89, 23);
		frame.getContentPane().add(labelLimpar);

		JButton voltarLabel = new JButton("Voltar");
		voltarLabel.addActionListener(e -> {
			new TelaInicial(usuarioLogado);
			frame.dispose();
		});
		voltarLabel.setBounds(112, 195, 89, 23);
		frame.getContentPane().add(voltarLabel);
	}
}
