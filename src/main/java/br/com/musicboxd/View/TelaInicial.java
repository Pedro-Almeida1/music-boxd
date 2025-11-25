package br.com.musicboxd.view;

import javax.swing.JFrame;
import br.com.musicboxd.model.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class TelaInicial {

	private JFrame frmTelaInicial;
	private Usuario usuarioLogado;

	public TelaInicial(Usuario usuario) {
		usuarioLogado = usuario;
		initialize();
		this.frmTelaInicial.setVisible(true);
	}
	
	private void initialize() {
		frmTelaInicial = new JFrame();
		frmTelaInicial.setBounds(100, 100, 593, 305);
		frmTelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelaInicial.getContentPane().setLayout(null);
		
		JLabel lblUsuarioNome = new JLabel("Bem vindo(a), " + usuarioLogado.getNome() + "!");
		lblUsuarioNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuarioNome.setBounds(204, 27, 270, 13);
		frmTelaInicial.getContentPane().add(lblUsuarioNome);
		
		JButton btnMusicasDisponiveis = new JButton("Músicas Disponíveis");
		btnMusicasDisponiveis.addActionListener(e -> {
			new TelaMusicasDisponiveis(usuarioLogado);
			frmTelaInicial.dispose();
		});
		btnMusicasDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMusicasDisponiveis.setBounds(10, 107, 176, 39);
		frmTelaInicial.getContentPane().add(btnMusicasDisponiveis);
		
		JButton btnCadastrarMusica = new JButton("Cadastrar Música");
		btnCadastrarMusica.addActionListener(e -> {
			new TelaCadastroMusica();
			frmTelaInicial.dispose();
		});
		btnCadastrarMusica.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarMusica.setBounds(10, 156, 176, 39);
		frmTelaInicial.getContentPane().add(btnCadastrarMusica);
		
		JButton btnVitrolasDisponiveis = new JButton("Vitrolas disponíveis");
		btnVitrolasDisponiveis.addActionListener(e -> {
			new TelaVitrolasDisponiveis(usuarioLogado);
			frmTelaInicial.dispose();
		});
		btnVitrolasDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVitrolasDisponiveis.setBounds(204, 134, 176, 39);
		frmTelaInicial.getContentPane().add(btnVitrolasDisponiveis);
		
		JButton btnDiscosDisponiveis = new JButton("Discos Disponíveis");
		btnDiscosDisponiveis.addActionListener(e -> {
			new TelaCantoresDisponiveis(usuarioLogado);
			frmTelaInicial.dispose();
		});
		btnDiscosDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDiscosDisponiveis.setBounds(390, 107, 179, 39);
		frmTelaInicial.getContentPane().add(btnDiscosDisponiveis);
		
		JButton btnCadastrarDisco = new JButton("Cadastrar Disco");
		btnCadastrarDisco.addActionListener(e -> {
			new TelaCadastroCantor(usuarioLogado);
			frmTelaInicial.dispose();
		});
		btnCadastrarDisco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarDisco.setBounds(390, 156, 179, 39);
		frmTelaInicial.getContentPane().add(btnCadastrarDisco);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(e -> {
			frmTelaInicial.dispose();
			new TelaLogin();
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSair.setBounds(484, 237, 85, 21);
		frmTelaInicial.getContentPane().add(btnSair);
		
		frmTelaInicial.setLocationRelativeTo(null);
	}
}
