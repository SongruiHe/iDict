package iDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class CollectionUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollectionUI frame = new CollectionUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public CollectionUI() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 46, 633, 183);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);
		
		JButton button_show = new JButton("\u663E\u793A\u6536\u85CF\u5939");
		button_show.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		button_show.setBounds(15, 2, 149, 37);
		contentPane.add(button_show);
		
		JButton button_clear = new JButton("\u6E05\u7A7A\u6536\u85CF\u5939");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_clear.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 20));
		button_clear.setBounds(212, 2, 149, 37);
		contentPane.add(button_clear);
		
		//œ‘ æ ’≤ÿº–
		button_show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Collect sc = new Collect();
					textArea.setText(sc.show());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//«Âø’ ’≤ÿº–
		button_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Collect sc = new Collect();
				sc.clear();
				textArea.setText("");
			}
		});
	}
}
