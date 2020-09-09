package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.merchandise.Merchandise;

public class AddItemCommand implements Command {
    private final String userInput;

    public AddItemCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
            throw new DukeException("Please provide a name for the item you want to add.");
        }
        String itemName = inputArray[1].trim();
        Merchandise merchandise = storage.loadMerchandise();
        merchandise.addMerchandise(itemName, "");
        String response = itemName + " is now in your collection of "
                + merchandise + "!\n";
        ui.setResponse(response);
    }
}
