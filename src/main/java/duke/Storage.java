package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    /** The file where tasks are loaded from and saved to. */
    private final File file;

    /**
     * Creates a new storage that loads tasks from and saves tasks to the given file.
     *
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
     *
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
                String taskShortForm = splitTask[0];
                boolean isDone = splitTask[1].equals("1");
                String fullDescription = splitTask[2];
                Task task;
                switch (taskShortForm) {
                case "T":
                    task = new Todo(fullDescription);
                    break;
                case "D":
                    String deadlineDescription = splitTask[2];
                    String deadlineDateTime = splitTask[3];
                    task = Parser.parseDateTimeTask(TaskType.DEADLINE,
                            deadlineDescription + " /by " + deadlineDateTime);
                    break;
                case "E":
                    String eventDescription = splitTask[2];
                    String eventDateTime = splitTask[3];
                    task = Parser.parseDateTimeTask(TaskType.EVENT, eventDescription + " /at " + eventDateTime);
                    break;
                default:
                    // Invalid task type.
                    assert false : splitTask[0];
                    throw new IllegalArgumentException();
                }
                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! No saved tasks were found.");
        } catch (DukeException | IllegalArgumentException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("OOPS!!! The previous tasks were not saved correctly.");
        }
    }

    /**
     * Saves the tasks into the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If there was a problem with saving the tasks into the file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                int isDone = task.isDone() ? 1 : 0;
                switch (task.getShortForm()) {
                case "T":
                    writer.write(task.getShortForm()
                            + " | " + isDone
                            + " | " + task.getDescription()
                            + "\n");
                    break;
                case "D": case "E":
                    writer.write(task.getShortForm()
                            + " | " + isDone
                            + " | " + task.getDescription()
                            + " | " + task.getDate() + " " + (task.hasTime() ? task.getTime() : "")
                            + "\n");
                    break;
                default:
                    // Invalid task type.
                    assert false : task.getShortForm();
                    throw new IllegalArgumentException();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! There were some problems with saving the tasks.");
        }
    }
}
