import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputFileSystem {
	BufferedReader bufferedReader;

	InputFileSystem(File file) {
		try {
			InputStreamReader inputStreamReader = new InputStreamReader (new FileInputStream(file), "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public BufferedReader getfileText() {
		return bufferedReader;
	}


}
