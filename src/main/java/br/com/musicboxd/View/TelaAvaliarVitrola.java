package br.com.musicboxd.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.musicboxd.DAO.AvaliacaoDAO.AvaliacaoDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.Vitrola;
import br.com.musicboxd.model.avaliacoes.Avaliacao;
import br.com.musicboxd.model.avaliacoes.AvaliacaoBanda;
import br.com.musicboxd.model.avaliacoes.AvaliacaoVitrola;

public class TelaAvaliarVitrola {
	private JFrame frmAvaliarVitrola;
	private Vitrola vitrola;
	private Usuario usuario;
	private double mediaAvaliacoes;
	private int quantidadeAvaliacoes;

	public TelaAvaliarVitrola(Vitrola vitrola, Usuario usuario) {
		this.vitrola = vitrola;
		this.usuario = usuario;
		
		AvaliacaoDAO<AvaliacaoVitrola> avaliacaoDAO = new AvaliacaoDAO<>(AvaliacaoVitrola.class);
		var avaliacoes = avaliacaoDAO.buscarAvaliacoes(vitrola.getId());
		this.mediaAvaliacoes = avaliacoes
				.stream()
				.mapToDouble(Avaliacao::getNota)
				.average()
				.orElse(0.0);
		this.quantidadeAvaliacoes = avaliacoes.size();
		initialize();
		this.frmAvaliarVitrola.setVisible(true);
	}

	private void initialize() {
		frmAvaliarVitrola = new JFrame();
		frmAvaliarVitrola.setBounds(100, 100, 393, 243);
		frmAvaliarVitrola.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAvaliarVitrola.getContentPane().setLayout(null);
		
		JLabel lblNomeMusica = new JLabel("Vitrola: " + vitrola.getNome());
		lblNomeMusica.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNomeMusica.setBounds(20, 10, 359, 38);
		frmAvaliarVitrola.getContentPane().add(lblNomeMusica);
		
		JButton btnNota1 = new JButton("1");
		btnNota1.addActionListener(e -> avaliar(1.0));
		btnNota1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota1.setBounds(27, 168, 53, 27);
		frmAvaliarVitrola.getContentPane().add(btnNota1);
		
		JButton btnNota2 = new JButton("2");
		btnNota2.addActionListener(e -> avaliar(2.0));
		btnNota2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota2.setBounds(90, 169, 53, 27);
		frmAvaliarVitrola.getContentPane().add(btnNota2);
		
		JButton btnNota3 = new JButton("3");
		btnNota3.addActionListener(e -> avaliar(3.0));
		btnNota3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota3.setBounds(153, 169, 53, 27);
		frmAvaliarVitrola.getContentPane().add(btnNota3);
		
		JButton btnNota4 = new JButton("4");
		btnNota4.addActionListener(e -> avaliar(4.0));
		btnNota4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota4.setBounds(216, 168, 53, 27);
		frmAvaliarVitrola.getContentPane().add(btnNota4);
		
		JButton btnNota5 = new JButton("5");
		btnNota5.addActionListener(e -> avaliar(5.0));
		btnNota5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNota5.setBounds(279, 168, 53, 28);
		frmAvaliarVitrola.getContentPane().add(btnNota5);
		
		JLabel lblNotaMedia = new JLabel("Nota média: " + mediaAvaliacoes + " (" + quantidadeAvaliacoes + ")");
		lblNotaMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNotaMedia.setBounds(20, 120, 349, 38);
		frmAvaliarVitrola.getContentPane().add(lblNotaMedia);
		
		JLabel lblModelo = new JLabel(vitrola.getModelo());
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModelo.setBounds(20, 58, 349, 59);
		frmAvaliarVitrola.getContentPane().add(lblModelo);
		frmAvaliarVitrola.setLocationRelativeTo(null);
	}
	
	private void avaliar(double nota) {
		AvaliacaoDAO<AvaliacaoBanda> avaliacaoDAO = new AvaliacaoDAO<>(AvaliacaoBanda.class);
	
		try {
			avaliacaoDAO.salvarOuAtualizar(vitrola.getId(), usuario, nota, vitrola);
			JOptionPane.showMessageDialog(null, "Avaliação registrada com sucesso!", "Avaliação feita", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao avaliar vitrola!\nTente novamente mais tarde", "Erro ao avaliar banda", JOptionPane.ERROR_MESSAGE);
		} finally {
			frmAvaliarVitrola.dispose();
		}
	}
	}
