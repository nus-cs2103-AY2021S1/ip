package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        //The bot says bye and the program terminates
        ui.sayBye();
    }
}
