import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {


	public final Path path;


	public Storage(String filePath) {

		path = Paths.get(filePath);

	}

	public String load() throws DukeException {
		if (Files.exists((path))) {
			File f = new File(String.valueOf(this.path)); // create a File for the given file path
			try {
				Scanner s = new Scanner(f);
				String list = "";
				while (s.hasNext()) {
					list += s.nextLine();
					list += "|";
				}
				return list;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		throw new DukeException("File does not exist");

	}

	public Path getPath() {
		return this.path;
	}



	public void writeToFile(String filePath, String textToAdd) {
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.write(textToAdd);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void appendToFile(String filePath, String textToAppend) {
		try {
			FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
			fw.write(textToAppend);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveListToHardDisk(TaskList lst) {
		String list = "";
		for (int i = 0; i < lst.getSize(); i++) {
			Task targetTask = lst.get(i);

			list += targetTask.getStoreAs();
			list += "\n";
		}

		writeToFile(this.path.toString(), list);
	}
}
