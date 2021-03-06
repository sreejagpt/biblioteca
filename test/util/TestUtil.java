package util;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class TestUtil {

	public String readFile(String filepath, boolean doTrim) {
		InputStream testFile = getClass().getClassLoader().getResourceAsStream(filepath);
		Scanner sc = new Scanner(testFile);
		String fileToString = "";
		while (sc.hasNextLine()) {
			fileToString += sc.nextLine() + "\n";
		}
		if (doTrim) {
			return fileToString.trim();
		} else {
			return fileToString;
		}
	}

}
