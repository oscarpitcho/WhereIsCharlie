package main;

public class SimilarityBasedSearch {
	

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {
		double sum = 0;
		for(int i = 0; i < image.length; i++) {
			for(int j = 0; j < image[0].length; j++) {
				sum += image[i][j];
			}
		}
		return sum / (image.length * image[0].length); 
	}

	//Calculate the average value of a double 2D matrix over the specified zone by row and width, col and height.
	static double windowMean(double [][] matrix , int row , int col ,int width , int height) {
		double sum = 0;
		for(int i = row; i < row + width; i++) {
			for(int j = col; j < col + height; j++) {
				sum += matrix[i][j];
			}
		}
		return sum / (width * height);
	}
	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		assert (pattern.length * pattern[0].length > 0) 
			&& (image.length * image[0].length > 0)
			&& (row >= 0)
			&& (col >= 0)
			&& (row <= image.length - pattern.length)
			&& (col <= image[0].length - pattern[0].length);
		int temp1 = pattern.length;
		int temp2 = pattern[0].length;
		double sumImage = 0;
		double sumPattern = 0;
		double productSum = 0;
		double patternAverage = mean(pattern);
		double windowAverage = windowMean(image, row, col, pattern.length, pattern[0].length);
		//The loop below calculates the sum: pattern(i,j) - patternAverage.
		//i and j ranging over the dimensions of the pattern.
		for(int i = 0; i < pattern.length; i++) {
			for(int j = 0; j < pattern[0].length; j++) {
				sumPattern += Math.pow(pattern[i][j] - patternAverage, 2);
				
			}
		}
		//The loop below calculates the sum: image(i,j) - imageAverage.
		//i and j range over the point starting at (row, col) and the dimensons of the pattern.
		for(int i = row; i < row + pattern.length; i++) {
			for(int j = col; j < col + pattern[0].length; j++) {
				sumImage += Math.pow(image[i][j] - windowAverage, 2);
			}
		}
		//The loop below calculates the sum: (image(row + i, col + j) - windowAverage) * (pattern(i, j) - patternAverage.
		// i and j range over the dimensions of pattern
		//This is the overlapping zone between the point defined on the image and the pattern.
		for(int i = row ; i < row + pattern.length; i++) {
			for(int j = col; j < col + pattern[0].length; j++) {
				productSum += (image[i][j] - windowAverage) * (pattern[i - row][j - col] - patternAverage);
			}
		}
		if (sumImage * sumPattern == 0) {
			return -1.0;
		}
		return productSum / (Math.sqrt(sumImage * sumPattern)); 
	}

	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		double similarityMatrix[][] = new double[image.length][image[0].length];
		assert pattern.length * pattern[0].length > 0
			&& image.length * image[0].length > 0;
			for(int i = 0; i < image.length - pattern.length; i++) {
				for(int j = 0; j < image[0].length - pattern[0].length; j++) {
					similarityMatrix[i][j] = normalizedCrossCorrelation(i, j, pattern, image);
				}
			}
			return similarityMatrix;
	}
	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] extendedSimilarityMatrix(double[][] pattern, int[][] image, double[][] extendedImage) {
		double similarityMatrix[][] = new double[image.length][image[0].length];
		assert pattern.length * pattern[0].length > 0
			&& image.length * image[0].length > 0;
			for(int i = 0; i < image.length; i++) {
				for(int j = 0; j < image[0].length; j++) {
					similarityMatrix[i][j] = normalizedCrossCorrelation(i, j, pattern, extendedImage);
				}
			}
			return similarityMatrix;
	}
	/**
	 * Compute the similarityMatrix between the extended gray-scale image and a gray-scale pattern over the whole dimensions.
	 * @param pattern : a 2D array of doubles, the gray-scale pattern to find
	 * @param image : a 2D array of doubles, the gray-scale image where to look for the pattern
	 * @param extendedImage: 2D array of doubles derived from the extendedImage method.
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image, double extendedImage[][]) {
		double similarityMatrix[][] = new double[image.length][image[0].length];
		assert pattern.length * pattern[0].length > 0
			&& image.length * image[0].length > 0;
			for(int i = 0; i < image.length; i++) {
				for(int j = 0; j < image[0].length; j++) {
					similarityMatrix[i][j] = normalizedCrossCorrelation(i, j, pattern, extendedImage);
				}
			}
			return similarityMatrix;
	}

}
