package duke.storage;

import duke.task.Task;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.TaskList;

import duke.dukeException.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the saving and loading of the file that contains the TaskList.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for storage object.
     *
     * @param filePath the file path of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method that loads and reads the save file and output the Tasks into an ArrayList.
     *
     * @return ArrayList of Tasks.
     * @throws DukeException If the save file is blank or syntax error exists.
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" \\| ");
                Task task;

                switch (line[0]) {
                case "T":
                    task = new Todo(line[2]);
                    break;
                case "D":
                    task = new Deadline(line[2], line[3]);
                    break;
                case "E":
                    task = new Event(line[2], line[3]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks, check file for syntax errors");
                }

                if (line[1].equals("1")) {
                    task.setDone();
                }
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found.");
        }
    }

    /**
     * A method that saves the TaskList into the save file specified.
     *
     * @param list TaskList object.
     * @throws DukeException If IOException occurs.
     */
    public void saveFile(TaskList list) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task t : list.getList()) {
                fileWriter.write(t.toFile());
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Save to File Failed.");
        }
    }
}
