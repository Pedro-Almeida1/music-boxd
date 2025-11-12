package br.com.musicboxd.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.musicboxd.DAO.ArtistaDAO.BandaDAO;
import br.com.musicboxd.model.artistas.Banda;

import java.awt.Font;

public class TelaCadastroBanda {

	private JFrame frame;
	private JTextField nome;
	private JTextField genero;
	private JTextArea descricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroBanda window = new TelaCadastroBanda();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroBanda() {
		initialize();
		this.frame.setVisible(true);
	}

	void realizarCadastro(){
		String textoNome = nome.getText();
		String textoGenero = genero.getText();
		String textoDescricao = descricao.getText();

		if((textoNome.equals("")) || (textoGenero.equals("")) || (textoDescricao.equals(""))){
			JOptionPane.showInternalMessageDialog(null, "Informe os dados para cadastro", "Todos os dados solicitados devem ser informados para cadastrar", JOptionPane.ERROR_MESSAGE);
		}
		
		Banda banda = new Banda(textoNome, textoGenero, textoDescricao);
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
			nome.setText("");
			genero.setText("");
			descricao.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar banda! Tente novamente mais tarde",
					"Erro no cadastro de banda", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	frame = new JFrame("Cadastre a banda");
		frame.setSize(480, 480);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel nomeLabel = new JLabel("Nome da banda:");
		nomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		nomeLabel.setBounds(32, 22, 136, 50);
		frame.getContentPane().add(nomeLabel);

		nome = new JTextField();
		nome.setBounds(32, 66, 200, 50);
		frame.getContentPane().add(nome);

		JLabel generoLabel = new JLabel("Genero musical:");
		generoLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		generoLabel.setBounds(250, 22, 200, 50);
		frame.getContentPane().add(generoLabel);

		genero = new JTextField();
		genero.setBounds(242, 66, 200, 50);
		frame.getContentPane().add(genero);

		JLabel descricaoLabel = new JLabel("Descrição da banda:");
		descricaoLabel.setBounds(32, 121, 200, 43);
		descricaoLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frame.getContentPane().add(descricaoLabel);

		descricao = new JTextArea();
		descricao.setBounds(32,172,400,200);
		descricao.setLineWrap(true);
		descricao.setWrapStyleWord(true);
		frame.getContentPane().add(descricao);

		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(32, 383, 400, 50);
		cadastrar.addActionListener(e -> realizarCadastro());
		frame.getContentPane().add(cadastrar);

	}

}
