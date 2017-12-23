import java.util.Arrays;
import java.util.Scanner;

public class Challenge
{

	public static void main(String[] args)
	{

		int x;
		int y;
		int n = 0;
		int[] xCoordinates;
		int[] yCoordinates;

		// specify the length and width of the map
		Scanner reader = new Scanner(System.in);
		System.out.println("Please insert the width of the map");
		x = reader.nextInt();
		System.out.println("Please insert the hgith of the map");
		y = reader.nextInt();

		// specify the number of supply points
		while (n <= 0)
		{
			System.out.println("insert number of supply points");
			n = reader.nextInt();
		}

		xCoordinates = new int[n];
		yCoordinates = new int[n];

		// provide the x array
		System.out.println("insert all x's");
		for (int i = 0; i < n; i++)
		{
			xCoordinates[i] = reader.nextInt();
			System.out.println("next x");
		}

		// provide the y array
		System.out.println("insert all y's");
		for (int i = 0; i < n; i++)
		{
			yCoordinates[i] = reader.nextInt();
			System.out.println("next x");
		}

		// declare the Logistic map array. this array will be filled with
		// the shortest distances from each cell to the supply points
		String[][] myLogisticMap = new String[x][y];

		// this array is the temporary holder for all the distances
		// between each cell and all the available supply points
		// tempForward and tempBackward arrays are the temporary holders of all the distances
		// between each cell and all the available supply points
		int[] tempForward = new int[n];
		int[] tempBackward = new int[n];

		// core computation starts
		// the two most outer for loops traverse the Logistic map 2d array
		// the very most outer loop will traverse the array from the beginning and the end which improves efficiency
		for (int i = 0; i <= x/2; i++)
			for (int j = 0; j < y; j++)
			{
				// this inner loop iterates through each of the
				// X and Y Supply points arrays and finds the distance
				// between each supply point and the current cell
				for (int ni = 0; ni < n; ni++)
				{
					// This logic computes the distances and determines
					// whether a supply point does not exist in the grid.
					if (xCoordinates[ni] < x && yCoordinates[ni] < y)
					{
						tempForward[ni] = Math.abs(xCoordinates[ni] - i) + Math.abs(yCoordinates[ni] - j);
						tempBackward[ni] = Math.abs(xCoordinates[ni] - (x-(i+1))) + Math.abs(yCoordinates[ni] - (y-(j+1)));
						}
					else
					{
						tempForward[ni] = -1;
						tempBackward[ni] = -1;
						}
				}
				// this function will return the smallest distance for every given cell
				// it will assign null to the cell if there was no reachable supply point by the
				// cell
				int min2 = getMin(tempForward);
				int max2 = Arrays.stream(tempForward).max().getAsInt();

				int min3 = getMin(tempBackward);
				int max3 = Arrays.stream(tempBackward).max().getAsInt();

				// here is where the distance values are assigned to the Logistic Map array
				if (max2 != -1)
					myLogisticMap[i][j] = String.valueOf(min2);
				if (max3 != -1)
					myLogisticMap[x-(i+1)][y-(j+1)] = String.valueOf(min3);
			}
		// core computation ends

		// print the Logistic Map array just to see the final results
		System.out.println("below are the distances");

		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				System.out.print(myLogisticMap[i][j] + "   ");
			}
			System.out.println();
		}
	}

	// helper functions
	public static int getMin(int[] temp)
	{

		int min = temp[0];

		for (int i = 0; i < temp.length; i++)
		{
			if (min >= 0)
			{
				if (temp[i] < min && temp[i] >= 0)
					min = temp[i];
			} else
				min = temp[i];
		}

		return min;
	}
}
