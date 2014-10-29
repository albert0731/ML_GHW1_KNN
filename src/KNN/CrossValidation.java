/**
 * Graded Homework of Machine Learning: Using cross validation to evaluate KNN algorithm
 * 
 * Name: Chao-Wei Liao
 * UTD-ID: 202-122-5352
 * Date: Oct. 10 2014
 * 
 * Cross Validation to evaluate KNN algorithm
 * 
 */
package KNN;
import java.util.Arrays;

public class CrossValidation {

    public void run(Instance instanceOfData){
        double epsilon;
        double sigma;
        int countOfRight = 0, countOfWrong = 0;//count for right or wrong classify
        int lastExample = 0; //point to last example just counted
        String classifyOfTarget;

    	
        //0. create a KNN instance
        KNN kNearestNeighbors = new KNN();
        
    	//1. how many groups?  examples divide by folds
    	int sizeOfEachFold = instanceOfData.numberOfExamples/instanceOfData.numberOfFolds;
    	//System.out.printf("\nsizeOfEachFold:%d\n",sizeOfEachFold);
    	

    	//2.1 test K-1 folds
    	for(int i=1; i<instanceOfData.numberOfFolds; i++){
            int[] disablePoints = Arrays.copyOfRange(instanceOfData.orderOfPermutations[0],i-1, i*sizeOfEachFold);
    		//set disable points
    		instanceOfData.setDisablePoints(disablePoints);
    		for(int j=sizeOfEachFold*(i-1); j<sizeOfEachFold*i; j++){
    			//
    			classifyOfTarget = kNearestNeighbors.findNearestNeighbors(instanceOfData,instanceOfData.orderedExamples[j][1],
    					instanceOfData.orderedExamples[j][0],instanceOfData.numberOfKNeighbors);//instance,X1,X2,K-Neighbors
    			if(instanceOfData.orderedExamples[j][2]==0 && classifyOfTarget.equals("+"))
    				countOfWrong++;
    			else if(instanceOfData.orderedExamples[j][2]==1 && classifyOfTarget.equals("-"))
    				countOfWrong++;
    				
    				//kNearestNeighbors.findNearestNeighbors(instanceOfData,instanceOfData.orderedExamples[i][1],instanceOfData.orderedExamples[i][0],instanceOfData.numberOfKNeighbors);//instance,X1,X2,K-Neighbors
    			System.out.printf("j=%d orderedExamples[j]=%d  countOfWrong=%d \n", j, instanceOfData.orderedExamples[j][2], countOfWrong);
    		}
    	}
    	
    	//2.2 test last fold
		//set disable points
		int[] disablePoints = Arrays.copyOfRange(instanceOfData.orderOfPermutations[0],sizeOfEachFold*(instanceOfData.numberOfFolds-1), instanceOfData.numberOfExamples);
		instanceOfData.setDisablePoints(disablePoints);
		for(int j=sizeOfEachFold*(instanceOfData.numberOfFolds-1); j<instanceOfData.numberOfExamples; j++){
			classifyOfTarget = kNearestNeighbors.findNearestNeighbors(instanceOfData,instanceOfData.orderedExamples[j][1],
					instanceOfData.orderedExamples[j][0],instanceOfData.numberOfKNeighbors);//instance,X1,X2,K-Neighbors
			if(instanceOfData.orderedExamples[j][2]==0 && classifyOfTarget.equals("+"))
				countOfWrong++;
			else if(instanceOfData.orderedExamples[j][2]==1 && classifyOfTarget.equals("-"))
				countOfWrong++;
    		
			//kNearestNeighbors.findNearestNeighbors(instanceOfData,instanceOfData.orderedExamples[i][1],instanceOfData.orderedExamples[i][0],instanceOfData.numberOfKNeighbors);//instance,X1,X2,K-Neighbors
			System.out.printf("j=%d orderedExamples[j]=%d  countOfWrong=%d \n", j, instanceOfData.orderedExamples[j][2], countOfWrong);
		}
		
		//3. count epsilon and sigma	
		int Permutations = 0;
		instanceOfData.epsilonOfEveryKNeighbors[instanceOfData.numberOfKNeighbors-1][Permutations] = Double.valueOf(countOfWrong)/Double.valueOf(instanceOfData.numberOfExamples);
	      //double[] sigmaOfEveryKNeighbors = new double[k];
    	System.out.printf("epsilon = %f", instanceOfData.epsilonOfEveryKNeighbors[instanceOfData.numberOfKNeighbors-1][Permutations]);
    }
}
