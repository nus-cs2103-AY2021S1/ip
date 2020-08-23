package main.command;

import java.time.LocalDateTime;

import main.task.Deadline;
import main.task.TaskList;
import main.ui.Ui;

public class AddDeadlineCommand implements Command {
    private final Deadline deadline;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        deadline = new Deadline(description, dateTime);
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        tasks.add(deadline);
        ui.printAddSuccess(deadline, tasks.size());
    }

    @Override
    public boolean hasCommand() {
        return true;
    }
}
