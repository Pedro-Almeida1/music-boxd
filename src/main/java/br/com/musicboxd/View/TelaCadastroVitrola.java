package br.com.musicboxd.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.VitrolaDAO;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.Vitrola;
import java.awt.Font;
import java.awt.Toolkit;

public class TelaCadastroVitrola {

	private JFrame frmCadastroVitrola;
	private JTextField txtNome;
	private JTextArea txtModelo;
	private Usuario usuarioLogado;

	public TelaCadastroVitrola(Usuario usuario) {
		usuarioLogado = usuario;
		initialize();
		this.frmCadastroVitrola.setVisible(true);
	}

	void realizarCadastro() {
		String nome = txtNome.getText();
		String modelo = txtModelo.getText();

		if ((nome.equals("")) || (modelo.equals(""))) {
			JOptionPane.showInternalMessageDialog(null, "Informe os dados para cadastro",
					"Todos os dados solicitados devem ser informados para btnCadastrar", JOptionPane.ERROR_MESSAGE);
		}

		Vitrola vitrola = new Vitrola(nome, modelo);
		try {
			VitrolaDAO vitrolaDAO = new VitrolaDAO();

			if (vitrolaDAO.existePorNome(nome)) {
				JOptionPane.showMessageDialog(null, "Vitrola já cadastrada!",
						"Erro no cadastro de vitrola", JOptionPane.ERROR_MESSAGE);
				return;
			}

			vitrolaDAO.salvar(vitrola);
			JOptionPane.showMessageDialog(null, "Cadastro da vitrola " + vitrola.getNome() + " realizado com sucesso!",
					"Cadastro de vitrola concluído", JOptionPane.INFORMATION_MESSAGE);
			txtNome.setText("");
			txtModelo.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar vitrola! Tente novamente mais tarde",
					"Erro no cadastro de vitrola", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frmCadastroBanda.
	 */
	private void initialize() {
		frmCadastroVitrola = new JFrame("Cadastre a vitrola");
		frmCadastroVitrola.setSize(480, 480);
		frmCadastroVitrola.setLocationRelativeTo(null);
		frmCadastroVitrola.getContentPane().setLayout(null);
		frmCadastroVitrola.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroVitrola.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconCadastroMusica.png")));

		JLabel lblNome = new JLabel("Nome da vitrola:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNome.setBounds(32, 40, 175, 50);
		frmCadastroVitrola.getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(32, 77, 190, 50);
		frmCadastroVitrola.getContentPane().add(txtNome);

		JLabel lblmodelo = new JLabel("Modelo da vitrola:");
		lblmodelo.setBounds(32, 138, 200, 43);
		lblmodelo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmCadastroVitrola.getContentPane().add(lblmodelo);

		txtModelo = new JTextArea();
		txtModelo.setBounds(32, 172, 400, 200);
		txtModelo.setLineWrap(true);
		txtModelo.setWrapStyleWord(true);
		frmCadastroVitrola.getContentPane().add(txtModelo);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(32, 383, 400, 50);
		btnCadastrar.addActionListener(e -> realizarCadastro());
		frmCadastroVitrola.getContentPane().add(btnCadastrar);
		frmCadastroVitrola.getRootPane().setDefaultButton(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(e -> {
			new TelaInicial(usuarioLogado);
			frmCadastroVitrola.dispose();
		});
		btnVoltar.setBounds(32, 22, 89, 23);
		frmCadastroVitrola.getContentPane().add(btnVoltar);

	}
}
