import java.io.File;

public class ChangeFileText {
	IESFileContent iesfile;
	String textAdjust;
	int lampcat = 0;
	int lumcat = 0;
	int luminaire;
	String label = "";

	ChangeFileText(IESFileContent iesfile, File file) {
		this.iesfile = iesfile;
		for (int i = 0; i < iesfile.Text_ArrayListString.size(); i++) {

			textAdjust = (String) iesfile.Text_ArrayListString.get(i);

			int endIndex = textAdjust.lastIndexOf(']') + 1;
			if (endIndex > 0) {
				label = textAdjust.substring(0, endIndex);
				if (textAdjust.length() >= endIndex + 1) {
					if (!textAdjust.substring(endIndex + 1, endIndex + 1).equals(" ")) {
						iesfile.Text_ArrayListString.set(i,
								label + " " + textAdjust.substring(endIndex + 1, textAdjust.length()));
					}
				}
			}
			// System.out.println(textAdjust);
			iesRemove(i, 15, "[_LAMPPOSITION]");
			iesRemove(i, 6, "[LAMP]");
			iesRemove(i, 10, "[TESTDATE]");
			iesRemove(i, 6, "[LAMP]");
			iesRemove(i,11,"[ISSUEDATE]");
			if (textAdjust.length() >= 9) {
				if (textAdjust.substring(0, 9).equals("[MANUFAC]")) {
					iesfile.Text_ArrayListString.set(i, "[MANUFAC] METEOR Lighting - ILOS Corporation");
				}
			}
			if (textAdjust.length() >= 9) {
				if (textAdjust.substring(0, 9).equals("[LAMPCAT]")) {
					lampcat = i;
				}
			}
			if (textAdjust.length() >= 8) {
				if (textAdjust.substring(0, 8).equals("[LUMCAT]")) {
					lumcat = i;
				}
			}
			if (textAdjust.length() >= 11) {
				if (textAdjust.substring(0, 11).equals("[LUMINAIRE]")) {
					luminaire = i;
				}
			}
			if (label.equals("[LAMPCAT]")) {
				iesfile.Text_ArrayListString.set(i, "[LUMCAT]" + textAdjust.substring(endIndex + 1, textAdjust.length()));
				System.out.println(iesfile.Text_ArrayListString.get(i));
			}
			label = "";
			endIndex = 0;

		}
		if (lumcat > luminaire) {
			textAdjust = (String) iesfile.Text_ArrayListString.get(lumcat);
			iesfile.Text_ArrayListString.set(lumcat, (String) iesfile.Text_ArrayListString.get(luminaire));
			iesfile.Text_ArrayListString.set(luminaire, textAdjust);
		}

		String luminaireText = (String) iesfile.Text_ArrayListString.get(luminaire);
		String lumcatText = (String) iesfile.Text_ArrayListString.get(lumcat);
		if (luminaireText.contains("00K")) {

			System.out.println(luminaireText.substring(11, luminaireText.length()));
			System.out.println(lumcatText.substring(8, lumcatText.length()));
			iesfile.Text_ArrayListString.set(luminaire, "[LUMINAIRE]" + lumcatText.substring(8, lumcatText.length()));
//			iesfile.Text_ArrayListString.set(lumcat, "[LUMCAT]" + luminaireText.substring(11, luminaireText.length()));
		}
		
		int endIndex = file.getName().lastIndexOf('.');
//		System.out.print(file.getName().substring(0, endIndex));
		if (luminaire > 0) {
			iesfile.Text_ArrayListString.set(luminaire, "[LUMINAIRE] " + file.getName().substring(0, endIndex));
		}
//		if(lumcat>0){
//			String tempText=(String)iesfile.Text_ArrayListString.get(lumcat);
//			if(tempText.contains("-")){
//				iesfile.Text_ArrayListString.set(lumcat,tempText.replace('-','/'));
//			}
//		}
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("00 K/", "00K/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("00K/", "00K(CRI85)/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("00K /", "00K(CRI85)/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("5700K(CRI85)/", "5700K(CRI75)/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("5000K(CRI85)/", "5000K(CRI75)/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("8 inch LED Architectural Downlight / REV Series", "8 inch REV Series"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("[LUMCAT] 0 inch Cylinder Mini Series", "[LUMCAT] 10 inch Cylinder Mini Series"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("Deg", "deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("0deg", "0 deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("5deg", "5 deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("/9 deg", "/ 9 deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("/35 deg", "/ 35 deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("/50 deg", "/ 50 deg"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("/120V/", "/"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("/Diffuser", "/ Diffuser"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("[LUMCAT] Cylinder Series/ 115W", "[LUMCAT] 16 inch Cylinder Series/ 115W"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("[LUMCAT] Cylinder Series/ 145W", "[LUMCAT] 16 inch Cylinder Series/ 145W"));
//		iesfile.Text_ArrayListString.set(lumcat,iesfile.Text_ArrayListString.get(lumcat).toString().replace("[LUMCAT] Cylinder Series/ 195W", "[LUMCAT] 16 inch Cylinder Series/ 195W"));
	}

	void iesRemove(int inputindex, int subword, String text) {
		if (textAdjust.length() > subword) {
//			 System.out.println(true);
			if (textAdjust.substring(0, subword).equals(text)) {
				// System.out.println(text + "word");
				iesfile.Text_ArrayListString.remove(inputindex);
				iesfile.QuantityPerRow_ArrayListInteger.remove(inputindex);
			}
		}
	}
}
