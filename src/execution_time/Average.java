/**
 * 
 */
package execution_time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Sawan
 *
 */
public class Average {
	/**
	 *  The average time
	 */
	private float average_time;
	
	/**
	 * Initiates a average
	 */
	public Average() {
		average_time = 0;		
	}
	
	/**
	 * Given a set of data it calculates the average
	 * 
	 * @param time_data
	 */
	public void getAverage(ArrayList<Long> time_data) {
		
		int number_problems = 0;
		
		for (Iterator<Long> iterator = time_data.iterator(); iterator.hasNext();) {
			Long time = (Long) iterator.next();
			
			average_time += (float) time;
			
			number_problems++;
		}
		
		average_time = average_time / number_problems;
	}

	/**
	 * @return the average_time
	 */
	public float getAverage_time() {
		return average_time;
	}
	
	
	
}
