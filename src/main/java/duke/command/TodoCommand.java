package duke.command;

import duke.task.Task;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

public class TodoCommand extends Command {
    private final String description;


    public TodoCommand(String description) {
        this.description = description;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        tasks.addTask(new Task(description));
    }


}