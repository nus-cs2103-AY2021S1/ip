package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = ui.showGoodBye();
        System.exit(0);
        return response;
    }
}
