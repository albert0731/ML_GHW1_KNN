
/**
 * Graded Homework of Machine Learning: Using cross validation to evaluate KNN algorithm
 * 
 * Name: Chao-Wei Liao
 * UTD-ID: 202-122-5352
 * Date: Oct. 10 2014
 * 
 * main program
 * 
 */
package KNN;

import java.io.IOException;
public class Main {

    /**
     * @param args the command line arguments 使用@param註記會產生參數(parameter)argv的相關說明
     * @return 傳回值的意義說明
     * @throws IOException 
     */
    public static void main(String[] args){
        //get user input (filenames)
        String[] inputFileNames = FileProcessor.readFilename(args);
        String outputResult = new String();
    	Instance instanceOfData = new Instance();
        //get input data from files
        FileProcessor.readFirstFile(inputFileNames[0], instanceOfData);
        FileProcessor.readSecondFile(inputFileNames[1],instanceOfData);
        //Set Max K and allocate space for epsilon and sigma 
        instanceOfData.setMaxKNeighbors(5, instanceOfData.numberOfPermutations);
        
        //set method of distance calculate 
        instanceOfData.methodOfDistanceCalculate = Integer.parseInt(inputFileNames[3]);
        if(instanceOfData.methodOfDistanceCalculate==1)
        	System.out.println("Taxicab Distance(Manhattan Distance) has been select!");
        else
        	System.out.println("Euclidean Distance has been select!");
        
        //instanceOfData.printInstance();
        //instanceOfData.printExampleList();
        
        //create a KNN instance and run
        KNN kNearestNeighbors = new KNN();
        for(instanceOfData.numberOfKNeighbors=1; instanceOfData.numberOfKNeighbors<=5; instanceOfData.numberOfKNeighbors++){
        	for(int row=0; row<instanceOfData.numberOfRows; row++){
        		for(int colum=0; colum<instanceOfData.numberOfColums; colum++){
        			if(instanceOfData.inputPoints[row][colum].equals("."))
        				instanceOfData.outputPoints[row][colum]=
        				kNearestNeighbors.findNearestNeighbors(instanceOfData,row,colum,instanceOfData.numberOfKNeighbors);//instance,X1,X2,K-Neighbors
        		}
        	}
        	//instanceOfData.printOutput();
        	//System.out.println(instanceOfData.printOutputToFile());
        	
    		//save output
    		outputResult += instanceOfData.printOutputToFile();
        }
        //instanceOfData.samplePoints[1][2]=kNearestNeighbors.findNearestNeighbors(instanceOfData,1,2,2);//instance,X1,X2,K-Neighbors
        //instanceOfData.printOutput(k);
        
        
        //Running Cross Validation
        CrossValidation CV = new CrossValidation();
        instanceOfData.numberOfKNeighbors=4;
        CV.run(instanceOfData);
        
        FileProcessor.writeResult(outputResult,inputFileNames[2]);
        
    }
}
