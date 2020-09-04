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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DateTimeParseException, DukeException {
        Task newTask;

        assert taskCategory != null;
        assert taskDescription != null;

        switch (taskCategory) {
        case "todo":
            newTask = new ToDo(taskDescription);
            break;
        case "event" :
            assert taskTime != null;
            newTask = new Event(taskDescription, taskTime);
            break;
        case "deadline":
            assert taskTime != null;
            newTask = new Deadline(taskDescription, taskTime);
            break;
        default:
            newTask = new Task();
        }

        tasks.addTask(newTask);
        String successMsg = "Got it. I've added this task:\n" + newTask;
        String remainingTasksMsg = "\nNow you have " + tasks.getSize() + " tasks in the list.";
        String finalMsg = successMsg + remainingTasksMsg;
        ui.showMessage(finalMsg);
        storage.save(tasks);
        return finalMsg;
    }
}
