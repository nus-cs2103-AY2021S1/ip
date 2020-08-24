package duke.main;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> tasks;

    TaskList(File file) {
        this.tasks = initTasks(file);
    }

    private static List<Task> initTasks(File file) {
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String current = sc.nextLine();
                String[] strings = current.split(" \\| ");
                Task task = null;
                switch (strings[0]) {
                case "T":
                    task = new ToDo(strings[2]);
                    break;
                case "D":
                    task = Deadline.create(strings[2], strings[3]);
                    break;
                case "E":
                    task = Event.create(strings[2], strings[3]);
                    break;
                default:
                    System.out.println(Ui.LINE + "\n"
                            + "Hmm, something's wrong with this task\n"
                            + Ui.LINE);
                    break;
                }
                if (task != null) {
                    if (strings[1].equals("1")) task.setDone();
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String write() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.print()).append(System.lineSeparator());
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    Task add(String task) throws MissingDateException,
            InvalidDateException, InvalidCommandException, EmptyTaskException {
        Task newTask;
        if (task.startsWith("todo")) {
            newTask = ToDo.create(task);
        } else if (task.startsWith("deadline")) {
            newTask = Deadline.create(task);
        } else if (task.startsWith("event")) {
            newTask = Event.create(task);
        } else {
            throw new InvalidCommandException();
        }
        tasks.add(newTask);
        return newTask;
    }

    Task delete(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        return tasks.remove(index);
    }

    Task complete(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        return task;
    }

    int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        int number = 1;
        for (Task task : tasks) {
            list.append(String.format("%d.", number++))
                    .append(task)
                    .append(System.lineSeparator());
        }
        list.deleteCharAt(list.length() - 1);
        return list.toString();
    }
}
