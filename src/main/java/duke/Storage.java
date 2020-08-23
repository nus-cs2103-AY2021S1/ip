package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the read and write operations to the data file
 */
public class Storage {

    String path;
    String content = "";

    /**
     * Constructor for the Storage Class
     *
     * @param path relative filepath where the datafile is located
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads the datafile if it exists else it creates a new data file
     * Adds all the tasks on the datafile into a new ArrayList
     *
     * @return returns a list of Task
     * @throws DukeException exception to be thrown if there is error reading the file
     */
    public List<Task> load() throws DukeException {

        String fileLine = "";
        List<Task> taskList = new ArrayList<>();
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            try {
                File dir = new File("data");
                dir.mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File cannot be created");
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((fileLine = reader.readLine()) != null) {
                    content += fileLine + "\n";
                    String[] str = fileLine.split(" \\| ");
                    switch (str[0]) {
                        case "T":
                            taskList.add(new Todo(str[2], Integer.parseInt(str[1])));
                            break;
                        case "D":
                            taskList.add(new Deadline(str[2], Integer.parseInt(str[1]), str[3]));
                            break;
                        case "E":
                            taskList.add(new Event(str[2], Integer.parseInt(str[1]), str[3]));
                            break;
                    }
                }
                content = content.trim();
                reader.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("File not Found");
            } catch (IOException e) {
                throw new DukeException("Error reading file: " + path);
            }
        }
        return taskList;
    }

    /**
     * Saves the task into the current datafile
     *
     * @param task task to be saved
     */
    public void save(Task task) {
        if(content.isEmpty()) {
            content = task.saveText(task.getStatus());
        } else {
            content = content + "\n" + task.saveText(task.getStatus());
        }
    }

    /**
     * Deletes the task from the data file
     *
     * @param task task to be deleted
     */
    public void delete(Task task) {
        content = content.replace(task.saveText(task.getStatus()), "");
    }

    /**
     * Replaces the uncompleted task with completed task
     *
     * @param task task that has been completed
     */
    public void replace(Task task) {
        content = content.replace(task.saveText(0), task.saveText(1));
    }

    /**
     * Updates and writes all the changes into a text file upon exiting the program
     *
     * @throws DukeException exception to be thrown if there is error writing file
     */
    public void update() throws DukeException {
        File file = new File("data/duke.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error writing file: " + path);
        }

    }

}
