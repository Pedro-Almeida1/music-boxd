package br.com.musicboxd.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin {

	private JFrame frmLogin;
	private JPasswordField txtSenha;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		if (nome.equals("admin") && senha.equals("123")) {
			JOptionPane.showMessageDialog(null, "Login correto! Bem vindo " + nome, "Login correto!", JOptionPane.INFORMATION_MESSAGE);
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
