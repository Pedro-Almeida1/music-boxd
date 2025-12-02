package br.com.musicboxd.view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.musicboxd.DAO.VitrolaDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.Vitrola;

public class TelaVitrolasDisponiveis {

	private JFrame frmVitrolasDisponiveis;
	private JTable table;
	private List<Vitrola> listaVitrolas;
	private Usuario usuario;
	private JButton btnVoltar;
	
	public TelaVitrolasDisponiveis(Usuario usuario) {
		this.usuario = usuario;
		initialize();
		this.frmVitrolasDisponiveis.setVisible(true);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[]{"Nome", "Modelo"}, 0
		){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }};
		table.setModel(modelo);
		
		VitrolaDAO vitrolaDAO = new VitrolaDAO();
		listaVitrolas = vitrolaDAO.listarTodos();
		
		modelo.setRowCount(0);
		for (Vitrola v : listaVitrolas) {
			modelo.addRow(new Object[] {
					v.getNome(),
					v.getModelo()
			});
		}
	}

	private void initialize() {
		frmVitrolasDisponiveis = new JFrame();
		frmVitrolasDisponiveis.setBounds(100, 100, 737, 300);
		frmVitrolasDisponiveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVitrolasDisponiveis.getContentPane().setLayout(null);
		frmVitrolasDisponiveis.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconCadastroMusica.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 703, 176);
		frmVitrolasDisponiveis.getContentPane().add(scrollPane);
				
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linha = table.getSelectedRow();
				if (linha != -1) {
					Vitrola vitrolaSelecionada = listaVitrolas.get(linha);
					 new TelaAvaliarVitrola(vitrolaSelecionada, usuario);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuario);
			frmVitrolasDisponiveis.dispose();
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVoltar.setBounds(628, 232, 85, 21);
		frmVitrolasDisponiveis.getContentPane().add(btnVoltar);
		
		frmVitrolasDisponiveis.setLocationRelativeTo(null);
	}
}
