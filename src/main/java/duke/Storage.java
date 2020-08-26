package duke;

import java.io.*;
import java.util.Scanner;

/**
 * Handles reading and writing from database.
 */
public class Storage {
    String dest;

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
    public TaskList loadFile() throws IOException {
        try {
            TaskList tasks = new TaskList();
            File file = new File(dest);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(Parser.parseSavedTask(scanner.nextLine()));
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find file after creating.");
        } catch (IOException e) {
            throw new IOException("Error creating file.");
        }
    }

    /**
     * Saves a taskList to file at destination.
     *
     * @param taskList the taskList to be saved
     * @throws FileNotFoundException couldn't find the file at destination.
     */
    public void writeFile(TaskList taskList) throws FileNotFoundException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));
            for (Task task: taskList) {
                bufferedWriter.write(task.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException("Couldn't find file");
        }
    }
}
