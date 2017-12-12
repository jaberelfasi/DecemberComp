import java.util.Arrays;
import java.util.Scanner;

public class challenge {

	public static void main(String[] args) {

		int x;
		int y;
		int n = 0;
		int[] Xs;
		int[] Ys;

		// specify the length and width of the map
		Scanner reader = new Scanner(System.in);
		System.out.println("Please insert the width of the map");
		x = reader.nextInt();
		System.out.println("Please insert the hgith of the map");
		y = reader.nextInt();

		// specify the number of supply points
		while (n <= 0) {
			System.out.println("insert number of supply points");
			n = reader.nextInt();
		}

		Xs = new int[n];
		Ys = new int[n];

		//provide the x array
		System.out.println("insert all x's");
		for (int i = 0; i < n; i++) {
			Xs[i] = reader.nextInt();
			System.out.println("next x");
		}
		
		//provide the y array
		System.out.println("insert all y's");
		for (int i = 0; i < n; i++) {
			Ys[i] = reader.nextInt();
			System.out.println("next x");
		}
		
		//declare the Logistic map array. this array will be filled with
		//the shortest distances from each cell to the supply points
		String[][] myLogisticMap = new String[x][y];
		
		//this array is the temporary holder for all the distances 
		//between each cell and all the available supply points
		int[] temp2 = new int[n];

		// core computation starts
		// the two most outer for loops traverse the Logistic map 2d array
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				//this inner loop iterates through each of the 
				//X and Y Supply points arrays and finds the distance 
				//between each supply point and the current cell
				for (int ni = 0; ni < n; ni++) {
					//This logic computes the distances and determines  
					//whether a supply point does not exist in the grid.
					if (Xs[ni] < x && Ys[ni] < y)
						temp2[ni] = Math.abs(Xs[ni] - i) + Math.abs(Ys[ni] - j);
					else
						temp2[ni] = -1;
				}
				//this function will return the smallest distance for every given cell
				//it will assign null to the cell if there was no reachable supply point by the cell
				int min2 = getMin(temp2);
				int max2 = Arrays.stream(temp2).max().getAsInt();
				
				//here is where the distance values are assigned to the Logistic Map array
				if (max2 != -1)
					myLogisticMap[i][j] = String.valueOf(min2);

			}
		// core computation ends

		// print the array just to see
		System.out.println("bellow are the distances");

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(myLogisticMap[i][j] + "   ");
			}
			System.out.println();
		}
	}

	// helper functions
	public static int getMin(int[] temp2) {

		int min = temp2[0];

		for (int s = 0; s < temp2.length; s++) {
			if (min >= 0) {
				if (temp2[s] < min && temp2[s] >= 0)
					min = temp2[s];
			} else
				min = temp2[s];
		}

		return min;
	}
}