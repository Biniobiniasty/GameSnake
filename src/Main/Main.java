package Main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {

	public static int WIDTH, HEIGHT;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 278, 145);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Resolution ");

		JComboBox comboBox = new JComboBox();
		JCheckBox chckbxNewCheckBox = new JCheckBox("Full screen");
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1024x768", "1600x900" }));
		JComboBox comboBox1 = new JComboBox();
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Format (16:9)

				int WordSize = 20;
				if (comboBox1.getSelectedIndex() == 0)
					WordSize = 20;
				if (comboBox1.getSelectedIndex() == 1)
					WordSize = 30;
				if (comboBox1.getSelectedIndex() == 2)
					WordSize = 40;
				if (comboBox1.getSelectedIndex() == 3)
					WordSize = 50;
				if (comboBox1.getSelectedIndex() == 4)
					WordSize = 75;
				if (comboBox1.getSelectedIndex() == 5)
					WordSize = 100;

				int factor = 50;

				if (chckbxNewCheckBox.isSelected()) {

					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double Sc_width = screenSize.getWidth();
					double Sc_height = screenSize.getHeight();
					
//					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//					int width = gd.getDisplayMode().getWidth();
//					int height = gd.getDisplayMode().getHeight();
//					
					Main.HEIGHT = (int)Sc_height;
					Main.WIDTH = (int)Sc_width;

					System.out.println(Main.HEIGHT + " - " + Main.WIDTH);
					
					Game game = new Game(Main.WIDTH, Main.HEIGHT, 16, 9, factor, WordSize);
					
				} else {
					if (comboBox.getSelectedIndex() == 0) {
						Main.HEIGHT = 768;
						Main.WIDTH = 1024;
						Game game = new Game(1024, 768, 16, 9, factor, WordSize);
					} else if (comboBox.getSelectedIndex() == 1) {
						Main.HEIGHT = 900;
						Main.WIDTH = 1600;
						Game game = new Game(1600, 900, 16, 9, factor, WordSize);
					}
				}
			}
		});

		chckbxNewCheckBox.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					comboBox.setEnabled(false);
				} else {
					comboBox.setEnabled(true);
				}
			}
		});
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		comboBox1.setModel(
				new DefaultComboBoxModel(new String[] { "20x20", "30x30", "40x40", "50x50", "75x75", "100x100" }));

		JLabel lblNewLabel_1 = new JLabel("Size map");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(lblNewLabel).addGap(76)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(579, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup().addComponent(chckbxNewCheckBox)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(btnNewButton))
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup()
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 112,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(comboBox1,
														GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(543, Short.MAX_VALUE)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(lblNewLabel_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(chckbxNewCheckBox)
						.addComponent(btnNewButton))
				.addGap(367)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
