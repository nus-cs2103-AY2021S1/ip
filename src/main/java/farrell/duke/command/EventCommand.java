package main.java.farrell.duke.command;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.Event;
import main.java.farrell.duke.task.TaskList;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String description;
    private LocalDate date;

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
