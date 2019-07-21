package main;
public class Program {
    public static void main(String[] args) {
    	/*System.out.println("Test Charlie, distance based");
     	int[][] image = Helper.read("images/charlie_beach.png");
     	int pattern[][] = Helper.read("images/charlie.png");
     	double distanceMatrix[][] = new double[image.length][image[0].length];
     	distanceMatrix = DistanceBasedSearch.distanceMatrix(pattern, image);
    	int [][] NBest = Collector.findNBest(4, distanceMatrix, true);
    	for (int i = 0; i < NBest.length; i++) {
    		Helper.drawBox(NBest[i][0], NBest[i][1], pattern[0].length, pattern.length, image);
    	}
    	
    	Helper.show(image, "Charlie est là !");*/
    	
    	System.out.println("Test Charlie, similarity");
     	int[][] image = Helper.read("images/charlie_beach.png");
     	int pattern[][] = Helper.read("images/charlie.png");
     	double similarityMatrix[][] = new double[image.length][image[0].length];
     	double patternGray[][] = ImageProcessing.toGray(pattern);
     	double imageGray[][] = ImageProcessing.toGray(image); 
     	similarityMatrix = SimilarityBasedSearch.similarityMatrix(patternGray, imageGray);
    	int [][] NBest = Collector.findNBest(4, similarityMatrix, false);
    	for (int i = 0; i < NBest.length; i++) {
    		Helper.drawBox(NBest[i][0], NBest[i][1], pattern[0].length, pattern.length, image);
    	}
    	Helper.show(image, "Charlie est là !");
    }

}
