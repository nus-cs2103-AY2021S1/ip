package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.merchandise.Merchandise;

public class ViewMerchandiseCommand implements Command {
    private final String userInput;

    public ViewMerchandiseCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
            throw new DukeException("This collection does not exist.");
        }
        Merchandise merchandise = storage.loadMerchandise();
        String response = "This is your collection of " + merchandise + ":\n";
        response += merchandise.printCollection();
        ui.setResponse(response);
    }
}
