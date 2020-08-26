package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DueCommand extends Command {
    private final LocalDate date;

    public DueCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<Task> dueTasks = tasks.getDueTasks(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        if (dueTasks.size() == 0) {
            throw new DukeException("There are no tasks in your list occurring on "
                    + date.format(formatter) + ".");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list occurring on "
                + date.format(formatter) + ":\n");

        for (Task task : dueTasks) {
            output.append(tasks.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        ui.showPrompt(output.toString());
    }
}
