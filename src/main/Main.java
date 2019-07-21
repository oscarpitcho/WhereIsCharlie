package main;

/**
 * 
 * @author Name of the students
 *
 *	Where is Charlie Project 
 *
 */
public final class Main {

	/* 
	 * This class is incomplete!!
	 * 
	 * You are expected to write at least one testcase for each required method.
	 * You will find some examples of testcases below.
	 */
	
    public static void main(String[] args) {
    	//testGetRed();
    	//testGetBlue();
    	//testGetGreen();
    	//testGrayscale();
    	//testColor();
    	//testAbsoluteError();
    	//testDistance();
    	//testIdenticalNormalizedCrosscorrelation();
    	//testDistanceBasedSearch();
    	//testDistance();
    	//testFindNBest();
    	//testDistanceBasedSearch();
    	//testSimilarityBasedSearch();   
    	//findCharlie();
    	//findCharlieExtended();
    	testMirroring();
    }
    
    /*
     * Tests for Class ImageProcessing
     */
    public static void testGetRed() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	if (red == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + red + " Expected value = " + ref);
    	}
    }
    //As said
    public static void testGetGreen() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	if (green == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + green + " Expected value = " + ref);
    	}
    }
    //Test functions ability to transform an rgb image into individual pixel and recombine it into the original image.
    public static void testColor() {
    	System.out.println("Test Color");
     	int[][] image = Helper.read("images/charlie_beach.png");
    	int imageNew[][] = new int[image.length][image[0].length];
    	for(int i = 0; i < image.length; i++) {
    		for(int j = 0; j < image[0].length; j++) {
    			imageNew[i][j] = ImageProcessing.getRGB(ImageProcessing.getRed(image[i][j]), ImageProcessing.getGreen(image[i][j]), ImageProcessing.getBlue(image[i][j]));
    		}
    	}
    	Helper.show(imageNew, "test color");
    	
    }
    
    //Tests the absolute error between two pixels function in DistanceBased Search.
    public static void testAbsoluteError() {
    	System.out.println("Testing the function absolute error");
    	int temp1 = 0b00100000_11000000_11111111; 
    	double test = DistanceBasedSearch.pixelAbsoluteError(0b00100000_11000000_11111111, 0b00100001_11000100_11101111);
    	double test2 = ((Math.abs((int) 0b00100000 - (int) 0b00100001) 
    			+ Math.abs((int) 0b11000000 - (int) 0b11000100) 
    			+ Math.abs((int) 0b11111111 - (int) 0b11101111)));
    	test2 /= 3;
    	System.out.println(ImageProcessing.getBlue(temp1) + "  " + 0b11111111);
    	if (test == test2)
    		System.out.println("Test Passed");
    	else {
    		System.out.println("Test failed");
    	System.out.println(test + "  " + test2); 
    	}
    }
    
    
    //As said
    public static void testGetBlue() { 
        	int color = 0b11110000_00001111_01010101;
        	int ref = 0b01010101;
        	int blue = ImageProcessing.getBlue(color);
        	if (blue == ref) {
        		System.out.println("Test passed");
        	} else {
        		System.out.println("Test failed. Returned value = " + blue + " Expected value = " + ref);
        	}
    }
    
    public static void testGrayscale() {
    	System.out.println("Test Grayscale");
     	int[][] image = Helper.read("images/charlie_beach.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test bw");
    }
    
    
    /**
     * This function tests the distanceMatrix method by displaying a gray scale heat map of the imagg
     * and the pattern.
     * 
     */
    public static void testDistance() {
    	System.out.println("Test distance");
     	int[][] image = Helper.read("images/food.png");
     	int pattern[][] = Helper.read("images/onions.png");
     	double distanceMatrix[][] = new double[image.length][image[0].length];
     	distanceMatrix = DistanceBasedSearch.distanceMatrix(pattern, image);
     	int RGBimage[][] = ImageProcessing.matrixToRGBImage(distanceMatrix, 0, 250);
     	double gray[][] = ImageProcessing.toGray(RGBimage);
     	Helper.show(ImageProcessing.toRGB(gray), "Test distance search");
    }
    
    
        
    /*
     * Tests for Class Collector
     */
    
    public static void testFindNBest() {
    	System.out.println("Test findNBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, true);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }
    


    /*
     * Tests for Class DistanceBasedSearch
     */
    
    public static void testDistanceBasedSearch() {
    	System.out.println("Test DistanceBasedSearch");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food); 			
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    }
    

    public static void testIdenticalNormalizedCrosscorrelation() {
    	System.out.println("Test normalized cross correlation.");
    	int image[][] = Helper.read("images/onions.png");
    	int pattern[][] = Helper.read("images/onions.png");
    	double imageGray[][] = ImageProcessing.toGray(image);
    	double patternGray[][] = ImageProcessing.toGray(pattern);
    	System.out.println(SimilarityBasedSearch.normalizedCrossCorrelation(0, 0, patternGray, imageGray));
    	
    }
    
    /*
     * Tests for Class SimilarityBasedSearch
     */

    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
		int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	int[][] best = Collector.findNBest(8, similarity, false);    			
    	for (int[] a : best) {
    		int r = a[0];
    		int c = a[1];
        	Helper.drawBox(r, c, onions[0].length, onions.length, food);
    	}
    	Helper.show(food, "Found again!");    	
    }
   
    
    // Tests the mirroring method contained in image processing,
    //by displaying the image mirrored over its bottom and right side.
    public static void testMirroring() {
    	int image [][] = Helper.read("images/charlie_beach.png");
    	int pattern[][] = Helper.read("images/charlie.png");
    	int extendedImage[][] = ImageProcessing.extendedArray(image, pattern);
    	Helper.show(extendedImage, "Testing the mirroring method.");
    }
    
    public static void findCharlie() {
    	System.out.println("Find Charlie");
		int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] beachGray = ImageProcessing.toGray(beach);
    	double[][] charlieGray = ImageProcessing.toGray(charlie);    	

    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(charlieGray, beachGray);

    	System.out.println("Find N Best");
    	int[] best = Collector.findBest(similarity, false);   
    	double max = similarity[best[0]][best[1]];
    	
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");    	
    }
    
    /**
     * Same function as above but using the extended image obtained through mirroring 
     */
    public static void findCharlieExtended() {
    	System.out.println("Find Charlie");
		int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	int beachExtended[][] = ImageProcessing.extendedArray(beach, charlie);
    	double[][] extendedBeachGray = ImageProcessing.toGray(beachExtended);
    	double[][] charlieGray = ImageProcessing.toGray(charlie);    	
    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.extendedSimilarityMatrix(charlieGray, beach, extendedBeachGray);

    	System.out.println("Find N Best");
    	int[] best = Collector.findBest(similarity, false);   
    	double max = similarity[best[0]][best[1]];
    	
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");    	
    }
    
    //TODO: complete
}
