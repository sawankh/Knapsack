/**
 * 
 */
package knapsack.branch_bound;

/**
 * The Class Branch.
 *
 * @author Sawan
 */
public class Branch implements Cloneable{
	
	/** The data. */
	private Integer[] data;
	
	/** The solution. */
	private double solution;
	
	private boolean last_node;
	
	private int weight;
	
	/**
	 * Instantiates a new branch.
	 *
	 * @param size_data the size_data
	 */
	public Branch(int size_data) {
		data =  new Integer[size_data];
		solution = 0;
		last_node = false;
		weight = 0;
	}
	
	public Branch(Branch branch) {
		
	}
	
	/**
	 * Initialize data.
	 */
	public void initializeData() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
	}
	
	/**
	 * Update data.
	 *
	 * @param position the position
	 * @param value the value
	 */
	public void updateData(int position, Integer value) {
		data[position] = new Integer(value);
	}

	public void printData() {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
	
	/**
	 * @return the data
	 */
	public Integer[] getData() {
		return data;
	}

	/**
	 * @return the solution
	 */
	public double getSolution() {
		return solution;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(double solution) {
		this.solution = solution;
	}

	/**
	 * @return the last_node
	 */
	public boolean isLast_node() {
		return last_node;
	}

	/**
	 * @param last_node the last_node to set
	 */
	public void setLast_node(boolean last_node) {
		this.last_node = last_node;
	}
	
	
	
	/**
	 * @param data the data to set
	 */
	public void setData(Integer[] data) {
		this.data = data;
	}

	public int getLevel() {
		int level = 0;
		
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				level = i;
				break;
			}
		}
		return level;
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public static Branch copyBranch(Branch branch) {
		Branch branch_result = new Branch(branch.getData().length);
		System.arraycopy(branch.getData(), 0, branch_result.getData(), 0, branch.getData().length);
		branch_result.setLast_node(branch.isLast_node());
		branch_result.setSolution(branch.getSolution());
		return branch_result;
	}
}
