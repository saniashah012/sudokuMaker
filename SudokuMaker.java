/**
 *	SudokuMaker - Creates a Sudoku puzzle using recursion and backtracking
 *
 *	@author Sania Shah
 *	@since February 11, 2021
 *
 */
public class SudokuMaker 
{

	private int[][] puzzle = new int[9][9];
	private int counter = 0;

	public static void main(String[] args)
	{
		SudokuMaker sm = new SudokuMaker();
		sm.createPuzzle(0, 0);
		sm.printPuzzle();
	}

	public boolean createPuzzle(int row, int column)
	{
		int[] num = new int[9];

		if(row == 9) // puzzle is complete
			return true;

		// generates random numbers from 1-9 for the indices of the array
		for(int a = 0; a < 9; a++)
		{
			num[a] = (int)(Math.random()*9 + 1);
			// makes sure the number generates hasn't already been saved
			for(int b = 0; b < a; b++)
			{
				if(num[a] == num[b]) a--;
			}
		}

		for(int a = 0; a < 9; a++)
		{
			boolean checks = true;
			boolean done = false;
			for(int b = 0; b < 9; b++)
			{
				counter++;
				if(num[a] == puzzle[row][b])
					checks = false;
				if(num[a] == puzzle[b][column])
					checks = false;
			}

			for(int boxRow = 3*(row/3); boxRow < 3*(row/3) + 3; boxRow++) // iterates for each row in the box
			{
				for(int boxCol = 3*(column/3); boxCol < 3*(column/3) + 3; boxCol++) // iterates of each column in the box
				{
					if(num[a] == puzzle[boxRow][boxCol])
						checks = false;
				}
			}
			if(checks)
			{
				puzzle[row][column] = num[a];
				if(column == 8)
					if(createPuzzle(row + 1, 0)) // creates the puzzle
						return true;
					else puzzle[row][column] = 0;
				else if(createPuzzle(row, column + 1))
						return true;
				else puzzle[row][column] = 0;
			}
		}
		return false;

	}
		
	/**
	 *	printPuzzle - prints the Sudoku puzzle with borders
	 *	If the value is 0, then print an empty space; otherwise, print the number.
	 */
	public void printPuzzle() 
	{
		System.out.print("  +-----------+-----------+-----------+\n");
		String value = "";
		for (int row = 0; row < puzzle.length; row++) 
		{
			for (int col = 0; col < puzzle[0].length; col++) 
			{
				// if number is 0, print a blank
				if (puzzle[row][col] == 0) value = " ";
				else value = "" + puzzle[row][col];
				if (col % 3 == 0)
					System.out.print("  |  " + value);
				else
					System.out.print("  " + value);
			}
			if ((row + 1) % 3 == 0)
				System.out.print("  |\n  +-----------+-----------+-----------+\n");
			else
				System.out.print("  |\n");
		}
	}
}