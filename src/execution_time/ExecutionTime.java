package execution_time;

// TODO: Auto-generated Javadoc
/**
 * The Class ExecutionTime.
 */

/**
 * @author alu4412
 *
 */
public class ExecutionTime {
	
	/** The starting_time. */
	private long starting_time;
	
	/** The ending_time. */
	private long ending_time;
	
	/** The time_elapsed. */
	private long time_elapsed;
	
	/** The Constant MICRO_SECONDS. */
	private final static long MICRO_SECONDS = 1000;
	
	/**
	 * Instantiates a new execution time.
	 */
	public ExecutionTime() {
		starting_time = 0;
		ending_time = 0;
		time_elapsed = 0;
	}
	
	/**
	 * Start chrono.
	 */
	public void startChrono() {
		starting_time = System.nanoTime() / MICRO_SECONDS;
	}
	
	/**
	 * Stop chrono.
	 */
	public void stopChrono() {
		ending_time = System.nanoTime() / MICRO_SECONDS;
	}
	
	/**
	 * Gets the time elapsed.
	 *
	 * @return the time elapsed
	 */
	public long getTimeElapsed() {
		time_elapsed = ending_time - starting_time;
		return time_elapsed;
	}
 }
