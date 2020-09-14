package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Saves the tasks in the hard disk automatically whenever the task list
 * changes and loads the data from the hard disk when Duke starts up.
 */
public class Storage {
    private static final String FOLDER_PATH = "data/";
    private final String storagePath;

    /**
     * Initializes a Storage object.
     *
     * @param fileName Name of the saved file.
     */
    public Storage(String fileName) {
        this.storagePath = FOLDER_PATH + fileName;
    }

    /**
     * Writes text to the saved file.
     *
     * @param textToAdd Text to be added into the file.
     */
    private void writeToFile(String textToAdd) {
        try {
            FileWriter fileWriter = new FileWriter(storagePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    /**
     * Writes the content of the task list to the saved file.
     *
     * @param taskList The task list such that its details are copied in the saved file.
     */
    public void writeTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            String taskStorageString = task.getStorageString();
            data.append(taskStorageString);
        }
        writeToFile(data.toString());
    }

    /**
     * Reads the file and forms a list of individual string representations of tasks in the file.
     *
     * @return A list of string representation of tasks in the saved file.
     * @throws IOException when the directory to the saved file is not found.
     */
    public List<String> readStorageFile() throws IOException {
        File folder = new File(this.FOLDER_PATH);
        File file = new File(this.storagePath);
        if (!folder.exists()) {
            folder.mkdirs();
            throw new IOException("Folder data does not exist");
        } else if (folder.exists() && !file.isFile()) {
            file.createNewFile();
            throw new IOException("duke.txt does not exist");
        }
        Scanner sc = new Scanner(file);
        List<String> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Converts the string representation of task in the saved file to a task object.
     *
     * @param savedTask String representation of task in the saved file.
     * @return The corresponding task.
     * @throws IOException when the folder or the saved file cannot be found.
     */
    private Task translateStringToTask(String savedTask) throws IOException {
        assert !savedTask.equals("") : "Saved task string cannot be empty";
        String[] components = savedTask.split(" \\| ");
        String command = components[0];
        boolean isDone = components[1].equals("\u2713");
        String description = components[2];
        String timeNotProcessed = components.length == 4 ? "" : components[3];
        boolean hasTime = timeNotProcessed.contains("T");
        String timeProcessed = timeNotProcessed.replace("T", " ");
        String priority = components.length == 5 ? components[4] : components[3];
        if (command.equals("T")) {
            return new ToDo(description, isDone, priority);
        } else if (command.equals("E")) {
            return new Event(description, timeProcessed, hasTime, isDone, priority);
        } else if (command.equals("D")) {
            return new DeadLine(description, timeProcessed, hasTime, isDone, priority);
        } else {
            throw new IOException("Saved task is invalid");
        }
    }

    /**
     * Reads from the saved file and creates the task list.
     *
     * @return Task list that contains all the tasks in the saved file.
     * @throws IOException when the directory to the saved file is not found.
     */
    public TaskList formTaskList() throws IOException {
        List<String> taskListInString = this.readStorageFile();
        TaskList tasks = new TaskList();
        for (String taskInString : taskListInString) {
            // check whether the current line is the end of file's line
            if (!taskInString.equals("")) {
                Task task = this.translateStringToTask(taskInString);
                assert task != null : "Task does not exist";
                tasks.addTask(task);
            }
        }
        return tasks;
    }
}
