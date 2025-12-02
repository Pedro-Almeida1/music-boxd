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
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Toolkit;

public class TelaCadastro {

	private JFrame frame;
	private JTextField lblEmail;
	private JTextField lblNome;
	private JPasswordField lblSenha;
	private JPasswordField lblConfirmarSenha;

	public TelaCadastro() {
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 348, 258);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconCadastroMusica.png")));

		JLabel txtNome = new JLabel("Nome:");
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNome.setBounds(13, 12, 70, 12);
		frame.getContentPane().add(txtNome);

		JLabel txtEmail = new JLabel("Email: ");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setBounds(13, 41, 70, 12);
		frame.getContentPane().add(txtEmail);

		JLabel txtSenha = new JLabel("Senha: ");
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSenha.setBounds(10, 69, 96, 12);
		frame.getContentPane().add(txtSenha);
		
		lblEmail = new JTextField();
		lblEmail.setBounds(131, 38, 96, 18);
		frame.getContentPane().add(lblEmail);
		lblEmail.setColumns(10);
		
		JButton btnLogin = new JButton("Fazer login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.addActionListener(e -> {
			new TelaLogin();
			frame.dispose();
		});
		btnLogin.setBounds(107, 170, 128, 20);
		frame.getContentPane().add(btnLogin);
		
		lblNome = new JTextField();
		lblNome.setColumns(10);
		lblNome.setBounds(131, 10, 96, 18);
		frame.getContentPane().add(lblNome);
		
		lblSenha = new JPasswordField();
		lblSenha.setBounds(131, 66, 96, 19);
		frame.getContentPane().add(lblSenha);
		
		lblConfirmarSenha = new JPasswordField();
		lblConfirmarSenha.setBounds(131, 94, 96, 19);
		frame.getContentPane().add(lblConfirmarSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeUsu, emailUsu, senhaUsu, confirmarSenhaUsu;
				nomeUsu = lblNome.getText();
				emailUsu = lblEmail.getText();
				senhaUsu = new String(lblSenha.getPassword());
				confirmarSenhaUsu = new String(lblConfirmarSenha.getPassword());
				
				if (nomeUsu.equals("") || emailUsu.equals("") || senhaUsu.equals("")) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar!\nPreencha todos os campos",
							"Dados incompletos", JOptionPane.ERROR_MESSAGE);		
					return;
				}

				if (!senhaUsu.equals(confirmarSenhaUsu)) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar!\nOs campos de senha devem ser idênticos!",
							"Senhas não conferem", JOptionPane.ERROR_MESSAGE);		
					return;
				}
				
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
		btnSalvar.setBounds(107, 140, 128, 20);
		frame.getContentPane().add(btnSalvar);
		frame.getRootPane().setDefaultButton(btnSalvar);

		JLabel txtConfirmarSenha = new JLabel("Confirmar senha: ");
		txtConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtConfirmarSenha.setBounds(10, 90, 111, 28);
		frame.getContentPane().add(txtConfirmarSenha);
		
		frame.setLocationRelativeTo(null);

	}
}
