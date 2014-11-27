/**
 * 
 */
package knapsack;

import interface_knapsack.IKnapsack;

// TODO: Auto-generated Javadoc
/**
 * The Class KnapsackGreedy.
 * 
 * @author Sawan
 */
public class KnapsackGreedy implements IKnapsack {

	/** The number_objects. */
	private int number_objects;

	/** The knapsack_capacity. */
	private int knapsack_capacity;

	/** The object_weights. */
	private int[] object_weights;

	/** The object_values. */
	private int[] object_values;

	/** The knapsack_solution. */
	private float knapsack_solution;

	/** The objects_used. */
	private int[] objects_used;
	
	private final static int USED = 1;

	/**
	 * Instantiates a new knapsack greedy.
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
	public KnapsackGreedy(int n_objects, int capacity, int[] weights,
			int[] values) {
		number_objects = n_objects;
		knapsack_capacity = capacity;
		object_weights = weights;
		object_values = values;
		knapsack_solution = 0;
		objects_used = new int[number_objects];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface_knapsack.IKnapsack#solveKnapsack()
	 */
	@Override
	public void solveKnapsack() {
		int current_weight = knapsack_capacity;
		int best_current_object;
		
		// While there is still space in the knapsack
		while (current_weight > 0) {
			// Choose the best object possible 
			best_current_object = -1;
			best_current_object = bestObject(best_current_object);
			
			// Mark as used the object
			setValuePos(objects_used, best_current_object, USED);
			
			// Reduce the weight of the knapsack as it is we are carrying a new object
			current_weight -= getValuePos(object_weights, best_current_object);
			knapsack_solution += getValuePos(object_values, best_current_object);
			
			if (current_weight < 0) {
				knapsack_solution -= object_values[best_current_object];
	            knapsack_solution += (1 + (float)current_weight/object_weights[best_current_object]) * object_values[best_current_object];
			}
			
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface_knapsack.IKnapsack#getKnapsackSolution()
	 */
	@Override
	public float getKnapsackSolution() {
		return knapsack_solution;
	}

	public int bestObject(int best_current_object) {
		for (int best_object = 0; best_object < number_objects; best_object++) {
			if ((objects_used[best_object] == 0) && ((best_current_object == -1) || ((float) object_values[best_object] / object_weights[best_object] > (float) object_values[best_current_object]
							/ object_weights[best_current_object])))
				return best_object;
		}
		
		return -1;
	}

	public static int getValuePos(int[] data, int position) {
		return data[position];
	}
	
	public static void setValuePos(int[] data, int position, int value) {
		data[position] = value;
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
	 * Gets the knapsack_capacity.
	 * 
	 * @return the knapsack_capacity
	 */
	public int getKnapsack_capacity() {
		return knapsack_capacity;
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
	 * Gets the knapsack_solution.
	 * 
	 * @return the knapsack_solution
	 */
	public float getKnapsack_solution() {
		return knapsack_solution;
	}

}
