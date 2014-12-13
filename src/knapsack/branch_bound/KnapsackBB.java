/**
 * 
 */
package knapsack.branch_bound;

import interface_knapsack.IKnapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
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
	private ArrayList<Branch> branch_bound_tree;

	private ArrayList<Double> branches_solution;

	/** The knapsack_solution. */
	private double knapsack_solution;

	private final static Integer PICK_OBJECT = 1;

	private final static Integer DONT_PICK_OBJECT = 0;

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
		branch_bound_tree = new ArrayList<Branch>();
		branches_solution = new ArrayList<Double>();
		knapsack_solution = 0;
	}

	/**
	 * Initial solution.
	 * 
	 * Calculates de initial solution set.
	 * 
	 * @return the int
	 */
	public double initialSolution() {
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
	public void intialBranch() {
		// Initial branches one intiates to 0 and the other to 1
		Branch branch_0 = new Branch(number_objects);
		Branch branch_1 = new Branch(number_objects);

		// Set branch_0 to 0 and branch_1 to 1
		branch_0.updateData(0, DONT_PICK_OBJECT);
		branch_1.updateData(0, PICK_OBJECT);

		// Get solution of each branch
		calculateSolution(branch_0);
		calculateSolution(branch_1);

		
		// If the sol is valid, means, greater than the actual solution add
		// branch
		addBranch(branch_0);
		addBranch(branch_1);

		// Check if they are last nodes
		updateSolution(branch_0, branch_1);
		
		cutBranches();
	}

	public void updateSolution(Branch branch_0, Branch branch_1) {
		if ((branch_0.getWeight() <= knapsack_capacity) && (branch_1.getWeight() <= knapsack_capacity)) {
			if (branch_0.isLast_node() && branch_1.isLast_node()) {
				knapsack_solution = Math.max(branch_0.getSolution(), branch_1.getSolution());
				if (branch_bound_tree.contains(branch_0)) {
					branch_bound_tree.remove(branch_0);
				}
				
				if (branch_bound_tree.contains(branch_1)) {
					branch_bound_tree.remove(branch_1);
				}
			}
		} else if ((branch_0.getWeight() <= knapsack_capacity) && (branch_0.isLast_node())) {
			knapsack_solution = branch_0.getSolution();
			if (branch_bound_tree.contains(branch_0)) {
				branch_bound_tree.remove(branch_0);
			}
			
			if (branch_bound_tree.contains(branch_1)) {
				branch_bound_tree.remove(branch_1);
			}
		} else if ((branch_1.getWeight() <= knapsack_capacity) && (branch_1.isLast_node())) {
			knapsack_solution = branch_1.getSolution();
			if (branch_bound_tree.contains(branch_0)) {
				branch_bound_tree.remove(branch_0);
			}
			
			if (branch_bound_tree.contains(branch_1)) {
				branch_bound_tree.remove(branch_1);
			}
		}
	}
	
	public void addBranch(Branch branch) {
		if (branch.getWeight() <= knapsack_capacity) {
			branch_bound_tree.add(branch);
			branches_solution.add(branch.getSolution());
		}
	}

	/**
	 * Calculate solution of a set.
	 * 
	 * @param branch
	 *            the data
	 * 
	 */
	public void calculateSolution(Branch branch) {
		double solution = 0;

		int sum_values = 0;
		int sum_weight = 0;
		int iterator_data = 0;

		while (((iterator_data < branch.getData().length)) && (branch.getData()[iterator_data] != null)) {
			sum_values += (branch.getData()[iterator_data] * object_values[iterator_data]);
			sum_weight += (branch.getData()[iterator_data] * object_weights[iterator_data]);
			iterator_data++;
		}
			if (branch.getData().length == iterator_data) {
			solution = sum_values;
			branch.setLast_node(true);
		} else {
			double next_average = ((double) object_values[iterator_data] / (double) object_weights[iterator_data]);
			solution = sum_values
					+ ((knapsack_capacity - sum_weight) * next_average);
		}
			
		branch.setWeight(sum_weight);
		branch.setSolution(solution);
	}

	public void createLevel(int position, Branch branch_father) {
		// Branches one intiates to 0 and the other to 1
		Branch branch_0 = Branch.copyBranch(branch_father);
		Branch branch_1 = Branch.copyBranch(branch_father);

		// Set branch_0 to 0 and branch_1 to 1
		// NO HACE 0 a 0
		branch_1.updateData(position, PICK_OBJECT);
		branch_0.updateData(position, DONT_PICK_OBJECT);
		
		
		// Get solution of each branch
		calculateSolution(branch_0);
		calculateSolution(branch_1);

		// If the sol is valid, means, greater than the actual solution add
		// branch
		addBranch(branch_0);
		addBranch(branch_1);
		
		// Check if they are last nodes
		updateSolution(branch_0, branch_1);
		
		
	}

	public void cutBranches() {
		Iterator<Branch> iterator = branch_bound_tree.iterator();
		while (iterator.hasNext()) {
			Branch branch = (Branch) iterator.next();
			if (branch.getSolution() <= knapsack_solution) {
				iterator.remove();
			}
		}
			
		
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

		// Add initial branches
		intialBranch();

		while (!branch_bound_tree.isEmpty()) {
			
			// Get Branch with highest solution value
			int index_max_solution = getBranch(Collections
					.max(branches_solution));
			Branch branch_max_solution = Branch.copyBranch(branch_bound_tree
					.get(index_max_solution));
			
			// Create next level
			createLevel(branch_max_solution.getLevel(), branch_max_solution);
			
			cutBranches();
			
			// Remove father branch
			if (!branch_bound_tree.isEmpty()) {
				branch_bound_tree.remove(index_max_solution);
			}
			
			branches_solution.remove(branches_solution.indexOf(branch_max_solution.getSolution()));
			
		}

	}

	public int getBranch(double solution) {
		int index = -1;
		for (Iterator<Branch> iterator = branch_bound_tree.iterator(); iterator.hasNext();) {
			Branch branch = (Branch) iterator.next();
			if (branch.getSolution() == solution) {
				index = branch_bound_tree.indexOf(branch);
				break;
			}
		}
		
		return index;
	}
	
	public void printSolutions() {
		for (Iterator<Double> iterator = branches_solution.iterator(); iterator.hasNext();) {
			Double type = (Double) iterator.next();
			System.out.println(type);
		}
		System.out.println("----------------------------------");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see interface_knapsack.IKnapsack#getKnapsackSolution()
	 */
	@Override
	public float getKnapsackSolution() {
		return (float) knapsack_solution;
	}

}
