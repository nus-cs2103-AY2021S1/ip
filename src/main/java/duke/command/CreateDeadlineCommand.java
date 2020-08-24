package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

import main.java.duke.task.Deadline;

import java.time.LocalDate;

public class CreateDeadlineCommand extends Command {

    private final LocalDate date;

    private final String description;

    private final boolean isComplete;

    public CreateDeadlineCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Deadline deadline = tasks.addDeadline(this.description, this.isComplete, this.date);
        ui.printCreateTask(tasks, deadline);
    }
}
