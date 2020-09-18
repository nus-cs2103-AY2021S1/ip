package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskType;

/**
 * Represents a storage that read from and writes to a file.
 * @version 1.0
 */
public class Storage {
    private final File file;

    /**
     * Creates a new Storage object with the given file path.
     *
     * @param filePath A String representation of the target file path.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Finds the file through file path reference.
     * Translates the file into an ArrayList of tasks.
     *
     * @return The ArrayList of tasks translated from the file.
     * @throws DukeException thrown if file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> lst = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] command = line.split(" \\| ");
                switch (command[0]) {
                case "T":
                    Task todo = loadTodo(command);
                    lst.add(todo);
                    break;
                case "D":
                    Task deadline = loadDeadline(command);
                    lst.add(deadline);
                    break;
                case "E":
                    Task event = loadEvent(command);
                    lst.add(event);
                    break;
                default:
                }
            }
            fileReader.close();
        } catch (IOException e) {
            throw new DukeException("\tFile not Found.");
        } catch (Exception e) {
            throw new DukeException("\tError loading history. " + e.getMessage());
        }
        return lst;
    }

    private Task loadTodo(String[] command) {
        Task todo = new Task(TaskType.TODO, command[2]);
        if (command[1].equals("1")) {
            todo.setDone();
        }
        return todo;
    }

    private Task loadDeadline(String[] command) {
        Task deadline = new Task(TaskType.DEADLINE, command[2], LocalDate.parse(command[3]));
        if (command[1].equals("1")) {
            deadline.setDone();
        }
        return deadline;
    }

    private Task loadEvent(String[] command) {
        Task event = new Task(TaskType.EVENT, command[2], LocalDate.parse(command[3]));
        if (command[1].equals("1")) {
            event.setDone();
        }
        return event;
    }

    /**
     * Creates the directory if the targeted file path does not exist.
     * Translates the ArrayList of tasks into a String representation.
     * Writes the String representation of the task list into the targeted file.
     *
     * @param lst the ArrayList of tasks from some TaskList.
     * @throws DukeException thrown if error during writing to file.
     */
    public void write(ArrayList<Task> lst) throws DukeException {
        try {
            file.getParentFile().mkdir();
            FileWriter fileWriter = new FileWriter(file);
            String listToString = "";
            for (Task t : lst) {
                if (t.getType() == TaskType.TODO) {
                    listToString += "T | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + "\n";
                } else if (t.getType() == TaskType.DEADLINE) {
                    listToString += "D | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | "
                            + t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
                } else {
                    listToString += "E | " + (t.getStatus() ? 1 : 0) + " | " + t.getDescription() + " | "
                            + t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
                }
            }
            fileWriter.write(listToString);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error during writing. " + e.getMessage());
        }

    }
}
