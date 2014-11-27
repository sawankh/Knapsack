package knapsack;

import interface_knapsack.IKnapsack;

import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 *Nombre de Usuario: Sawan
 Correo electronico : alu0100694765@ull.edu.es
 Nombre fichero: Knapsack.java
 Fecha: 10/11/2014
 */

/**
 * @author Sawan
 * 
 */
public class KnapsackDinamic implements IKnapsack {

	/** The knapsack_capacity. */
	private int knapsack_capacity;

	/** The number_objects. */
	private int number_objects;

	/** The object_weights. */
	private int[] object_weights;

	/** The object_values. */
	private int[] object_values;

	/** The value_table. */
	private int[][] value_table;

	/** The object_table. */
	private int[][] object_table;

	/** The solution_objects. */
	private ArrayList<Integer> solution_objects;
	
	/**
	 * Instantiates a new knapsack.
	 * 
	 * @param n_objects
	 *            the n_objects
	 * @param capacity
	 *            the capacity
	 * @param weights
	 *            the weights
	 * @param values
	 *            the values
	 */
	public KnapsackDinamic(int n_objects, int capacity, int[] weights, int[] values) {
		number_objects = n_objects;
		knapsack_capacity = capacity;
		object_weights = weights;
		object_values = values;
		value_table = new int[number_objects + 1][knapsack_capacity + 1];
		object_table = new int[number_objects][knapsack_capacity];
		solution_objects = new ArrayList<Integer>();

	}

	/**
	 * Solve knapsack.
	 */
	@Override
	public void solveKnapsack() {
		for (int object_analized = 1; object_analized <= number_objects; object_analized++) {
			for (int capacity_analized = 1; capacity_analized <= knapsack_capacity; capacity_analized++) {
				if (capacity_analized < object_weights[object_analized - 1]) {
					value_table[object_analized][capacity_analized] = value_table[object_analized - 1][capacity_analized];
				} else {
					
					int previous_row_value = value_table[object_analized - 1][capacity_analized];
					int new_possible_value = object_values[object_analized - 1] + value_table[object_analized - 1][capacity_analized - object_weights[object_analized - 1]];
					
					value_table[object_analized][capacity_analized] = Math.max(previous_row_value, new_possible_value);
					
					if (value_table[object_analized][capacity_analized] == new_possible_value) {
						object_table[object_analized - 1][capacity_analized - 1] = 1;
					}
					
				}
			}
		}
	}

	/**
	 * Gets the knapsack solution.
	 *
	 * @return the knapsack solution
	 */
	@Override
	public float getKnapsackSolution() {
		return value_table[number_objects][knapsack_capacity];
	}
	
	/**
	 * Gets the solution objects.
	 *
	 * @return the solution objects
	 */
	public void getSolutionObjects() {
		int capacity_left = knapsack_capacity;
		
		for (int object_analyzed = number_objects; object_analyzed > 0; object_analyzed--) {
			if (object_table[object_analyzed - 1][capacity_left - 1] == 1) {
				solution_objects.add(object_analyzed);
				capacity_left = capacity_left - object_weights[object_analyzed -1];
			}
		}
	}
	
	
	/**
	 * Prints the array list.
	 *
	 * @param arrayList the array list
	 */
	public static void printArrayList(ArrayList<Integer> arrayList) {
		for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
	}
	
	/**
	 * Prints the table.
	 *
	 * @param table the matrix
	 */
	public static void printTable(int [][] table) {
		for (int i = 0; i < table.length; i++) {
			System.out.print("|");
			for (int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j]);
				if (j != table[i].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}
	}

	/**
	 * Prints the array.
	 *
	 * @param array the array
	 */
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	/**
	 * Gets the knapsack_capacity.
	 *
	 * @return the knapsack_capacity
	 */
	public int getKnapsack_capacity() {
		return knapsack_capacity;
	}

	/**
	 * Gets the number_objects.
	 *
	 * @return the number_objects
	 */
	public int getNumber_objects() {
		return number_objects;
	}

	/**
	 * Gets the object_weights.
	 *
	 * @return the object_weights
	 */
	public int[] getObject_weights() {
		return object_weights;
	}

	/**
	 * Gets the object_values.
	 *
	 * @return the object_values
	 */
	public int[] getObject_values() {
		return object_values;
	}

	/**
	 * Gets the value_table.
	 *
	 * @return the value_table
	 */
	public int[][] getValue_table() {
		return value_table;
	}

	/**
	 * Gets the object_table.
	 *
	 * @return the object_table
	 */
	public int[][] getObject_table() {
		return object_table;
	}

	/**
	 * Gets the solution_objects.
	 *
	 * @return the solution_objects
	 */
	public ArrayList<Integer> getSolution_objects() {
		return solution_objects;
	}
	
	

}
