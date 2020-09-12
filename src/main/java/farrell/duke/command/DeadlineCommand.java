package main.java.farrell.duke.command;

import java.time.LocalDate;

import main.java.farrell.duke.DukeException;
import main.java.farrell.duke.task.Deadline;
import main.java.farrell.duke.task.TaskList;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate date;

    /**
     * Creates a DeadlineCommand with a specified description and date.
     *
     * @param description The description of the resulting deadline task.
     * @param date The date of the resulting deadline task.
     */
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
