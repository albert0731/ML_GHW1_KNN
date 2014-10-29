/**
 * Graded Homework of Machine Learning: Using cross validation to evaluate KNN algorithm
 * 
 * Name: Chao-Wei Liao
 * UTD-ID: 202-122-5352
 * Date: Oct. 10 2014
 * 
 * K Nearest-Neighbors
 * 
 */
package KNN;

public class KNN {
    /**
     * @param disablePoint: for cross validation, don't calculate those points  
     * @return 
     * @throws 
     */
    public String findNearestNeighbors(Instance instanceOfData, int targetPointX2, int targetPointX1, 
    		int kNeighbors){    	
        
        double[] distanceToEveryExamples = new double[instanceOfData.numberOfExamples];
        double nearestDistance = Double.POSITIVE_INFINITY;//set distance to positive infinity
        int nearestExample = 0;
        //int[] valueOfKNeighbors = new int[kNeighbors];
        int numberOfPositive=0, numberOfNegative=0; //number of positive and negative near the target point
        
        //calculate the distance of target to every other points
        for(int i=0; i<instanceOfData.numberOfExamples; i++){
        	//!!!!calculate by Taxicab Distance(Manhattan Distance)
        	if(instanceOfData.methodOfDistanceCalculate==1){
        		distanceToEveryExamples[i] = Math.abs(instanceOfData.orderedExamples[i][0]-targetPointX1) +
            			Math.abs(instanceOfData.orderedExamples[i][1]-targetPointX2); 
        	}
        	else{
            //!!!!calculate by Euclidean Distance
            	distanceToEveryExamples[i] = Math.pow(Math.abs(instanceOfData.orderedExamples[i][0]-targetPointX1), 2) +
            			Math.pow(Math.abs(instanceOfData.orderedExamples[i][1]-targetPointX2), 2); 		

        	}    	
        }
        
        //check disable point
        if(instanceOfData.disablePoint!= null){
        	for(int i=0; i<instanceOfData.disablePoint.length; i++){
        		distanceToEveryExamples[instanceOfData.disablePoint[i]]=Double.POSITIVE_INFINITY;
        	}
        	//System.out.printf("disablePoint is not NULL!!   length=%d\n",instanceOfData.disablePoint.length);
        }
        //System.out.printf("Distance of (%d,%d) to Every Examples\n",targetPointX1,targetPointX2);
        //System.out.println(Arrays.toString(distanceToEveryExamples));
      
        //find nearest points
        for(int k=0; k<kNeighbors; k++){
        	for(int i=0; i<instanceOfData.numberOfExamples; i++){
        		if(distanceToEveryExamples[i]<nearestDistance){
        			nearestDistance = distanceToEveryExamples[i];
        			nearestExample = i;
        		}
        	}
			//valueOfKNeighbors[k]=instanceOfData.orderedExamples[nearestExample][2];
			
			//sum and decide target attribute (positive or negative)
			if(instanceOfData.orderedExamples[nearestExample][2]==1)
				numberOfPositive++;
			else
				numberOfNegative++;
			//reset values
			distanceToEveryExamples[nearestExample] = Double.POSITIVE_INFINITY;
			nearestDistance = Double.POSITIVE_INFINITY;
			//System.out.printf("nearestExample = %d\n",nearestExample);
        }

        //System.out.println(Arrays.toString(valueOfKNeighbors));
        //return value of target 
        if(numberOfPositive>numberOfNegative)
        	return "+";
        else
        	return "-";
    }
}
