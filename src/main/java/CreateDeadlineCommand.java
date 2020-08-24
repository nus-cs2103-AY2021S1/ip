package main.java;

import java.time.LocalDate;

class CreateDeadlineCommand extends Command {

    private final LocalDate date;

    private final String description;

    private final boolean isComplete;

    CreateDeadlineCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        Deadline deadline = tasks.addDeadline(this.description, this.isComplete, this.date);
        ui.printCreateTask(tasks, deadline);
    }
}
