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
		int[] temp = new int[n];

		// core computation starts
		// the two most outer for loops traverse the Logistic map 2d array
		for (int i = 0; i < x; i++)
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
						temp[ni] = Math.abs(xCoordinates[ni] - i) + Math.abs(yCoordinates[ni] - j);
					else
						temp[ni] = -1;
				}
				// this function will return the smallest distance for every given cell
				// it will assign null to the cell if there was no reachable supply point by the
				// cell
				int min = GetMin(temp);
				int max = Arrays.stream(temp).max().getAsInt();

				// here is where the distance values are assigned to the Logistic Map array
				if (max != -1)
					myLogisticMap[i][j] = String.valueOf(min);

			}
		// core computation ends

		// print the array just to see
		System.out.println("bellow are the distances");

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
	public static int GetMin(int[] temp)
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