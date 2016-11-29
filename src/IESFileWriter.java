import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class IESFileWriter {
	BufferedWriter fileWriter;
	IESFileContent iesfile;
	File file;
	int qrow;
	int flagq = 1;
	int n=0;
	DecimalFormat decimalFormat = new DecimalFormat("#.####");

	IESFileWriter(IESFileContent iesfile, File file) {
		this.iesfile = iesfile;
		this.file=file;
	}

	void Write() {

		System.out.println(file);
		
		try {
			fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "utf-8"));
			ArrayWrite(iesfile.Text_ArrayListString,true);
			ArrayWrite(iesfile.Variable_ArrayListFloat,false);
			ArrayWrite(iesfile.HorizonAngle_ArrayListFloat,false);
			ArrayWrite(iesfile.VerticalAngle_ArrayListFloat,false);
			System.out.println("Close");
			LumenWrite();
			fileWriter.close();
			
		} catch (IOException e) {
		}

	}

	void ArrayWrite(ArrayList arrayList,boolean text) {
		try {
			for (int i = 0; i < arrayList.size(); i++) {
				
				if (!text) {
//					 System.out.print(decimalFormat.format((float));
					// arrayList.get(i)));
					
					fileWriter.write(String.valueOf(decimalFormat.format(arrayList.get(i))));
				} else {
					 System.out.println(arrayList.get(i));
					if (String.valueOf(arrayList.get(i)) != null && String.valueOf(arrayList.get(i)) != "") {
						fileWriter.write(arrayList.get(i).toString());
					}
				}

				addrow();
			}

		} catch (IOException e) {
		}
	}

	void LumenWrite() {
		for (int i = 0; i < iesfile.Luminance.size(); i++) {
			for (int j = 0; j < ((ArrayList)iesfile.Luminance.get(i)).size(); j++) {
//				System.out.print(decimalFormat.format(iesfile.Luminance[i][j])+" ");
				try {
					fileWriter.write(String.valueOf(decimalFormat.format(((ArrayList) iesfile.Luminance.get(i)).get(j))));
				} catch (IOException e) {
				}
				addrow();
			}
		}
	}

	void addrow() {
		qrow++;
//		System.out.print(iesfile.IESMap.get("QuantityPerRow").size());
//		System.out.print(flagq+"."+iesfile.IESMap.get("QuantityPerRow").get(flagq));
		if (qrow >= (int) iesfile.QuantityPerRow_ArrayListInteger.get(flagq)) {
//			System.out.println();
			try {
				fileWriter.write("\r\n");
			} catch (IOException e) {
			}
			flagq++;
			qrow = 0;
		} else {
//			System.out.print(" ");
			try {
				fileWriter.write(" ");
			} catch (IOException e) {
			}
		}
	}
}
