package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.StorageNotCreatedException;
import duke.exceptions.StorageNotLoadedException;
import duke.exceptions.StorageNotUpdatedException;
import duke.logic.TaskList;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.Task;
import duke.logic.tasks.Todo;

/**
 * Represents the storing of the tasks in the user's computer. The Storage class
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Instantiates a Storage object.
     *
     * @param filePath The filePath of the file which stores the data.
     * @throws DukeException If the file to store the data is unable to be created.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        createFileIfAbsent(filePath);
        assert new File(filePath).exists()
                : "File to store tasks is supposed to be created";
    }

    /**
     * Creates a file to store the data if it does not already exist on the user computer.
     *
     * @param filePath The filePath of the file which stores the data.
     * @throws DukeException If the file to store the data is unable to be created.
     */
    public static void createFileIfAbsent(String filePath) throws DukeException {
        File f = new File(filePath);

        if (!f.exists()) {
            try {
                File dir = new File ("data");
                dir.mkdir();
                f.createNewFile();
            } catch (IOException ex) {
                throw new StorageNotCreatedException();
            }
        }
    }

    /**
     * Loads the list of tasks saved in the user's computer.
     *
     * @return Arraylist of tasks.
     * @throws DukeException If saved list of tasks cannot be loaded.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException ex) {
            throw new StorageNotLoadedException();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String data = sc.nextLine();
            Task t = createTask(data);
            tasks.add(t);
        }

        return tasks;
    }

    /**
     * Creates and returns a new task according to its task type.
     * @param data String representation of the task in the storage.
     * @return A new task according to its task type.
     * @throws DukeException If the deadline or event is not specified in the correct format.
     */
    public Task createTask(String data) throws DukeException {
        String[] taskInfos = data.split(" ; ");
        Task t;
        if (taskInfos[0].equals("T")) {
            t = new Todo(taskInfos[2]);
        } else if (taskInfos[0].equals("E")) {
            t = new Event(taskInfos[2], taskInfos[3]);
        } else {
            t = new Deadline(taskInfos[2], taskInfos[3]);
        }

        if (taskInfos[1].equals("1")) {
            t.markDone();
        }

        return t;
    }

    /**
     * Updates the list of tasks stored in the users data when a change is made.
     *
     * @param tasks The up-to-date list of tasks.
     * @throws DukeException If saved list of tasks cannot be loaded.
     */
    public void updateData(TaskList tasks) throws DukeException {
        try {
            String data = "";

            for (int i = 1; i <= tasks.getSize(); i++) {
                if (i == tasks.getSize()) {
                    data += tasks.getTask(i).toTaskData();
                } else {
                    data += tasks.getTask(i).toTaskData() + "\n";
                }
            }

            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException ex) {
            throw new StorageNotUpdatedException();
        }

    }
}
