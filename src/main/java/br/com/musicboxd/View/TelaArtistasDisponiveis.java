package br.com.musicboxd.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.musicboxd.DAO.ArtistaDAO.ArtistaDAO;
import br.com.musicboxd.model.artistas.Artista;
import br.com.musicboxd.model.artistas.Banda;

public class TelaArtistasDisponiveis {

	private JFrame frmArtistasDisponiveis;
	private JTable tblArtistas;
	private List<Artista> listaArtistas;
	private TelaCadastroMusica telaCadastroMusica;


	public TelaArtistasDisponiveis(TelaCadastroMusica tela) {
		this.telaCadastroMusica = tela;
		initialize();
		this.frmArtistasDisponiveis.setVisible(true)
		;
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[]{"Nome", "Tipo", "Gênero"}, 0
		){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }};
		tblArtistas.setModel(modelo);
		
		ArtistaDAO artistaDAO = new ArtistaDAO();
		listaArtistas = artistaDAO.listarTodos();
		
		modelo.setRowCount(0);
		for (Artista artista : listaArtistas) {
			modelo.addRow(new Object[] {
					artista.getNome(),
					artista instanceof Banda ? "Banda" : "Cantor",
					artista.getGeneroMusical()
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArtistasDisponiveis = new JFrame();
		frmArtistasDisponiveis.setBounds(100, 100, 450, 300);
		frmArtistasDisponiveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArtistasDisponiveis.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 38, 391, 215);
		frmArtistasDisponiveis.getContentPane().add(scrollPane);
		
		tblArtistas = new JTable();
		tblArtistas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = tblArtistas.getSelectedRow();
				if (linha != -1) {
					Artista artistaSelecionado = listaArtistas.get(linha);
					telaCadastroMusica.setArtista(artistaSelecionado);
					frmArtistasDisponiveis.dispose();
				}
			}
		});
		scrollPane.setViewportView(tblArtistas);
	}
}
