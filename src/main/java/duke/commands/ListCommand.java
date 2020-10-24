package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = ui.showListOfTask(tasks.taskList);
        return response;
    }

}
