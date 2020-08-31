package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filepath) {
        this.file = new File(filepath);
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
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String loadTask = sc.nextLine();
                Task task;
                if (loadTask.startsWith("T")) {
                    task = ToDo.load(loadTask);
                } else if (loadTask.startsWith("D")) {
                    task = Deadline.load(loadTask);
                } else if (loadTask.startsWith("E")) {
                    task = Event.load(loadTask);
                } else {
                    break;
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry!!! File not found.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Previous tasks not saved properly.");
        }
    }

    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                int isFinished = task.getIsDone() ? 1 : 0;
                fw.write(task.save(isFinished) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Encountered difficulties when saving tasks.");
        }
    }
}