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
		
		JButton btnBandasDisponiveis = new JButton("Bandas disponíveis");
		btnBandasDisponiveis.addActionListener(e -> {
			new TelasBandasDisponiveis();
			frmTelaInicial.dispose();
		});
		btnBandasDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBandasDisponiveis.setBounds(204, 107, 176, 39);
		frmTelaInicial.getContentPane().add(btnBandasDisponiveis);
		
		JButton btnCadastrarBanda = new JButton("Cadastrar Banda");
		btnCadastrarBanda.addActionListener(e -> {
			new TelaCadastroBanda(usuarioLogado);
			frmTelaInicial.dispose();
		});
		btnCadastrarBanda.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarBanda.setBounds(204, 156, 176, 39);
		frmTelaInicial.getContentPane().add(btnCadastrarBanda);
		
		JButton btnCantoresDisponiveis = new JButton("Cantores Disponíveis");
		btnCantoresDisponiveis.addActionListener(e -> {
			new TelaCantoresDisponiveis();
			frmTelaInicial.dispose();
		});
		btnCantoresDisponiveis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCantoresDisponiveis.setBounds(390, 107, 179, 39);
		frmTelaInicial.getContentPane().add(btnCantoresDisponiveis);
		
		JButton btnCadastrarCantor = new JButton("Cadastrar Cantor");
		btnCadastrarCantor.addActionListener(e -> {
			new TelaCadastroCantor();
			frmTelaInicial.dispose();
		});
		btnCadastrarCantor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarCantor.setBounds(390, 156, 179, 39);
		frmTelaInicial.getContentPane().add(btnCadastrarCantor);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(e -> {
			frmTelaInicial.dispose();
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSair.setBounds(484, 237, 85, 21);
		frmTelaInicial.getContentPane().add(btnSair);
		
		frmTelaInicial.setLocationRelativeTo(null);
	}
}
