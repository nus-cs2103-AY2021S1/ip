package butler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import butler.command.AddCommand;
import butler.exception.ButlerException;
import butler.task.Task;
import butler.task.TaskList;

/**
 * Represents a storage responsible for reading and writing into the hard disk.
 * Reads a list of tasks from a given location in the hard disk and
 * stores the list of tasks into the same location within the hard disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new storage with the given <code>filePath</code>.
     * The storage will read and write into the file at the given <code>filePath</code>.
     *
     * @param filePath File path of the file to be read from and written into.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Stores the given <code>taskList</code> into the <code>filePath</code>.
     *
     * @param taskList List of tasks to be written into the hard disk.
     */
    public void storeTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String fileText = "";

            // Content of text to be written into file
            int listSize = taskList.getSize();
            for (int i = 0; i < listSize; i++) {
                Task task = taskList.getTask(i);
                String taskDetails = task.toStorageString();
                fileText += taskDetails + System.lineSeparator();
            }

            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            assert false : "There is an error with writing to the path. File is not detected.";
        }
    }

    /**
     * Loads the list of tasks from the <code>filePath</code>.
     *
     * @return A list of tasks read from the <code>filePath</code>.
     * @throws ButlerException if there is an error within the content of the file
     *                         or the file is not detected.
     */
    public ArrayList<Task> load() throws ButlerException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();

            //Inputs each task line by line
            while (s.hasNext()) {
                String input = s.nextLine();
                Task task = loadTaskFromLine(input);
                taskList.add(task);
            }
            return taskList;

        } catch (FileNotFoundException e) {
            createFilePath();
            throw new ButlerException("There is no file to access.");
        }
    }

    /**
     * Loads a task from a line of input.
     *
     * @param input String representation of a task.
     * @return A task represented by <code>input</code>.
     * @throws ButlerException if there is an error within the input.
     */
    private Task loadTaskFromLine(String input) throws ButlerException {
        try {
            String taskDetails = input.split(" ", 2)[1];
            AddCommand c = (AddCommand) Parser.parse(taskDetails);
            Task task = c.getTask();

            String completionStatus = input.split(" ", 2)[0];
            if (completionStatus.equals("complete")) {
                task.markComplete();
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("There is an error inside the file. The task has incomplete details.");
        } catch (ClassCastException e) {
            throw new ButlerException("Instead of tasks, a command was written into the file.");
        }
    }

    /**
     * Creates a filePath. Called only when filePath does not exist.
     */
    private void createFilePath() {
        try {
            Files.createDirectory(Paths.get("./data/"));
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            assert false : "FilePath was already created when calling this method."
                    + e.getMessage();
        }
    }
}
