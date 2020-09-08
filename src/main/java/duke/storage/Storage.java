package duke.storage;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with the loading and saving of tasks in text file.
 */
public class Storage {
    private static File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Reads the tasks saved in text file and collate them into a list.
     *
     * @return ArrayList of tasks from loaded file.
     * @throws DukeException when error is encountered while reading text file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            if (!this.file.exists()) {
                createNewEmptyFile();
                return new ArrayList<Task>();
            }
            Scanner fileScanner = new Scanner(this.file);
            return createTaskListFromFile(fileScanner);

        } catch (IOException e) {
            throw new DukeException("Error loading file!");
        }
    }

    /**
     * Creates a new empty file in the specified file path.
     *
     * @throws IOException
     */
    private void createNewEmptyFile() throws IOException {
        this.file.getParentFile().mkdirs();
        this.file.createNewFile();
    }

    /**
     * Loads all the tasks from the text file into a task list.
     *
     * @param fileScanner the text file scanner.
     * @return ArrayList of tasks corresponding to those in the text file.
     */
    private ArrayList<Task> createTaskListFromFile(Scanner fileScanner) {
        ArrayList<Task> taskList = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String task = fileScanner.nextLine();
            taskList.add(createTask(task));
        }
        fileScanner.close();
        return taskList;
    }

    /**
     * Creates a new task based on the String taken from the text file.
     *
     * @param task a single line from the text file.
     * @return Task corresponding to the parameter String task.
     */
    private Task createTask(String task) {
        String[] taskSplit = task.split(">", 4);
        String taskType = taskSplit[0];
        boolean taskIsDone = taskSplit[1].equals("1") ? true : false;
        String taskDescription = taskSplit[2];
        if (taskType.equals("T")) {
            return new ToDo(taskDescription, taskIsDone);
        } else if (taskType.equals("D")) {
            String taskBy = taskSplit[3];
            return new Deadline(taskDescription, taskBy, taskIsDone);
        } else {
            String taskAt = taskSplit[3];
            return new Event(taskDescription, taskAt, taskIsDone);
        }
    }

    /**
     * Saves the tasks in the TaskList into a text file.
     *
     * @param taskList the TaskList containing all the tasks to be saved.
     * @throws DukeException when error is encountered while saving text file.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.toEncoding() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file!");
        }
    }
}
