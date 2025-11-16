package br.com.musicboxd.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import br.com.musicboxd.DAO.ArtistaDAO.CantorDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Cantor;

public class TelaCadastroCantor {

	private JFrame frame;

	private JTextField lblNome;
	private JTextField lblGenero;
	private JTextField lblIdade;
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

		JLabel txtNome = new JLabel("Nome:");
		txtNome.setBounds(74, 70, 43, 14);
		frame.getContentPane().add(txtNome);

		lblNome = new JTextField();
		lblNome.setBounds(121, 67, 199, 20);
		frame.getContentPane().add(lblNome);
		lblNome.setColumns(10);

		JLabel txtGeneroMusical = new JLabel("Gênero Musical:");
		txtGeneroMusical.setBounds(28, 95, 89, 14);
		frame.getContentPane().add(txtGeneroMusical);

		lblGenero = new JTextField();
		lblGenero.setBounds(121, 95, 199, 20);
		frame.getContentPane().add(lblGenero);
		lblGenero.setColumns(10);

		JLabel txtIdade = new JLabel("Idade:");
		txtIdade.setBounds(72, 120, 45, 14);
		frame.getContentPane().add(txtIdade);

		lblIdade = new JTextField();
		lblIdade.setBounds(121, 120, 55, 20);
		frame.getContentPane().add(lblIdade);
		lblIdade.setColumns(10);

		JButton salvarLabel = new JButton("Salvar");
		salvarLabel.addActionListener(e ->  {
			try {
				String nome = lblNome.getText();
				String genero = lblGenero.getText();
				int idade = Integer.parseInt(lblIdade.getText());
				
				if (nome.equals("") || genero.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos para cadastrar um cantor!",
							"Erro no cadastro de cantor", JOptionPane.ERROR_MESSAGE);
				}
				
				CantorDAO cantorDAO = new CantorDAO();
				if (cantorDAO.existePorNome(nome)) {
					JOptionPane.showMessageDialog(null, "Cantor já cadastrado!",
							"Erro no cadastro de cantor", JOptionPane.ERROR_MESSAGE);
				}				
				
				Cantor cantor = new Cantor(nome, genero, idade);
				cantorDAO.salvar(cantor);
				JOptionPane.showMessageDialog(null, "Cantor cadastrado com sucesso!");
				limpar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar cantor!",
						"Erro no cadastro de cantor", JOptionPane.ERROR_MESSAGE);
			}
		});
		salvarLabel.setBounds(112, 151, 89, 23);
		frame.getContentPane().add(salvarLabel);

		JButton labelLimpar = new JButton("Limpar");
		labelLimpar.addActionListener(e -> limpar());
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
	
	private void limpar() {
		lblNome.setText("");
		lblGenero.setText("");
		lblIdade.setText("");
	}
}
