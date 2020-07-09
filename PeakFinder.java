/* *
 * 29.09.2019
 * 
 * Zeynep Ülkü Kılıç
 * 18120205011
 * 
 * 2-D peak bulma problemi 
 * 
 * */

import java.util.Random;

public class PeakFinder {
	//fields
	private int[][] data;	//dizi
	private int row;		//satır sayısı
	private int column;		//sütun sayısı
	
	//constructor
	public PeakFinder(int n, int m) {
		row = n;
		column = m;
		data = new int[row][column];
		Random generator = new Random();
		for(int i = 0; i < row; ++i) {
			for(int j = 0; j < column; ++j) {
				data[i][j] = generator.nextInt(10);	//[0,10) aralığında rastgele değerler atar
			}
		}
	}
	
	//PeakFinder class has two methods
	//method1
	public int gradientAscent () {
		//base case
		if((row == 1) && (column == 1))
			return data[0][0];
		int i = 0, j = 0;
		while(true) {
			//sol üst köşede ise
			if((i == 0) && (j == 0)) {
				if((data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if((data[i+1][j]) >= (data[i][j+1]))
					i = i+1;
				else
					j = j+1;
			}
			//en üst satırda ise
			else if(i == 0) {
				if((data[i][j] >= data[i][j-1])
					&& (data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if((data[i+1][j] <= data[i][j-1])
						&& (data[i][j+1] <= data[i][j-1]))
					j = j-1;
				else if((data[i][j+1] <= data[i][j+1])
						&& (data[i][j-1] <= data[i][j+1]))
					j = j+1;
				else
					i = i+1;
			}
			//en sol sütunda ise
			else if(j == 0) {
				if((data[i][j] >= data[i-1][j])
					&& (data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if((data[i+1][j] <= data[i-1][j])
						&& (data[i][j+1] <= data[i-1][j]))
					i = i-1;
				else if((data[i-1][j] <= data[i+1][j])
						&& (data[i][j+1] <= data[i+1][j]))
					i = i+1;
				else
					j = j+1;
			}
			//sağ alt köşede ise
			else if((i == row-1) && (j == column-1)) {
				if((data[i][j] >= data[i][j-1])
					&& (data[i][j] >= data[i-1][j]))
					return data[i][j];
				else if(data[i-1][j] <= data[i][j-1])
					j = j-1;
				else
					i = i-1;					
			}
			//sol alt köşede ise
			else if((i == row-1) && (j == 0)){
				if((data[i][j] >= data[i-1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if(data[i][j+1] <= data[i-1][j])
					i = i-1;
				else
					j = j+1;
			}
			//en altta ise
			else if(i == row-1) {
				if((data[i][j] >= data[i][j-1])
					&& (data[i][j] >= data[i-1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if((data[i-1][j] <= data[i][j-1])
						&& (data[i][j+1] <= data[i][j-1]))
					j = j-1;
				else if((data[i][j-1] <= data[i-1][j])
						&& (data[i][j+1] <= data[i-1][j]))
					i = i-1;
				else
					j = j+1;
			}
			//sağ üst köşede ise
			else if((j == column-1) && (i == 0)){
				if((data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j-1]))
					return data[i][j];
				else if(data[i][j-1] <= data[i+1][j])
					i = i+1;
				else
					j = j-1;
			}
			//en sağda ise
			else if(j == column-1){
				if((data[i][j] >= data[i-1][j])
					&& (data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j-1]))
					return data[i][j];
				else if((data[i+1][j] <= data[i-1][j])
						&& (data[i][j-1] <= data[i-1][j]))
					i = i-1;
				else if((data[i-1][j] <= data[i+1][j])
						&& (data[i][j-1] <= data[i+1][j]))
					i = i+1;
				else
					j = j-1;
			}
			//ortalarda ise
			else {
				if((data[i][j] >= data[i][j-1])
					&& (data[i][j] >= data[i-1][j])
					&& (data[i][j] >= data[i+1][j])
					&& (data[i][j] >= data[i][j+1]))
					return data[i][j];
				else if((data[i-1][j] <= data[i][j-1])
						&& (data[i+1][j] <= data[i][j-1])
						&& (data[i][j+1] <= data[i][j-1]))
					j = j-1;
				else if((data[i][j-1] <= data[i-1][j])
						&& (data[i+1][j] <= data[i-1][j])
						&& (data[i][j+1] <= data[i-1][j]))
					i = i-1;
				else if((data[i][j-1] <= data[i+1][j])
						&& (data[i-1][j] <= data[i+1][j])
						&& (data[i][j+1] <= data[i+1][j]))
					i = i+1;
				else
					j = j+1;
					
			}
		}
	}

	//method2
	public int divideConquer(int j) {
		int i = 0;
		int max = 0;
	    for(int index = 0; index < row; ++index){
	        if(data[index][j] > max) {
	            max = data[index][j];
	        	i = index;
	        }
	    }
	    if(j == 0) {
	    	if(max >= data[i][j+1])
	    		return max;
	    	else
	    		divideConquer(j+1);
	    }
	    else if(j == column-1) {
	    	if(max >= data[i][j-1])
	    		return max;
	    	else
	    		return divideConquer(j-1);
	    }
	    else if((max >= data[i][j-1]) && (max >= data[i][j+1]))
	    	return max;
	    else if(data[i][j-1] >= data[i][j+1])
	    	return divideConquer(j-1);
	    else
	    	return divideConquer(j+1);
	return -1;
	}
	
	public void getElements() {
		for(int i = 0; i < row; ++i) {
			System.out.print("\n");
			System.out.print(i+1);
			System.out.print(".satir:	");
			for(int j = 0; j < column; ++j) {
				System.out.print(data[i][j]);
				System.out.print(" ");
			}
		}
	}
	public static void main(String []args) {
		PeakFinder A = new PeakFinder(6,17);
		A.getElements();
		System.out.print("\npeak:");
		System.out.print(A.gradientAscent());
		System.out.print("\npeak:");
		System.out.print(A.divideConquer(7));	//parametre olarak (sütun_sayısı/2 - 1) verilmesi gerekiyor
	}
}
