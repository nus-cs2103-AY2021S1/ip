package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** The file where tasks are loaded from and saved to. */
    private File file;

    /**
     * Creates a new Storage that loads tasks from and saves tasks to the given file.
     * @param filePath The file path of the file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved tasks from the file.
     * @return A list of tasks that was saved in the file.
     * @throws DukeException If there was a problem with loading the saved file.
     */
    public List<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();

            while (sc.hasNextLine()) {
                String savedTask = sc.nextLine();
                String[] splitTask = savedTask.split(" \\| ", 4);
                switch (splitTask[0]) {
                case "T":
                    Todo todo = new Todo(splitTask[2]);
                    if (splitTask[1] == "1") {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitTask[2], Parser.parseDate(splitTask[3]));
                    if (splitTask[1] == "1") {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitTask[2], Parser.parseDate(splitTask[3]));
                    if (splitTask[1] == "1") {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! No saved tasks were found.");
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The previous tasks were not saved correctly.");
        }
    }

    /**
     * Saves the tasks into the file.
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If there was a problem with saving the tasks into the file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                int isDone = task.getIsDone() ? 1 : 0;
                switch (task.getShortForm()) {
                case "T":
                    writer.write(task.getShortForm() + " | " + isDone + " | "
                            + task.getDescription() + "\n");
                    break;
                case "D":
                    writer.write(task.getShortForm() + " | " + isDone + " | "
                            + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n");
                    break;
                case "E":
                    writer.write(task.getShortForm() + " | " + isDone + " | "
                            + task.getDescription() + " | " + ((Event) task).getAt() + "\n");
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! There were some problems with saving the tasks.");
        }
    }
}
