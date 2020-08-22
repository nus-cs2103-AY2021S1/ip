package duke.command;

import duke.task.Deadline;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class DeadlineCommand extends Command {
    private final String description;
    private final String by;


    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            tasks.addTask(new Deadline(description, by));
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("Error creating this deadline");
        }
    }


}