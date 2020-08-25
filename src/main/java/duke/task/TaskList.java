package duke.task;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void runCommand(String[] commands, Ui ui, Storage storage) throws DukeException {
        switch (commands[0]) {
        case "list": {
            printList(ui);
            break;
        }
        case "done": {
            markAsDone(ui, Integer.parseInt(commands[1]) - 1);
            break;
        }
        case "delete": {
            delete(ui, Integer.parseInt(commands[1]) - 1);
            break;
        }
        case "todo":
        case "deadline":
        case "event":
            addTask(ui, commands[0], commands[1], commands[2]);
        }
        storage.store(taskList);
    }

    public void printList(Ui ui) {
        ui.printList(this.taskList);
    }

    public void markAsDone(Ui ui, int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        ui.printDone(task);
    }

    public void delete(Ui ui, int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.printDelete(task, taskList.size());
    }

    public void addTask(Ui ui, String type, String description, String time) {
        Task task;
        switch (type) {
        case "todo": {
            task = new Todo(description);
            break;
        }
        case "deadline": {
            task = new Deadline(description, time);
            break;
        }
        case "event": {
            task = new Event(description, time);
            break;
        }
        default:
            task = null;
        }
        taskList.add(task);
        ui.printAdd(task, taskList.size());
    }
}
