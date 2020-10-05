package duke.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Permanent storage that stores user's task list in a data file.
 */
public class Storage {
    private final String path;

    /**
     * Initializes a storage object using the given file path.
     *
     * @param path the given file path.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads from the data file and returns the task list it stores.
     *
     * @return the task list stored in the data file.
     * @throws DukeException if errors occur while trying to read the data file.
     */
    public LinkedList<Task> readList() throws DukeException {
        LinkedList<Task> taskList = new LinkedList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                Task task = generateTask(nextLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            createDataFile();
        }
        return taskList;
    }

    private void createDataFile() throws DukeException {
        new File("data").mkdir();
        try {
            new File(path).createNewFile();
        } catch (IOException ioException) {
            throw new DukeException("I cannot create the data file!");
        }
        assert new File(path).exists() : "Failed to access the file!";
        throw new DukeException("No data file found, a new data file created!");
    }

    private Task generateTask(String nextLine) {
        Task task = new Task("");
        String[] components = nextLine.split(" \\| ");
        try {
            switch (components[0]) {
            case "T":
                task = new Todo(components[2]);
                break;
            case "D":
                task = new Deadline(components[2], components[3]);
                break;
            case "E":
                task = new Event(components[2], components[3]);
                break;
            default:
                throw new DukeException("I found an illegal string in the data file.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        if (components[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the given task list to the data file.
     *
     * @param list the task list to be stored in the data file.
     * @throws DukeException if errors occur when trying to write task list to the data file.
     */
    public void saveList(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(path);
            for (Task task : list.getList()) {
                writer.write(task.toStorageString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I cannot write the data!");
        }

    }
}
