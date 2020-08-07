package iDict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Collect {

	//收藏
	public void collect(String str) throws IOException {
		try {
			BufferedWriter target = new BufferedWriter(
					new FileWriter("myCollection.txt", true));
			target.write(str);
			target.newLine();
			target.flush();
			target.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "收藏成功！");
	}
	
	//显示收藏夹
	public String show() throws FileNotFoundException {

		String str = "";
		BufferedReader source = new BufferedReader(
				new FileReader("myCollection.txt"));

		try {
			String temp = null;
			while ((temp = source.readLine()) != null) {
				str += temp;
				str += "\n";
			}
			source.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}

	//清空收藏夹
	public void clear() {
		File file = new File("myCollection.txt");
		file.delete();
	}
}

//C:\\Users\\Pinenut\\Desktop\\iDict\\
