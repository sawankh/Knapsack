package read_write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// TODO: Auto-generated Javadoc
/**
 * The Class FileWriter.
 */

/**
 * @author Sawan
 *
 */
public class FileWriter {
	
	/** The file_name. */
	private String file_name;
	
	/** The buffer_wirter. */
	private BufferedWriter buffer_wirter;
	
	/** The file_writer. */
	private java.io.FileWriter file_writer;
	
	/** The output_file. */
	private File output_file;
	
	/**
	 * Instantiates a new file writer.
	 *
	 * @param file_name the file_name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FileWriter(String file_name) throws IOException {
		this.file_name = file_name;
		output_file = new File(this.file_name);
		file_writer = new java.io.FileWriter(output_file.getAbsoluteFile());
		buffer_wirter = new BufferedWriter(file_writer);
		
		// If the file does not exist create it
		createFile();
	}

	/**
	 * Write content.
	 *
	 * @param file_content the file_content
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeContent(ArrayList<String> file_content) throws IOException {
		for (Iterator<String> content_iterator = file_content.iterator(); content_iterator.hasNext();) {
			String content_line = (String) content_iterator.next();
			
			buffer_wirter.write(content_line);
			buffer_wirter.newLine();
		}
		
		buffer_wirter.close();
	}
	
	
	/**
	 * Creates the file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createFile() throws IOException {
		if (!output_file.exists()) {
			output_file.createNewFile();
		}
	}
	
	
}
