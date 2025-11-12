package br.com.musicboxd.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import br.com.musicboxd.Model.Musica;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class TelaCadastroMusica {

	private JFrame frmCadastrarMsica;
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private JTextField txtAnoDeLancamento;
	private JTextField txtDuracao;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroMusica window = new TelaCadastroMusica();
					window.frmCadastrarMsica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroMusica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastrarMsica = new JFrame();
		frmCadastrarMsica.setTitle("Cadastrar Música");
		frmCadastrarMsica.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\pedro\\Downloads\\telacadastro.png"));
		frmCadastrarMsica.setBounds(100, 100, 399, 370);
		frmCadastrarMsica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastrarMsica.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setBounds(91, 69, 30, 23);
		frmCadastrarMsica.getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(147, 70, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genêro:");
		lblGenero.setBounds(82, 118, 39, 14);
		frmCadastrarMsica.getContentPane().add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(147, 115, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtGenero);
		
		JLabel lblAnoDeLanamento = new JLabel("Ano de lançamento:");
		lblAnoDeLanamento.setBounds(25, 165, 96, 14);
		frmCadastrarMsica.getContentPane().add(lblAnoDeLanamento);
		
		txtAnoDeLancamento = new JTextField();
		txtAnoDeLancamento.setColumns(10);
		txtAnoDeLancamento.setBounds(147, 162, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtAnoDeLancamento);
		
		JLabel lblDuracao = new JLabel("Duração (min):");
		lblDuracao.setBounds(50, 210, 71, 14);
		frmCadastrarMsica.getContentPane().add(lblDuracao);
		
		txtDuracao = new JTextField();
		txtDuracao.setColumns(10);
		txtDuracao.setBounds(147, 207, 86, 20);
		frmCadastrarMsica.getContentPane().add(txtDuracao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String titulo = txtTitulo.getText();
					String genero = txtGenero.getText();
					int ano = Integer.parseInt(txtAnoDeLancamento.getText());
					int duracao = Integer.parseInt(txtDuracao.getText());

					Musica musicanova = new Musica(titulo, ano, genero, duracao);
					
					JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso!\n" + "Título: " + titulo);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Erro: ano e duração devem ser números!");
				}
			}
		});
		btnCadastrar.setBounds(133, 256, 118, 46);
		frmCadastrarMsica.getContentPane().add(btnCadastrar);
		
		lblNewLabel = new JLabel("Cadastrar Música");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel.setBounds(125, 22, 126, 34);
		frmCadastrarMsica.getContentPane().add(lblNewLabel);
	}
}
