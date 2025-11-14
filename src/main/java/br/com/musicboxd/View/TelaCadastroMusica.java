package br.com.musicboxd.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.MusicaDAO;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.artistas.Artista;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class TelaCadastroMusica {

	private JFrame frmCadastrarMsica;
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private JTextField txtAnoDeLancamento;
	private JTextField txtDuracao;
	private JLabel lblNewLabel;
	private JLabel lblArtista;
	private JTextField txtArtista;
	private Artista artista;


	public void setArtista(Artista artista) {
		this.artista = artista;
		this.txtArtista.setText(artista.getNome());
	}
	
	public TelaCadastroMusica() {
		initialize();
		this.frmCadastrarMsica.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastrarMsica = new JFrame();
		frmCadastrarMsica.setTitle("Cadastrar Música");
		frmCadastrarMsica.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\pedro\\Downloads\\telacadastro.png"));
		frmCadastrarMsica.setBounds(100, 100, 399, 409);
		frmCadastrarMsica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastrarMsica.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(91, 69, 30, 23);
		frmCadastrarMsica.getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(147, 70, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genêro:");
		lblGenero.setBounds(82, 118, 39, 14);
		frmCadastrarMsica.getContentPane().add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(147, 115, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtGenero);
		
		JLabel lblAnoDeLanamento = new JLabel("Ano de lançamento:");
		lblAnoDeLanamento.setBounds(25, 165, 96, 14);
		frmCadastrarMsica.getContentPane().add(lblAnoDeLanamento);
		
		txtAnoDeLancamento = new JTextField();
		txtAnoDeLancamento.setColumns(10);
		txtAnoDeLancamento.setBounds(147, 162, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtAnoDeLancamento);
		
		JLabel lblDuracao = new JLabel("Duração (min):");
		lblDuracao.setBounds(50, 210, 71, 14);
		frmCadastrarMsica.getContentPane().add(lblDuracao);
		
		txtDuracao = new JTextField();
		txtDuracao.setColumns(10);
		txtDuracao.setBounds(147, 207, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtDuracao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String titulo = txtTitulo.getText();
					String genero = txtGenero.getText();
					int ano = Integer.parseInt(txtAnoDeLancamento.getText());
					int duracao = Integer.parseInt(txtDuracao.getText());
					
					if (titulo == "" || genero == "" || artista == null) {
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
		frmCadastrarMsica.getContentPane().add(btnCadastrar);
		
		lblNewLabel = new JLabel("Cadastrar Música");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 22, 126, 34);
		frmCadastrarMsica.getContentPane().add(lblNewLabel);
		
		lblArtista = new JLabel("Artista:");
		lblArtista.setBounds(50, 254, 71, 14);
		frmCadastrarMsica.getContentPane().add(lblArtista);
		
		txtArtista = new JTextField();
		txtArtista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaArtistasDisponiveis(TelaCadastroMusica.this);
			}
		});
		txtArtista.setColumns(10);
		txtArtista.setBounds(147, 252, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtArtista);
	}
}
