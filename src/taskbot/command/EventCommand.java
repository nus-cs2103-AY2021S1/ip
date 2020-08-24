package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.exceptions.WrongFormatException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class EventCommand extends Command {
    private String task;
    public EventCommand(String task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.addEventTask(task);
    }
}
