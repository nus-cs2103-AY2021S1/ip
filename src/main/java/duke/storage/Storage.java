package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * Implements file storage.
 *
 * @author Audrey Felicio Anwar
 */
public class Storage {
    private File data;

    /**
     * Intializes a Storage object.
     * @param filePath The location of the storage.
     * @throws DukeException If directory not found.
     */
    public Storage(String filePath) throws DukeException {
        try {
            File data = new File(filePath);
            if (!data.exists()) {
                data.createNewFile();
                assert data.exists() : " Directory cannot be made";
            }
            this.data = data;
        } catch (IOException error) {
            throw new DukeException(" I cannot find the directory :(");
        }
    }

    /**
     * Generates list of tasks from save file.
     *
     * @return List of tasks generated from save file.
     * @throws DukeException If there is file reading error.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split(" \\| ");
                String type = inputs[0];
                boolean isDone = inputs[1].equals("1");
                String description = inputs[2];
                LocalDate time;
                if (type.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (type.equals(("D"))) {
                    time = LocalDate.parse(inputs[3]);
                    tasks.add(new Deadline(description, isDone, time));
                } else if (type.equals("E")) {
                    time = LocalDate.parse(inputs[3]);
                    tasks.add(new Event(description, isDone, time));
                } else {
                    throw new DukeException(" I cannot identify the task type :(");
                }
            }
            return tasks;
        } catch (FileNotFoundException error) {
            throw new DukeException(" I cannot find the directory :(");
        }
    }

    /**
     * Saves task to hard disk.
     *
     * @param tasks Tasks user currently have.
     * @throws DukeException If there is writing file error.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(data));
            for (int i = 0; i < tasks.size(); i++) {
                writer.println(tasks.get(i).saveToHardDisk());
            }
            writer.close();
        } catch (IOException error) {
            throw new DukeException(" I cannot save your tasks :(");
        }
    }
}
