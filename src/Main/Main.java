package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.DropMode;

public class Main {

	public static int WIDTH, HEIGHT;
	public static String nick;

	private JFrame frmSnake;
	private JTextField textField;
	public static JTextPane textPane = new JTextPane();

	public static File katalog = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmSnake.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public Main() throws FileNotFoundException {
		initialize();
		Main.textPane.setText(Files.Load(katalog + "\\Table.sn"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSnake = new JFrame();
		frmSnake.setTitle("Snake");
		frmSnake.getContentPane().setBackground(Color.BLACK);
		frmSnake.setBounds(100, 100, 603, 439);
		frmSnake.setResizable(false);
		frmSnake.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Resolution ");
		lblNewLabel.setForeground(Color.RED);

		JComboBox comboBox = new JComboBox();
		textPane.setEnabled(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setEnabled(false);
		JCheckBox chckbxNewCheckBox = new JCheckBox("Full screen");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBackground(Color.BLACK);
		chckbxNewCheckBox.setForeground(Color.CYAN);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1024x768", "1600x900" }));
		JComboBox comboBox1 = new JComboBox();
		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().length() <= 0) {
					JOptionPane.showMessageDialog(null, "You have to enter your nick :)", "Warning",
							JOptionPane.INFORMATION_MESSAGE);

					return;
				}

				nick = textField.getText();
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
					Main.HEIGHT = (int) Sc_height;
					Main.WIDTH = (int) Sc_width;

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
		comboBox1.setSelectedIndex(1);

		JLabel lblNewLabel_1 = new JLabel("Size map");
		lblNewLabel_1.setForeground(Color.RED);

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Nick:");
		lblNewLabel_1_1.setForeground(Color.RED);

		textPane.setForeground(new Color(240, 230, 140));
		textPane.setBackground(new Color(0, 0, 128));
		GroupLayout groupLayout = new GroupLayout(frmSnake.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING).addComponent(chckbxNewCheckBox)
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 112,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 113,
																GroupLayout.PREFERRED_SIZE))))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel_1_1, Alignment.LEADING,
														GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														314, Short.MAX_VALUE)
												.addComponent(btnNewButton))))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1).addComponent(lblNewLabel_1_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(chckbxNewCheckBox)
								.addComponent(btnNewButton))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE).addContainerGap()));
		frmSnake.getContentPane().setLayout(groupLayout);
	}
}
