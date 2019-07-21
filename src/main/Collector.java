package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest) for the given matrix
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicates if the smallest element is the best or not (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {
		int best[] = new int[2];
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		if(smallestFirst == true) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[0].length; j++) {
					if(matrix[i][j] < min) {
						min = matrix[i][j];
						best[0] = i;
						best[1] = j;
					}
				}
			}
		}
		else if(smallestFirst == false) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[0].length; j++) {
					if(matrix[i][j] > max) {
						max = matrix[i][j];
						best[0] = i;
						best[1] = j;
					}
				}
			}
		}

    	// TODO implement me !
		return best;
	}

	
	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean,  indicates if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {
		double matrixCopy[][] = new double[matrix.length][matrix[0].length];
		int results[][] = new int[n][2];
		for(int i = 0; i < matrixCopy.length; i++) {
			for(int j = 0; j < matrixCopy[0].length; j++) {
				matrixCopy[i][j] = matrix[i][j];
			}
		}
		for(int i = 0; i < n; i++) {
			results[i][0] = findBest(matrixCopy, smallestFirst)[0];
			results[i][1] = findBest(matrixCopy, smallestFirst)[1];
			if (smallestFirst)
				matrixCopy[findBest(matrixCopy, smallestFirst)[0]][findBest(matrixCopy, smallestFirst)[1]] = Double.POSITIVE_INFINITY;
			else if(!smallestFirst)
				matrixCopy[findBest(matrixCopy, smallestFirst)[0]][findBest(matrixCopy, smallestFirst)[1]] = Double.NEGATIVE_INFINITY;
		}
		
		

    	// TODO implement me !
		return results;
	}
	
	

	/**
	 * BONUS 
	 * Notice : Bonus points are underpriced ! 
	 * 
	 * Sorts all the row, column coordinates based on their pixel value
	 * Hint : Use recursion !
	 * @param matrix : an 2D array of doubles
	 * @return A list of points, each point is an array of length 2.
	 */
	public static ArrayList<int[]> quicksortPixelCoordinates(double[][] matrix) {

		// TODO implement me correctly for "underpriced" bonus!
		return new ArrayList<int[]>();
	}

	
	/**
	 * BONUS
	 * Notice : Bonus points are underpriced !
	 * 
	 * Use a quick sort to find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * Hint : return the n first or n last elements of a sorted ArrayList  
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicate if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBestQuickSort(int n, double[][] matrix, boolean smallestFirst) {

    	// TODO implement me correctly for underpriced bonus!
		return new int[][]{};
	}
}
