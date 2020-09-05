package command;

import java.time.LocalDate;
import java.util.Optional;

import duke.Storage;
import duke.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * Add a new deadline into the task list
 */
public class UpdateCommand extends Command {
    private int taskNumber;
    private Optional<String> description;
    private Optional<LocalDate> date;

    /**
     * Command to update a task
     *
     * @param taskNumber  index in taskList
     * @param description new task description
     * @param date        new task date
     */
    public UpdateCommand(int taskNumber, Optional<String> description, Optional<LocalDate> date) {
        this.taskNumber = taskNumber;
        this.description = description;
        this.date = date;
    }

    /**
     * Insert a new deadline into tasklist, and save it to storage file
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.print("Sorry, I don't find it in your list!");
            return new CommandResult("Sorry, I can't find it in your list!");
        }

        Task oldTask = tasks.remove(taskNumber - 1);
        Task newTask;
        String newDescription = description.orElse(oldTask.getDescription());

        if (oldTask instanceof Todo) {
            newTask = new Todo(newDescription);
        } else if (oldTask instanceof Deadline) {
            LocalDate newDate = date.orElse(((Deadline) oldTask).getDate());
            newTask = new Deadline(newDescription, newDate);
        } else if (oldTask instanceof Event) {
            LocalDate newDate = date.orElse(((Event) oldTask).getDate());
            newTask = new Event(newDescription, newDate);
        } else {
            // should not reach this phase, since known event type have all been covered
            assert false : "Unknown event type to update";
            return new CommandResult("Unknown event type to update");
        }

        tasks.add(newTask, taskNumber - 1);
        storage.save(tasks);

        int size = tasks.size();
        return new CommandResult(ui.printAddConfirmation(newTask.showTask(), size));
    }
}
