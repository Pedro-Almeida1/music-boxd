package br.com.musicboxd.view;

import javax.swing.JFrame;

public class TelaMusicasBanda {

	private JFrame frame;

	public TelaMusicasBanda() {
		initialize();
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
