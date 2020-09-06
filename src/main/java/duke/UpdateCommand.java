package duke;

import java.io.IOException;

/**
 * Represents an update command.
 */
public class UpdateCommand extends Command {
    /**
     * Constructs an update command with description.
     * @param description Description of command.
     */
    UpdateCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        try {
            String output = "";
            int itemNumber = Integer.parseInt(description);
            tasks.get(itemNumber - 1).isDone = true;
            output += "Nice, I've marked this item as done:\n\t" + tasks.get(itemNumber - 1);
            storage.writeToFile(tasks);
            return ui.showOutput(output);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("Please key in a valid number for \"done\"");
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
