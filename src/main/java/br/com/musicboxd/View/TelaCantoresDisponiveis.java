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

import br.com.musicboxd.DAO.ArtistaDAO.CantorDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Cantor;

public class TelaCantoresDisponiveis {

	private JFrame frmCantoresDisponiveis;
	private JTable tblCantores;
	private List<Cantor> listaCantores;
	private Usuario usuario;
	private JButton btnVoltar;
	
	public TelaCantoresDisponiveis(Usuario usuario) {
		this.usuario = usuario;
		initialize();
		this.frmCantoresDisponiveis.setVisible(true);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[]{"Nome", "Gênero", "Idade"}, 0
		){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }};
		tblCantores.setModel(modelo);
		
		CantorDAO cantorDAO = new CantorDAO();
		listaCantores = cantorDAO.listarTodos();
		
		modelo.setRowCount(0);
		for (Cantor c : listaCantores) {
			modelo.addRow(new Object[] {
					c.getNome(),
					c.getGeneroMusical(),
					c.getIdade()
			});
		}
	}

	private void initialize() {
		frmCantoresDisponiveis = new JFrame();
		frmCantoresDisponiveis.setBounds(100, 100, 737, 300);
		frmCantoresDisponiveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCantoresDisponiveis.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 703, 176);
		frmCantoresDisponiveis.getContentPane().add(scrollPane);
				
		tblCantores = new JTable();
		tblCantores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = tblCantores.getSelectedRow();
				if (linha != -1) {
					Cantor cantorSelecionado = listaCantores.get(linha);
					// new TelaAvaliarCantor(cantorSelecionado, usuario);
				}
			}
		});
		scrollPane.setViewportView(tblCantores);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuario);
			frmCantoresDisponiveis.dispose();
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(628, 232, 85, 21);
		frmCantoresDisponiveis.getContentPane().add(btnVoltar);
		
		frmCantoresDisponiveis.setLocationRelativeTo(null);
	}

}
