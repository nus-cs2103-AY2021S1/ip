package duke.command;

import duke.DukeException;
import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ViewMerchandiseCommand implements Command {
    private final String userInput;

    public ViewMerchandiseCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) throws DukeException {
        String response = "This is your collection of (merchandise):\n";
        response += merchandises.printCollection();
        ui.setResponse(response);
    }
}
