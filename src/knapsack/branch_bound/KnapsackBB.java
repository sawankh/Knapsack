/**
 * 
 */
package knapsack.branch_bound;

import java.util.ArrayList;

import interface_knapsack.IKnapsack;

/**
 * The Class KnapsackBB.
 * 
 * @author Sawan
 */
public class KnapsackBB implements IKnapsack {

	/** The knapsack_capacity. */
	private int knapsack_capacity;

	/** The number_objects. */
	private int number_objects;

	/** The object_weights. */
	private int[] object_weights;

	/** The object_values. */
	private int[] object_values;

	/** The branch_bound_tree. */
	private ArrayList<Integer[]> branch_bound_tree;

	private int knapsack_solution;

	/**
	 * Instantiates a new knapsack bb.
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
	public KnapsackBB(int n_objects, int capacity, int[] weights, int[] values) {
		number_objects = n_objects;
		knapsack_capacity = capacity;
		object_weights = weights;
		object_values = values;
		branch_bound_tree = new ArrayList<Integer[]>();
		knapsack_solution = 0;
	}

	/**
	 * Initial solution.
	 * 
	 * Calculates de initial solution set.
	 * 
	 * @return the int
	 */
	public int initialSolution() {
		int solution_weight = 0;
		int solution_value = 0;
		for (int i = 0; i < object_values.length; i++) {
			solution_weight += object_weights[i];

			if (solution_weight <= knapsack_capacity) {
				solution_value += object_values[i];
			} else {
				break;
			}
		}
		return solution_value;
	}

	/**
	 * Intial branch.
	 *
	 * Creates an initial point to develop the tree
	 *
	 * @return the integer[]
	 */
	public Integer[] intialBranch() {
		Integer[] branch =  new Integer[number_objects];
		
		for (int i = 0; i < branch.length; i++) {
			branch[i] = null;
		}
		
		return branch;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface_knapsack.IKnapsack#solveKnapsack()
	 */
	@Override
	public void solveKnapsack() {
		// Get an initial solution 
		knapsack_solution = initialSolution();
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interface_knapsack.IKnapsack#getKnapsackSolution()
	 */
	@Override
	public float getKnapsackSolution() {
		return 0;
	}

}
