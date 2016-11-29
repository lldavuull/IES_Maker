import java.awt.datatransfer.FlavorTable;
import java.beans.DefaultPersistenceDelegate;
import java.util.ArrayList;

import javax.swing.OverlayLayout;

public class ChangeLuminanceMultipliedIntoOne {

	ChangeLuminanceMultipliedIntoOne(IESFileContent iesfile, Windows windows) {
		if (!windows.jTextMultiple.getText().isEmpty()) {
			float MultipleText = Float.parseFloat(windows.jTextMultiple.getText());
			iesfile.Variable_ArrayListFloat.set(2, (float) iesfile.Variable_ArrayListFloat.get(2) * MultipleText);
		}
		float coefficent = (float) iesfile.Variable_ArrayListFloat.get(2);

		for (int i = 0; i < iesfile.Luminance.size(); i++) {
			for (int j = 0; j < ((ArrayList) iesfile.Luminance.get(i)).size(); j++) {
				// System.out.print(i+","+j+"/");
				// System.out.println(((ArrayList)iesfile.Luminance.get(i)).get(j).getClass());
				try {
					((ArrayList) iesfile.Luminance.get(i)).set(j,
							Math.round((Double) ((ArrayList) iesfile.Luminance.get(i)).get(j) * coefficent * 100)
									* 0.01);
				} catch (ClassCastException e) {
					System.out.print(e + ":");
					System.out.println(i + "/" + j);
				}
				// System.out.println(iesfile.Luminance[i][j]);
			}
			// System.out.println();
		}

		iesfile.Variable_ArrayListFloat.set(2, 1.0f);
		iesfile.Variable_ArrayListFloat.set(1, -1.0f);
		iesfile.Variable_ArrayListFloat.set(0, 1.0f);
		iesfile.Variable_ArrayListFloat.set(6, 1f);
		// iesfile.Variable_ArrayListFloat.set(12,
		// (float)Math.random()*2+123.2f);
		if (!windows.jTextLength.getText().isEmpty()) {
			iesfile.Variable_ArrayListFloat.set(7, Float.parseFloat(windows.jTextLength.getText()));
		}
		if (!windows.jTextWidth.getText().isEmpty()) {
			iesfile.Variable_ArrayListFloat.set(8, Float.parseFloat(windows.jTextWidth.getText()));
		}
		if (!windows.jTextHeight.getText().isEmpty()) {
			iesfile.Variable_ArrayListFloat.set(9, Float.parseFloat(windows.jTextHeight.getText()));
		}
		if (!windows.jTextWattage.getText().isEmpty()) {
			iesfile.Variable_ArrayListFloat.set(12, Float.parseFloat(windows.jTextWattage.getText()));
		}
		// iesfile.Variable_ArrayListFloat.set(7, 1.2467f);
		// iesfile.Variable_ArrayListFloat.set(8, 0.7218f);
		// iesfile.Variable_ArrayListFloat.set(9, 0.1345f);
		// System.out.println(iesfile.IESMap.get("Variable").get(2));
//		System.out.println(iesfile.Variable_ArrayListFloat.get(1));
//		System.out.println(iesfile.VerticalAngle_ArrayListFloat.get(0));
//		RoundedCorner(iesfile, windows);

//		 deleteUplight(iesfile);
//		 deleteTail(iesfile);
		

	}

	void deleteUplight(IESFileContent iesfile) {
		for (int j = 0; j < iesfile.VerticalAngle_ArrayListFloat.size(); j++) {
			for (int i = 37; i < 73; i++) {
				((ArrayList) iesfile.Luminance.get(j)).set(i, 0);
			}
		}

	}

	void deleteTail(IESFileContent iesfile) {
		for (int j = 0; j < iesfile.VerticalAngle_ArrayListFloat.size(); j++) {
			for (int i = 22; i < 37; i++) {
				((ArrayList) iesfile.Luminance.get(j)).set(i,
						((Double) ((ArrayList) iesfile.Luminance.get(j)).get(21)) * (1 - 0.05 * (i - 21)));
			}
		}

	}

	void RoundedCorner(IESFileContent iesfile, Windows windows) {// 把SP-8-NW 轉換成
																	// SP-018-NW
																	// 用
		for (int j = 0; j < iesfile.VerticalAngle_ArrayListFloat.size(); j++) {

			Float f = new Float((Float) (iesfile.VerticalAngle_ArrayListFloat.get(j)));

			double coefficent = (Double) Math.abs(Math.cos(2 * Math.toRadians((f.doubleValue()))));

			System.out.println(coefficent);
			for (int i = 11; i < 37; i++) {

				double cd[] = getLuminanceCD(iesfile, j, i);

				cdCalculation(i, j, cd, iesfile, coefficent);
			}
		}
	}

	double[] getLuminanceCD(IESFileContent iesfile, int j, int i) {
		double cd[] = new double[5];
		for (int x = 0; x < 5; x++) {
			cd[x] = (double) ((ArrayList) iesfile.Luminance.get(j)).get(Math.min(i +x+1, 36));
		}
			return cd;
		
	}

	void cdCalculation(int i, int j, double cd[], IESFileContent iesfile, double coefficent) {
		for (int k = 0; k < 5; k++) {
			double c = cd[k];
			double Modified = (5 - k) * c * coefficent / 21;
			double cd0 = (double) ((ArrayList) iesfile.Luminance.get(j)).get(i);
			((ArrayList) iesfile.Luminance.get(j)).set(i, cd0 + Modified);
			((ArrayList) iesfile.Luminance.get(j)).set(Math.min(i + k + 1, 36), c - Modified);
		}

	}

}
