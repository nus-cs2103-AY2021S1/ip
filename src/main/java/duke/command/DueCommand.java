package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a due command.
 */
public class DueCommand extends Command {
    private final LocalDate date;

    public DueCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command, listing all tasks due on the given date.
     *
     * @param taskList A TaskList instance.
     * @param ui A Ui instance.
     * @param storage A Storage instance.
     * @throws DukeException if there are no tasks due on the given date.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> dueTasks = taskList.getDueTasks(this.date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        if (dueTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list occurring on "
                    + this.date.format(formatter) + ".");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list occurring on "
                + this.date.format(formatter) + ":\n");

        for (Task task : dueTasks) {
            output.append(taskList.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        ui.showPrompt(output.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DueCommand) {
            return this.date.equals(((DueCommand) obj).date);
        }

        return false;
    }
}
