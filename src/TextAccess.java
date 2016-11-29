import java.io.BufferedReader;
import java.io.IOException;

public class TextAccess {
	TextAccess(BufferedReader InputText,FileContent filecontent) {
		try {
			
			while (InputText.ready()) {
				filecontent.input(InputText.readLine());				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}