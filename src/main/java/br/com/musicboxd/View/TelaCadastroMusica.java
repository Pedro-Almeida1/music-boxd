package br.com.musicboxd.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.MusicaDAO;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Artista;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class TelaCadastroMusica {

	private JFrame frmCadastrarMusica;
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private JTextField txtAnoDeLancamento;
	private JTextField txtDuracao;
	private JLabel lblNewLabel;
	private JLabel lblArtista;
	private JTextField txtArtista;
	private Artista artista;
	private JButton btnVoltar;
	private Usuario usuario;


	public void setArtista(Artista artista) {
		this.artista = artista;
		this.txtArtista.setText(artista.getNome());
	}
	
	public TelaCadastroMusica(Usuario usuario) {
	    this.usuario = usuario; 
	    initialize();
	    this.frmCadastrarMusica.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastrarMusica = new JFrame();
		frmCadastrarMusica.setTitle("Cadastrar Música");
		frmCadastrarMusica.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconCadastroMusica.png")));
		frmCadastrarMusica.setBounds(100, 100, 399, 409);
		frmCadastrarMusica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastrarMusica.getContentPane().setLayout(null);
		frmCadastrarMusica.setLocationRelativeTo(null);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitulo.setBounds(91, 69, 67, 23);
		frmCadastrarMusica.getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(147, 70, 86, 20);
		frmCadastrarMusica.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genêro:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenero.setBounds(82, 118, 76, 14);
		frmCadastrarMusica.getContentPane().add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(147, 115, 86, 20);
		frmCadastrarMusica.getContentPane().add(txtGenero);
		
		JLabel lblAnoDeLanamento = new JLabel("Ano de lançamento:");
		lblAnoDeLanamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAnoDeLanamento.setBounds(25, 165, 181, 14);
		frmCadastrarMusica.getContentPane().add(lblAnoDeLanamento);
		
		txtAnoDeLancamento = new JTextField();
		txtAnoDeLancamento.setColumns(10);
		txtAnoDeLancamento.setBounds(147, 162, 86, 20);
		frmCadastrarMusica.getContentPane().add(txtAnoDeLancamento);
		
		JLabel lblDuracao = new JLabel("Duração (min):");
		lblDuracao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuracao.setBounds(44, 213, 98, 14);
		frmCadastrarMusica.getContentPane().add(lblDuracao);
		
		txtDuracao = new JTextField();
		txtDuracao.setColumns(10);
		txtDuracao.setBounds(147, 207, 86, 20);
		frmCadastrarMusica.getContentPane().add(txtDuracao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String titulo = txtTitulo.getText();
					String genero = txtGenero.getText();
					int ano = Integer.parseInt(txtAnoDeLancamento.getText());
					int duracao = Integer.parseInt(txtDuracao.getText());
					
					if (titulo.isEmpty() || genero.isEmpty() || String.valueOf(ano).isEmpty() || String.valueOf(duracao).isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos para cadastrar uma música!",
								"Erro no cadastro de música", JOptionPane.ERROR_MESSAGE);
						return;
					}

					Musica musicanova = new Musica(titulo, ano, genero, duracao, artista);
					MusicaDAO musicaDAO = new MusicaDAO();
					
					if (musicaDAO.existePorNome(musicanova.getTitulo())) {
						JOptionPane.showMessageDialog(null, "Música já cadastrada!",
								"Erro no cadastro de música", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					musicaDAO.salvar(musicanova);
					JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso!\n" + "Título: " + titulo);
					
					txtTitulo.setText("");
					txtGenero.setText("");
					txtAnoDeLancamento.setText("");
					txtDuracao.setText("");
					txtArtista.setText("");
					artista = null;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erro: ano e duração devem ser números!");
				}
			}
		});
		btnCadastrar.setBounds(133, 316, 118, 46);
		frmCadastrarMusica.getContentPane().add(btnCadastrar);
		
		lblNewLabel = new JLabel("Cadastrar Música");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 22, 164, 34);
		frmCadastrarMusica.getContentPane().add(lblNewLabel);
		
		lblArtista = new JLabel("Artista:");
		lblArtista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblArtista.setBounds(88, 253, 49, 14);
		frmCadastrarMusica.getContentPane().add(lblArtista);
		
		txtArtista = new JTextField();
		txtArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaArtistasDisponiveis(TelaCadastroMusica.this);
			}
		});
		txtArtista.setColumns(10);
		txtArtista.setBounds(147, 252, 86, 20);
		frmCadastrarMusica.getContentPane().add(txtArtista);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaInicial(usuario);
				frmCadastrarMusica.dispose();
			}
		});
		btnVoltar.setBounds(10, 11, 89, 23);
		frmCadastrarMusica.getContentPane().add(btnVoltar);

		btnVoltar.setBounds(10, 11, 89, 23);
		frmCadastrarMusica.getContentPane().add(btnVoltar);
	}
}
