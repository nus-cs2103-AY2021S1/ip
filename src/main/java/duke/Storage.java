package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Creates a storage of the tasks.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the content of the file into an array list of tasks
     *
     * @return An array list of tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] task = line.split(" \\| ");
                if (task[0].equals("T")) {
                    Task todo = new Todo(task[2]);
                    if (task[1].equals("✓")) {
                        todo.markDone();
                    }
                    tasks.add(todo);
                } else if (task[0].equals("D")) {
                    Task deadline = new Deadline(task[2], LocalDateTime.parse(task[3]));
                    if (task[1].equals("✓")) {
                        deadline.markDone();
                    }
                    tasks.add(deadline);
                } else if (task[0].equals("E")) {
                    Task event = new Event(task[2], LocalDateTime.parse(task[3]));
                    if (task[1].equals("✓")) {
                        event.markDone();
                    }
                    tasks.add(event);
                } else {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Folder or file does not exist yet! "
                    + "Please make sure you have data/duke.txt in ip directory. ");
        }
        return tasks;
    }

    /**
     * Writes the tasks in the TaskList into the file.
     *
     * @param tasks A list of tasks to be written into the file.
     */
    public void writeFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(tasks.getList());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

}
