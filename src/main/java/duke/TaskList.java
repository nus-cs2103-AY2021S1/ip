package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.function.Consumer;

public class TaskList {
    private List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void add(String task) {
        // for reading from file
        String[] input = task.split("\\s\\|\\s");
        boolean isDone = input[1].equals("1");
        switch (input[0]) {
        case "E":
            Event event = new Event(input[2], isDone, LocalDate.parse(input[3]));
            this.tasks.add(event);
            break;
        case "T":
            ToDo todo = new ToDo(input[2], isDone);
            this.tasks.add(todo);
            break;
        case "D":
            Deadline deadline = new Deadline(input[2], isDone, LocalDate.parse(input[3]));
            this.tasks.add(deadline);
            break;
        }
    }

    public Task markAsDone(int position) throws DukeException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new DukeException("Invalid task.");
        }
        return this.tasks.get(position - 1).markAsDone();
    }

    public Task delete(int position) throws DukeException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new DukeException("Invalid task.");
        }
        return this.tasks.remove(position - 1);
    }

    public void showList(Ui ui) {
        ui.showHorizontalLine();
        if (this.tasks.size() <= 0) {
            ui.showIndentedMessage("No tasks added.");
        } else {
            int position = 1;
            for (Task task : this.tasks) {
                ui.showIndentedMessage(position + ". " + task);
                position++;
            }
        }
        ui.showHorizontalLine();
    }

    public void showList(LocalDate date, Ui ui) {
        ui.showHorizontalLine();
        if (this.tasks.size() <= 0) {
            ui.showIndentedMessage("No tasks added.");
        } else {
            int position = 1;
            boolean hasTask = false;
            for (Task task : this.tasks) {
                if ((task instanceof Event || task instanceof Deadline)
                        && task.getDate().equals(date)) {
                    ui.showIndentedMessage(position + ". " + task);
                    hasTask = true;
                }
                position++;
            }
            if (!hasTask) {
                ui.showIndentedMessage("No tasks on that date.");
            }
        }
        ui.showHorizontalLine();
    }

    public void forEach(Consumer<? super Task> consumer) {
        this.tasks.forEach(consumer);
    }
}
