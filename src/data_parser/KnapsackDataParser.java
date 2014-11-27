package data_parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

// TODO: Auto-generated Javadoc
/**
 *Nombre de Usuario: Sawan
  Correo electronico : alu0100694765@ull.edu.es
  Nombre fichero: KnapsackDataParser.java
  Fecha: 10/11/2014
 */

/**
 * @author Sawan
 *
 */
public class KnapsackDataParser {
	
	/** The content_to_parse. */
	private ArrayList<String> content_to_parse;
	
	/** The num_objects. */
	private int num_objects;
	
	/** The knapsack_capacity. */
	private int knapsack_capacity;
	
	/** The object_weights. */
	private int[] object_weights;

	/** The object_values. */
	private int[] object_values;
	
	/** The Constant ROW_NUM_OBJECT. */
	private final static int ROW_NUM_OBJECT = 0;
	
	/** The Constant ROW_CAPACITY. */
	private final static int ROW_CAPACITY = 1;
	
	/** The Constant OBJECT_VALUE. */
	private final static int OBJECT_VALUE = 0;
	
	/** The Constant OBJECT_WEIGHT. */
	private final static int OBJECT_WEIGHT = 1;
	
	
	/**
	 * Instantiates a new knapsack data parser.
	 *
	 * @param data the data
	 */
	public KnapsackDataParser(ArrayList<String> data) {
		content_to_parse = data;
	}
	
	/**
	 * Parses the data.
	 */
	public void parseData() {
		
		int position_values = 0;
		int position_weights = 0;
		int line_number = 0;
		
		
		for (Iterator<String> iterator = content_to_parse.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			
			int token_number = 0;
			
			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();
				
				if (line_number == ROW_NUM_OBJECT) {
					num_objects = Integer.parseInt(word);
					object_values = new int[num_objects];
					object_weights = new int[num_objects];
				} else if (line_number == ROW_CAPACITY) {
					knapsack_capacity = Integer.parseInt(word);
				} else {
					if (token_number == OBJECT_VALUE) {
						object_values[position_values] = Integer.parseInt(word);
						position_values++;
					} else if (token_number == OBJECT_WEIGHT) {
						object_weights[position_weights] = Integer.parseInt(word);
						position_weights++;
					}
				}
			token_number++;
			}
			
			line_number++;
		}
	}

	/**
	 * @return the num_objects
	 */
	public int getNum_objects() {
		return num_objects;
	}

	/**
	 * @return the knapsack_capacity
	 */
	public int getKnapsack_capacity() {
		return knapsack_capacity;
	}

	/**
	 * @return the object_weights
	 */
	public int[] getObject_weights() {
		return object_weights;
	}

	/**
	 * @return the object_values
	 */
	public int[] getObject_values() {
		return object_values;
	}
	
	
	
}
