package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handles the opening and storing of tasks from text files.
 */
public class Storage {
    private final Path path;

    /**
     * Constructor for the Storage object to track locally saved tasks.
     * The storage object checks for any pre-existing files and
     * if it is not available, the storage object will create a file.
     */
    public Storage() {
        String filePath = "./data/taskList.txt";
        path = Paths.get(filePath);
        File taskListfile = new File(filePath);
        if (Files.notExists(this.path)) {
            try {
                taskListfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Parses text file from file path specified to get
     * the Task objects stored, and returns them all
     * packaged in an ArrayList.
     *
     * @return ArrayList containing Task objects.
     * @throws DukeException Exception while loading from text file.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> outputTaskList = new ArrayList<>();
        Scanner taskReader;
        try {
            taskReader = new Scanner(path.toFile());
            while (taskReader.hasNextLine()) {
                String taskFromFile = taskReader.nextLine();
                // Note, this is assuming that format of
                // task.Task.getDescriptionForDatabase() remains the same.
                String[] formattedTaskString = taskFromFile.split(" - ");
                String taskType = formattedTaskString[0];
                boolean isTaskDone = formattedTaskString[1].equals("1");
                switch (taskType) {
                case "todo":
                    outputTaskList.add(new Todo(formattedTaskString[2], isTaskDone));
                    break;
                case "event":
                    outputTaskList.add(
                            new Event(
                                    formattedTaskString[2],
                                    formattedTaskString[3],
                                    isTaskDone
                            )
                    );
                    break;
                case "deadline":
                    outputTaskList.add(
                            new Deadline(
                                    formattedTaskString[2],
                                    formattedTaskString[3],
                                    isTaskDone
                            )
                    );
                    break;
                default:
                    break;
                }
            }
            taskReader.close();
        } catch (IOException e) {
            throw new DukeException("Exception while scanning task list file: " + e);
        }
        return outputTaskList;
    }

    /**
     * Saves the Tasks stored in a TaskList into the file path of the Storage.
     *
     * @param taskList TaskList object containing Task(s) to store.
     * @return true if store was successful.
     * @throws DukeException Exception while storing into file.
     */
    public boolean store(TaskList taskList) throws DukeException {
        try {
            File taskListFile = path.toFile();
            // Clear the pre-existing file if there is one.
            if (taskListFile.exists()) {
                taskListFile.delete();
                taskListFile.createNewFile();
            } else {
                throw new IOException("No file found at the specified path.");
            }
            assert taskListFile.exists() : "Task list file does not exist.";
            if (taskList.getSize() > 0) {
                FileWriter taskWriter = new FileWriter(taskListFile);
                for (Task t : taskList.getTaskList()) {
                    taskWriter.write(t.getDescriptionForDatabase());
                    taskWriter.write("\n");
                }
                taskWriter.close();
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("Exception occurred while storing into file: " + e);
        }
    }
}
