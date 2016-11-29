import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import Lagrange.Lagrange;

public class ConculateLumen {
	BufferedWriter fileWriter;

	ConculateLumen(IESFileContent iesfile, Windows windows) {
		Lagrange lagrange = new Lagrange();
		ArrayList<Float> HA=iesfile.HorizonAngle_ArrayListFloat;
		lagrange.set_X(HA);
		ArrayList<Float> HorizonAngle_TenthOne_AL_F=new ArrayList<>();
		int HA_Num=0;
		do{
			for(int i=0;i<10;i++){
//				System.out.print(HA_Num+", ");
				float temp_HA_tenth  =  ((float)HA.get(HA_Num))  +  (i*((HA.get(HA_Num+1)-HA.get(HA_Num)))/10);
				HorizonAngle_TenthOne_AL_F.add(temp_HA_tenth);
			}
			
			HA_Num++;
		}while(HA_Num<HA.size()-1);
		
		HorizonAngle_TenthOne_AL_F.add(HA.get(HA_Num));
		
		for(int j=0;j<HorizonAngle_TenthOne_AL_F.size();j++){
			System.out.print(HorizonAngle_TenthOne_AL_F.get(j)+", ");
		}
		
		for (int i = 0; i < iesfile.Luminance.size(); i++) {
//			lagrange.set_Y((ArrayList<Double>) iesfile.Luminance.get(i));
		}

		////////////////////////////////////////////
		File file = new File("21");
		try {
			fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "ANSI"));
			System.out.println("Close");
			fileWriter.close();

		} catch (IOException e) {
		}
	}

}
