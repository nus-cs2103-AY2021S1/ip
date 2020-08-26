import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Storage {

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public Tasklist load() throws DukeException {
        Tasklist tasks = new Tasklist();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                Task nextTask = Parser.parseTask(nextLine);
                tasks.add(nextTask);
                nextLine = reader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
           throw new DukeException("Warning: Unable to find file containing task history. " +
                    "Please carry on if this is the first time using Duke.");
        } catch (IOException ioException) {
            throw new DukeException("Warning: Unable to read task history.");
        }
        return tasks;
    }

    public void save(Tasklist tasks) throws DukeException {
        try {
            // Make data directory if it doesn't exist
            Path pathToDirectory = Paths.get("data");
            if (!pathToDirectory.toFile().exists()) {
                File data = new File("data");
                data.mkdir();
            }

            String latestTaskListString = tasks.toSavedString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(latestTaskListString);
            writer.close();

        } catch (IOException ioException) {
            throw new DukeException("Unable to save task list.");
        }
    }

}
