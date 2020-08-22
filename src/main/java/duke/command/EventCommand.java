package duke.command;

import duke.task.Event;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


public class EventCommand extends Command {
    private final String description;
    private final String at;


    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            tasks.addTask(new Event(description, at));
            fileHandler.writeToFile(tasks.getList());
        } catch (Exception ex) {
            throw new DukeException("Error creating this event");
        }
    }


}