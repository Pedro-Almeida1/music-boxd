package br.com.musicboxd.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.UsuarioDAO;
import br.com.musicboxd.model.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastro {

	private JFrame frame;
	private JTextField email;
	private JTextField senha;
	private JTextField nome;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro window = new TelaCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastro() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 255, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(46, 33, 34, 12);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email: ");
		lblNewLabel_1.setBounds(46, 64, 34, 12);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha: ");
		lblNewLabel_2.setBounds(46, 86, 44, 12);
		frame.getContentPane().add(lblNewLabel_2);

		email = new JTextField();
		email.setBounds(78, 58, 96, 18);
		frame.getContentPane().add(email);
		email.setColumns(10);

		senha = new JTextField();
		senha.setColumns(10);
		senha.setBounds(78, 83, 96, 18);
		frame.getContentPane().add(senha);

		JButton btnLogin = new JButton("Fazer login");
		btnLogin.addActionListener(e -> {

			new TelaLogin();
			frame.dispose();

		});
		btnLogin.setBounds(59, 168, 128, 20);
		frame.getContentPane().add(btnLogin);

		nome = new JTextField();
		nome.setColumns(10);
		nome.setBounds(78, 30, 96, 18);
		frame.getContentPane().add(nome);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeUsu, emailUsu, senhaUsu;

				nomeUsu = nome.getText();
				emailUsu = email.getText();
				senhaUsu = senha.getText();
				Usuario usuario = new Usuario(nomeUsu, emailUsu, senhaUsu);

				try {
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					if (usuarioDAO.existePorEmail(emailUsu)) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Email já cadastrado",
								"Email já cadastrado", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					usuarioDAO.salvar(usuario);
					JOptionPane.showMessageDialog(null, "Cadastro concluído!\nRealize o login para acessar a aplicação",
							"Cadastro concluído", JOptionPane.INFORMATION_MESSAGE);
					new TelaLogin();
					frame.dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Tente novamente mais tarde",
							"Erro no cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(59, 138, 128, 20);
		frame.getContentPane().add(btnSalvar);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(78, 110, 96, 18);
		frame.getContentPane().add(textField);

		JLabel lblNewLabel_2_1 = new JLabel("Confirmar senha: ");
		lblNewLabel_2_1.setBounds(29, 116, 51, 12);
		frame.getContentPane().add(lblNewLabel_2_1);
	}
}
