
/**
 * Graded Homework of Machine Learning: Using cross validation to evaluate KNN algorithm
 * 
 * Name: Chao-Wei Liao
 * UTD-ID: 202-122-5352
 * Date: Oct. 10 2014
 * 
 * a collection of function to handle file I/O
 * 
 */
package KNN;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileProcessor {
	/**
	 * read first file, which contains cross-validation folds, number of examples, and 
	 * random permutations
	 * 
	 * @param fileName1 name of first file
	 * @param fileName2 name of second file
	 * @return Data instance
	 */
	@SuppressWarnings("resource")
	public static Instance readFirstFile(String fileName1, Instance instanceOfData){
		BufferedReader bufferOfFirstFile = null;
		String stringOfLine = new String();
		try{
			String fileLocation = FileProcessor.class.getResource(fileName1).getPath();
			System.out.println(fileLocation);
			bufferOfFirstFile = new BufferedReader(new FileReader(fileLocation));

		}catch(FileNotFoundException e){
			System.err.println(fileName1+" not found!");
		}

		//parse first line of input file
		try {
			stringOfLine = bufferOfFirstFile.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println("Input format of First File is WORNG!");
		}
		String initNumbers[]=stringOfLine.split(" ");
		//Instance instanceOfData = new Instance();
		instanceOfData.setNumberOfFold(Integer.parseInt(initNumbers[0]));
		instanceOfData.setOrderedExamples(Integer.parseInt(initNumbers[1]));
		instanceOfData.setNumberOfPermutations(Integer.parseInt(initNumbers[1]), Integer.parseInt(initNumbers[2]));;

		//parse permutations
		for(int i=0;i<instanceOfData.numberOfPermutations;i++){
			try {
				stringOfLine = bufferOfFirstFile.readLine();
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println("Input format of First File is WORNG!");
			}
			String[] orderOfPermutations=stringOfLine.split(" ");
			for(int j=0;j<instanceOfData.numberOfExamples;j++){
				instanceOfData.orderOfPermutations[i][j] = Integer.parseInt(orderOfPermutations[j]);
			}
		}

		return instanceOfData;
	}

	/**
	 * read second file, which contains trainning and testing data
	 * 
	 * @param filename name of second file
	 * @return Data instance of second file
	 */
	@SuppressWarnings("resource")
	public static Instance readSecondFile(String filename, Instance instanceOfData){
		BufferedReader bufferOfSecondFile = null;
		String stringOfLine = new String();
		try{
			String fileLocation = FileProcessor.class.getResource(filename).getPath();
			System.out.println(fileLocation);
			bufferOfSecondFile = new BufferedReader(new FileReader(fileLocation));

		}catch(FileNotFoundException e){
			System.err.println(filename+" not found!");
		}

		//parse first line of input file
		try {
			stringOfLine = bufferOfSecondFile.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
			System.err.println("Input format of Second File is WORNG!");
		}
		String initNumbers[]=stringOfLine.split(" ");
		instanceOfData.setInputPoints(Integer.parseInt(initNumbers[0]), Integer.parseInt(initNumbers[1]));

		//parse example grid
		int serialOfExamples = 0;
		for(int i=0;i<instanceOfData.numberOfRows;i++){
			try {
				stringOfLine = bufferOfSecondFile.readLine();
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println("Input format of Second File is WORNG!");
			}
			String[] lineOfRows=stringOfLine.split(" ");
			for(int j=0;j<instanceOfData.numberOfColums;j++){
				instanceOfData.inputPoints[i][j] = lineOfRows[j];
				instanceOfData.outputPoints[i][j] = lineOfRows[j];//copy to output array
				if(lineOfRows[j].equals("+")){
					instanceOfData.orderedExamples[serialOfExamples][1]=i;//x1
					instanceOfData.orderedExamples[serialOfExamples][0]=j;//x2
					instanceOfData.orderedExamples[serialOfExamples++][2]=1;//y
				}
				if(lineOfRows[j].equals("-")){
					instanceOfData.orderedExamples[serialOfExamples][1]=i;//x1
					instanceOfData.orderedExamples[serialOfExamples][0]=j;//x2
					instanceOfData.orderedExamples[serialOfExamples++][2]=0;//y
				}
			}
		}

		return instanceOfData;
	}

	public static void writeResult(String outputStrings, String filename){
		FileWriter fstream = null;
		try{
			fstream = new FileWriter(filename);
		}catch(java.io.FileNotFoundException e){
			//cannot create file or don't have permission to write
			System.err.println("Error: cannot create output file or don't have permission to write");
			System.exit(-1);
		} catch (IOException ex) {
			System.err.println("Error: IO error");
			System.exit(-1);
		}

		BufferedWriter fout = new BufferedWriter(fstream);
		try {
			fout.write(outputStrings);
			fout.close();
		} catch (IOException ex) {
			System.err.println("Error: IO error");
			System.exit(-1);
		}

	}

	/**
	 * @param args the command line arguments
	 * @return arrays of First, Second and output filenames
	 */
	public static String[] readFilename(String[] args){
		String[] inputFileNames = new String[5];
		if(args.length >= 3){
			//get input from command line argument
			inputFileNames[0] = args[0];
			inputFileNames[1] = args[1];
			inputFileNames[2] = args[2];
			inputFileNames[3] = args[3];
		}else{
			//get input from user
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input arguments ([first file] [second file] [output file] [Distance Alog]) keep blank to use default: ");
			String userInput = scanner.nextLine();
			String[] userInputSplit = userInput.split(" ");
			if(userInputSplit.length==4){
				inputFileNames[0] = userInputSplit[0];
				inputFileNames[1] = userInputSplit[1];
				inputFileNames[2] = userInputSplit[2];
				inputFileNames[3] = userInputSplit[3];
			}
			else{
				System.out.println("Use Default files ([FirstFile.txt] [SecondFile.txt] [output.txt] [1])");
				inputFileNames[0] = "FirstFile.txt";
				inputFileNames[1] = "SecondFile.txt";
				inputFileNames[2] = "output.txt";
				inputFileNames[3] = "1";
			}
		}
		return inputFileNames;
	}

}
