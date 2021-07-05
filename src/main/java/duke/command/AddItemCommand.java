package duke.command;

import duke.DukeException;
import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.merchandise.Merchandise;

import java.io.IOException;

public class AddItemCommand implements Command {
    private final String userInput;

    public AddItemCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) throws DukeException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
            throw new DukeException("Please provide a name for the item you want to add.");
        }
        String merchandise = inputArray[1].trim();
        merchandises.addMerchandise(new Merchandise(merchandise));
        String response = merchandise
                + " is now in your collection of (merchandise)!\n"; //eg. stamps
        ui.setResponse(response);
        try {
            storage.updateMerchandisesFile(merchandises.getMerchandises());
        } catch (IOException error) {
            ui.setResponse(error.getMessage());
        }
    }
}
