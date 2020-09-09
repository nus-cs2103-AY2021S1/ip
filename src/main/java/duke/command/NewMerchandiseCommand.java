package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.merchandise.Merchandise;

public class NewMerchandiseCommand implements Command {
    private final String userInput;

    public NewMerchandiseCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length == 1 || inputArray[1].trim().equals("")) {
            throw new DukeException("Please provide a name for the new merchandise.");
        }
        String merchandiseName = inputArray[1].trim();
        Merchandise newMerchandise = new Merchandise(merchandiseName);
        storage.addMerchandise(newMerchandise);
        String response = "A new collection of " + newMerchandise
                + " has been created! \n";
        ui.setResponse(response);
    }
}
