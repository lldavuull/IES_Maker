import java.io.File;
import java.util.ArrayList;

public class IES_Executer {
	public IES_Executer(Windows windows, boolean FindSub, String Text, String TextReplace) {
		FileLooker filelooker = new FileLooker();
		ArrayList<File> files = filelooker.lookfile(new File("."), "ies", FindSub);

		int n = 0;
		while (n < files.size()) {
			System.out.println();
			System.out.println(files.get(n).getName());
			InputFileSystem inputFileSystem = new InputFileSystem(files.get(n));
			IESFileContent iesfilecontent = new IESFileContent();
			TextAccess textAccess = new TextAccess(inputFileSystem.getfileText(), iesfilecontent);
			ChangeLuminanceMultipliedIntoOne lumen2one = new ChangeLuminanceMultipliedIntoOne(iesfilecontent,windows);
			System.out.println(1);
			new ChangeFileText(iesfilecontent, files.get(n));
			System.out.println(2);
			new ChangeReplace(iesfilecontent, Text, TextReplace);
			System.out.println(3);
			IESFileWriter fileWriter = new IESFileWriter(iesfilecontent, files.get(n));
			System.out.println(4);
			
			fileWriter.Write();
			System.out.println(5);

			if (windows.jcheckCalLumen.isSelected()) {
				new ConculateLumen(iesfilecontent, windows);
			}
			if (windows.jcheckUplight.isSelected()) {
				new ChangeUplight(iesfilecontent, files.get(n),windows);
			}
			

			n++;
		}
	}
}
