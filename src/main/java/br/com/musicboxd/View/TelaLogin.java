package br.com.musicboxd.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.musicboxd.DAO.UsuarioDAO;
import br.com.musicboxd.model.Usuario;

import java.awt.Component;

public class TelaLogin {

	private JFrame frmLogin;
	private JPasswordField txtSenha;
	private JTextField txtNome;

	public TelaLogin() {
		initialize();
		this.frmLogin.setVisible(true);
	}
	
	void autenticar() {
		String nome = txtNome.getText();
		String senha = new String(txtSenha.getPassword());
		
		if (nome.equals("") || senha.equals("")) {
			JOptionPane.showInternalMessageDialog(null, "Informe o login e senha!", "Login e senha precisam ser fornecidos", JOptionPane.ERROR_MESSAGE);
			return;
		}

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioBD = usuarioDAO.buscarPorNomeESenha(nome, senha);
		
		if (usuarioBD != null) {
			JOptionPane.showMessageDialog(null, "Login correto! Bem vindo, " + usuarioBD.getNome(), "Login correto!", JOptionPane.INFORMATION_MESSAGE);
			new TelaInicial(usuarioBD);
			frmLogin.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Login incorreto!", "Erro ao realizar login", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 233, 246);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconCadastroMusica.png")));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 36, 75, 20);
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmLogin.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 79, 75, 20);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmLogin.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(69, 82, 113, 19);
		frmLogin.getContentPane().add(txtSenha);
		
		txtNome = new JTextField();
		txtNome.setBounds(69, 39, 113, 19);
		frmLogin.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> autenticar());
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(41, 122, 141, 29);
		frmLogin.getContentPane().add(btnLogin);
		frmLogin.getRootPane().setDefaultButton(btnLogin); // Botão login sempre acionado ao apertar enter
		
		JButton btnCadastro = new JButton("Cadastrar-se");
		btnCadastro.addActionListener(e -> {
			new TelaCadastro();
			frmLogin.dispose();
		});
		btnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastro.setBounds(41, 161, 141, 29);
		frmLogin.getContentPane().add(btnCadastro);
		frmLogin.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNome, txtSenha, btnLogin, btnCadastro}));
		frmLogin.setLocationRelativeTo(null);
	}
}
