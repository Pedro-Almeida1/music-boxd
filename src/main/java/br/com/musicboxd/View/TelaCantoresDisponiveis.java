package br.com.musicboxd.view;

import javax.swing.JFrame;

public class TelaCantoresDisponiveis {

	private JFrame frame;

	public TelaCantoresDisponiveis() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
