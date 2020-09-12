package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Creates a Storage instance.
     *
     * @param filePath A string representing the file path tasks are saved to.
     * @throws DukeException  If unable to create a file in given path.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Unable to create file...");
            }
        }

    }

    /**
     * Returns an array list containing tasks from previous session by extracting details
     * of tasks from local file and creating corresponding task objects.
     *
     * @return An array list containing tasks from previous session.
     * @throws DukeException  If there is no file available to load.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                // extract task details eg. type of task, if it is done, task description and date
                String[] currLine = scanner.nextLine().split(" \\| ");
                String typeOfTask = currLine[0];
                // adds task objects to current task list
                switch (typeOfTask) {
                case "T":
                    Task todo = new Todo(currLine[2]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        todo.doTask();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        deadline.doTask();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        event.doTask();
                    }
                    list.add(event);
                    break;
                default:
                    throw new DukeException("Trouble encountered when loading file :c");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load file.. :c");
        }
    }

    /**
     * Saves the tasks in the hard disk when the task list changes.
     *
     * @param taskToAdd A string representing details of the task to be added to the file.
     * @throws DukeException  If unable to edit file.
     */
    public void saveTaskList(String taskToAdd) throws DukeException {
        try {
            // appends task to local saved file
            FileWriter fw = new FileWriter(this.file, true);
            fw.write(taskToAdd + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error editing file!");
        }
    }

    /**
     * Edit the existing tasks in the hard disk when the task list changes.
     *
     * @param newTask A string representing new details of the task already present in the file.
     * @param taskNum Position of task in file.
     * @param delete If the action is to delete the task in file.
     * @throws DukeException  If unable to edit file.
     */
    public void editTaskList(String newTask, int taskNum, boolean delete) throws DukeException {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(this.filePath)));
            if (delete) {
                fileContent.remove(taskNum - 1);
            } else {
                fileContent.set(taskNum - 1, newTask);
            }
            Files.write(Paths.get(this.filePath), fileContent);
        } catch (IOException e) {
            throw new DukeException("Error editing file!");
        }
    }

}
