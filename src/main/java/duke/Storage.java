package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles reading and writing from database.
 */
public class Storage {
    private String dest;

    /**
     * Initialise the destination of database.
     *
     * @param dest
     */
    public Storage(String dest) {
        this.dest = dest;
    }

    /**
     * Returns the taskList saved at destination. If file does not exist attempts to create one.
     *
     * @return a taskList
     * @throws IOException error creating the file
     */
    public TaskList loadTaskList() throws IOException {
        try {
            TaskList tasks = new TaskList();
            File file = new File(dest);

            new File(dest.split("/")[0]).mkdir();
            file.createNewFile();

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(Parser.parseSavedTask(scanner.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find file after creating.");
        } catch (IOException e) {
            throw new IOException("Error creating file.\n" + e.getMessage());
        }
    }

    /**
     * Saves a taskList to file at destination.
     *
     * @param taskList the taskList to be saved
     * @throws FileNotFoundException couldn't find the file at destination.
     */
    public void saveTaskList(TaskList taskList) throws FileNotFoundException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));
            for (Task task: taskList) {
                bufferedWriter.write(task.toSavedString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Couldn't find file");
        }
    }
}
