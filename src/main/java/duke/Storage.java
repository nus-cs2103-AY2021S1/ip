package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File f;

    public Storage(String filepath) {
        this.f = new File(filepath);
    }

    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (int i = 1; i < tasks.getTasklist().size() + 1; i++) {
                Task t = tasks.get(i - 1);
                fw.write(t.fileFormat() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Can't update file!");
        }
    }


    public TaskList load() throws DukeException {
        try {
            Scanner s = new Scanner(f);
            ArrayList<Task> storedTasks = new ArrayList<>();

            while (s.hasNext()) {
                Task t = processTaskToLoad(s.nextLine());
                storedTasks.add(t);
            }

            return new TaskList(storedTasks);

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
    }

    public void checkIfDone(String s, Task task) {
        if (s.equals("1")) {
            task.markAsDone();
        }
    }

    private Task processTaskToLoad(String line) throws DukeException {
        String[] data = line.split(" , ");
        Task t = new Task("null");

        if (data[0].equals("T")) {
            t = new Todo(data[2]);
            checkIfDone(data[1], t);
        }

        if (data[0].equals("D")) {
            t = new Deadline(data[2], data[3]);
            checkIfDone(data[1], t);
        }

        if (data[0].equals("E")) {
            t = new Event(data[2], data[3]);
            checkIfDone(data[1], t);
        }

        return t;
    }
}
