package main.java.farrell.duke.command;

import java.time.LocalDate;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.Event;
import main.java.farrell.duke.task.TaskList;

public class EventCommand extends Command {
    private String description;
    private LocalDate date;

    /**
     * Creates an EventCommand with a specified description and date.
     *
     * @param description The description of the resulting event task.
     * @param date The date of the resulting event task.
     */
    public EventCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
        type = CommandType.EVENT;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        Event event = new Event(description, date);
        taskList.addTask(event);
        return "Added: " + event.toString();
    }
}
