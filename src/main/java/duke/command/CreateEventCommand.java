package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

import main.java.duke.task.Event;

import java.time.LocalDate;

public class CreateEventCommand extends Command {

    private final LocalDate date;

    private final String description;

    private final boolean isComplete;

    public CreateEventCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        Event event = tasks.addEvent(description, isComplete, date);
        ui.printCreateTask(tasks, event);
    }
}
