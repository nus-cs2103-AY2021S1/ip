package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static Task genTaskFromString(String string) {
        boolean status = (string.charAt(4) == '\u2713');
        if (string.charAt(1) == 'T') {
            return new ToDo(string.substring(7), status);
        } else if (string.charAt(1) == 'D') {
            int endIndex = string.indexOf("(by:") - 1;
            return new Deadline(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
        } else {
            int endIndex = string.indexOf("(at:") - 1;
            return new Event(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
        }
    }

    public void clear() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }

    public void save(TaskList tasksToSave) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        for (Task task : tasksToSave.getListOfTasks())
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> task = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentTask = s.nextLine();
            task.add(genTaskFromString(currentTask));
        }
        return task;
    }
}
