package br.com.musicboxd.view;

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
	private JTextField lblEmail;
	private JTextField lblSenha;
	private JTextField lblNome;
	private JTextField textField;

	public TelaCadastro() {
		initialize();
		this.frame.setVisible(true);
	}

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
		
		lblEmail = new JTextField();
		lblEmail.setBounds(88, 61, 96, 18);
		frame.getContentPane().add(lblEmail);
		lblEmail.setColumns(10);
		
		lblSenha = new JTextField();
		lblSenha.setColumns(10);
		lblSenha.setBounds(88, 83, 96, 18);
		frame.getContentPane().add(lblSenha);
		
		JButton btnLogin = new JButton("Fazer login");
		btnLogin.addActionListener(e -> {

			new TelaLogin();
			frame.dispose();

		});
		btnLogin.setBounds(59, 168, 128, 20);
		frame.getContentPane().add(btnLogin);
		
		lblNome = new JTextField();
		lblNome.setColumns(10);
		lblNome.setBounds(90, 30, 96, 18);
		frame.getContentPane().add(lblNome);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeUsu, emailUsu, senhaUsu;
				
				
				nomeUsu = lblNome.getText();
				emailUsu = lblEmail.getText();
				senhaUsu = lblSenha.getText();
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
		lblNewLabel_2_1.setBounds(7, 106, 83, 28);
		frame.getContentPane().add(lblNewLabel_2_1);
	}
}
