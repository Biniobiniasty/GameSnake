package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Files {
	public static String Load(String path) throws FileNotFoundException {

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

		PrintWriter f = new PrintWriter(path.toString());

		f.println(text);
		f.close();

	}
}
