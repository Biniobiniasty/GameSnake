package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Files {
	public static String Load(String path) throws FileNotFoundException {

		path = CutPath(path) + "\\Table.sn";
		
		
		File plik = new File(path.toString());

		if (plik.exists()) {

			File f = new File(path);
			Scanner fo = new Scanner(f);

			String dane = "";

			while (fo.hasNextLine())
				dane += fo.nextLine() + '\n';

			return dane;

		}
		return "";
	}

	public static void Save(String path, String text) throws FileNotFoundException {

		path = CutPath(path) + "\\Table.sn";
		
		PrintWriter f = new PrintWriter(path.toString());

		f.println(text);
		f.close();

	}
	
	private static String CutPath(String path)
	{
		
		StringBuilder newPath = new StringBuilder();
		boolean coping = false;
		for(int x=path.length()-1;x>1;x--)
		{
			if(coping)
				newPath.append(path.toCharArray()[x]);
			if(path.toCharArray()[x] == '\\' || path.toCharArray()[x] == '/')
				coping = true;
		}
		
		newPath.reverse();
				
		return newPath.toString();
	}
}
