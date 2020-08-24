package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.ReadFailedException;
import duke.task.Task;
import duke.task.Tasks;

/**
 * The Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /**
     * The file path where the file is located.
     */
    private final String filePath;
    /**
     * The File to be read and written.
     */
    private final File file;

    /**
     * Instantiates a new Storage.
     * Create a file using the file path.
     *
     * @param filePath the file path.
     * @throws IOException If the file cannot be created.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = createEmptyFile();
    }

    /**
     * Returns an empty file.
     *
     * @return the file.
     * @throws IOException If the file cannot be created.
     */
    private File createEmptyFile() throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
        return file;
    }

    /**
     * Returns tasks by reading the file.
     *
     * @return the tasks
     * @throws ReadFailedException If the file cannot be read.
     */
    public Tasks getTasks() throws ReadFailedException {
        Tasks tasks = new Tasks();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new ReadFailedException("tasks");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stringArr = line.split("_");
            tasks.addTask(stringArr);
        }
        scanner.close();

        return tasks;
    }

    /**
     * Adds the task.
     *
     * @param task the task.
     * @throws IOException If the task cannot be added.
     */
    public void addTask(Task task) throws IOException {
        this.writeData(task.getData() + "\n", true);
    }

    /**
     * Write data.
     *
     * @param data       the data.
     * @param appendMode true if data is to be appended, false otherwise.
     * @throws IOException If the file cannot be written.
     */
    private void writeData(String data, boolean appendMode) throws IOException {
        FileWriter fileWriter = new FileWriter(file, appendMode);
        fileWriter.write(data);
        fileWriter.close();
    }

    /**
     * Updates the tasks.
     *
     * @param newTasks the new tasks.
     * @throws IOException If the tasks cannot be updated.
     */
    public void updateTasks(Tasks newTasks) throws IOException {
        this.writeData(newTasks.getData(), false);
    }
}
