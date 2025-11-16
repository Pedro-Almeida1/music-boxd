package br.com.musicboxd.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.ArtistaDAO.BandaDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Banda;

import java.awt.Font;

public class TelaCadastroBanda {

	private JFrame frmCadastroBanda;
	private JTextField txtNome;
	private JTextArea txtDescricao;
	private JTextField txtGenero;
	private Usuario usuarioLogado;

	public TelaCadastroBanda(Usuario usuario) {
		usuarioLogado = usuario;
		initialize();
		this.frmCadastroBanda.setVisible(true);
	}

	void realizarCadastro() {
		String textoNome = txtNome.getText();
		String textotxtDescricao = txtDescricao.getText();
		String textotxtGenero = txtGenero.getText();

		if ((textoNome.equals("")) || (textotxtGenero.equals("")) || (textotxtDescricao.equals(""))) {
			JOptionPane.showInternalMessageDialog(null, "Informe os dados para cadastro",
					"Todos os dados solicitados devem ser informados para btnCadastrar", JOptionPane.ERROR_MESSAGE);
		}

		Banda banda = new Banda(textoNome, textotxtGenero, textotxtDescricao);
		try {
			BandaDAO bandaDAO = new BandaDAO();

			if (bandaDAO.existePorNome(textoNome)) {
				JOptionPane.showMessageDialog(null, "Banda já cadastrada!",
						"Erro no cadastro de banda", JOptionPane.ERROR_MESSAGE);
				return;
			}

			bandaDAO.salvar(banda);
			JOptionPane.showMessageDialog(null, "Cadastro da banda " + banda.getNome() + " realizado com sucesso!",
					"Cadastro de banda concluído", JOptionPane.INFORMATION_MESSAGE);
			txtNome.setText("");
			txtDescricao.setText("");
			txtGenero.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao btnCadastrar banda! Tente novamente mais tarde",
					"Erro no cadastro de banda", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frmCadastroBanda.
	 */
	private void initialize() {
		frmCadastroBanda = new JFrame("Cadastre a banda");
		frmCadastroBanda.setSize(480, 480);
		frmCadastroBanda.setLocationRelativeTo(null);
		frmCadastroBanda.getContentPane().setLayout(null);
		frmCadastroBanda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNome = new JLabel("Nome da banda:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNome.setBounds(32, 40, 136, 50);
		frmCadastroBanda.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(32, 77, 190, 50);
		frmCadastroBanda.getContentPane().add(txtNome);

		JLabel lblGenero = new JLabel("Genero musical:");
		lblGenero.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblGenero.setBounds(242, 40, 200, 50);
		frmCadastroBanda.getContentPane().add(lblGenero);

		txtGenero = new JTextField();
		txtGenero.setBounds(242, 77, 190, 50);
		frmCadastroBanda.getContentPane().add(txtGenero);

		JLabel lblDescricao = new JLabel("Descrição da banda:");
		lblDescricao.setBounds(32, 138, 200, 43);
		lblDescricao.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmCadastroBanda.getContentPane().add(lblDescricao);

		txtDescricao = new JTextArea();
		txtDescricao.setBounds(32, 172, 400, 200);
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);
		frmCadastroBanda.getContentPane().add(txtDescricao);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(32, 383, 400, 50);
		btnCadastrar.addActionListener(e -> realizarCadastro());
		frmCadastroBanda.getContentPane().add(btnCadastrar);
		frmCadastroBanda.getRootPane().setDefaultButton(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuarioLogado);
			frmCadastroBanda.dispose();
		});
		btnVoltar.setBounds(32, 22, 89, 23);
		frmCadastroBanda.getContentPane().add(btnVoltar);

	}
}
