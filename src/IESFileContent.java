import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;

public class IESFileContent implements FileContent {
	int flag = 1;
	int lumpart[] = { 0, 0 };
	// double Luminance[][];
	ArrayList Text_ArrayListString = new ArrayList<String>();
	ArrayList Variable_ArrayListFloat = new ArrayList<Float>();
	ArrayList HorizonAngle_ArrayListFloat = new ArrayList<Float>();
	ArrayList VerticalAngle_ArrayListFloat = new ArrayList<Float>();
	ArrayList QuantityPerRow_ArrayListInteger = new ArrayList<Integer>();
	ArrayList Luminance = new ArrayList<ArrayList>();
	ArrayList QuantityNumber_ArrayListInteger= new ArrayList<Integer>();
	
	int Qt_HorizonInt;
	IESFileContent() {
	}

	public void input(String inputtext) {
		String[] variable = inputtext.split(" ");
		float num;
		double numdouble;
		switch (flag) {
		case 1:
			char Word[] = inputtext.toCharArray();
			if (Word.length > 0) {

				QuantityPerRow_ArrayListInteger.add(1);
				if (!Character.isDigit(Word[0])) {
					Text_ArrayListString.add(inputtext);
					break;
				} else {
					flag++;
				}
			} else {
				break;
			}
		case 2:
			addQu(variable);
			for (String v : variable) {
				num = Float.parseFloat(v);
				Variable_ArrayListFloat.add(num);
			}
			if (Variable_ArrayListFloat.size() >= 13) {
				flag++;
				Qt_HorizonInt=QuantityPerRow_ArrayListInteger.size();
			}
			break;
		case 3:
			addQu(variable);
			for (String v : variable) {
				char Word3[] = v.toCharArray();
				if (Word3.length > 0 && Character.isDigit(Word3[0])) {
					num = Float.parseFloat(v);
					HorizonAngle_ArrayListFloat.add(num);
				}
				
			}
			
			if ((float)HorizonAngle_ArrayListFloat.size() >= (float) Variable_ArrayListFloat.get(3)) {
				flag++;
			}
			break;
		case 4:
			addQu(variable);
			for (String v : variable) {
				char Word4[] = v.toCharArray();
				if (Word4.length > 0 && Character.isDigit(Word4[0])) {
					num = Float.parseFloat(v);
					VerticalAngle_ArrayListFloat.add(num);
				}
			}
			if ((float) VerticalAngle_ArrayListFloat.size() >= (float) Variable_ArrayListFloat.get(4)) {
				flag++;
				// Luminance = new
				// double[VerticalAngle_ArrayListFloat.size()][(int)
				// HorizonAngle_ArrayListFloat.size()];
				Luminance.add(new ArrayList<Double>());
				QuantityNumber_ArrayListInteger.add(QuantityPerRow_ArrayListInteger.size());
			}
			break;
			
		case 5:
			int valueN = 0;
			for (String v : variable) {
				// System.out.print(v + " ");
				char Word5[] = v.toCharArray();

				if (Word5.length > 0 && Character.isDigit(Word5[0])) {
					numdouble = Double.parseDouble(v);
					((ArrayList) Luminance.get(lumpart[0])).add(numdouble);
					// Luminance[lumpart[0]][lumpart[1]] = num;
					lumpart[1]++;
					valueN++;
				}
				// System.out.println(valueN);

				if (lumpart[1] >= (float) HorizonAngle_ArrayListFloat.size()) {
					lumpart[1] = 0;
					lumpart[0]++;
					QuantityNumber_ArrayListInteger.add(QuantityPerRow_ArrayListInteger.size()+1);
					Luminance.add(new ArrayList<Double>());
				}
			}
			if (valueN > 0)
				QuantityPerRow_ArrayListInteger.add(valueN);
			// System.out.println(valueN);

		default:
		}

	}

	private void addQu(String[] variable) {
		QuantityPerRow_ArrayListInteger.add(variable.length);
	}

}
