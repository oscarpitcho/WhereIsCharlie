package main;
import java.util.ArrayList;
public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {
		double absoluteError = Math.abs(ImageProcessing.getRed(patternPixel) - ImageProcessing.getRed(imagePixel)) 
				+ Math.abs(ImageProcessing.getBlue(patternPixel) - ImageProcessing.getBlue(imagePixel)) 
				+ Math.abs(ImageProcessing.getGreen(patternPixel) - ImageProcessing.getGreen(imagePixel));
		absoluteError = absoluteError / 3;
    	// TODO implement me !
		return absoluteError;
	}
	
	
	
	
	/**
	 * Computes the average absolute value of two image the same size
	 * @param pattern
	 * @param image
	 * @return the average absolute error between two corresponding pixels
	 */
	public static double meanAbsoluteError(int[][] pattern, int[][] image) {
		assert (pattern.length * pattern[0].length > 0 ) && (image.length * image[0].length > 0);
		double meanAbsoluteError = 0;
		for(int i = 1; i < pattern.length; ++i) {
			for(int j = 1; j < pattern[0].length; ++j) {
				meanAbsoluteError += pixelAbsoluteError(pattern[i][j], image[i][j]);
			}
		}
		meanAbsoluteError /=  1.0 * (pattern.length * pattern[0].length);
		return meanAbsoluteError;
	}
	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {
		assert (image.length * image[0].length >= 1) 
				&& (pattern.length * pattern[0].length >= 1) 
				&& (image.length - row > pattern.length) 
				&& (image[0].length - col > pattern[0].length); {
			double meanAbsoluteError = 0;
			for(int i = 0; i < pattern.length; i++) {
				for(int j = 0; j < pattern[0].length; j++) {
					meanAbsoluteError += pixelAbsoluteError(pattern[i][j], image[row + i][col + j]);
				}
			}
			meanAbsoluteError = meanAbsoluteError / (pattern.length * pattern[0].length);
			return meanAbsoluteError;
		}
			// TODO set return to worst value possible as pattern can not be prese
	}

	
	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {
		assert (pattern.length * pattern[0].length > 0) 
				&& (image.length * image[0].length > 0)
				&& image.length >= pattern.length 
				&& image[0].length >= pattern[0].length;
		double distanceMatrix[][] = new double[image.length - pattern.length][image[0].length - pattern[0].length];
		for(int i = 0; i < image.length - pattern.length; ++i) {
			for(int j = 0; j < image[0].length - pattern[0].length; ++j) {
				distanceMatrix[i][j] = meanAbsoluteError(i, j, pattern, image);
			}
		}
		return distanceMatrix;
	}
	
	/**
	 * Compute the  extended distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] extendedDistanceMatrix(int[][] pattern, int[][] image, int[][] extendedImage) {
		assert (pattern.length * pattern[0].length > 0) 
				&& (image.length * image[0].length > 0)
				&& image.length >= pattern.length 
				&& image[0].length >= pattern[0].length;
		double distanceMatrix[][] = new double[image.length][image[0].length];
		for(int i = 0; i < image.length; ++i) {
			for(int j = 0; j < image[0].length; ++j) {
				distanceMatrix[i][j] = meanAbsoluteError(i, j, pattern, extendedImage);
			}
		}
		return distanceMatrix;
	}	
	
	/**
	 * Computes a sum of doubles entered using an Array
	 * @param an Array of doubles
	 * @return the sum as a double
	 */
	public static double sum(double values[]) {
		double sum = 0;
		for(int i = 0; i < values.length; ++i) {
			sum += values[i];
		}
		return sum;
	}
	
	/**
	 * Computes the sum of ints.
	 * @param an Array of type int containing the values.
	 * @return an int.
	 */
	public static int sum(int values[]) {
		int sum = 0;
		for(int i = 0; i < values.length; ++i) {
			sum += values[i];
		}
		return sum;
	}
}
