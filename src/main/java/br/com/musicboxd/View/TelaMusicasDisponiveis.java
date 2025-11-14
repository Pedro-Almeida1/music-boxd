package br.com.musicboxd.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.musicboxd.DAO.MusicaDAO;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;

public class TelaMusicasDisponiveis {

	private JFrame frmMusicasDisponiveis;
	private JTable table;
	private List<Musica> listaMusicas;
	private Usuario usuario;
	private JButton btnVoltar;
	
	public TelaMusicasDisponiveis(Usuario usuario) {
		this.usuario = usuario;
		initialize();
		this.frmMusicasDisponiveis.setVisible(true);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[]{"Nome", "Artista", "Gênero"}, 0
		){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }};
		table.setModel(modelo);
		
		MusicaDAO musicaDAO = new MusicaDAO();
		listaMusicas = musicaDAO.listarTodos();
		
		modelo.setRowCount(0);
		for (Musica m : listaMusicas) {
			modelo.addRow(new Object[] {
					m.getTitulo(),
					m.getArtista().getNome(),
					m.getGenero()
			});
		}
	}

	private void initialize() {
		frmMusicasDisponiveis = new JFrame();
		frmMusicasDisponiveis.setBounds(100, 100, 544, 300);
		frmMusicasDisponiveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusicasDisponiveis.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 510, 176);
		frmMusicasDisponiveis.getContentPane().add(scrollPane);
				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.getSelectedRow();
				if (linha != -1) {
					Musica musicaSelecionada = listaMusicas.get(linha);
					new TelaAvaliarMusica(musicaSelecionada, usuario);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuario);
			frmMusicasDisponiveis.dispose();
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(435, 232, 85, 21);
		frmMusicasDisponiveis.getContentPane().add(btnVoltar);
		
		frmMusicasDisponiveis.setLocationRelativeTo(null);
	}
}
