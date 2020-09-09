package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.Deadline;
import duke.time.DateTime;

public class RemindCommand extends Command {

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        DateTime date = DateTime.getToday();

        TaskManager deadlines = new TaskManager(taskManager
                .filter(task -> task instanceof Deadline)
                .getAllTasks()
                .stream()
                .map(deadline -> (Deadline) deadline)
                .filter(deadline -> deadline.getDeadline() instanceof DateTime)
                .filter(deadline -> ((DateTime) deadline.getDeadline()).hasSameDate(date))
                .collect(Collectors.toList()));

        ui.queueMessageToDisplay("~~ REMINDER!! - These deadlines are due today! ~~~");
        ui.queueMessageToDisplay("");
        ui.queueMessageToDisplay(deadlines.toString());

    }

}
