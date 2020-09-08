package main.java.farrell.duke;

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
