package br.com.musicboxd.view;

import javax.swing.JFrame;

public class TelasBandasDisponiveis {

	private JFrame frame;

	public TelasBandasDisponiveis() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
