package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

public class Storage {
    /**
     * Saves task list onto a prescribed file.
     * @param tasks the tasklist to be saved
     * @throws IOException if file not successfully made (e.g. permission error)
     */
    public static void save(TaskList tasks) throws IOException {
        // Save file
        Path dirPath = Paths.get("data");
        Path filePath = Paths.get("data", "duke.txt");

        // Possible error: directory doesn't exist
        if (!dirPath.toFile().exists()) {
            File data = new File(dirPath.toString());
            data.mkdir();
        }

        // Write to file
        String toWrite = tasks.toString();
        BufferedWriter wr = new BufferedWriter(new FileWriter(filePath.toString()));
        wr.write(toWrite);
        wr.close();
    }

    public static TaskList load() throws FileNotFoundException, IOException, DukeException, ParseException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Path filePath = Paths.get("data", "duke.txt");
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()));
        String nextLine = reader.readLine();
        while (nextLine != null) {
            // Task res = parser.parseAdd(nextLine);
            // TODO: 26/8/20 Not working yet; add tasks to the list
        }

        return tasks;
    }

}
