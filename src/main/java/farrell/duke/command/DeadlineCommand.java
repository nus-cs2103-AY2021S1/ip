package main.java.farrell.duke.command;

import main.java.farrell.duke.task.Deadline;
import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {
        this.description = description;
        this.date = date;
        type = CommandType.DEADLINE;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        Deadline deadline = new Deadline(description, date);
        taskList.addTask(deadline);
        return "Added: " + deadline.toString();
    }
}
