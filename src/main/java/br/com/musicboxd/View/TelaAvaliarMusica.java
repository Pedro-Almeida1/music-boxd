package br.com.musicboxd.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import br.com.musicboxd.DAO.AvaliacaoDAO.AvaliacaoMusicaDAO;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.avaliacoes.Avaliacao;

import javax.swing.JButton;

public class TelaAvaliarMusica {

	private JFrame frmAvaliarMusica;
	private Musica musica;
	private Usuario usuario;
	private double mediaAvaliacoes;
	private int quantidadeAvaliacoes;
//	private ImageIcon estrelaVazia;

	public TelaAvaliarMusica(Musica musica, Usuario usuario) {
		this.musica = musica;
		this.usuario = usuario;
		
		AvaliacaoMusicaDAO avaliacaoMusicaDAO = new AvaliacaoMusicaDAO();
		var avaliacoes = avaliacaoMusicaDAO.buscaPorMusica(musica);
		this.mediaAvaliacoes = avaliacoes
				.stream()
				.mapToDouble(Avaliacao::getNota)
				.average()
				.orElse(0.0);
		this.quantidadeAvaliacoes = avaliacoes.size();
		initialize();
		this.frmAvaliarMusica.setVisible(true);
	}

	private void initialize() {
		frmAvaliarMusica = new JFrame();
		frmAvaliarMusica.setBounds(100, 100, 393, 183);
		frmAvaliarMusica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAvaliarMusica.getContentPane().setLayout(null);
		
		JLabel lblNomeMusica = new JLabel("Música: " + musica.getTitulo());
		lblNomeMusica.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomeMusica.setBounds(20, 10, 359, 38);
		frmAvaliarMusica.getContentPane().add(lblNomeMusica);
		
//		estrelaVazia = new ImageIcon(getClass().getResource("/estrela_vazia1.png"));
//		URL url = getClass().getResource("/estrela_vazia.png");
//		System.out.println(url);

		JButton btnNota1 = new JButton("1");
		btnNota1.addActionListener(e -> avaliar(1.0));
		btnNota1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota1.setBounds(27, 91, 53, 27);
		frmAvaliarMusica.getContentPane().add(btnNota1);
		
//		Image imagemRedimensionada = estrelaVazia.getImage().getScaledInstance(
//				btnNota1.getWidth(),
//				btnNota1.getHeight(),
//				Image.SCALE_SMOOTH
//				);
//		btnNota1.setIcon(new ImageIcon(imagemRedimensionada));
		
		JButton btnNota2 = new JButton("2");
		btnNota2.addActionListener(e -> avaliar(2.0));
		btnNota2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota2.setBounds(90, 92, 53, 27);
		frmAvaliarMusica.getContentPane().add(btnNota2);
		
		JButton btnNota3 = new JButton("3");
		btnNota3.addActionListener(e -> avaliar(3.0));
		btnNota3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota3.setBounds(153, 92, 53, 27);
		frmAvaliarMusica.getContentPane().add(btnNota3);
		
		JButton btnNota4 = new JButton("4");
		btnNota4.addActionListener(e -> avaliar(4.0));
		btnNota4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota4.setBounds(216, 91, 53, 27);
		frmAvaliarMusica.getContentPane().add(btnNota4);
		
		JButton btnNota5 = new JButton("5");
		btnNota5.addActionListener(e -> avaliar(5.0));
		btnNota5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota5.setBounds(279, 91, 53, 28);
		frmAvaliarMusica.getContentPane().add(btnNota5);
		
		JLabel lblNotaMedia = new JLabel("Nota média: " + mediaAvaliacoes + " (" + quantidadeAvaliacoes + ")");
		lblNotaMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNotaMedia.setBounds(20, 43, 349, 38);
		frmAvaliarMusica.getContentPane().add(lblNotaMedia);
		frmAvaliarMusica.setLocationRelativeTo(null);
	}
	
	private void avaliar(double nota) {
		AvaliacaoMusicaDAO avaliacaoMusicaDAO = new AvaliacaoMusicaDAO();
	
		try {
			avaliacaoMusicaDAO.salvarOuAtualizar(musica, usuario, nota);
			JOptionPane.showMessageDialog(null, "Avaliação registrada com sucesso!", "Avaliação feita", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao avaliar música!\nTente novamente mais tarde", "Erro ao avaliar música", JOptionPane.ERROR_MESSAGE);
		} finally {
			frmAvaliarMusica.dispose();
		}
	}
}
