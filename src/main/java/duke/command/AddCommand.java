package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.exception.DukeException;

public class AddCommand extends Command {

    String description;
    String date;
    TaskType type;

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\n%s";

    public AddCommand(String description, String date, TaskType type) {
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(description, date, type);
        storage.writeToFile(taskList);
        ui.printReply(String.format(MESSAGE_SUCCESS, taskList.get(taskList.size() - 1), taskList));
    }

    public boolean isExit() {
        return false;
    }
}
