package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import taskList.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to get data from and send data to the data file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor.
     * @param filePath the path of data file.
     */
    public Storage(String filePath) {
        assert filePath.length() > 0 : "File Path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Reads data from data file.
     * @return a list of tasks.
     * @throws DukeException to show error in reading the data file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(new File(filePath));
            ArrayList<Task> store = new ArrayList<>(100);
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split("\\|");
                String title = str[0].trim();
                boolean isDone = str[1].trim().equals(1 + "");
                if (title.equals("T")) {
                    store.add(new Todo(str[2].trim(), isDone));
                } else if (title.equals("D")) {
                    store.add(new Deadline(str[2].trim(), isDone, str[3].trim()));
                } else {
                    store.add(new Event(str[2].trim(), isDone, str[3].trim()));
                }
            }
            return store;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
    }

    /**
     * Wrties data to the data file.
     * @param tasks an updated list of tasks.
     */
    public void save(TaskList tasks) {
        assert tasks != null : "There is not task to be saved";
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTaskList()) {
                fw.write(task.toStringOfDatabase() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
