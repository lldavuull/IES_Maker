import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.naming.directory.DirContext;
import javax.swing.OverlayLayout;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class FileLooker {
	ArrayList<File> fileList = new ArrayList<File>();

	ArrayList lookfile(File file, String FileExtension, boolean subFolders) {

		File[] filearray = file.listFiles();
		if (subFolders) {
		}

		System.out.println("filename:" + file.getName());

		for (File filesub : filearray) {

			String fileExtension = fileExtensionTaker(filesub).toLowerCase();
			if (fileExtension.equals("ies")) {
				fileList.add(filesub);
			}
			if (subFolders && filesub.isDirectory()) {
				lookfile(filesub,FileExtension,subFolders);
			}
		}

		for (int i = 0; i < fileList.size(); i++) {
			System.out.println(fileList.get(i));
		}
		return fileList;
	}

	String fileExtensionTaker(File filearray) {
		int beginIndex = filearray.getName().lastIndexOf(46) + 1;
		int endIndex = filearray.getName().length();
		if (beginIndex > 0) {
			return filearray.getName().substring(beginIndex, endIndex);
		}
		return "";
	}

}
