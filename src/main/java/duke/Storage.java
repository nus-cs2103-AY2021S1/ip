package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    Deadline deadline = new Deadline(splitTask[2], splitTask[3]);
                    if (splitTask[1] == "1") {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitTask[2], splitTask[3]);
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
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The previous tasks were not saved correctly.");
        }
    }

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
