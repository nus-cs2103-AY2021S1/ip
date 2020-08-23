package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> listOfTask = new ArrayList<>();
    private final File file;

    TaskList (File file) {
        this.file = file;
    }

    public void loadTasks() throws DukeException {
        try {
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                checkTask(s.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void checkTask(String s) {
        Task t;
        String taskType = s.substring(0, 3);
        String status = s.substring(3,6);
        boolean isDone = status.equals("[" + "\u2713" + "]");
        if (taskType.equals("[T]")) {
            t = new Todo(s.substring(7), isDone);
        } else if (taskType.equals("[D]")){
            int indOfTime = s.lastIndexOf("(FINISH by: ");
            t = new Deadline(s.substring(7, indOfTime), s.substring(indOfTime + 11,
                    s.lastIndexOf(")")).trim(), isDone);
        } else {
            int indOfTime = s.lastIndexOf("(APPEAR at: ");
            t = new Event(s.substring(7, indOfTime), s.substring(indOfTime + 11,
                    s.lastIndexOf(")")).trim(), isDone);
        }
        listOfTask.add(t);
    }

    public int addTask(Task t) throws IOException {
        listOfTask.add(t);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(t.toString());
        fileWriter.write(System.getProperty("line.separator"));
        fileWriter.close();
        return listOfTask.size();
    }

    public Task deleteTask(int ind) throws IndexOutOfBoundsException, IOException, NumberFormatException {
        Task t = listOfTask.get(ind);
        listOfTask.remove(ind);
        FileWriter fileWriter = new FileWriter(file);

        for (Task task : listOfTask) {
            fileWriter.write(task.toString());
            fileWriter.write(System.getProperty("line.separator"));
        }

        fileWriter.close();
        return t;
    }

    public int total() {
        return listOfTask.size();
    }

    public void updateTasks() throws IOException {
        FileWriter fileWriter = new FileWriter(file);

        for (Task task : listOfTask) {
            fileWriter.write(task.toString());
            fileWriter.write(System.getProperty("line.separator"));
        }

        fileWriter.close();
    }

    public void markDone(int ind) throws IndexOutOfBoundsException, IOException {
        listOfTask.get(ind).markAsDone();
        updateTasks();
    }

    public List<Task> checkDate(LocalDate date) {
        List<Task> sameDates = new ArrayList<>();
        for (Task t : listOfTask) {
            if (t.compareDate(date)) {
                sameDates.add(t);
            }
        }
        return sameDates;
    }

    public List<Task> displayAll() {
        return listOfTask;
    }
}
