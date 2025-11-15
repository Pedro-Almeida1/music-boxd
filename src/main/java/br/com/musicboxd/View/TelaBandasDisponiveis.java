package br.com.musicboxd.view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.musicboxd.DAO.ArtistaDAO.BandaDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Banda;

public class TelaBandasDisponiveis {

	private JFrame frmBandasDisponiveis;
	private JTable table;
	private List<Banda> listaBandas;
	private Usuario usuario;
	private JButton btnVoltar;
	
	public TelaBandasDisponiveis(Usuario usuario) {
		this.usuario = usuario;
		initialize();
		this.frmBandasDisponiveis.setVisible(true);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[]{"Nome", "Gênero", "Descrição"}, 0
		){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }};
		table.setModel(modelo);
		
		BandaDAO bandaDAO = new BandaDAO();
		listaBandas = bandaDAO.listarTodos();
		
		modelo.setRowCount(0);
		for (Banda b : listaBandas) {
			modelo.addRow(new Object[] {
					b.getNome(),
					b.getGeneroMusical(),
					b.getDescricao()
			});
		}
	}

	private void initialize() {
		frmBandasDisponiveis = new JFrame();
		frmBandasDisponiveis.setBounds(100, 100, 737, 300);
		frmBandasDisponiveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBandasDisponiveis.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 703, 176);
		frmBandasDisponiveis.getContentPane().add(scrollPane);
				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.getSelectedRow();
				if (linha != -1) {
					Banda bandaSelecionada = listaBandas.get(linha);
					 new TelaAvaliarBanda(bandaSelecionada, usuario);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuario);
			frmBandasDisponiveis.dispose();
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(628, 232, 85, 21);
		frmBandasDisponiveis.getContentPane().add(btnVoltar);
		
		frmBandasDisponiveis.setLocationRelativeTo(null);
	}
}
