import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import knapsack.KnapsackDinamic;
import knapsack.KnapsackGreedy;
import knapsack.branch_bound.KnapsackBB;
import read_write.FileWriter;
import read_write.ReadFile;
import data_parser.KnapsackDataParser;
import execution_time.Average;
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
	
	/** The knapsack_bb. */
	private static KnapsackBB knapsack_bb;

	/** The files. */
	private static ArrayList<String> files;
	
	/** The Constant OUTPUT_DYNAMIC. */
	private final static String OUTPUT_DYNAMIC = "result_dynamic.txt";
	
	/** The Constant OUTPUT_GREEDY. */
	private final static String OUTPUT_GREEDY = "result_greedy.txt";
	
	private final static String OUTPUT_AVG_GREEDY = "avg_greedy.txt";
	
	private final static String OUTPUT_AVG_DYNAMIC = "avg_dynamic.txt";
	
	/** The Constant OUTPUT_BB. */
	private final static String OUTPUT_BB = "result_bb.txt";
	
	/** The Constant OUTPUT_AVG_BB. */
	private final static String OUTPUT_AVG_BB = "avg_bb.txt";
	
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
		getResultKnapsackBB(knapsack_bb, files, OUTPUT_BB);
		
		//Calculates the average time of each set of problems
		getAverage(OUTPUT_GREEDY, OUTPUT_AVG_GREEDY);
		getAverage(OUTPUT_DYNAMIC, OUTPUT_AVG_DYNAMIC);
		getAverage(OUTPUT_BB, OUTPUT_AVG_BB);
		
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
	 * Gets the result knapsack bb.
	 *
	 * @param knapsack the knapsack
	 * @param data_files the data_files
	 * @param output_file_name the output_file_name
	 * @return the result knapsack bb
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void getResultKnapsackBB(KnapsackBB knapsack, ArrayList<String> data_files, String output_file_name) throws IOException {
		ArrayList<String> result_data = new ArrayList<String>();
		
		for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
			String file_input = (String) iterator.next();
			MainClass.file = new ReadFile(file_input);
			file.extractContent();

			ExecutionTime method_time = new ExecutionTime();
			
			parser = new KnapsackDataParser(file.getContent());
			parser.parseData();
			
			knapsack = new KnapsackBB(parser.getNum_objects(),
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
	
	public static void getAverage(String input_file, String output_file) throws IOException {
		final int RANGE = 20;
		
		ReadFile input = new ReadFile(input_file);
		input.extractContent();
		
		ArrayList<Integer> average_data_time = new ArrayList<Integer>();
		ArrayList<Integer> average_data_num_objects = new ArrayList<Integer>();
		ArrayList<String> result = new ArrayList<String>();
		
		input.extractTime(average_data_time);
		input.extractNumObject(average_data_num_objects);
		
		Integer [] avg_data_time = arrayListToArray(average_data_time);
		
		int time_position = 0;
		int time_position_end = RANGE;		
		
		for (Iterator<Integer> iterator_num_obj = average_data_num_objects.iterator(); iterator_num_obj
				.hasNext();) {
			Integer num_object = (Integer) iterator_num_obj.next();
			ArrayList<Long> time = new ArrayList<Long>();
			
			for (int i = time_position; i < time_position_end; i++) {
				time.add((long) avg_data_time[i]);
			}
			
			time_position += RANGE;
			time_position_end += RANGE;
			
			Average average_t = new Average();
			average_t.getAverage(time);
			
			result.add(createResult(average_t.getAverage_time(), 0, num_object));
		}
		FileWriter fileWriter = new FileWriter(output_file);
		fileWriter.writeContent(result);
	}
	
	
	/**
	 * Array list to array.
	 *
	 * @param data the data
	 * @return the integer[]
	 */
	public static Integer[] arrayListToArray(ArrayList<Integer> data) {
		Integer[] result = data.toArray(new Integer[data.size()]);
		return result;
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
