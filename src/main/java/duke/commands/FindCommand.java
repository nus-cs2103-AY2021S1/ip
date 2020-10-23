package duke.commands;

import duke.*;

import java.util.ArrayList;

public class FindCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) throws DukeException {
        // parse instruction
        String keyword = Parser.parseFindInstr(userInput);

        // execute
        ArrayList<Task> foundTasks = tasks.find(keyword);

        // show message on UI if not Loading
        String response = "";
        if (!isLoading) {
            response = ui.showFoundTask(foundTasks);
        }
        return response;
    }
}
