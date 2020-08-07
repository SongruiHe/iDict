package iDict;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DictUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static final String UTF8 = "utf-8";
	private static int flag = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictUI frame = new DictUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DictUI() {
		setTitle("iDict");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 606);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 192, 192));
		menuBar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 25));
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("\u529F\u80FD");
		menu.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		menuBar.add(menu);

		JMenuItem menuItem_ez = new JMenuItem("\u672C\u5730\u67E5\u8BE2-\u82F1\u6C49");
		menuItem_ez.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		menu.add(menuItem_ez);

		JMenuItem menuItem_online = new JMenuItem("\u5728\u7EBF\u67E5\u8BE2-\u82F1\u6C49");
		menuItem_online.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		menu.add(menuItem_online);

		JMenuItem menuItem_online2 = new JMenuItem("\u5728\u7EBF\u67E5\u8BE2-\u6C49\u82F1");
		menuItem_online2.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		menu.add(menuItem_online2);

		JMenuItem menuItem_collect = new JMenuItem("\u6536\u85CF\u5939");
		menuItem_collect.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		menu.add(menuItem_collect);

		JMenu menu_2 = new JMenu("\u5173\u4E8E");
		menu_2.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		menuBar.add(menu_2);

		JMenuItem menuItem_count = new JMenuItem("\u8BCD\u5E93\u5355\u8BCD\u6570");
		menuItem_count.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 22));
		menu_2.add(menuItem_count);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
				JLabel label_notice = new JLabel("\u6B63\u5728\u672C\u5730\u82F1\u6C49\u67E5\u8BE2\uFF01");
				label_notice.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				label_notice.setBounds(0, 0, 360, 30);
				panel.add(label_notice);

		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(0, 34, 200, 40);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField.setBounds(202, 40, 417, 30);
		panel.add(textField);
		textField.setColumns(100);

		JButton search_button = new JButton("\u67E5\u8BE2");
		search_button.setFont(new Font("微软雅黑", Font.PLAIN, 20));

		/*JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textArea.setBackground(new Color(240, 248, 255));
		textArea.setBounds(0, 83, 802, 262);
		panel.add(textArea);*/

		search_button.setBounds(634, 39, 123, 30);
		panel.add(search_button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 85, 802, 260);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textArea.setBackground(new Color(240, 248, 255));
		textArea.setBounds(0, 83, 802, 262);

		JButton button_collect = new JButton("\u6536\u85CF");
		button_collect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_collect.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_collect.setBounds(224, 360, 123, 49);
		panel.add(button_collect);

		JButton button_speak = new JButton("\u53D1\u97F3");
		button_speak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_speak.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_speak.setBounds(224, 428, 123, 49);
		panel.add(button_speak);

		JButton button_insert = new JButton("\u6DFB\u52A0\u5230\u672C\u5730\u8BCD\u5E93");
		button_insert.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_insert.setBounds(436, 360, 200, 54);
		panel.add(button_insert);

		JButton button_delete = new JButton("\u4ECE\u672C\u5730\u8BCD\u5E93\u4E2D\u5220\u9664");
		button_delete.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_delete.setBounds(436, 423, 200, 54);
		panel.add(button_delete);

		// 查询button
		search_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String word = textField.getText();

				if (flag == 1) {// 本地查询
					local_search local_search = new local_search();
					try {
						if (local_search.query(word) == null)
							textArea.setText("没有查到这个单词！");
						else
							textArea.setText(local_search.query(word));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (flag == 2) {// 在线查询英汉
					online_search search = new online_search();
					try {
						textArea.setText(search.translate_ez(word, "auto", "zh" + ""));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (flag == 3) {// 在线查询汉英
					online_search search = new online_search();
					try {
						textArea.setText(search.translate_ze(word, "auto", "en" + ""));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		// 选择在线查询英汉
		menuItem_online.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = 2;
				label_notice.setText("正在在线英汉查询！数据来源：百度翻译");
			}
		});

		// 选择在线查询汉英
		menuItem_online2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = 3;
				label_notice.setText("正在在线汉英查询！数据来源：百度翻译");
			}
		});

		// 选择本地查询
		menuItem_ez.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = 1;
				label_notice.setText("正在本地英汉查询！");
			}
		});

		// 发音
		button_speak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String word = textField.getText();
				Speak sp = new Speak();

				try {
					sp.speak(word);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// 收藏
		button_collect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = textField.getText();
				String str = "";
				Collect collect = new Collect();

				if (flag == 1) {
					local_search local_search = new local_search();
					try {
						str = textField.getText() + "  " + local_search.query_collect(word);

					} catch (SQLException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					}
				}
				if (flag == 2) {
					str = textField.getText() + "  " + textArea.getText();
				}
				try {
					collect.collect(str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// 收藏夹
		menuItem_collect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					CollectionUI col = new CollectionUI();
					col.setVisible(true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 添加到本地词库
		button_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String word = textField.getText();
				String meaning = textArea.getText();
				if (flag == 2) {
					local_search local_search = new local_search();
					local_search.insert(word, meaning);
				}
			}
		});

		// 从本地词库删除
		button_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String word = textField.getText();

				local_search local_search = new local_search();
				local_search.delete(word);
			}
		});

		// 词库总词数
		menuItem_count.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				local_search counter = new local_search();
				JOptionPane.showMessageDialog(null, "本地词库总次数：" + counter.count());
			}
		});

	}
}
