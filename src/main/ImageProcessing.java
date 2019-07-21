package main;
public final class ImageProcessing {
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {

    	// TODO implement me !
    	int y = rgb >> 16;
    	y = y & 0b11111111;
    	if(y > 255) {
    		return 255;
    	}
    	else if (y < 0 ) {
    		return 0;
    	}
    	return y;  
    }

    /**
     * Returns green component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {
    	// TODO implement me !
    	int y = rgb >> 8;
    	y = y & 0b11111111;
    	if(y > 255) {
    		return 255;
    	}
    	else if (y < 0) {
    		return 0;
    	}
    	return y; 
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    public static int getBlue(int rgb) {
    	// TODO implement me !
    	int y = rgb & 0b11111111;
    	if(y > 255) {
    		return 255;
    	}
    	else if (y < 0) {
    		return 0;
    	}
    	return y; 
    }

   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
    	// TODO implement me !
    	
        return (getBlue(rgb) + getGreen(rgb) + getRed(rgb)) * 1.0 / 3.0;
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer 
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	// TODO implement me !
    	
    	if(blue > 255) {
    		blue = 255;
    	}
    	else if (blue < 0) {
    		blue = 0;
    	}
    	if (green > 255) {
    		green = 255;
    	}
    	else if (green < 0) {
    		green = 0;
    	}
    	if (red > 255) {
    		red = 255;
    	}
    	else if (red < 0) {
    		red = 0;
    	}
    	int y = (int) (red * Math.pow(2, 16) + green * Math.pow(2, 8) + blue);
    	return y; 
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : a double 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    	
    	// TODO implement me !y
    	gray = (int) gray;
    	if (gray > 255) {
    		gray = 255;
    	}
    	else if (gray < 0) {
    		gray = 0;
    	}
    	int y = (int) (gray * Math.pow(2, 16) + gray * Math.pow(2, 8) + gray);
    	return y;
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {
    	double grayScale[][] = new double[image.length][image[0].length];
    	for(int i = 0; i < image.length; ++i) {
    		for (int j = 0; j < image[0].length; ++j) {
    			grayScale[i][j] = getGray(image[i][j]);
    		}
    	}
    	// TODO implement me !
    	return grayScale;
    }
    

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
    	int formatRGB[][] = new int[gray.length][gray[0].length];
    	for(int i = 0; i < gray.length; ++i) {
    		for(int j = 0; j < gray[0].length; ++j) {
    			formatRGB[i][j] = (int) getRGB(gray[i][j]);
    		}
    	}

    	// TODO implement me !
    	return formatRGB;
    }
    
    
    /**
     * returns a double matrix as an int matrix, excess or default values are set to 255 or 0 respectively.
     * @param matrix
     * @return
     */
    public static int[][] doubleToInt(double matrix[][]) {
    	int RGBimage[][] = new int[matrix.length][matrix[0].length];
    	for(int i = 0; i < matrix.length; ++i) {
    		for(int j = 0; j < matrix[0].length; ++j) {
    			RGBimage[i][j] = (int) matrix[i][j];
    			if (matrix[i][j] < 0) {
    				RGBimage[i][j] =  0;
    			}
    			else if (matrix[i][j] > 255) {
    				RGBimage[i][j] = 255;
    			}
    		}
    	}
    	return RGBimage;
    	
    }

    
    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
    	assert matrix.length * matrix[0].length > 0;
    	int RGBimage[][] = new int[matrix.length][matrix[0].length];
    	for(int i = 0; i < matrix.length; ++i) {
    		for(int j = 0; j < matrix[0].length; ++j) {
    			double grayValue = (matrix[i][j] - min) * 255 / (max - min);
    			RGBimage[i][j] = getRGB(grayValue);
    		}
    			
    	}
	
    	return RGBimage;
    }
    
    /**
     * This functions transforms the image argument into a 2D integer Array.
     * It then mirrors the edges in order to allow the pattern to fit and converts it back to an array.
     * We can then use this array normally into our previously defined methods.
     * @param image: the background image that is extended
     * @param pattern: the pattern which is used to determine by how much the image must be extended
     * @return: the new extended array
     */
    public static int[][] extendedArray(int image[][], int pattern[][]) {
		assert (pattern.length * pattern[0].length > 0) 
		&& (image.length * image[0].length > 0)
		&& image.length >= pattern.length 
		&& image[0].length >= pattern[0].length;
    	int extendedArray[][] = new int[image.length + pattern.length][image[0].length + pattern[0].length];
    	//Copying image into an ArrayList.
    	for(int i = 0; i < image.length; i++) {
    		for(int j = 0; j < image[0].length; j++) {
    			extendedArray[i][j] = image[i][j];
    		}
    	}
    	
    	//Extending the first dimension(bottom side).
    	for(int i = 0; i < pattern.length; i++) {
    		for(int j = 0; j < image[0].length; j++)
    		extendedArray[image.length + i][j] = image[image.length - i - 2][j];
    	}
    	
    	for(int i = 0; i < extendedArray.length; i++) {
    		for(int j = 0; j < pattern[0].length; j++) {
    			extendedArray[i][image[0].length + j] = extendedArray[i][image[0].length - j - 2];
    		}
    	}
	
    	return extendedArray;
    }
}
