package duke;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        ui.printFindings(taskList.findTasks(userInput));
    }
}
