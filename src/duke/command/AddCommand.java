package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command{
    String taskCategory;
    String taskDescription;
    String taskTime;

    public AddCommand(String taskCategory, String taskDescription, String taskTime) throws DateTimeParseException {
        this.taskCategory = taskCategory;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
    }

    public AddCommand(String taskCategory, String taskDescription) {
        this.taskCategory = taskCategory;
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DateTimeParseException, DukeException {
        Task newTask;

        switch (taskCategory) {
        case "todo":
            newTask = new ToDo(taskDescription);
            break;
        case "event" :
            newTask = new Event(taskDescription, taskTime);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, taskTime);
            break;
        default:
            newTask = new Task();
        }

        tasks.addTask(newTask);
        String successMsg = "Got it. I've added this task:\n" + newTask;
        String remainingTasksMsg = "\nNow you have " + tasks.getSize() + " tasks in the list.";
        ui.showMessage(successMsg + remainingTasksMsg);
        storage.save(tasks);
    }
}
