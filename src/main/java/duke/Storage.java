package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Storage object deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String homeDirectory;
    private final String filePath;

    /**
     * Initializes the Storage object.
     *
     * @param homeDirectory Path to the data directory.
     * @param fileName File name of the .txt file that stores the task data.
     */
    public Storage(String homeDirectory, String fileName) {
        this.homeDirectory = homeDirectory;
        this.filePath = homeDirectory + fileName;
    }

    /**
     * Writes the current list of tasks to the duke.txt file.
     *
     * @param tasks TaskList containing current list of tasks.
     * @throws DukeException If unable to create or write file.
     */
    public void write(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.getTaskList()) {
                textToAdd.append(task.toFileString());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing task to file..");
        }
    }

    /**
     * Returns ArrayList of tasks found in duke.txt file.
     *
     * @return ArrayList of tasks.
     * @throws DukeException If the duke.txt file has an unknown task type. If the file cannot be loaded.
     */
    public ArrayList<Task> load() throws DukeException {
        File dir = new File(homeDirectory);
        File file = new File(filePath);

        if (!dir.exists()) {
            new File(homeDirectory).mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Cannot load, file not found!");
            }
        }

        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String type = sc.nextLine();
                boolean done = Boolean.parseBoolean(sc.nextLine());
                String desc = sc.nextLine();
                Task task;

                switch (type) {
                case "T":
                    task = new Todo(desc);
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                case "D":
                    task = new Deadline(desc, sc.nextLine());
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                case "E":
                    task = new Event(desc, sc.nextLine());
                    if (done) {
                        task.markAsDone();
                    }
                    if (sc.hasNext()) {
                        sc.nextLine();
                    }
                    break;
                default:
                    throw new DukeException("Hmm.. The duke.txt file contains an unknown task type.");
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot load, file not found!");
        }
    }
}
