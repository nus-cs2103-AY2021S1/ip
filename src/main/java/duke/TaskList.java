package duke;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.time.LocalDate;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> list;

    @Override
    public Iterator<Task> iterator() {
        return this.list.iterator();
    }

    public int size() {
        return this.list.size();
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    private TaskList(ArrayList<Task> arr) {
        this.list = arr;
    }

    public Task addTask(String type, String input) throws DukeException {
        return this.addTask(type, input, false);
    }

    public Task addTask(String type, String input, boolean done) throws DukeException {
        Task task;
        switch (type) {
        case "todo":
            task = new Todo(input);
            break;
        case "deadline":
            String[] arr = input.split("/by");
            try {
                task = new Deadline(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the deadline? (/by...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/by...)");
            }
            break;
        case "event":
            arr = input.split("/at");
            try {
                task = new Event(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the event? (/at...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/at...)");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :<");
        }
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, the description cannot be empty :<");
        }
        task = done ? task.markAsDone() : task;
        this.list.add(task);
        return task;
    }

    public TaskList getTimedTasks(LocalDate date) throws DukeException { // input of form "dd-MM-yyyy"
        try {
            ArrayList<Task> timedTasks = new ArrayList<>(this.list);
            timedTasks.removeIf(i -> i instanceof Todo);
            Task[] timedTasksArr = timedTasks.toArray(new Task[0]);
            for (Task i : timedTasksArr) {
                assert i instanceof TimedTask;
                if (!((TimedTask) i).getDate().equals(date)) {
                    timedTasks.remove(i);
                }
            }
            if (timedTasks.isEmpty()) {
                throw new DukeException("OOPS!!! I'm sorry, your list is empty :<");
            } else {
                return new TaskList(new ArrayList<>(timedTasks));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time format! (/at...)");
        }
    }

    public Task markAsDone(int i) throws DukeException {
        try {
            return this.list.get(i).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    public Task deleteTask(int i) throws DukeException {
        try {
            return this.list.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            int taskNum = i + 1;
            output.append(taskNum);
            output.append(".");
            output.append(this.list.get(i).toString());
            output.append(i == this.size() - 1 ? "" : '\n');
        }
        return output.toString();
    }
}
