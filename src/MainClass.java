import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import knapsack.KnapsackDinamic;
import knapsack.KnapsackGreedy;
import read_write.FileWriter;
import read_write.ReadFile;
import data_parser.KnapsackDataParser;
import execution_time.ExecutionTime;



// TODO: Auto-generated Javadoc
/**
 *Nombre de Usuario: Sawan
 Correo electronico : alu0100694765@ull.edu.es
 Nombre fichero: MainClass.java
 Fecha: 10/11/2014
 */

/**
 * @author Sawan
 * 
 */
public class MainClass {

	/** The file. */
	private static ReadFile file;

	/** The parser. */
	private static KnapsackDataParser parser;

	/** The knapsack. */
	private static KnapsackDinamic knapsack_dynamic;
	
	/** The knapsack_greedy. */
	private static KnapsackGreedy knapsack_greedy;

	/** The files. */
	private static ArrayList<String> files;
	
	/** The Constant OUTPUT_DYNAMIC. */
	private final static String OUTPUT_DYNAMIC = "result_dynamic.txt";
	
	/** The Constant OUTPUT_GREEDY. */
	private final static String OUTPUT_GREEDY = "result_greedy.txt";
	
	/**
	 * The main method.
	 *
	 * @param args            the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Read all files from a directory, in this case, the input directory
		files = new ArrayList<String>();
		ReadFile.getAllFiles("input files", files);
		
		//Calculates the time elapsed of all the knapsack cases time give in microseconds
		getResultKnapsackDinamic(knapsack_dynamic, files, OUTPUT_DYNAMIC);
		getResultKnapsackGreedy(knapsack_greedy, files, OUTPUT_GREEDY);		
		
		// Succesfull end
		System.out.println("Files Generated succesfully !");
	}

	
	/**
	 * Gets the result knapsack dinamic.
	 *
	 * @param knapsack the knapsack
	 * @param data_files the data_files
	 * @param output_file_name the output_file_name
	 * @return the result knapsack dinamic
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void getResultKnapsackDinamic(KnapsackDinamic knapsack, ArrayList<String> data_files, String output_file_name) throws IOException {
		ArrayList<String> result_data = new ArrayList<String>();
		
		for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
			String file_input = (String) iterator.next();
			MainClass.file = new ReadFile(file_input);
			file.extractContent();

			ExecutionTime method_time = new ExecutionTime();
			
			parser = new KnapsackDataParser(file.getContent());
			parser.parseData();
			
			knapsack = new KnapsackDinamic(parser.getNum_objects(),
					parser.getKnapsack_capacity(), parser.getObject_values(),
					parser.getObject_weights());
			method_time.startChrono();
			knapsack.solveKnapsack();
			method_time.stopChrono();
			
			result_data.add(createResult(knapsack.getKnapsackSolution(), method_time.getTimeElapsed(), knapsack.getNumber_objects()));
		}
		
		FileWriter fileWriter = new FileWriter(output_file_name);
		fileWriter.writeContent(result_data);
	}
	
	/**
	 * Gets the result knapsack greedy.
	 *
	 * @param knapsack the knapsack
	 * @param data_files the data_files
	 * @param output_file_name the output_file_name
	 * @return the result knapsack greedy
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void getResultKnapsackGreedy(KnapsackGreedy knapsack, ArrayList<String> data_files, String output_file_name) throws IOException {
		ArrayList<String> result_data = new ArrayList<String>();
		
		
		for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
			String file_input = (String) iterator.next();
			MainClass.file = new ReadFile(file_input);
			file.extractContent();

			ExecutionTime method_time = new ExecutionTime();
			
			parser = new KnapsackDataParser(file.getContent());
			parser.parseData();
			
			knapsack = new KnapsackGreedy(parser.getNum_objects(),
					parser.getKnapsack_capacity(), parser.getObject_values(),
					parser.getObject_weights());
			method_time.startChrono();
			knapsack.solveKnapsack();
			method_time.stopChrono();
			
			result_data.add(createResult(knapsack.getKnapsackSolution(), method_time.getTimeElapsed(), knapsack.getNumber_objects()));
		}
		
		FileWriter fileWriter = new FileWriter(output_file_name);
		fileWriter.writeContent(result_data);
	}
	
	/**
	 * Creates the result.
	 *
	 * @param solution the solution
	 * @param time_elapsed the time_elapsed
	 * @return the string
	 */
	public static String createResult(float solution, long time_elapsed, int number_objects) {
		return Float.toString(solution) + "\t" + Long.toString(time_elapsed) + "\t" + Integer.toString(number_objects);
	}
}
