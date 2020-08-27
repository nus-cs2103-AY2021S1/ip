package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.time.LocalDate;

public class AddCommand extends Command {

    Task taskToAdd;

    private AddCommand(Task task) {
        taskToAdd = task;
    }

    public static AddCommand addTodo(String description) {
        return new AddCommand(new Todo(description));
    }

    public static AddCommand addEvent(String description, LocalDate date) {
        return new AddCommand(new Event(description, date));
    }

    public static AddCommand addDeadline(String description, LocalDate date) {
        return new AddCommand(new Deadline(description, date));
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(taskToAdd);
        System.out.println("Alrighty, I'll put it on yer tab:\n" + taskToAdd.toString() + "\n" + "You've got a total of " + tasks.size() + " items right now.");
    }

}
