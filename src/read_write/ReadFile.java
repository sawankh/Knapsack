package read_write;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

// TODO: Auto-generated Javadoc
/**
 *Nombre de Usuario: Sawan
 Correo electronico : alu0100694765@ull.edu.es
 Nombre fichero: ReadFile.java
 Fecha: 10/11/2014
 */

/**
 * @author Sawan
 * 
 */
public class ReadFile {

	/** The reading_buffer. */
	private BufferedReader reading_buffer;

	/** The file_name. */
	private String file_name;

	/** The content. */
	private ArrayList<String> content;

	/**
	 * Instantiates a new read file.
	 * 
	 * @param file
	 *            the file
	 */
	public ReadFile(String file) {
		file_name = file;
		content = new ArrayList<String>();
	}

	/**
	 * Extract content.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void extractContent() throws IOException {
		reading_buffer = new BufferedReader(new FileReader(file_name));

		String content_str;

		while ((content_str = reading_buffer.readLine()) != null) {
			content.add(content_str);
		}
	}

	/**
	 * @return the reading_buffer
	 */
	public BufferedReader getReading_buffer() {
		return reading_buffer;
	}

	/**
	 * @return the file_name
	 */
	public String getFile_name() {
		return file_name;
	}

	/**
	 * @return the content
	 */
	public ArrayList<String> getContent() {
		return content;
	}

	/**
	 * Gets the all files from a directory and subdirectories.
	 * 
	 * @param directory
	 *            the directory
	 * @return the all files
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void getAllFiles(String directory, ArrayList<String> file_name)
			throws IOException {
		File f = new File(directory);
		File[] listfiles = f.listFiles();
		for (int i = 0; i < listfiles.length; i++) {
			if (listfiles[i].isDirectory()) {
				File[] internalFile = listfiles[i].listFiles();
				for (int j = 0; j < internalFile.length; j++) {
					// System.out.println(internalFile[j].getPath());
					file_name.add(internalFile[j].getPath());
					if (internalFile[j].isDirectory()) {
						String name = internalFile[j].getAbsolutePath();
						getAllFiles(name, file_name);
					}
				}
			} else {
				// System.out.println(listfiles[i]);
				file_name.add(listfiles[i].getPath());
			}

		}
	}

	/**
	 * Extract time.
	 * Extracts the time of execution of each result.
	 * 
	 * @param result_array the result_array
	 */
	public void extractTime(ArrayList<Integer> result_array) {
		final int TIME = 1;
		
		for (Iterator<String> iterator = content.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");

			int number_token = 0;
			
			while (tokenizer.hasMoreTokens()) {
				String data = tokenizer.nextToken();
				
				if (number_token == TIME) {
					result_array.add(Integer.parseInt(data));
				}
				
				number_token++;
			}
		}	
	}
	
	/**
	 * Extract num object.
	 * Extracts the number of objects that had the problem
	 * from each result
	 *
	 * @param result_array the result_array
	 */
	public void extractNumObject(ArrayList<Integer> result_array) {
		final int NUM_OBJECTS = 2;
		
		for (Iterator<String> iterator = content.iterator(); iterator.hasNext();) {
			String line = (String) iterator.next();
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");

			int number_token = 0;
			
			while (tokenizer.hasMoreTokens()) {
				String data = tokenizer.nextToken();
				
				if (number_token == NUM_OBJECTS) {
					if (!result_array.contains(Integer.parseInt(data))) {
						result_array.add(Integer.parseInt(data));
					}
				}
				
				number_token++;
			}
		}	
	}
	
}
