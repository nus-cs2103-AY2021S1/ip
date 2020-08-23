import java.io.*;
import java.util.ArrayList;


public class Storage {

	File file;
	ArrayList<Task> tasks;

	/**
	 * Constructor for a Storage object.
	 * @param path The path to read/write the file to
	 * @throws IOException if it is unable to create the file or if an IO error occurs
	 */
	public Storage(String path) throws IOException, DukeException {
		tasks = new ArrayList<>();
		file = new File(path);
		file.createNewFile(); // makes new file only if not already existing
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		String line;
		// Add all the parsed lines to the ArrayList
		while ((line = csvReader.readLine()) != null) {
			tasks.add(Parser.parseLine(line));
		}
	}

	public void add(Task task) throws IOException {
		tasks.add(task);
		String taskString = Parser.convertTask(task);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.append(taskString);
		bw.flush();
		bw.close();
	}

	/**
	 * Removes the task at the given index from the ArrayList,
	 * then clears the existing file and rewrites the contents of the list back to it.
	 * @param index The index of the task to be removed
	 * @return The deleted task
	 * @throws IOException if there are issues with the IO operations
	 */
	public Task delete(int index) throws IOException, ArrayIndexOutOfBoundsException {
		Task deleted = tasks.remove(index);
		rewrite();
		return deleted;
	}

	/**
	 * Clears the existing file and rewrites the contents of the list to the file.
	 * @throws IOException if there are issues with the IO operations
	 */
	private void rewrite() throws IOException{
		new FileWriter(file, false).close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (Task t : tasks) {
			bw.append(Parser.convertTask(t));
			bw.flush();
		}
		bw.close();
	}

	@Override
	public String toString() {
		if (tasks.isEmpty()) {
			return "The list is empty.";
		} else {
			String result = "";
			for (Task t : tasks) {
				result = result.concat(t.toString()).concat("\n");
			}
			return result;
		}
	}
}
