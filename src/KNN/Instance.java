
/**
 * Graded Homework of Machine Learning: Using cross validation to evaluate KNN algorithm
 * 
 * Name: Chao-Wei Liao
 * UTD-ID: 202-122-5352
 * Date: Oct. 10 2014
 * 
 * a data structure that represent an instance of input data
 * 
 */
package KNN;

public class Instance {
    public String[][] inputPoints;//[x1][x2] value
    public String[][] outputPoints;//[x1][x2] value
    public int[][] orderedExamples;//[serialNumber][x1][x2][y 0:-  1:+]
    public int[] disablePoint;//for cross validation, don't calculate those points  
    public int[][] orderOfPermutations;
    public double[][] epsilonOfEveryKNeighbors;//Neighbors Permutations
    public double[][] sigmaOfEveryKNeighbors;
    public int numberOfFolds;
    public int numberOfExamples;
    public int numberOfPermutations;
    public int numberOfRows;
    public int numberOfColums;
    public int numberOfKNeighbors;
    public int MaxKNeighbors;
    public int methodOfDistanceCalculate;//0=Euclidean Distance;  1=Taxicab Distance(Manhattan Distance)
    
    public Instance(){
    	//this.numberOfKNeighbors = 5;
    }
    
    //set which points should not be calculate
    public void setDisablePoints(int[] points){
    	this.disablePoint = new int[points.length];
    	for(int i=0; i<points.length; i++){
    		disablePoint[i] = points[i];
    	}
    }
    
    public void setMaxKNeighbors(int k, int p){
        this.epsilonOfEveryKNeighbors = new double[k][p];
        this.sigmaOfEveryKNeighbors = new double[k][p];
        this.MaxKNeighbors = k;
    }
    
    //relate to first file
    public void setNumberOfPermutations(int examples, int permutation)
    {
    	this.numberOfPermutations = permutation;
    	this.orderOfPermutations = new int[permutation][examples];
    }
    public void setNumberOfFold(int fold){
    	this.numberOfFolds = fold;    	
    }
    //relate to second file
    public void setInputPoints(int row, int colum){
    	this.inputPoints = new String[row][colum];
    	this.outputPoints = new String[row][colum];
        this.numberOfRows = row;
        this.numberOfColums = colum;
    }
    //process by second file
    public void setOrderedExamples(int examples){
    	this.orderedExamples = new int [examples][4];//[SerialNumber][X1 X2 Y Groups]
    	this.numberOfExamples = examples;
    }
    
    //print instances
    public void printInstance(){
    	//print contance of First file
    	System.out.println("FirstFile:");
    	System.out.printf("%d ",this.numberOfFolds);
    	System.out.printf("%d ",this.numberOfExamples);
    	System.out.printf("%d \n",this.numberOfPermutations);
        for(int i=0;i<orderOfPermutations.length;i++){
        	for(int j=0; j< orderOfPermutations[i].length; j++)
        		System.out.printf(orderOfPermutations[i][j]+" ");
        	System.out.printf("\n");
        }
        System.out.printf("\n");       
        
    	//print content of Second file
    	System.out.println("SecondFile:");
    	System.out.printf("%d ",this.numberOfRows);
    	System.out.printf("%d \n",this.numberOfColums);
        for(int i=0;i<inputPoints.length;i++){
        	for(int j=0; j< inputPoints[i].length; j++)
        		System.out.printf(inputPoints[i][j]+" ");
        	System.out.printf("\n");
        }
        System.out.printf("\n");
    }
    
	//print example list
    public void printExampleList(){
        System.out.print("\nExample List\n");
    	System.out.println("No. | x1 | x2 | y");
        for(int i=0;i<orderedExamples.length;i++){
        	System.out.printf(i+"   | "+orderedExamples[i][0]+"  | "+orderedExamples[i][1]+"  | "+orderedExamples[i][2]+"\n");
        }
    }
    
  //print content of Output Grid
    public void printOutput(){  	
    	System.out.printf("\n==Output Grid==\n");
    	System.out.printf("k=%d \n",this.numberOfKNeighbors);
        for(int i=0;i<outputPoints.length;i++){
        	for(int j=0; j< outputPoints[i].length; j++)
        		System.out.printf(outputPoints[i][j]+" ");
        	System.out.printf("\n");
        }   
    }
    
    //print content of Output Grid
    public String printOutputToFile(){
    	String output = new String();
    	output += "k="+this.numberOfKNeighbors+" e="+this.epsilonOfEveryKNeighbors[this.numberOfKNeighbors-1][0]
    			+" sigma="+this.sigmaOfEveryKNeighbors[this.numberOfKNeighbors-1][0]+" \n";
        for(int i=0;i<outputPoints.length;i++){
        	for(int j=0; j< outputPoints[i].length; j++)
        		output += outputPoints[i][j]+" ";
        	output += "\n";
        }
        output += "\n";
        return output;
    }
    
    //assume target attribute is the last attribute
//    public int targetAttribute(){
//        return coordinate[coordinate.length-1];
//    }
}
