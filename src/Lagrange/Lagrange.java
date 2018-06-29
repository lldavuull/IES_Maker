package Lagrange;

import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Lagrange {
	Hashtable<Float, Double> Weights = new Hashtable();
	ArrayList x_AL = new ArrayList<>();
	ArrayList y_AL = new ArrayList<>();
	int WeightsNumber = 0;

	public void add(float x, double y) {
		x_AL.add(x);
		y_AL.add(y);
		calculateWeights();
		WeightsNumber=WeightsNumber+1;
	}

	public void add(ArrayList<Float> x, ArrayList<Double> y) {
		x_AL.addAll(x);
		y_AL.addAll(y);
		calculateWeights();
	}

	public void set(ArrayList<Float> x, ArrayList<Double> y) {
		x_AL = x;
		y_AL = y;
		calculateWeights();
	}
	

	public void set_X(ArrayList<Float> x) {
		x_AL = x;
		calculateWeights();
	}
	
	public void set_Y(ArrayList<Double> y) {
		y_AL = y;
	}

	/*
	 * 權重公式: Weights Formula: wj=1/(xj-xi) for i=0~k
	 */
	void calculateWeights() {

		// 計算新權重 Calculate New Expand
		for (int i = 0; i < x_AL.size() - WeightsNumber; i++) {
			double accumulation = 1;
			float xj = (float) x_AL.get(x_AL.size()-i-1);
			for (int j = 0; j < x_AL.size(); j++) {
				float xj_xi = xj - (float) x_AL.get(j);
				if (xj_xi != 0) {
					accumulation = accumulation * (xj - (float) x_AL.get(j));
//					System.out.println(" new  xj:"+xj+" acc:"+accumulation);
				}
			}
			Weights.put((float) xj, accumulation);

//			System.out.println(xj+" new acc:"+accumulation);
			
		}
		// 舊權重擴充 Expand Old Weights:

		for (int i = 0; i < WeightsNumber; i++) {
			
			double accumulation = Weights.get((float) x_AL.get(i));
			float xj = (float) x_AL.get(i);
		
			for (int j = x_AL.size() - 1; j > WeightsNumber-1; j--) {
				
				
				float xj_xi = xj - (float) x_AL.get(j);
				if (xj_xi != 0) {
					accumulation = accumulation * (xj - (float) x_AL.get(j));
				}

//				System.out.println(" oldxj:"+xj+" oldacc:"+accumulation);
			}
			Weights.replace((float) xj, accumulation);

		}
//		System.out.println(" -------------");
	}

	/*
	 * 重心拉格朗日插值法公式: Barycentric Lagrange Interpolation Formula: [(wj/x-xj)*yj] /
	 * (wj/x-xj) for j=0~k
	 */
	//
	public double Lagrange_x(float x) {
		double wj_x_xj=0;
		double molecular = 0;
		double denominator = 0;
		for (int i = 0; i < x_AL.size(); i++) {
			wj_x_xj = (double) Weights.get(x_AL.get(i)) * (x - (float) x_AL.get(i));
			molecular=molecular+ ((double) y_AL.get(i) / wj_x_xj);
			denominator=denominator+ (1 / wj_x_xj);
//			System.out.println(Weights.get(x_AL.get(i)));

		}

//		System.out.println();
//		System.out.println(molecular);
//		System.out.println(denominator);
//		System.out.println(wj_x_xj);
		return molecular/denominator;
	}
	public ArrayList<Double> Lagrange_ArrayListX(ArrayList<Float> x_AL) {
		ArrayList<Double> y_AL=new ArrayList();
		for(int i=0;i<x_AL.size();i++){
			y_AL.add(Lagrange_x((float)x_AL.get(i)));
		}
		return y_AL; 
	}

//	double coeff_d(double constant_d, double coefficient_d, double power_d) {
//		return constant_d * Math.pow(coefficient_d, power_d);
//	}
}
