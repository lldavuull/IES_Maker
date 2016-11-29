import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.stream.events.EndElement;
import javax.xml.transform.Templates;

public class ChangeUplight {

	IESFileContent iesfile;
	File file;
	File uplightFile;

	ChangeUplight(IESFileContent iesfile, File file,Windows windows) {
		this.iesfile = iesfile;
		this.file = file;
		// Collections.reverse(iesfile.IESMap.get("VerticalAngle"));
		// Collections.reverse(iesfile.IESMap.get("HorizonAngle"));

		// System.out.println(file.getPath().substring(0,
		// (file.getPath().length() - file.getName().length())));

		if (!file.getName().toUpperCase().contains("-UPLIGHT")) {
			File uplightFile = new File(getFilePathInUplightRemoveFileExtension() + "-Uplight.ies");
			System.out.println(uplightFile.getPath());
			
			iesfile.Variable_ArrayListFloat.set(2, (float)(0.95+0.02*Math.random()));
			ChangeLuminanceMultipliedIntoOne lumen2one = new ChangeLuminanceMultipliedIntoOne(iesfile,windows);

			float HorizenAngleMaxFloat = (float) iesfile.HorizonAngle_ArrayListFloat
					.get(iesfile.HorizonAngle_ArrayListFloat.size() - 1);
			float VariableHorizenInt = (float) iesfile.Variable_ArrayListFloat.get(3);
			if (HorizenAngleMaxFloat <= 90.0) {
				for (int i = 2; i <= VariableHorizenInt; i++) {
					float symmetry = 180
							- (float) iesfile.HorizonAngle_ArrayListFloat.get((int) VariableHorizenInt - i);
					iesfile.HorizonAngle_ArrayListFloat.add(symmetry);
					iesfile.QuantityPerRow_ArrayListInteger.set(iesfile.Qt_HorizonInt,
							(int) iesfile.QuantityPerRow_ArrayListInteger.get(iesfile.Qt_HorizonInt) + 1);
				}
				for (float i = 0; i < (float) iesfile.Variable_ArrayListFloat.get(4); i++) {
					System.out.println();
					for (int j = 0; j < VariableHorizenInt - 1; j++) {
						System.out.print(
								(int) i + "," + j + "/" + ((ArrayList) iesfile.Luminance.get((int) i)).get(j) + " ");
						((ArrayList) iesfile.Luminance.get((int) i)).add(0);

						int QuantityNumberInt = (int) iesfile.QuantityNumber_ArrayListInteger.get((int) i);
						iesfile.QuantityPerRow_ArrayListInteger.set(QuantityNumberInt,
								(int) iesfile.QuantityPerRow_ArrayListInteger.get(QuantityNumberInt) + 1);
					}
				}
				iesfile.Variable_ArrayListFloat.set(3, iesfile.HorizonAngle_ArrayListFloat.size());
				ReverseLumen();

				IESFileWriter fileWriter = new IESFileWriter(iesfile, uplightFile);
				fileWriter.Write();

			} else {
				ReverseLumen();

				IESFileWriter fileWriter = new IESFileWriter(iesfile, uplightFile);
				fileWriter.Write();

			}
		}

	}

	String getFileFolderPath() {
		return file.getPath().substring(0, (file.getPath().length() - file.getName().length()));
	}

	String getFilePathInUplightRemoveFileExtension() {
		File dir_Uplight = new File(getFileFolderPath() + "Uplight\\");
		if (!dir_Uplight.exists()) {
			dir_Uplight.mkdirs();
		}
		int endIndex = file.getName().lastIndexOf(46);

		return getFileFolderPath() + "Uplight\\" + (String) file.getName().substring(0, endIndex);
	}

	void ReverseLumen() {

		ArrayList lumArray = iesfile.Luminance;
		for (int i = 0; i < lumArray.size() - 1; i++) {
			Collections.reverse((ArrayList) iesfile.Luminance.get(i));
			for (int j = ((ArrayList) lumArray.get(i)).size(); j > 0; j--) {
				// ((ArrayList)iesfile.Luminance.get(i)).set(j,((ArrayList)lumArray.get(i)).get(j));
			}
		}
		for (int i = 0; i < iesfile.Text_ArrayListString.size(); i++) {
			String textAdjust = (String) iesfile.Text_ArrayListString.get(i);
			if (textAdjust.length() >= 8) {
				if (textAdjust.substring(0, 8).equals("[LUMCAT]")) {
					iesfile.Text_ArrayListString.set(i, iesfile.Text_ArrayListString.get(i) + "/ Uplight");
				}
			}
			if (textAdjust.length() >= 11) {
				if (textAdjust.substring(0, 11).equals("[LUMINAIRE]")) {
					iesfile.Text_ArrayListString.set(i, iesfile.Text_ArrayListString.get(i) + "-Uplight");
				}
			}
		}

	}

}
