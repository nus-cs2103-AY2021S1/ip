package main.java;

import java.time.LocalDate;

class CreateEventCommand extends Command {

    private final LocalDate date;

    private final String description;

    private final boolean isComplete;

    CreateEventCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        Event event = tasks.addEvent(description, isComplete, date);
        ui.printCreateTask(tasks, event);
    }
}
